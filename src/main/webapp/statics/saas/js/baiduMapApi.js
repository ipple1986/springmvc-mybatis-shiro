/**
 * 百度地图公用方法
 */
/**
 * 地图变量
 */
var mp;
/**
 * 地图中心点
 */
var point;
/**
 * 初始化地图中心
 */
function initPoint(longitude,latitude) {
	point = new BMap.Point(longitude,latitude);
}
/**
 * 初始化地图 并显示在div id="allmap" 下
 * @param longitude
 * @param latitude
 * @param level
 */
function initMap(longitude,latitude,level) {
	mp = new BMap.Map("allmap"); // 创建Map实例
	initPoint(longitude,latitude);
	mp.centerAndZoom(point,level);// 初始化地图,设置中心点坐标和地图级别
	mp.setCurrentCity("广州市");// 设置地图显示的城市 此项是必须设置的
	mp.enableScrollWheelZoom(true);//开启鼠标滚轮缩放
}


function alert1(){
	alert("test");
}
/*
 * 
"shuju":
[
  {  "zuming":"1","renyuan": [{"name","x"},{"operID":"11"},{"tuoke","111"},{"paidan","1111"}]},
  {   "zuming":"2","renyuan": [{"name","x"},{"operID":"22"},{"tuoke","222"},{"paidan","2222"}]},
  {  "zuming":"3","renyuan": [{"name","x"},{"operID":"33"},{"tuoke","333"},{"paidan","3333"}]}
]
 */

/**
 * 
 * @param point 显示位置
 * @param text 显示文字
 * @param png 背景图
 * @param style 显示样式1：显示底部头像。2：不显示底部头像
 */
function ComplexCustomOverlay(point, text, png,style){
  this._point = point;
  this._text = text;
  this._png= png;
}
ComplexCustomOverlay.prototype = new BMap.Overlay();
ComplexCustomOverlay.prototype.initialize = function(map){
	
  this._map = map;
  var div = this._div = document.createElement("div");
  div.style.position = "absolute";
  div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
  div.style.backgroundColor = "#EE5D5B";
  div.style.border = "1px solid #BC3B3A";
  div.style.color = "white";
  div.style.height = "18px";
  div.style.padding = "2px";
  div.style.lineHeight = "18px";
  div.style.whiteSpace = "nowrap";
  div.style.MozUserSelect = "none";
  div.style.fontSize = "12px"
  var span = this._span = document.createElement("span");
  div.appendChild(span);
  span.appendChild(document.createTextNode(this._text));      
  var that = this;

  var arrow = this._arrow = document.createElement("div");
  arrow.style.background = 'url('+this._png+ ') no-repeat';
  arrow.style.position = "absolute";
  arrow.style.width = "80px";
  arrow.style.height = "110px";
  arrow.style.top = "22px";
  arrow.style.left = "10px";
  arrow.style.overflow = "hidden";
  div.appendChild(arrow);
 

 
  mp.getPanes().labelPane.appendChild(div);
  
  return div;
}
ComplexCustomOverlay.prototype.draw = function(){
  var map = this._map;
  var pixel = map.pointToOverlayPixel(this._point);
  this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
  this._div.style.top  = pixel.y - 30 + "px";
}
/**
 * 新增覆盖物
 * @param point
 * @param text
 * @param png
 * @param style
 */
function addOverlay(point,text,png,style) {
    var myCompOverlay = new ComplexCustomOverlay(point, text,png,'');

    mp.addOverlay(myCompOverlay);
}

/* 初始化百度地图 */
function initBaiDuMap(map) {
	var point = new BMap.Point(116.331398,39.897445);			//定义一个中心点坐标
	map.centerAndZoom(point, 12);  								//初始化地图,设置中心点坐标和地图级别
	map.enableScrollWheelZoom(true);     						//开启鼠标滚轮缩放
	
	/*var myCity = new BMap.LocalCity();
	myCity.get(myFun);											//IP定位
	
	function myFun(result) {
		var cityName = result.name;
		map.setCenter(cityName);
	}*/
	
	var size = new BMap.Size(0, 20);
	/* 添加带有定位的导航控件 */
	var navigationControl = new BMap.NavigationControl({
	    anchor: BMAP_ANCHOR_BOTTOM_RIGHT,						//靠右下角位置
	    offset: size,											//偏移
	    type: BMAP_NAVIGATION_CONTROL_LARGE,					//LARGE类型
	    enableGeolocation: false								//禁用显示定位
	});
	
	var size2 = new BMap.Size(80, 20);
	
	/* 添加比例尺 */
	var scaleControl = new BMap.ScaleControl({
		anchor: BMAP_ANCHOR_BOTTOM_RIGHT,						//靠右下角位置
		offset: size2											//偏移
	});
	
	map.addControl(navigationControl);
	map.addControl(scaleControl);
}

/* 添加点    map：map实例，point：坐标，
 *       url：图标路径， iconWidth：图标Width，iconHeight：图标Height，
 *       showLabelTOF：是否显示标签，
 *       labelContent：标签内容， backgroundUrl：标签背景图，fontColor：文字颜色， borderStyle：边框样式，labelWidth：标签宽度，labelHeight：标签高度，lineHeight：每行高度，labelX：标签X偏移， labelY：标签Y偏移，
 *       clickVar：点击事件参数 */
function addMarker(map, point, url, iconWidth, iconHeight, showLabelTOF, labelContent, backgroundUrl, fontColor, borderStyle, labelWidth, labelHeight, lineHeight, labelX, labelY, clickVar) {
	var marker = null;
	var label = null;
	
	if (url != null && url != "") {
		if (iconWidth == null || iconWidth == "") {
			iconWidth = 30;
		}
		
		if (iconHeight == null || iconHeight == "") {
			iconHeight = 30;
		}
		
		var myIcon = new BMap.Icon(url, new BMap.Size(iconWidth, iconHeight));
		
		marker = new BMap.Marker(point, {icon: myIcon});
	}
	else {
		marker = new BMap.Marker(point);
	}
	
	if (showLabelTOF) {
		label = new BMap.Label(labelContent, {offset: new BMap.Size(labelX, labelY)});
		
		if (backgroundUrl != null && backgroundUrl != "") {
			label.setStyle({
				 color: fontColor,
				 fontSize: "11px",
				 width: labelWidth,
				 height: labelHeight,
				 fontFamily: "微软雅黑",
				 textAlign: "center",
				 border: "0px",
				 background: "url(" + backgroundUrl + ") no-repeat",
				 cursor: "pointer"
			});
		}
		else {
			label.setStyle({
				 color: fontColor,
				 border: borderStyle,
				 fontSize: "11px",
				 height: labelHeight,
				 lineHeight: lineHeight,
				 fontFamily: "微软雅黑",
				 textAlign: "center",
				 borderRadius: "5px",
				 padding: "4px"
			});
		}
	}
	
	if (label != null) {
		marker.setLabel(label);
	}
	
	if (clickVar != null && clickVar != "") {
		marker.addEventListener("click", function() {
			$(".mapInfo02").empty();
			$(".mapInfo02").append(clickVar);
			
			var imageUrl = marker.getIcon().imageUrl;
			
			if ($(".saas-link01.pljc").length > 0 && imageUrl.indexOf("jkpt_rw02.png") > 0) {
				$(".saas-link01.pljc").attr("onclick", "statusRemoveConfirm()");
				$(".saas-link01.pljc").html("批量解除");
				
				currentMarker = marker;
			}
			
			/* 显示监控信息 */
			showMonitor();
		});
		
		if (label != null) {
			label.addEventListener("click", function() {
				$(".mapInfo02").empty();
				$(".mapInfo02").append(clickVar);
				
				var imageUrl = marker.getIcon().imageUrl;
				
				if ($(".saas-link01.pljc").length > 0 && imageUrl.indexOf("jkpt_rw02.png") > 0) {
					$(".saas-link01.pljc").attr("onclick", "statusRemoveConfirm()");
					$(".saas-link01.pljc").html("批量解除");
					
					currentMarker = marker;
				}
				
				/* 显示监控信息 */
				showMonitor();
			});
		}
	}
	
	marker.addEventListener("mouseover", function() {
		marker.setTop(true);
	});
	
	marker.addEventListener("mouseout", function() {
		marker.setTop(false);
	});
	
	if (label != null) {
		label.addEventListener("mouseover", function() {
			marker.setTop(true);
		});
		
		label.addEventListener("mouseout", function() {
			marker.setTop(false);
		});
	}

	marker.enableDragging();	//设置点可拖拽
	
	map.addOverlay(marker);
	
	return marker;
}

/* 添加圆    map：map实例，point：坐标，ranges：半径(米)，
 * 		 color：边框颜色，strokeWeight：圆形边线的宽度(以像素为单位)，fillOpacity：圆形填充的透明度(0-1) */
function addCircle(map, point, ranges, color, strokeWeight, fillOpacity) {
	var circle = new BMap.Circle(point, ranges, {strokeColor: color, strokeWeight: strokeWeight, fillOpacity: fillOpacity});
	map.addOverlay(circle);
}

/* 添加折线   map：map实例，pointArray：坐标数组，
 *        strokeColor：线的颜色，strokeWeight：线的宽度(以像素为单位)，strokeOpacity：透明度(0-1) */
function addPolyline(map, pointArray, strokeColor, strokeWeight, strokeOpacity) {
	var polyline = new BMap.Polyline(pointArray, {strokeColor: strokeColor, strokeWeight: strokeWeight, strokeOpacity: strokeOpacity});  
	map.addOverlay(polyline);
	
	return polyline;
}
