<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
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

	<link rel="stylesheet" href="<%=request.getContextPath()%>/style/gov_style_10.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style/zTreeStyle.css">
	<style type="text/css">
		 
		#bMapDiv{ float:right; width:86%; height:800px;} 
	</style>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
<script type="text/javascript" charset="gbk"  src="http://api.map.baidu.com/library/MarkerTool/1.2/src/MarkerTool_min.js" ></script>  
  </head>
  
  <body>
  <form action="" id="areaForm" method="post">
	  <input type="hidden" id="showinfoFlag" name="showinfoFlag" value="${showinfoFlag}"/>
	  <table border="0" width="100%" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="100%" height="27" bgcolor="#E3EBFE">
		    <table border="0" width="100%" cellspacing="0" cellpadding="0" height="27">
		        <tr>
		          <td width="3%"><img src="<%=request.getContextPath()%>/picture/truck.png" width="29" height="27"></td>
		          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
		                <tr>
							<td width="100%" class="f3">地图>>点、线路</td>
		                </tr>
		            </table></td>
		          <td width="50%"></td>
		        </tr>
		      </table></td>
		  </tr>
	  </table>	  
		  <div id="bMapDiv"></div> 
		  <input type="button" value="开始" onclick="run();" />
		  <input type="button" value="开启" onclick="myDis.open()" />    
          <input type="button" value="关闭" onclick="myDis.close()" />   
	</form>
  </body>
  <script type="text/javascript">	
       //地图创建
		var map = new BMap.Map("bMapDiv");                        // 创建Map实例		
		map.centerAndZoom("中国", 6);     // 初始化地图,设置中心点坐标和地图级别
		//var opts = {type: BMAP_NAVIGATION_CONTROL_SMALL};   //改变控件配置
		//map.addControl(new BMap.NavigationControl(opts));               // 添加平移缩放控件    左上
		map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件    左上
		map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件     左下
		map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件    右下
		map.enableScrollWheelZoom();                            //启用滚轮放大缩小
		//map.addControl(new BMap.MapTypeControl());          //添加地图类型控件       右上
		//map.setCurrentCity("西安");          // 设置地图显示的城市 此项是必须设置的
		
		var myDis = new BMapLib.DistanceTool(map);
		
//标注
var myP3 = new BMap.Point(108.424374,23.914668); //标注点
var marker = new BMap.Marker(myP3); 
//自定义显示的标注的图片
//var myIcons = new BMap.Icon("picture/save.png", new BMap.Size(32, 70), { //小车图片
 //offset: new BMap.Size(0, -5), //相当于CSS精灵
// imageOffset: new BMap.Size(0, 0) //图片的偏移量。为了是图片底部中心对准坐标点。
// }); 
//var marker = new BMap.Marker(myP3, {icon: myIcons});  
map.addOverlay(marker);     //将标注点添加到地图上
marker.addEventListener("click", function(){     //监听事件  
 alert("您点击了标注");   
}); 

marker.enableDragging();    
marker.addEventListener("dragend", function(e){    
 alert("当前位置：" + e.point.lng + ", " + e.point.lat);    
}); 

//信息窗口
var opts = {    
 width : 250,     // 信息窗口宽度    
 height: 100,     // 信息窗口高度    
 title : "Hello"  // 信息窗口标题   
}    
var infoWindow = new BMap.InfoWindow("World123", opts);  // 创建信息窗口对象    
map.openInfoWindow(infoWindow, myP3);      // 打开信息窗口 


//折线
/*var polyline = new BMap.Polyline([    
   new BMap.Point(116.399, 39.910),    
   new BMap.Point(106.405, 38.920)    
 ],    
 {strokeColor:"red", strokeWeight:6, strokeOpacity:0.5}    
);    
map.addOverlay(polyline);*/

/*自定义覆盖物
// 定义自定义覆盖物的构造函数  
function SquareOverlay(center, length, color){    
 this._center = center;    
 this._length = length;    
 this._color = color;    
}    
// 继承API的BMap.Overlay    
SquareOverlay.prototype = new BMap.Overlay(); 

// 实现初始化方法  
SquareOverlay.prototype.initialize = function(map){    
// 保存map对象实例   
 this._map = map;        
 // 创建div元素，作为自定义覆盖物的容器   
 var div = document.createElement("div");    
 div.style.position = "absolute";        
 // 可以根据参数设置元素外观   
 div.style.width = this._length + "px";    
 div.style.height = this._length + "px";    
 div.style.background = this._color;      
// 将div添加到覆盖物容器中   
 map.getPanes().markerPane.appendChild(div);      
// 保存div实例   
 this._div = div;      
// 需要将div元素作为方法的返回值，当调用该覆盖物的show、   
// hide方法，或者对覆盖物进行移除时，API都将操作此元素。   
 return div;    
}  

// 实现绘制方法   
SquareOverlay.prototype.draw = function(){    
// 根据地理坐标转换为像素坐标，并设置给容器    
 var position = this._map.pointToOverlayPixel(this._center);    
 this._div.style.left = position.x - this._length / 2 + "px";    
 this._div.style.top = position.y - this._length / 2 + "px";    
}  

// 实现显示方法    
SquareOverlay.prototype.show = function(){    
 if (this._div){    
   this._div.style.display = "";    
 }    
}      
// 实现隐藏方法  
SquareOverlay.prototype.hide = function(){    
 if (this._div){    
   this._div.style.display = "none";    
 }    
}  

var mySquare = new SquareOverlay(myP3, 100, "red");    
map.addOverlay(mySquare);  

//下面是给地图添加监听器 
map.addEventListener("click", function(e){   
  alert("您点击了地图2。"+e.point.lng + ", " + e.point.lat);     
});   */    
 
 
 //在地图上手绘折线，只能绘制一次
/*var points = []; 
map.addEventListener("click", function (e) {

alert("当前位置1：" + e.point.lng + ", " + e.point.lat);
map.clearOverlays();
points.push(new BMap.Point(e.point.lng, e.point.lat));
map.addOverlay(new BMap.Polyline(points));
alert("当前位置2：" + points.length);
}); */

 //在地图上手绘折线，只能绘制一次，和上面是一样的效果
var e1,e2,j=0;
var p = [];           //用来存储折线的点
var polyline;
var doneDraw = 0;     //判断是否绘制折线结束
  
function addMarker(point, index){
var marker = new BMap.Marker(point);
map.addOverlay(marker);     //将标注点添加到地图上
}

map.addEventListener("click",function(e1){        //当鼠标单击时  
  if(doneDraw == 0){                              //判断是否绘制曲线完毕    
    p.push(new BMap.Point(e1.point.lng,e1.point.lat))   //存储曲线上每个点的经纬度     
    if(polyline){polyline.setPath(p,{strokeColor:"blue", strokeWeight:2, strokeOpacity:1});}                //如果曲线存在，则获取折线上的点    
    else{polyline = new BMap.Polyline(p,{strokeColor:"red", strokeWeight:2, strokeOpacity:1});}              //如果折线不存在，就增加此点    
    if(p.length<2){return ;}                            //当折线上的点只有一个时，不绘制    
    map.addOverlay(polyline);                           //绘制曲线  
  }
});
map.addEventListener("dblclick",function(e2){     //当鼠标双击时：结束绘制，并可以编辑曲线  
  // 添加自定义控件到地图当中
  alert("j的值"+p.length);
  doneDraw = 10;  
  polyline.enableEditing();
  for(var k=0;k<p.length;k++){
     addMarker(p[k], k); 
        alert("waypoint["+k+"]=new BMap.Point("+p[k].lng + "," +p[k].lat+")<br/>");
      }  
});
      
/*var myPushpin = new BMap.PushpinTool(map);          // 创建标注工具实例
myPushpin.addEventListener("markend", function(e){  // 监听事件，提示标注点坐标信息
  alert("您标注的位置：" +    
       e.marker.getPoint().lng + ", "+    
       e.marker.getPoint().lat);   
});   
myPushpin.open();*/ 


var myP1 = new BMap.Point(110.380967,39.913285); //起点
var myP2 = new BMap.Point(116.424374,25.914668); //终点    
var myIcon = new BMap.Icon("picture/save.png", new BMap.Size(32, 70), { //小车图片
 //offset: new BMap.Size(0, -5), //相当于CSS精灵
 imageOffset: new BMap.Size(0, 0) //图片的偏移量。为了是图片底部中心对准坐标点。
 });
var driving2 = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}}); //驾车实例
driving2.search(myP1, myP2); //显示一条公交线路


function run(){
 var driving = new BMap.DrivingRoute(map); //驾车实例
 driving.search(myP1, myP2);
 driving.setSearchCompleteCallback(function(){
 var pts = driving.getResults().getPlan(0).getRoute(0).getPath(); //通过驾车实例，获得一系列点的数组
 var paths = pts.length; //获得有几个点

 var carMk = new BMap.Marker(pts[0],{icon:myIcon});
 map.addOverlay(carMk);                     
 i=0;
 function resetMkPoint(i){
 carMk.setPosition(pts[i]);
 if(i < paths){
 setTimeout(function(){
 i++;
 resetMkPoint(i);
 },1);  //修改这个数值，可以改变小车的运行速度
 }
 }
 setTimeout(function(){
 resetMkPoint(5);
 console.log(i);
 },100)

 });
}



</script>
</html>


