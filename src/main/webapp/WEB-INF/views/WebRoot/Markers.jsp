<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>路线坐标提取器</title>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4&services=true"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=C539106eb9882e26e4afd5a554baee95"></script>	
<script type="text/javascript" src="http://api.map.baidu.com/api?key=46ce9d0614bf7aefe0ba562f8cf87194&v=1.1&services=true"></script>
<script type="text/javascript" src=" http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
</head>
<body>
  <div id="info"></div>
  <div style="width:100%;height:95%;border:1px solid gray" id="container"></div>
</body>

<script type="text/javascript">

var map = new BMap.Map("container");
var e1,e2,e3,j=0;
var p = [];           //用来存储折线的点
//var waypoint= new Array();
var polyline;
var doneDraw = 0;     //判断是否绘制折线结束

//*****************************
  //*自定义一个控件类,即function
  //*
  function ZoomControl(){
    // 默认停靠位置和偏移量
    this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
    this.defaultOffset = new BMap.Size(70, 20);
  }

  // 通过JavaScript的prototype属性继承于BMap.Control
  ZoomControl.prototype = new BMap.Control();

  // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
  // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
  // 初始化自定义控件
  ZoomControl.prototype.initialize = function(map){
    // 创建一个DOM元素
    var div = document.createElement("div");
    // 添加文字说明
    div.appendChild(document.createTextNode("点击输出坐标"));
    // 设置样式
    div.style.cursor = "pointer";
    div.style.border = "1px solid gray";
    div.style.backgroundColor = "#FFEE99";
    // 绑定事件,点击后作什么事情
    div.onclick = function(e){
      alert("绘制完成"); 
      j=0;
      for (j in p){
        document.write("waypoint["+j+"]=new BMap.Point("+p[j].lng + "," +p[j].lat+")<br/>")
      }  
    }
    // 添加DOM元素到地图中
    map.getContainer().appendChild(div);
    // 将DOM元素返回
    return div;
  }
  // 创建控件
  var myZoomCtrl = new ZoomControl();
  

  
map.centerAndZoom(new BMap.Point(119.212432, 35.713939), 17);
map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件



map.addEventListener("click",function(e1){        //当鼠标单击时  
  if(doneDraw == 0){                              //判断是否绘制曲线完毕    
    p.push(new BMap.Point(e1.point.lng,e1.point.lat))   //存储曲线上每个点的经纬度 
    //waypoint.push("waypoint[" + j +"]=new BMap.Point(" + e1.point.lng + "," + e1.point.lat + ")<br />");    //保存坐标赋值
    j=j+1;
    //    alert(e1.point.lng + e1.point.lat);      
    if(polyline){polyline.setPoints(p,{strokeColor:"blue", strokeWeight:2, strokeOpacity:1});}                //如果曲线存在，则获取折线上的点    
    else{polyline = new BMap.Polyline(p,{strokeColor:"red", strokeWeight:2, strokeOpacity:1});}              //如果折线不存在，就增加此点    
    if(p.length<2){return ;}                            //当折线上的点只有一个时，不绘制    
    map.addOverlay(polyline);                           //绘制曲线  
  }
});


map.addEventListener("dblclick",function(e2){     //当鼠标双击时：结束绘制，并可以编辑曲线  
  // 添加自定义控件到地图当中
  map.addControl(myZoomCtrl);
  doneDraw = 10;  
  polyline.enableEditing();
});

</script>
</html>