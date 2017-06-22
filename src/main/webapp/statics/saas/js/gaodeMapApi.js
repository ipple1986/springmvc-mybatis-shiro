var labelCount = 0;
var markerStyle = "";

/* 设置置顶 */
function setTopPer(obj) {
	$obj = $(obj);
	
	markerStyle = $obj.parent().parent().attr("style");
	
	$obj.parent().parent().css("z-index", "100000");
}

/* 取消置顶 */
function setBottomPer(obj) {
	$obj = $(obj);
	
	$obj.parent().parent().attr("style", markerStyle);
	
	markerStyle = "";
}

/* 初始化高德地图 */
function initGaoDeMap(map) {
	map.setStatus({
		zoom: 10,				//设置地图级别
		resizeEnable: true,		//监控地图容器尺寸变化
        isHotspot: false,		//关闭底图可点功能
        scrollWheel: true		//开启鼠标滚轮缩放
	});
	
	/* 添加工具条 */
	var pixel = new AMap.Pixel(30, 20);
	map.plugin(["AMap.ToolBar"], function() {
	    var toolBar = new AMap.ToolBar({
	    	offset: pixel,		//偏移
	    	position: 'RB',		//靠右下角位置
	    	locate: false		//禁用显示定位
	    });
	    
	    map.addControl(toolBar);
	});
	
	/* 添加比例尺 */
	var pixel2 = new AMap.Pixel(90, 25);
	map.plugin(["AMap.Scale"], function() {
	    var scale = new AMap.Scale({
	    	offset: pixel2,		//偏移
	    	position: 'RB'		//靠右下角位置
	    });
	    
	    map.addControl(scale);
	});
}

/* 添加点    map：map实例，point：坐标，
 *       url：图标路径， iconWidth：图标Width，iconHeight：图标Height，markerX：标注X偏移，markerY：标注Y偏移，
 *       showLabelTOF：是否显示标签，
 *       labelContent：标签内容， backgroundUrl：标签背景图，fontColor：文字颜色， borderStyle：边框样式，labelWidth：标签宽度，labelHeight：标签高度，lineHeight：每行高度，labelX：标签X偏移， labelY：标签Y偏移，
 *       clickVar：点击事件参数 */
function addMarker(map, point, url, iconWidth, iconHeight, markerX, markerY, showLabelTOF, labelContent, backgroundUrl, fontColor, borderStyle, labelWidth, labelHeight, lineHeight, labelX, labelY, clickVar) {
	var marker = null;
	var label = null;
	
	labelCount = eval(labelCount + 1);
	
	if (url != null && url != "") {
		if (iconWidth == null || iconWidth == "") {
			iconWidth = 30;
		}
		
		if (iconHeight == null || iconHeight == "") {
			iconHeight = 30;
		}
		
		var myIcon = new AMap.Icon({
			image: url,
			size: new AMap.Size(iconWidth, iconHeight)
		});
		
		marker = new AMap.Marker({
			icon: myIcon,
			position: point,
			offset: new AMap.Pixel(markerX, markerY)
		});
	}
	else {
		marker = new AMap.Marker({
			icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
			position: point
		});
	}
	
	if (showLabelTOF) {
		if (backgroundUrl != null && backgroundUrl != "") {
			var divContent = '<div id="label_' + labelCount + '" style="color: ' + fontColor + '; '
						   + 'font-size: 11px; '
						   + 'width: ' + labelWidth + '; '
						   + 'height: ' + labelHeight + '; '
						   + 'line-height: ' + lineHeight + '; '
						   + 'font-family: 微软雅黑; ' 
						   + 'text-align: center; ' 
						   + 'border: 0px; '
						   + 'background: url(' + backgroundUrl + ') no-repeat; '
						   + 'padding-top: 4px" '
						   + 'onMouseover="setTopPer(this)" onMouseout="setBottomPer(this)">'
						   + labelContent
						   + '</div>';
			
			marker.setLabel({
				offset: new AMap.Pixel(labelX, labelY),
				content: divContent
			});
		}
		else {
			var divContent = '<div id="label_' + labelCount + '" style="color: ' + fontColor + '; '
			   			   + 'border: ' + borderStyle + '; '
			   			   + 'font-size: 11px; '
			   			   + 'height: ' + labelHeight + '; '
			   			   + 'line-height: ' + lineHeight + '; '
			   			   + 'font-family: 微软雅黑; '
			   			   + 'text-align: center; '
			   			   + 'border-radius: 5px; '
			   			   + 'background-color: white; '
			   			   + 'padding: 4px" '
			   			   + 'onMouseover="setTopPer(this)" onMouseout="setBottomPer(this)">'
			   			   + labelContent
			   			   + '</div>';
			
			marker.setLabel({
				offset: new AMap.Pixel(labelX, labelY),
				content: divContent
			});
		}
	}
	
	if (clickVar != null && clickVar != "") {
		marker.on("click", function() {
			$(".mapInfo02").empty();
			$(".mapInfo02").append(clickVar);
			
			var iconObj = marker.getIcon();
			
			var returnTOF = 0;
			
			for (var i in iconObj) {
				if (typeof(iconObj[i]) == "object") {
					var iconObj_i = iconObj[i];
					
					for (var j in iconObj_i) {
						if (j == "image") {
							var imageUrl = iconObj_i[j];
							
							if ($(".saas-link01.pljc").length > 0 && imageUrl.indexOf("jkpt_rw02.png") > 0) {
								$(".saas-link01.pljc").attr("onclick", "statusRemoveConfirm()");
								$(".saas-link01.pljc").html("批量解除");
								
								currentMarker = marker;
							}
							
							returnTOF = 1;
							
							break;
						}
					}
				}
				
				if (returnTOF == 1) {
					break;
				}
			}
			
			/* 显示监控信息 */
			showMonitor();
		});
	}
	
	marker.on("mouseover", function() {
		marker.setTop(true);
	});
	
	marker.on("mouseout", function() {
		marker.setTop(false);
	});

	marker.setDraggable(true);	//设置点可拖拽
	
	marker.setExtData({
		id: labelCount
	});
	
	marker.setMap(map);
	
	return marker;
}

/* 添加圆    map：map实例，point：坐标，ranges：半径(米)，
 * 		 color：边框颜色，strokeWeight：圆形边线的宽度(以像素为单位)，fillOpacity：圆形填充的透明度(0-1) */
function addCircle(map, point, ranges, color, strokeWeight, fillOpacity) {
	var circle = new AMap.Circle({
		center: point,
		radius: ranges,
		strokeColor: color,
		strokeWeight: strokeWeight,
		fillOpacity: fillOpacity
	});
	
	circle.setMap(map);
}

/* 添加折线   map：map实例，pointArray：坐标数组，
 *        strokeColor：线的颜色，strokeWeight：线的宽度(以像素为单位)，strokeOpacity：透明度(0-1) */
function addPolyline(map, pointArray, strokeColor, strokeWeight, strokeOpacity) {
	var polyline = new AMap.Polyline({
		path: pointArray,
		strokeColor: strokeColor,
		strokeWeight: strokeWeight,
		strokeOpacity: strokeOpacity
	});
	
	polyline.setMap(map);
	
	return polyline;
}
