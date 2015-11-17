package com.risk.plan.controller.transport;

import java.util.ArrayList;
import java.util.List;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.risk.plan.common.EdgePoints;
import com.risk.plan.entity.Gene;
import com.risk.plan.entity.Nodes;
import com.risk.plan.entity.Path;
import com.risk.plan.entity.Points;
import com.risk.plan.entity.Task;
import com.risk.plan.service.box.disaster.NodesService;
import com.risk.plan.service.box.edge.GeneService;
import com.risk.plan.service.box.edge.PathService;
import com.risk.plan.service.box.edge.PointsService;
import com.risk.plan.service.box.edge.TaskService;

@Controller
public class StraightlineController {
	//折线两点间的总长度
    public double total_length = 0.0;
    //在总长度范围内，使用simpson算法的分割数
    static int TOTAL_SIMPSON_STEP = 30;
    //一条折线是由多段直线组成的，每段的长度不一样，那么直线分割的份数也应该不一样，由基准长度来对应比例分割份数
    static double reference_length =0.1;
    //直线分割的份数
    static int step = 5;            //赋个初值，下面再用
    
	String axisx="";    //方便下面调用，问题是什么时候清零？调试的时候解决这个问题
	String axisy="";  
	String edgeType="";//区分不同连接边，用于读取显示
	//String nodeid="";
	String fnodename="";//用于单次任务运输终点起点的记录
	String snodename="";//用于单次任务运输终点的记录
    //直线的斜率，调用的时候，直接将两个点的x，y坐标赋值过来
    public double beze_k(double x1, double x2, double y1, double y2) {
        return (y2-y1)/(x2-x1);
    }
 
    //直线的又一关键值b
    public double beze_b(double x1, double x2, double y1, double y2) {
        double beze_k =beze_k(x1,x2,y1,y2) ;
        return y1-beze_k*x1;
    }
    
    //直线的长度
    public double beze_length(double x1, double x2, double y1, double y2) {       
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }
    
    
    //service
    @Autowired
    NodesService nodesService;
    @Autowired
    PointsService pointsService;
    @Autowired
    PathService pathService;
    @Autowired
    GeneService geneService;
    @Autowired
    TaskService taskService;
    
    //entity
    @Autowired
    EdgePoints edgePoints;
    
    @ResponseBody
    @RequestMapping("/testpoint")
    public List<Points> testpoint()	{
    	return pointsService.findByELL("80d479c770c547dc88b3891091eee3d3", "34.049348", "106.693487");
    }
    
      
    //返回一条折线上（几段折线组成的），对应匀速需要的所有点的经纬度,注意：这只是一条折线的
    public String point_array(String edgeID, String firstNodeID, String secondNodeID){ 
   	System.out.println("6进来point_array并且查找该边上所有的点");
	List<Points> pointlist=pointsService.findByEdgeid(edgeID);    //已经按画线时的顺序排序后的结果，起点即在[0]的位置
	int start_position=0;
	int end_position=pointlist.size();  	
	//由于nodes表中一个节点可能在多条边上出现，就可以对应多个points，所以，此处作如下查询
	Nodes firstNode=nodesService.selectByPrimaryKey(firstNodeID);
	Nodes secondNode= nodesService.selectByPrimaryKey(secondNodeID);
	List<Points> pointlistF=pointsService.findByELL(edgeID, firstNode.getLatitude(), firstNode.getLongitude())  ;       //可能路径变换是从折线上任意点开始的
	Points point3=pointlistF.get(0);                 
	List<Points> pointlistS=pointsService.findByELL(edgeID, secondNode.getLatitude(), secondNode.getLongitude()) ;        //可能路径换结束再折线上任意点
	Points point4=pointlistS.get(0);                    //可能路径换结束再折线上任意点    
	//查找路径变换的开始位置是从折线的第几段开始的（减一）
	for(int i=0;i<pointlist.size()-1;i++)
	{
		Points point1=pointlist.get(i);             //保存到数据库中的折线两端的点之一
		Points point2=pointlist.get(i+1);           //保存到数据库中的折线两端的点之二
		double k=beze_k(Double.valueOf(point1.getLat()),Double.valueOf(point2.getLat()),Double.valueOf(point1.getLng()),Double.valueOf(point2.getLng()));
		double b=beze_b(Double.valueOf(point1.getLat()),Double.valueOf(point2.getLat()),Double.valueOf(point1.getLng()),Double.valueOf(point2.getLng()));
		if(point3.getPointsid().equals(point1.getPointsid()))   //路径变换的终点在这段折线上
		{ 
			start_position=i;
			break;
		}
		else if((Math.abs(Double.valueOf(point3.getLng())-(k*Double.valueOf(point3.getLat())+b))<0.00000001)&&((Double.valueOf(point1.getLat())<=Double.valueOf(point3.getLat())&&Double.valueOf(point3.getLat())<=Double.valueOf(point2.getLat()))||(Double.valueOf(point1.getLat())>=Double.valueOf(point3.getLat())&&Double.valueOf(point3.getLat())>=Double.valueOf(point2.getLat()))))   //路径变换的起点在这段折线上
		{
			start_position=i;
			break;
		}
	}
	//查找路径变换结束的位置是在折线的第几段（减一）
	for(int i=0;i<pointlist.size()-1;i++)
	{
		Points point1=pointlist.get(i);             //保存到数据库中的折线两端的点之一
		Points point2=pointlist.get(i+1);           //保存到数据库中的折线两端的点之二
		//System.out.println("7查看point3是什么"+point3.getPointsId()+"同时point1是什么"+point1.getPointsId());
		//System.out.println("7查看point4是什么"+point4.getPointsId()+"同时point2是什么"+point2.getPointsId());
		double k=beze_k(Double.valueOf(point1.getLat()),Double.valueOf(point2.getLat()),Double.valueOf(point1.getLng()),Double.valueOf(point2.getLng()));
		double b=beze_b(Double.valueOf(point1.getLat()),Double.valueOf(point2.getLat()),Double.valueOf(point1.getLng()),Double.valueOf(point2.getLng()));
		//System.out.println("7查看k是什么"+k+"同时b是什么"+b);
		//System.out.println("7查看k是什么"+(Double.valueOf(point3.getLng())-(k*Double.valueOf(point3.getLat())+b)));
		//System.out.println("7查看k是什么"+(Double.valueOf(point4.getLng())-(k*Double.valueOf(point4.getLat())+b)));
		//路径变换的终点在这段折线的起始点上，其实下面的else if包含了第一种if的情况
		if(point4.getPointsid().equals(point2.getPointsid()))   //路径变换的终点在这段折线上
		{ 
			//System.out.println("7查看起始点"+point4.getPointsId());
			end_position=i+1;
			break;
		}
		//路径变换的终点在这段折线的起始点之外的位置，并且避免“一条直线三个点”，即两端折线的方程一样，要增添纬度取值范围的判断
		else if((Math.abs(Double.valueOf(point4.getLng())-(k*Double.valueOf(point4.getLat())+b))<0.00000001)&&((Double.valueOf(point1.getLat())<=Double.valueOf(point4.getLat())&&Double.valueOf(point4.getLat())<=Double.valueOf(point2.getLat()))||(Double.valueOf(point1.getLat())>=Double.valueOf(point4.getLat())&&Double.valueOf(point4.getLat())>=Double.valueOf(point2.getLat())))) //路径变换的终点在这段折线上
		{ 
			//System.out.println("7查看end_position的位置"+pointlist.get(i).getPointsId()+"序列号"+pointlist.get(i).getSerialNum());
			end_position=i+1;
			break;
		}
	}
	//判断是否是逆序,也就是说动画中运行的方向和数据库中存储的折线的方向是不同的
	if(start_position>=end_position)
	{
		start_position+=1;
		end_position-=1;
	}
	System.out.println("8查看start_position和end_position"+start_position+end_position);
	//接下来，将每段上小车应该经过的点的纬度保留下来，分3种情况来考虑
	if((end_position-start_position==1)||(end_position-start_position==-1))   //-1的情况对应的是逆序，和顺序的处理方式一样
	{
		System.out.println("9拼接等于1或者-1");
		total_length=beze_length(Double.valueOf(point3.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point4.getLng()));
		step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
		for(int j=0;j<step;j++)
		{
			double k=beze_k(Double.valueOf(point3.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point4.getLng()));
			double b=beze_b(Double.valueOf(point3.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point4.getLng()));
			double x=Double.valueOf(point3.getLat())+(Double.valueOf(point4.getLat())-Double.valueOf(point3.getLat()))*j/step;
			double y=k*x+b;
			axisx=axisx+x+",";
			axisy=axisy+y+",";        			
		}
//        		axisx+=point4.getLat();          //考虑要不要也加“,”
//        		axisy+=point4.getLng();          //都加逗号隔开之后，拿到JSP页面上处理的时候，要把最后一个“,”去掉再处理
		System.out.println("9各个排查");
		//return "1";
	}
	//+2的情况对应的是顺序的时候，经过两段折线的处理过程；-2对应的是逆序的时候
	else if((end_position-start_position==2)||(end_position-start_position==-2))
	{
		System.out.println("9拼接等于2或者-2"+end_position+start_position);
		Points point1=new Points();            //未赋值
		if((end_position-start_position==2)) 
		{
			point1=pointlist.get(start_position+1);    //折点的经纬度，顺序的
		}
		//在逆序的时候，对折点处的位置进行赋值
		if((end_position-start_position==-2)) 
		{
			point1=pointlist.get(end_position+1);
		}
		//第一段折线上，小车应该经过的所有点的坐标
		total_length=beze_length(Double.valueOf(point3.getLat()),Double.valueOf(point1.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point1.getLng()));
		step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
		for(int j=0;j<step;j++)
		{
			double k=beze_k(Double.valueOf(point3.getLat()),Double.valueOf(point1.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point1.getLng()));
			double b=beze_b(Double.valueOf(point3.getLat()),Double.valueOf(point1.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point1.getLng()));
			double x=Double.valueOf(point3.getLat())+(Double.valueOf(point1.getLat())-Double.valueOf(point3.getLat()))*j/step;
			double y=k*x+b;
			axisx=axisx+x+",";
			axisy=axisy+y+",";
		}
		//第二段折线上，小车应该经过的所有点的坐标
		total_length=beze_length(Double.valueOf(point1.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point1.getLng()),Double.valueOf(point4.getLng()));
		step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
		for(int j=0;j<step;j++)
		{
			double k=beze_k(Double.valueOf(point1.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point1.getLng()),Double.valueOf(point4.getLng()));
			double b=beze_b(Double.valueOf(point1.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point1.getLng()),Double.valueOf(point4.getLng()));
			double x=Double.valueOf(point1.getLat())+(Double.valueOf(point4.getLat())-Double.valueOf(point1.getLat()))*j/step;
			double y=k*x+b;
			axisx=axisx+x+",";
			axisy=axisy+y+",";
		}
//        		axisx+=point4.getLat();
//        		axisy+=point4.getLng();
		//return "2";    		
	}
	//顺序时，多于两段折线的时候的处理情况
	else if(end_position-start_position>2)
	{
		System.out.println("9拼接大于2------------------");
		//第一段折线上，小车应该经过的所有点的坐标
		Points point1=pointlist.get(start_position+1);            //第一个经过的折点的经纬度   		
		total_length=beze_length(Double.valueOf(point3.getLat()),Double.valueOf(point1.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point1.getLng()));
		//step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
		if(point1.getEdge().getEdgetype().equals("A")){
			step=(int)(total_length*TOTAL_SIMPSON_STEP*4);
			System.out.println("航空线路------------------");	
		}//控制不同运输工具速度 杨丽
		else step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
		for(int j=0;j<step;j++)
		{
			double k=beze_k(Double.valueOf(point3.getLat()),Double.valueOf(point1.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point1.getLng()));
			double b=beze_b(Double.valueOf(point3.getLat()),Double.valueOf(point1.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point1.getLng()));
			double x=Double.valueOf(point3.getLat())+(Double.valueOf(point1.getLat())-Double.valueOf(point3.getLat()))*j/step;
			double y=k*x+b;
			axisx=axisx+x+",";
			axisy=axisy+y+",";
		}
		//中间几段折线的处理方法
		for(int i=start_position+1;i<end_position-1;i++)
		{
			Points point5=pointlist.get(i);             //保存到数据库中的折线两端的点之一
			Points point6=pointlist.get(i+1);           //保存到数据库中的折线两端的点之二
			total_length=beze_length(Double.valueOf(point5.getLat()),Double.valueOf(point6.getLat()),Double.valueOf(point5.getLng()),Double.valueOf(point6.getLng()));
			//step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
			if(point5.getEdge().getEdgetype().equals("A")){
				step=(int)(total_length*TOTAL_SIMPSON_STEP*4);
				System.out.println("航空线路------------------");	
			}//控制不同运输工具速度 杨丽
			else step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
			for(int j=0;j<step;j++)
			{
				double k=beze_k(Double.valueOf(point5.getLat()),Double.valueOf(point6.getLat()),Double.valueOf(point5.getLng()),Double.valueOf(point6.getLng()));
				double b=beze_b(Double.valueOf(point5.getLat()),Double.valueOf(point6.getLat()),Double.valueOf(point5.getLng()),Double.valueOf(point6.getLng()));
				double x=Double.valueOf(point5.getLat())+(Double.valueOf(point6.getLat())-Double.valueOf(point5.getLat()))*j/step;
				double y=k*x+b;
				axisx=axisx+x+",";
				axisy=axisy+y+",";
			}
		}       		
		//最后一段折线上，小车应该经过的所有点的坐标
		Points point2=pointlist.get(end_position-1);            //最后一个经过的折点的经纬度  
		total_length=beze_length(Double.valueOf(point2.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point2.getLng()),Double.valueOf(point4.getLng()));
		//step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
		if(point2.getEdge().getEdgetype().equals("A")){
			step=(int)(total_length*TOTAL_SIMPSON_STEP*4);
			System.out.println("航空线路------------------");	
		}//控制不同运输工具速度 杨丽
		else step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
		for(int j=0;j<step;j++)
		{
			double k=beze_k(Double.valueOf(point2.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point2.getLng()),Double.valueOf(point4.getLng()));
			double b=beze_b(Double.valueOf(point2.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point2.getLng()),Double.valueOf(point4.getLng()));
			double x=Double.valueOf(point2.getLat())+(Double.valueOf(point4.getLat())-Double.valueOf(point2.getLat()))*j/step;
			double y=k*x+b;
			axisx=axisx+x+",";
			axisy=axisy+y+",";
		}
//        		axisx+=point4.getLat();
//        		axisy+=point4.getLng();
		//return "3";    	
		System.out.println("9拼接"+"axisx="+axisx);
	}
	//逆序时，多于两段折线的时候的处理情况
	else if(end_position-start_position<-2)
	{
		System.out.println("9拼接小于-2");
		//第一段折线上，小车应该经过的所有点的坐标
		Points point1=pointlist.get(start_position-1);            //第一个经过的折点的经纬度，位置发生改变   		
		total_length=beze_length(Double.valueOf(point3.getLat()),Double.valueOf(point1.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point1.getLng()));
		//step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
		if(point1.getEdge().getEdgetype().equals("A")){
			step=(int)(total_length*TOTAL_SIMPSON_STEP*4);
			System.out.println("航空线路------------------");	
		}//控制不同运输工具速度 杨丽
		else step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
		for(int j=0;j<step;j++)
		{
			double k=beze_k(Double.valueOf(point3.getLat()),Double.valueOf(point1.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point1.getLng()));
			double b=beze_b(Double.valueOf(point3.getLat()),Double.valueOf(point1.getLat()),Double.valueOf(point3.getLng()),Double.valueOf(point1.getLng()));
			double x=Double.valueOf(point3.getLat())+(Double.valueOf(point1.getLat())-Double.valueOf(point3.getLat()))*j/step;
			double y=k*x+b;
			axisx=axisx+x+",";
			axisy=axisy+y+",";
		}
		//中间几段折线的处理方法，与顺序的时候尤为不同！
		for(int i=start_position-1;i>end_position+1;i--)
		{
			Points point5=pointlist.get(i);             //保存到数据库中的折线两端的点之一
			Points point6=pointlist.get(i-1);           //保存到数据库中的折线两端的点之二
			total_length=beze_length(Double.valueOf(point5.getLat()),Double.valueOf(point6.getLat()),Double.valueOf(point5.getLng()),Double.valueOf(point6.getLng()));
			//step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
			if(point5.getEdge().getEdgetype().equals("A")){
				step=(int)(total_length*TOTAL_SIMPSON_STEP*4);
				System.out.println("航空线路------------------");	
			}//控制不同运输工具速度 杨丽
			else step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
			for(int j=0;j<step;j++)
			{
				double k=beze_k(Double.valueOf(point5.getLat()),Double.valueOf(point6.getLat()),Double.valueOf(point5.getLng()),Double.valueOf(point6.getLng()));
				double b=beze_b(Double.valueOf(point5.getLat()),Double.valueOf(point6.getLat()),Double.valueOf(point5.getLng()),Double.valueOf(point6.getLng()));
				double x=Double.valueOf(point5.getLat())+(Double.valueOf(point6.getLat())-Double.valueOf(point5.getLat()))*j/step;
				double y=k*x+b;
				axisx=axisx+x+",";
				axisy=axisy+y+",";
			}
		}       		
		//最后一段折线上，小车应该经过的所有点的坐标
		Points point2=pointlist.get(end_position+1);            //最后一个经过的折点的经纬度，位置发生改变   
		total_length=beze_length(Double.valueOf(point2.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point2.getLng()),Double.valueOf(point4.getLng()));
		//step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
		if(point2.getEdge().getEdgetype().equals("A")){
			step=(int)(total_length*TOTAL_SIMPSON_STEP*4);
			System.out.println("航空线路------------------");	
		}//控制不同运输工具速度 杨丽
		else step=(int)(total_length/reference_length*TOTAL_SIMPSON_STEP);
		for(int j=0;j<step;j++)
		{
			double k=beze_k(Double.valueOf(point2.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point2.getLng()),Double.valueOf(point4.getLng()));
			double b=beze_b(Double.valueOf(point2.getLat()),Double.valueOf(point4.getLat()),Double.valueOf(point2.getLng()),Double.valueOf(point4.getLng()));
			double x=Double.valueOf(point2.getLat())+(Double.valueOf(point4.getLat())-Double.valueOf(point2.getLat()))*j/step;
			double y=k*x+b;
			axisx=axisx+x+",";
			axisy=axisy+y+",";
		}
//        		axisx+=point4.getLat();
//        		axisy+=point4.getLng();
		//return "3";    		
	}      	       	
	return "success";
        	}
    
    
    
    //从数据库中读取某个车辆的一次调度任务，按路线（折线）拼接
    public String one_task(String taskID){ 
    	System.out.println("4进来one_task"+"taskID=-------------------------"+taskID);
		List<Path> Pathlist=pathService.findByTaskId(taskID);
		//下面两句只是视觉上看不到效果！！！
		//axisx+=Pathlist.get(0).getNodesByFirstNodeId().getLatitude()+",";
		//axisy+=Pathlist.get(0).getNodesByFirstNodeId().getLongitude()+",";
		for(int i=0;i<Pathlist.size();i++)
		{ 
			Path path=Pathlist.get(i);
			System.out.println("5即将进入point_array"+"EdgeId="+path.getEdge().getEdgeid());
			point_array(path.getEdge().getEdgeid(),path.getFirstnodeid(),path.getSecondnodeid());
			edgeType+= path.getEdge().getEdgetype()+",";
			Nodes nodef= nodesService.selectByPrimaryKey(path.getEdge().getFirstnodeid()) ;
			fnodename+=nodef.getNodename();//任务起点
			Nodes nodes=  nodesService.selectByPrimaryKey(path.getEdge().getSecondnodeid()) ;
			snodename+=nodes.getNodename();//任务起点
		}
		String secondnodeid=Pathlist.get(Pathlist.size()-1).getSecondnodeid();
		Nodes node=nodesService.selectByPrimaryKey(secondnodeid);
		axisx+=node.getLatitude()+",";
		axisy+=node.getLongitude()+",";
		//axisx+=",";
		//axisy+=",";
		System.out.println("10拼接"+"axisx="+axisx);
		System.out.println("10拼接"+"axisy="+axisy);
		return "success";
    }   
    
  
    
    //从数据库中读取某个车辆、某个调度方案的一个基因
    //   public String one_Trsportation(String transID,String schemeID){ 
       //调试的时候写成不含参数的，是下面的函数设定了参数而已
    @ResponseBody
    @RequestMapping("/one_Trsportation")
   public List<EdgePoints>  one_Trsportation() throws Exception{ 
    	 axisx="";    //方便下面调用，问题是什么时候清零？调试的时候解决这个问题
    	 axisy="";  
    	 edgeType="";//区分不同连接边，用于读取显示
    	//String nodeid="";
    	 fnodename="";//用于单次任务运输终点起点的记录
    	 snodename="";//用于单次任务运输终点的记录
   	//原来最初的测试
   		//String transID="b40d925ad4da44aab621ac374d54a6b3";
   		//String schemeID="1";
   	//毕设截图用20150121
	    String transID="25461ab73d364c449a299898bddab9c8";
	    String schemeID="1";
   		System.out.println("1进来one_Trsportation"+"transID="+transID+"并且schemeID="+schemeID);    		
   		List<Gene> Genelist=geneService.findByTransIDandSchemeID(transID, schemeID);
   		Gene gene=Genelist.get(0);
   		String geneID=gene.getGeneid();
   		System.out.println("2进来one_Trsportation"+"geneID="+geneID);
   		List<Task> Tasklist=taskService.findByGeneID(geneID);   //也可以用gene.getTaskSum()获得task的数目
   		for(int i=0;i<Tasklist.size();i++)
   		{ 
   			Task task=Tasklist.get(i);
   			System.out.println("3即将进入one_task"+"taskID="+task.getTaskid());
   			one_task(task.getTaskid());
   		}
   			edgePoints.setAxisx(axisx);
   			edgePoints.setAxisy(axisy);
   			edgePoints.setEdgeType(edgeType);
   			edgePoints.setFnodename(fnodename);
   			edgePoints.setSnodename(snodename);
   			List<EdgePoints> list=new ArrayList<EdgePoints>();
   			list.add(edgePoints);
   		return  list;
   		}  

}
