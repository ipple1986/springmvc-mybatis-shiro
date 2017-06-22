/**
 * 饼图图表显示(动态添加标题列表和数值列表)
 * @param tittle	标题列表
 * @param value		数值列表
 * @returns option
 */
function showPieChart_option(tittle,value){
	var tittleArray = tittle.split(",");
	var valueArray = value.split(",");
	var option = {
	    title : {
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{b} : {c} ({d}%)"
	    },
	    //动态添加列
	    series : [ {
			type : 'pie',
			selectedMode: 'single',//单一选中模式(分离) 
			radius : '47%',
			center : [ '52%', '41%' ],
			data : (function() {
				var res = [];
				var len = tittleArray.length;
				while (len--) {
					res.push({
						name : tittleArray[len],
						value : valueArray[len]
					});
				}
				res[tittleArray.length - 1]['selected'] = 'true';//默认中分离
				return res;
			})()
		} ]   
	};
	return option;
}

/**
 * 饼图空心图表显示(动态添加标题列表和数值列表)
 * 
 * @param hollowTittle
 *            空心中的标题
 * @param tittle
 *            标题列表
 * @param value
 *            数值列表
 * @returns option
 */
function showPieChart2_option(hollowTittle,tittle,value){
	var	tittleArray = [];
	var valueArray = [];
	if(tittle.indexOf(',') >= 0){
		tittleArray = tittle.split(",");
		valueArray = value.split(",");
	}else{
		tittleArray = [tittle];
		valueArray = [value];
	}
	var hollowTittles = "";
	if(hollowTittle != '' && hollowTittle != null){
		hollowTittles = hollowTittle;
	}
	var option = {
		title : {
            text: hollowTittles,
            x: 'center',
            y: '34%'
	    },
	    tooltip: {
	        trigger: 'item',
	        formatter: "{b}: {c} ({d}%)"
	    },
		  //动态添加列
		    series : [ {
				type : 'pie',
				//radius ['50%'--空心, '75%'--大小]
				radius: ['35%', '50%'],
		        center: ['50%', '37%'],
	            avoidLabelOverlap: false,
				data : (function() {
					var res = [];
					var len = tittleArray.length;
					while (len--) {
						res.push({
							name : tittleArray[len],
							value : valueArray[len]
						});
					}
					return res;
				})()
			} ]   
	};
	return option;
}

/**
 * 报表【每组1个的柱状】
 * @param tittle 标题
 * @param value  值
 */
function showRankings_option(sumBig,tittle,value){
	var tittleArray = tittle.split(",");
	var valueArray = value.split(",");
	var option = {
	    color: ['#3398DB'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        },
	        formatter: function (params) {
	        	if (params !== null) {
	        		var res='<div><p>'+params[0].name+'</p></div>'; 
		        	for(var i=0;i<params.length;i++){
		        		res+='<p>'+params[i].seriesName+'：'+params[i].data+'</p>';
		        		res+='<p>占比：'+(params[i].data/sumBig * 100).toFixed(2) +'%</p>';
		        	}
		        	return res;
	        	}
	        }
	    },
	    grid: {
	        left: '6%',
	        right: '4%',
	        bottom: '30%',
	        top:'10',
	        containLabel: true,
	        textStyle:{
	        	fontSize : 12
	        }
	    },
	    xAxis : [
	        {
	            axisLabel :{  
	            	 interval:0,//横轴信息全部显示  
                     rotate:-35//45度角倾斜显示  
	            } , 
	            type : 'category',
	            data : [],
	            axisTick: {
	                alignWithLabel: true
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'数量',
	            type:'bar',
	            barWidth: '60%',
	            data:[]
	        }
	    ],
	};
	
	//标题 
	if (tittleArray !== null && tittleArray !== '') {
		option.xAxis[0].data = tittleArray;
	}
	//数量 
	if (valueArray !== null && valueArray !== '') {
		option.series[0].data = valueArray;
	}
	return option;
}






/**
 * 报表【每组2个的柱状，1个折线】
 * @param xArray x轴数组
 * @param yArray1 y轴数组1
 * @param yArray2 y轴数组2
 * @param title 报表标题
 * @param legend1 查询1 名称
 * @param legend2 查询2 名称
 * @param unit1 单位1
 * @param unit2 单位2
 * @return option
 */
function showRPT_option(xArray, yArray1, yArray2,title, legend1, legend2, unit1, unit2){
	var option = {
		title : {
			text : title
		},
		tooltip : {
	        trigger : 'axis',
	        axisPointer : {
	            type : 'line'
	        },
	        formatter: function (params) {
	        	var s = params[0].name;
	        	for (var i = 0; i < params.length; i++) {
	        		var tar = params[i];
	        		
	        		if (tar.seriesName == legend1) {
	        			s = s + '<br/>' + tar.seriesName + '：' + tar.value + unit1;
	        		}
	        		else if (tar.seriesName == legend2) {
	        			s = s + '<br/>' + tar.seriesName + '：' + tar.value + unit2;
	        		}
	        	}
	        	
	        	return s;
	        }
	    },
	    legend: {
	        data : [legend1, legend2],
	        //标题右对齐
	        //x : 'right'
	        left:'850px'
	    },
	    color : [
	        '#ff5400', '#ffddcc'
	    ],
	    textStyle : {
	    	color : '#555'
	    },
	    grid : {
	    	left : '6%',
	    	top : '20%',
	    	right : '5%',
	    	bottom : '16%'
	    },
	    xAxis : [
	        {
	        	axisLabel :{  
	            	interval:0,//横轴信息全部显示  
                    rotate:-35//45度角倾斜显示  
	            } , 
	            type : 'category',
	            splitLine : false,
	            axisTick : {
	                show : false
	            },
	            data : []
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            splitLine : false,
	            axisLabel : {
	                formatter : '{value}'
	            }
	        }
	    ],
	    series : [
	        {
	            name : legend1,
	            right:'right',
	            type : 'bar',
	            barWidth : 15,
	            barCategoryGap : '30%',
	            itemStyle : {
	            	normal : {
	            		barBorderColor : '#ff5400',
	            		barBorderWidth : 1,
	            		barBorderRadius : [5, 5, 0, 0]
	            	}
	            },
	            data : []
	        },
	        {
	            name : legend2,
	            right:'right',
	            type : 'bar',
	            stack : '叠加',
	            barWidth : 15,
	            barCategoryGap : '30%',
	            itemStyle : {
	            	normal : {
	            		barBorderColor : '#ffddcc',
	            		barBorderWidth : 1,
	            		barBorderRadius : [5, 5, 0, 0]
	            	}
	            },
	            data : []
	        }
	    ]
	};
	
	/* x轴赋值 */
	if (xArray !== null && xArray !== '') {
		option.xAxis[0].data = xArray;
	}
	
	/* y轴1赋值 */
	if (yArray1 !== null && yArray1 !== '') {
		option.series[0].data = yArray1;
	}
	
	/* y轴2赋值 */
	if (yArray2 !== null && yArray2 !== '') {
		option.series[1].data = yArray2;
	}
	
	
	/* y轴数组为空，不显示提示框组件 */
	if ((yArray1 === null || yArray1 === '' || yArray1.length === 0) && 
	    (yArray2 === null || yArray2 === '' || yArray2.length === 0)) {
		option.tooltip.show = false;
	}
	return option;
}
