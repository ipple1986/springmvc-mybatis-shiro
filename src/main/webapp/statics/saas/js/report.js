/**
 * 报表【每组2个的柱状，1个折线】
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
function showRPT_GLBB_option_web(xArray, yArray1, yArray2, yArray3, title, legend1, legend2, legend3, unit1, unit2, unit3){
	var option = {
		title : {
			text : title
		},
		toolbox: {
	        feature: {
	            saveAsImage: {
	                pixelRatio: 2
	            }
	        }
	    },
		tooltip : {
	        trigger : 'axis',
	        axisPointer : {
	            type : 'shadow'
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
	    	top : '10px',
	        /*right : legendRight,
	        bottom : legendBottom,
	        left : legendLeft,*/
	        data : [legend1, legend2, legend3]
	    },
	    color : [
	        '#ff5400', '#9bbb59', '#fac090'
	    ],
	    textStyle : {
	    	color : '#555'
	    },
	    grid : {
	    	left : '100px',
	    	top : '20%',
	    	right : '100px',
	    	bottom : '35%'
	    },
	    xAxis : [
	        {
	            type : 'category',
	            splitLine : false,
	            axisTick : {
	                show : false
	            },
	            axisLabel : {
	            	interval : 0,
	            	rotate : -35
	            },
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
	            /*stack : '叠加',*/
	            barWidth : 15,
	            barCategoryGap : '30%',
	            itemStyle : {
	            	normal : {
	            		barBorderColor : '#e46c0a',
	            		barBorderWidth : 1,
	            		barBorderRadius : [5, 5, 0, 0]
	            	}
	            },
	            data : []
	        },
	        {
	            name : legend2,
	            type : 'bar',
	            /*stack : '叠加',*/
	            barWidth : 15,
	            barCategoryGap : '30%',
	            itemStyle : {
	            	normal : {
	            		barBorderColor : '#9bbb59',
	            		barBorderWidth : 1,
	            		barBorderRadius : [5, 5, 0, 0]
	            	}
	            },
	            data : []
	        },
	        {
	            name : legend3,
	            type : 'line',
	            yAxisIndex : 1,
	            symbol: 'circle',
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
		console.log(yArray3);
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












/**
 * 报表【每组2个的柱状，1个折线】
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
function showRPT_GLBB_option(xArray, yArray1, yArray2, yArray3, title, legend1, legend2, legend3, unit1, unit2, unit3){
	var option = {
		title : {
			text : title
		},
		tooltip : {
	        trigger : 'axis',
	        axisPointer : {
	            type : 'shadow'
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
	    	top : 'bottom',
	        /*right : legendRight,
	        bottom : legendBottom,
	        left : legendLeft,*/
	        data : [legend1, legend2, legend3]
	    },
	    color : [
	        '#e46c0a', '#9bbb59', '#fac090'
	    ],
	    textStyle : {
	    	color : '#555'
	    },
	    grid : {
	    	left : '12%',
	    	top : '20%',
	    	right : '12%',
	    	bottom : '35%'
	    },
	    xAxis : [
	        {
	            type : 'category',
	            splitLine : false,
	            axisTick : {
	                show : false
	            },
	            axisLabel : {
	            	interval : 0,
	            	rotate : 45
	            },
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
	            /*stack : '叠加',*/
	            barWidth : 15,
	            barCategoryGap : '30%',
	            itemStyle : {
	            	normal : {
	            		barBorderColor : '#e46c0a',
	            		barBorderWidth : 1,
	            		barBorderRadius : [5, 5, 0, 0]
	            	}
	            },
	            data : []
	        },
	        {
	            name : legend2,
	            type : 'bar',
	            /*stack : '叠加',*/
	            barWidth : 15,
	            barCategoryGap : '30%',
	            itemStyle : {
	            	normal : {
	            		barBorderColor : '#9bbb59',
	            		barBorderWidth : 1,
	            		barBorderRadius : [5, 5, 0, 0]
	            	}
	            },
	            data : []
	        },
	        {
	            name : legend3,
	            type : 'line',
	            yAxisIndex : 1,
	            symbol: 'circle',
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
		console.log(yArray3);
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


/**
 * 报表【每组2个叠加的柱状，1个折线】
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
function showRPT_BBL_option(xArray, yArray1, yArray2, yArray3, title, legend1, legend2, legend3, unit1, unit2, unit3){
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
	        '#ff5400', '#ffddcc', '#fe453b'
	    ],
	    textStyle : {
	    	color : '#555'
	    },
	    grid : {
	    	left : '6%',
	    	top : '20%',
	    	right : '5%',
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
	            stack : '叠加',
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

/**
 * 报表【只有2个柱状，y轴查询的是同一种数据，不带坐标系、图例组件】
 * @param xString1 x轴 数值1
 * @param xString2 x轴 数值2
 * @param yString1 y轴 数值1
 * @param yString2 y轴 数值2
 * @param legend 查询 名称
 * @param unit 单位
 * @param maxValue y轴 最大值
 * @return option
 */
function showRPT_BBONLY_option(xString1, xString2, yString1, yString2, legend, unit, maxValue){
	var option = {
		tooltip : {
			show : false,
	        trigger : 'axis',
	        position : ['5%', '5%'],
	        axisPointer : {
	            type : 'line'
	        }
	    },
	    legend: {
	    	show : false,
	        data : [legend]
	    },
	    grid : {
	    	left : '20%',
	    	top : '10%',
	    	right : '20%',
	    	bottom : '13%'
	    },
	    xAxis : [
			{
			    type : 'category',
			    axisLine : {
			    	show : false
			    },
			    axisTick : {
	                show : false
	            },
			    splitLine : false,
			    data : [
			        {
			            value : '',
			            textStyle : {
			                color : '#555'
			            }
			        },
			        {
			            value : '',
			            textStyle : {
			                color : '#ff5400'
			            }
			        }
			    ]
			}
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLine : {
			    	show : false
			    },
			    axisTick : {
	                show : false
	            },
	            axisLabel : {
	                show : false
	            },
			    splitLine : false,
			    max : 'auto',
			    splitNumber : 5
	        }
	    ],
	    series : [
	        {
	        	name : legend,
	            type : 'bar',
	            barWidth : 15,
	            barCategoryGap : '30%',
	            itemStyle : { 
	            	normal : {
	            		label : {
	            			show : true, 
	            			position: 'top'
	            		}
	            	}
	            },
	            data : [
	                {
	                    value : '',
	                    label : {
	                        normal : { 
	                        	textStyle : {
	                        		color: '#555'
	                        	}
	                        }
	                    },
	                    itemStyle : {
	                        normal: {
	                        	color: '#cfcccc',
	                        	barBorderColor : '#cfcccc',
	    	            		barBorderWidth : 1,
	    	            		barBorderRadius : [5, 5, 0, 0]
	                        }
	                    }
	                },
	                {
	                    value : '',
	                    label : {
	                        normal : { 
	                        	textStyle : {
	                        		color : '#ff5400'
	                        	}
	                        }
	                    },
	                    itemStyle : {
	                        normal: {
	                        	color: '#ff5400',
	                        	barBorderColor : '#ff5400',
	    	            		barBorderWidth : 1,
	    	            		barBorderRadius : [5, 5, 0, 0]
	                        }
	                    }
	                }
	            ]
	        }
	    ]
	};
	
	//y轴 最大值
	if (maxValue != null && maxValue != "") {
		option.yAxis[0].max = maxValue;
	}
	
	//x轴 数值1
	if (xString1 != null) {
		option.xAxis[0].data[0].value = xString1;
	}
	
	//x轴 数值2
	if (xString2 != null) {
		option.xAxis[0].data[1].value = xString2;
	}
	
	//y轴 数值1
	if (yString1 != null) {
		option.series[0].data[0].value = yString1;
	}
	
	//y轴 数值2
	if (yString2 != null) {
		option.series[0].data[1].value = yString2;
	}
	
	/* y轴数值为空，不显示提示框组件 */
	if ((yString1 === null || yString1 === '') && 
	    (yString2 === null || yString2 === '')) {
		option.tooltip.show = false;
	}
	
	return option;
}

/**
 * 报表【每组1个柱状】
 * @param xArray x轴数组
 * @param yArray1 y轴数组1
 * @param title 报表标题
 * @param legend1 查询1 名称
 * @param unit1 单位1
 * @return option
 */
function showRPT_B_option(xArray, yArray1, title, legend1, unit1){
	
	console.log(yArray1);
	
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
	        	}
	        	
	        	return s;
	        }
	    },
	    legend: {
	    	top : 'bottom',
	        right : '',
	        bottom : '0px',
	        left : '50px',
	        orient : 'horizontal',
	        data : [legend1]
	    },
	    color : [
	        '#fc6900'
	    ],
	    textStyle : {
	    	color : '#555'
	    },
	    grid : {
	    	left : '50px',
	    	top : '10%',
	    	right : '50px',
	    	bottom : '70px'
	    },
	    xAxis : [
	        {
	            type : 'category',
	            splitLine : false,
	            axisTick : {
	                show : false
	            },
	            axisLabel : {
	            	interval : 0,
	            	rotate : 45
	            },
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
	        }
	    ],
	    series : [
	        {
	            name : legend1,
	            type : 'bar',
	            barWidth : 15,
	            barCategoryGap : '30%',
	            itemStyle : {
	            	normal : {
	            		barBorderColor : '#fc6900',
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
	
	/* y轴数组为空，不显示提示框组件 */
	if ((yArray1 === null || yArray1 === '' || yArray1.length === 0)) {
		option.tooltip.show = false;
	}
	
	
	
	return option;
}


/**
 * 报表【每组2个叠加的柱状，1个折线】
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
function showRPT_BBLRGQY_option(xArray, yArray1, yArray2,title, legend1, legend2, unit1, unit2){
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
	        left:'850px',
	        top:'10px'
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
	        }
	    ],
	    series : [
	        {
	            name : legend1,
	            type : 'bar',
	            //stack : '叠加',
	            barWidth : 10,
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
	            type : 'bar',
	            //stack : '叠加',
	            barWidth : 10,
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