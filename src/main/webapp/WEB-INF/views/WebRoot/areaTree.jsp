<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>区域地图</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/static/style/zTreeStyle.css" />" rel="stylesheet" type="text/css">
	<style type="text/css">
		 
		#saDiv{ float:left; width:10%; height:800px;}
		#bMapDiv{ float:right; width:86%; height:800px;} 
	</style>
	<script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.4.4.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/js/js/jquery.ztree.core-3.4.js" />"></script><!--
	 <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
	--><script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=aDZxlhKjfz0jBzjDhmae8iCn"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
<script type="text/javascript" charset="gbk"  src="http://api.map.baidu.com/library/MarkerTool/1.2/src/MarkerTool_min.js" ></script>   
<!--加载鼠标绘制工具-->
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	<script type="text/javascript">
		//zTree设置
		var disastername;
		var dnodeId;
		var setting = {
			treeId: "areaTree", //设置tree的id
			treeObj: null,
			
			data: {
				key:{
					name: "areaName", 
					title: "",
					url: null
				},
				simpleData: {
					enable: true,
					idKey: "areaId",
					pIdKey: "parAreaId",
					rootPid: "0"
				}
			},
			view: {
				fontCss: getFontCss
			},
			callback: {
				onClick: zTreeOnClick
			}
			
		};
 		
 		function focusKey(e) {
			if (key.hasClass("empty")) {
				key.removeClass("empty");
			}
		}
		function blurKey(e) {
			if (key.get(0).value === "") {
				key.addClass("empty");
			}
		}
		var lastValue = "", nodeList = [], fontCss = {};
		function searchNode(e) {
			var zTree = $.fn.zTree.getZTreeObj("areaTreeDiv");
			var value = $.trim(key.get(0).value);	
			if (lastValue === value) return;
			lastValue = value;		
			if (value === "") return;
			updateNodes(false);
			nodeList = zTree.getNodesByParamFuzzy("areaName", value);
			updateNodes(true);
			zTree.expandAll(true);//展开全部节点

		}
		function updateNodes(highlight) {
			var zTree = $.fn.zTree.getZTreeObj("areaTreeDiv");
			for( var i=0, l=nodeList.length; i<l; i++) {
				nodeList[i].highlight = highlight;
				zTree.updateNode(nodeList[i]);
			}
		}
		function getFontCss(treeId, treeNode) {
			return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
		}
        getAllNodeCup();
        getAllEdge();
        //onetrsport();
		var key;
 		var areas=${areaList };
		$(document).ready(function(){
			$.fn.zTree.init($("#areaTreeDiv"), setting,areas);
			key = $("#txtSearch");		
			key.bind("focus", focusKey)
			.bind("blur", blurKey)
			.bind("propertychange", searchNode)
			.bind("input", searchNode);
			
		});		
		//点击tree
		function zTreeOnClick(event, treeId, treeNode) {
		    map.clearOverlays();//清除地图上所有覆盖物（标注）
		     getAllNodeCup();
             getAllEdge();
		    var zarealevel = parseInt(treeNode.areaLevel);
		    var zareaname = treeNode.areaName;	 
		   
		    if(zarealevel === 1){
				map.centerAndZoom(zareaname);
		    }else if( zarealevel === 2){
		    	map.centerAndZoom(treeNode.getParentNode().areaName);
		    	var local = new BMap.LocalSearch(map, {  
				 renderOptions:{map: map}  
				});  
				local.search(zareaname);  
		    }else if( zarealevel === 3){
		    
		    	map.centerAndZoom(treeNode.getParentNode().getParentNode().areaName);	
				var myGeo = new BMap.Geocoder();
				// 将地址解析结果显示在地图上,并调整地图视野
				myGeo.getPoint(treeNode.getParentNode().getParentNode().areaName+treeNode.getParentNode().areaName+zareaname, function(point){
				  if (point) {
				    map.centerAndZoom(point, 15);
				    //map.addOverlay(new BMap.Marker(point));
				  }
				}, treeNode.getParentNode().getParentNode().areaName);
				getNodeCup(treeNode);	    
		    }
		}; 
		
		//首页刚进来的时候，获得所有节点信息，进行标记
		function getAllNodeCup(){
			$.ajax({
				type: "post",
				url: "getNodesByEmer?emerId="+encodeURI(encodeURI($(this).val())),
				async: true,
				success: function(data){
				   $.each(data,function(idx,item){   				   
					    addMarker(item);//将标注点添加在地图上
					})					
				}			
			});
		}
		
		//首页刚进来的时候，获得所有连接边信息，进行显示
		function getAllEdge(){
			$.ajax({
				type: "post",
				url: "getEdgesByEmer?emerId="+($(this).val()),
				async: true,
				success: function(data){
				   $.each(data,function(idx,item){   				   
					    addEdge(item);//将边添加在地图上
					})					
				}			
			});
		}
		//获得风险清单项信息
		function getNodeCup(treeNode){
			$.ajax({
				type: "post",
				url: "getNodesByAreaId?areaid="+treeNode.areaId+"&randomn="+Math.random(),
				async: true,
				success: function(data){
				   $.each(data,function(idx,item){   				   
					    addMarker(item);//将标注点添加在地图上
					})
					
				}			
			});
		}
		//在地图上添加标注
		function addMarker(item){
		  var point = new BMap.Point(item.axisx, item.axisy);//创建标注点
		  var myIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(23, 25), {  
                        offset: new BMap.Size(10, 25), // 指定定位位置   
                        imageOffset: new BMap.Size(0, 0 - 10 * 25) // 设置图片偏移   
                    });
         // var myIcon = new BMap.Icon("/risk/static/picture/save.png", new BMap.Size(23, 25), {  
                      //  offset: new BMap.Size(10, 25), // 指定定位位置   
                    //    imageOffset: new BMap.Size(0, 0 - 10 * 25) // 设置图片偏移   
                   // });
                    //自定义标注--by 杨丽          
		  var sContent;
		  if(item.type=='D'){ sContent =
			"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+item.nodename+"</h4>" + 
			"<img style='float:right;margin:4px' id='imgDemo' src='picture/psb1.jpg' width='119' height='84'/>"+
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:0em'>"+item.installaddress+"</p>" +
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:0em'><b>"+"物资需求："+"</b></p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:0em'>"+"食品：20t<br>"+"药品：10t<br>"+"纯净水：300箱"+"</p>" + 
			"</div>";}
			else if(item.type=='W'){ sContent =
			"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+item.nodename+"</h4>" + 
			"<img style='float:right;margin:4px' id='imgDemo' src='picture/psb.jpg' width='119' height='84'/>"+
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:0em'>"+item.installaddress+"</p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:0em'><b>"+"<br>物资存储："+"</b></p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:0em'>"+"生活用品：1000t &nbsp;&nbsp;"+"食品：500t<br>"+"药品：500t &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"纯净水：2000箱"+"</p>" + 
			"</div>";}
		 
		  var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
		  var marker;
		  //var marker = new BMap.Marker(point);
		  if(item.type=='D'){marker = new BMap.Marker(point);}
		  else if(item.type=='W') {marker = new BMap.Marker(point, {icon: myIcon});}   //创建标注
		  marker.setTitle(item.nodename);//设置标注的标题，当鼠标移至标注上时显示此标题;
		  marker.addEventListener("click", function(){ // 添加事件监听函数     
		   this.openInfoWindow(infoWindow);//打开信息窗口
		   //图片加载完毕重绘infowindow
		   document.getElementById('imgDemo').onload = function (){
		   infoWindow.redraw();
			   }
		   nodeId=item.nodeId;
		   nodename=item.nodename;
		   address = item.address;
		   
		   });
		  map.addOverlay(marker);//将标注添加到地图		 
		}
		
			
		
		//单个调度方案
		function oneScheme(){

     $.ajax({
				type: "post",
				url: "one_Scheme?"+"&randomn="+Math.random(),
				async: true,
				success: function(data){
				   $.each(data,function(idx,item){   				   
					    cartoonStart(item);//开始动画
					})					
				}			
			});
}
/*function areaclose(){

$.ajax({
				type: "post",
				url: "savepolylinepoints?axisx="+axisx+"&axisy="+axisy+"&randomn="+Math.random(),
				cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#points").html(data);
		    	}});
		    	points=[];	//将以下三个数组清空，以方便记录下一个折线的坐标并储存
		    	axisx=[];
		    	axisy=[];   

}*/		
	</script>
	
	
  </head>
  
  <body>
  <form action="" id="areaForm" method="post">
	  <input type="hidden" id="showinfoFlag" name="showinfoFlag" value="${showinfoFlag}"/>
	  <table border="0" width="100%" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
		    cellspacing="0" cellpadding="0" height="27">
		        <tr>
		          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
		          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
		                <tr>
							<td width="100%" class="f3">区域管理>>区域展現</td>
		                </tr>
		            </table></td>
		          <td width="50%"></td>
		        </tr>
		      </table></td>
		  </tr>
	  </table>
	  	  <div id="saDiv">
	  	  <div id="serarchDiv">
	  	  
	  	  <input type="button" value="开启测量工具" onclick="myDis.open()" />    
          <input type="button" value="关闭测量工具" onclick="myDis.close()" />
          <input type="button" value="单运输工具动画演示" onclick="onetrsport()" />
          <input type="button" value="调度方案动画演示" onclick="oneScheme()" />
         <!--   <input type="button" value="画线结束" onclick="areaclose()" />  -->
		  <input id="txtSearch" name="txtSearch" type="text">
	  	  </div>
		  <div id="areaTreeDiv"  class="ztree"></div> 	
		  </div>	  
		  <div id="bMapDiv"></div> 
	</form>
  </body>
  <script type="text/javascript">	
		var map = new BMap.Map("bMapDiv");                        // 创建Map实例
		map.centerAndZoom("中国", 6);     // 初始化地图,设置中心点坐标和地图级别
		map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
		map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
		map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件 	
		map.enableScrollWheelZoom();                            //启用滚轮放大缩小
		//map.addControl(new BMap.MapTypeControl());          //添加地图类型控件
		//map.setCurrentCity("西安");          // 设置地图显示的城市 此项是必须设置的
		
		
		var  mapStyle ={ 
        features: ["road","water"],//隐藏地图上的poi
        style : "light"  //设置地图风格为高端黑
    }
		
		map.setMapStyle(mapStyle);
		function showInfo1(e){
			var showinfoFlag = $("#showinfoFlag").val();
			if(showinfoFlag == "1"){
				if(confirm("该坐标为"+e.point.lng+ ", " + e.point.lat+"。是否将风险清单项添加至此？")){
					opener.setValue(e.point.lng, e.point.lat); 
					 window.close(); 	 		
				}
			}
			//返回到该页面被调用的地方addEdge.jsp，并返回flag="2"
            if(showinfoFlag == "2"){
				if(confirm("您选择的起点是'"+nodename+"'。是否将此处设置为线路起点？")){
					opener.setValue(nodeId,nodename,flag="2",e.point.lng,e.point.lat); 
					window.close(); 				
				}
			}
			//返回到该页面被调用的地方addEdge.jsp，并返回flag="2"
			if(showinfoFlag == "3"){
				if(confirm("您选择的终点是'"+nodename+"'。是否将此处设置为线路终点？")){
					opener.setValue(nodeId,nodename,flag="3",e.point.lng,e.point.lat); 
					window.close(); 				
				}
			}
			//返回到该页面被调用的地方addWareHouse.jsp
			if(showinfoFlag == "4"){
				if(confirm("该坐标为"+e.point.lng+ ", " + e.point.lat+"。是否将风险应对策略添加至此？")){
					opener.setValue(e.point.lng, e.point.lat);  
					window.close(); 				
				}
			}
			//显示曲线
			if(showinfoFlag == "5"){
			    var originLng = window.opener.document.getElementById('originLng').value;
			    var originLat = window.opener.document.getElementById('originLat').value;
			    var terminalLng = window.opener.document.getElementById('terminalLng').value;
			    var terminalLat = window.opener.document.getElementById('terminalLat').value;
				var axisx=[];    
	            var axisy=[];
	            var pts;
	            var polyline; 
				var origin=new BMap.Point(originLng,originLat);
				var terminal=new BMap.Point(terminalLng,terminalLat);			    
				var driving = new BMap.DrivingRoute(map); //驾车实例
				// alert("搜索驾车路线1");   
	            driving.search(origin, terminal);  
	           // alert("搜索驾车路线2");           
	 
	            //查询过程         
	            driving.setSearchCompleteCallback(function(){
	            if (driving.getStatus() != BMAP_STATUS_SUCCESS)
	            	{alert("这两点间没有直接可到达路线");}
	            if (driving.getStatus() == BMAP_STATUS_SUCCESS)
		            {alert("这两点间有直接可到达路线");
			            pts = driving.getResults().getPlan(0).getRoute(0).getPath(); //通过驾车实例，获得一系列点的数组
			            polyline = new BMap.Polyline(pts); 
			            map.addOverlay(polyline); 
			            for(var j=0;j<pts.length;j++)
			            {
				            axisx[j]=pts[j].lng;
				            axisy[j]=pts[j].lat;
			            }                              
		            }        
	            })//查询并显示结束
	            setTimeout(function(){
	                //添加连接边的时候addEdge.jsp，设置参数为flag == "5"	
	                 //alert("搜索驾车路线3");   
	       			if(confirm("是否保存曲线并退出？")){   
	    					opener.setValue(axisx,axisy,flag="5","","");  
	    					window.close(); 				
	    				}	    
	                ;;},1000)
			}
		}
		
		map.addEventListener("click", showInfo1);
		
		//测量距离的工具
		var myDis = new BMapLib.DistanceTool(map);
		
var e1,e2,j=0;
var p = [];           //用来存储折线的点
var points=[];     //用于存储折线任意修改后的转折处的关键点
var axisx=[];     //用于存储折线任意修改后的转折处的关键点的经度
var axisy=[];     //用于存储折线任意修改后的转折处的关键点的纬度
var polyline;
var overlays = [];	


var overlaycomplete = function(e){
	var showinfoFlag = $("#showinfoFlag").val();
	if(showinfoFlag != "4" && showinfoFlag !="1"){
		console.log("test overlay");
	    overlays.push(e.overlay);
	    //alert("j1的值"+overlays.length+e.overlay);
	    //alert("j2的值"+overlays[overlays.length-1]+(overlays.length-1));
	    overlays[overlays.length-1].enableEditing(); 
	    points=overlays[overlays.length-1].getPath();
	    //alert("j3的值"+points.length);
	          for(var j=0;j<points.length;j++)
	          {
	          axisx[j]=points[j].lng;
	          axisy[j]=points[j].lat;
	          }
	          //alert("折线上的点"+axisx+"其他坐标"+axisy);
	         /*做测试的时候，查看折线的点是不是可以保存时用的，现在程序不需要*/ 
			/*	$.ajax({
					type: "post",
					url: "savepolylinepoints?axisx="+axisx+"&axisy="+axisy+"&randomn="+Math.random(),
					cache: false,
			    	async: false,
			    	success: function(data){
			    		$("#points").html(data);
			    	}});   */
			    	//添加连接边的时候addEdge.jsp，设置参数为flag="4"
			    	opener.setValue(axisx,axisy,flag="4","","");
			    	window.close();
			        //points=[];	//将以下三个数组清空，以方便记录下一个折线的坐标并储存
			    	//axisx=[];
			    	//axisy=[];  
	}
	     
    };
//线的样式
var styleOptions = {
        strokeColor:"red",    //边线颜色。
        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    }
//实例化鼠标绘制工具
    var myDrawingManagerObject = new BMapLib.DrawingManager(map, {
    isOpen: true, 
    drawingMode:BMAP_DRAWING_MARKER ,
    enableDrawingTool: true,
    enableCalculate: false,
    drawingToolOptions: {
        anchor: BMAP_ANCHOR_TOP_RIGHT,//位置
        offset: new BMap.Size(5, 5), //偏离值
        scale: 0.8       //工具栏缩放比例
    },
    polylineOptions: styleOptions //线的样式
    });

   
    //添加鼠标绘制工具监听事件，用于获取绘制结果
    myDrawingManagerObject.addEventListener('overlaycomplete', overlaycomplete);
    //myDrawingManagerObject.addEventListener('polylinecomplete', polylinecomplete);
    function onetrsport(){
	console.log("Test onetrsport");
     $.ajax({
				type: "post",
				url: "one_Trsportation?"+"&randomn="+Math.random(),
				async: true,
				success: function(data){
				   $.each(data,function(idx,item){   				   
					    cartoonStart(item);//开始动画
					})					
				}			
			});
}
  //在地图上添加边
	function addEdge(item){
	//alert("进来显示边");
	//alert("进来显示边1"+item.axisx);
	  //将传递回来的纬度、经度重新放到数组中
	  var lngs=item.axisx.split(",");
	  var lats=item.axisy.split(",");
	   var edgeType = item.edgeType;//显示边类型--by 杨丽
	  //alert("进来显示边2"+item.axisx);
	 // alert("进来显示边类型1"+item.edgeType);
	  var points=[];	  
	  for(var j=0;j<lngs.length;j++)
	  {
	     points[j] = new BMap.Point(lngs[j],lats[j]);//创建点
	     //var marker = new BMap.Marker(points[j]);//创建标注
	     //map.addOverlay(marker);  //将折线添加到地图
	     
	  }	
	 
	  
	  var polyline;	  
	  /*if(lngs.length<100){polyline = new BMap.Polyline(points,{strokeColor:"red", strokeWeight:2, strokeOpacity:1});}
	  else polyline = new BMap.Polyline(points,{strokeColor:"blue", strokeWeight:2, strokeOpacity:1});*/
	  //var edge=[points[0],points[1],points[2],points[3]];
	  // alert("进来显示边类型2"+item.edgeType);
	  if(edgeType == 'H'){polyline = new BMap.Polyline(points,{strokeColor:"GREEN", strokeWeight:1.7, strokeOpacity:1});}
	else  if(edgeType == 'R'){polyline = new BMap.Polyline(points,{strokeColor:"#cc00ff", strokeWeight:1.7, strokeOpacity:1});}
	else  if(edgeType == 'A'){polyline = new BMap.Polyline(points,{strokeColor:"#3366FF", strokeWeight:1.7, strokeOpacity:1});}
	   // else  polyline = new BMap.Polyline(points,{strokeColor:"green", strokeWeight:2, strokeOpacity:1});
	 //  alert("进来显示边类型3"+item.edgeType);
	
	  //不同类型的边显示不同的颜色--by 杨丽
	  map.addOverlay(polyline);  //将折线添加到地图
	  //alert("进来显示边4"+points);		 
	}
	
	//单车动态演示		


//在地图上显示单个车辆的动态运行
	function cartoonStart(item){
	//alert("进来cartoonStart"+item.axisx);
	//alert("进来cartoonStart"+item.axisy);
	//alert("进来cartoonStart--edgeType"+item.edgeType); 
	  //将传递回来的纬度、经度重新放到数组中
	  var lng=item.axisx.substring(0,item.axisx.length-1).split(",");
	  var lat=item.axisy.substring(0,item.axisy.length-1).split(",");
	 // alert("进来cartoonStart--edgeType11"); 
		var edgeType=item.edgeType.substring(0,item.edgeType.length-1).split(",");
		var fnodename=item.fnodename.substring(0,item.fnodename.length-1).split(",");
		var snodename=item.snodename.substring(0,item.snodename.length-1).split(",");
	    //alert("进来cartoonStart--edgeType22"); 
	  //显示边类型--by 杨丽
	  //alert("进来cartoonStart--edgeType"+edgeType); 
	  //var lng=item.axisx.split(",");
	  //var lat=item.axisy.split(",");
	 	 var point=[];	
	 // alert("进来cartoonStart"+lng);  
	  for(var j=0;j<lng.length;j++)
	  {
	     point[j] = new BMap.Point(lat[j],lng[j]);//创建点
	  }		  
	 /* var myIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(23, 25), {  
                    offset: new BMap.Size(2, 10), // 指定定位位置   
                    imageOffset: new BMap.Size(0, 0 - 10 * 10) // 设置图片偏移   
                });
	  */
	  
	var myIcon ;
	if(edgeType=='H'){myIcon= new BMap.Icon("/risk/static/picture/truck2.jpg", new BMap.Size(43,30), { 
	//小车图片
     offset: new BMap.Size(100, 100), //相当于CSS精灵
    imageOffset: new BMap.Size(0 , 0 - 10 * 10) //图片的偏移量。为了是图片底部中心对准坐标点。
     });}
     if(edgeType=='R'){myIcon= new BMap.Icon("/risk/static/picture/r1.jpg", new BMap.Size(43,30), { 
	//小车图片
     offset: new BMap.Size(100, 100), //相当于CSS精灵
    imageOffset: new BMap.Size(0 , 0 - 10 * 10) //图片的偏移量。为了是图片底部中心对准坐标点。
     });}
      if(edgeType=='A'){myIcon= new BMap.Icon("/risk/static/picture/p1.jpg", new BMap.Size(43,30), { 
	//小车图片
     offset: new BMap.Size(100, 100), //相当于CSS精灵
    imageOffset: new BMap.Size(0 , 0 - 10 * 10) //图片的偏移量。为了是图片底部中心对准坐标点。
     });}
	//实现定时车辆演示
	var carMk = new BMap.Marker(point[0],{icon:myIcon});
	var sContent;
	  if(edgeType=='H'){ 
	  //alert("进来公路--");
	  sContent ="<h4 style='margin:0 0 10px 0;padding:0.2em 0'>"+"汽车"+"</h4>" + 
		"<img style='float:right;margin:4px' id='imgDemo' src='/risk/static/picture/truckOigin.jpg' width='119' height='124'/>"+
		"<p style='margin:0;line-height:1.5;font-size:5px;text-indent:0em'>"+"<b>速度：</b>80km/h<br><b>装卸货时间：</b><br>2h<br><b>起始节点：</b><br>"+item.fnodename+"<br><b>目标节点：</b><br>"+item.snodename+"<br><b>药品：</b>2t<br>"+"<b>食品：</b>3t"+"</p>" + 
		"</div>";}
		if(edgeType=='R'){
		//alert("进来铁路--");
		sContent ="<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+"火车"+"</h4>" + 
		"<img style='float:right;margin:4px' id='imgDemo' src='/risk/static/picture/trainOigin.jpg' width='119' height='124'/>"+
		"<p style='margin:0;line-height:1.5;font-size:5px;text-indent:0em'>"+"<b>速度：</b>60km/h<br><b>装卸货时间：</b><br>5h<br><b>起始节点：</b><br>"+item.fnodename+"<br><b>目标节点：</b><br>"+item.snodename+"<br><b>药品：</b>5t<br>"+"<b>食品：</b>10t<br>"+"<b>纯净水：</b>15t "+"<b>生活用品：</b>10t"+"</p>" + 
		"</div>";}
	 if(edgeType=='A'){ 
	// alert("进来航线速度--");
	 sContent ="<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+"飞机"+"</h4>" + 
		"<img style='float:right;margin:4px' id='imgDemo' src='/risk/static/picture/planeOigin.jpg' width='119' height='124'/>"+
		"<p style='margin:0;line-height:1.5;font-size:5px;text-indent:0em'>"+"<b>速度：</b>200km/h<br><b>装卸货时间：</b><br>3h<br><b>起始节点：</b><br>"+item.fnodename+"<br><b>目标节点：</b><br>"+item.snodename+"<br><b>药品：</b>3t<br>"+"<b>食品：</b>2t<br>"+"<b>纯净水：</b>10t "+"</p>" + 
		"</div>";}
	  var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
	 
	// var marker = new BMap.Marker( point[j]);
	carMk.addEventListener("click", function(){ // 添加事件监听函数     
	   this.openInfoWindow(infoWindow);//打开信息窗口
	   //图片加载完毕重绘infowindow
	  document.getElementById('imgDemo').onload = function (){
	   infoWindow.redraw();
		  }
	  
	   
	   });
	
		 
	
	//alert("进来cartoonStart，图片定位");
    map.addOverlay(carMk); 
    //alert("进来cartoonStart，图片定位"+point[0].lng);             
    var len =point.length;
   resetMkPoint(1,len,point,carMk)


function resetMkPoint(i,len,point,carMk){       
    carMk.setPosition(point[i]);
    //alert("进来resetMkPoint，经度"+points[i].lng+"纬度"+points[i].lat);
    if(i < len){
        setTimeout(function(){
            i++;
            resetMkPoint(i,len,point,carMk);
        },100);
    }
}


}
</script>
</html>


