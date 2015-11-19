<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.js"></script>    
<title>Insert title here</title>
</head>
<body>
<div style="height: 500px;" id="canvas" ></div>
<script src="http://demo.qunee.com/lib/qunee-min.js"></script>
<script>
function translateToQuneeElements(json, graph){
	    var map = {};
	    if(json.nodes){
	        Q.forEach(json.nodes, function(data){
	            var node = graph.createNode(data.name, data.x || 0, data.y || 0);
	            node.set("data", data);
	            map[data.id] = node;
	        });
	    }
	    if(json.edges){
	        Q.forEach(json.edges, function(data){
	            var from = map[data.from];
	            var to = map[data.to];
	            if(!from || !to){
	                return;
	            }
	            var edge = graph.createEdge(data.name, from, to);
	            edge.set("data", data);
	        }, graph);
	    }
	}
var graph = new Q.Graph("canvas");
$.ajax({
	type: "POST",
	url: "getEdgeInfo",
	cache: false,
	async: true,
	dataType:"json",
	success: function onDataCollected(txt){
	    var json = txt;
	    translateToQuneeElements(json,graph);
	   /*  graph.moveToCenter(); */
	    /* var layouter = new Q.SpringLayouter(graph);
	   layouter.repulsion=50;
	    layouter.start();  */
	    var layouter = new Q.TreeLayouter(graph);
	    layouter.layoutType = Q.Consts.LAYOUT_TYPE_EVEN_HORIZONTAL;
	    layouter.doLayout({callback: function(){
	        graph.zoomToOverview();
	    }});
	}});
</script>
</body>
</html>