/**
 * 报表【每组1个柱状，2个折线】
 * @param xArray x轴数组
 * @param yArray1 y轴数组1
 * @param yArray2 y轴数组2
 * @param yArray3 y轴数组3
 * @param title 报表标题
 * @param legend1 查询1 名称
 * @param legend2 查询2 名称
 * @param legend3 查询3 名称
 * @param unit1 单位1
 * @param unit2 单位2
 * @param unit3 单位3
 * @return option
 */
function showRPT_CALL_option(xArray, yArray1, yArray2, yArray3, title, legend1, legend2, legend3, unit1, unit2, unit3){
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
	        		else if (tar.seriesName == legend3) {
	        			s = s + '<br/>' + tar.seriesName + '：' + tar.value + unit3;
	        		}
	        	}
	        	
	        	return s;
	        }
	    },
	    legend: {
	        data : [legend1, legend2, legend3]
	    },
	    color : [
	        '#ff5400', '#0bbee7', '#fc6866'
	    ],
	    textStyle : {
	    	color : '#555'
	    },
	    grid : {
	    	left : '12%',
	    	top : '20%',
	    	right : '8%',
	    	bottom : '12%'
	    },
	    xAxis : [
	        {
	            type : 'category',
	            splitLine : false,
	            axisTick : {
	                show : false
	            },
	            /*axisLabel : {
	            	interval : 0,
	            	rotate : 45
	            },*/
	            data : []
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            /*interval : 20,*/
	            splitLine : false,
	            axisLabel : {
	                formatter : '{value}'
	            }
	        },
	        {
	            type : 'value',
	            /*interval : 20,*/
	            splitLine : false,
	            axisLabel : {
	                formatter : '{value}' + unit3
	            }
	        }
	    ],
	    series : [
	        {
	            name : legend1,
	            type : 'bar',
	            /*barWidth : '35',*/
	            barMaxWidth : '35',
	            barGap : '40%',
	            barCategoryGap : '40%',
	            itemStyle : {
	            	normal : {
	            		barBorderColor :'#ff5400',
	            		barBorderWidth : 1,
	            		barBorderRadius : [5, 5, 0, 0]
	            	}
	            },
	            data : []
	        },
	        {
	            name : legend2,
	            type : 'line',
	            yAxisIndex : 1,
	            data : []
	        },
	        {
	            name : legend3,
	            type : 'line',
	            yAxisIndex : 1,
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
	
	/* y轴3赋值 */
	if (yArray3 !== null && yArray3 !== '') {
		option.series[2].data = yArray3;
	}
	
	/* y轴数组为空，不显示提示框组件 */
	if ((yArray1 === null || yArray1 === '' || yArray1.length === 0) && 
	    (yArray2 === null || yArray2 === '' || yArray2.length === 0) && 
	    (yArray3 === null || yArray3 === '' || yArray3.length === 0)) {
		option.tooltip.show = false;
	}
	
	return option;
}