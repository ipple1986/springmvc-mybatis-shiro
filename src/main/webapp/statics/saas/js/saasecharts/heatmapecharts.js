/**
 * @author neo 
 * @since  2016/7/18
 * 热力图画图
 * mainMap 画图的区域 
 * heatData 画图的数据
 * heatData。x:x轴的坐标
 * heatDate。y:y轴的坐标
 * heatDate。z:热力图的偏移量用于控制颜色的渲染   
 * heatData.push([
            x,
            y,
            z,
        ]);
 * */
function heatmapecharts(mainMap,heatData){
    var myChart2 = echarts.init(mainMap);
    myChart2.hideLoading();
    option = {
        series : [

            {
                type : 'heatmap',
                data : heatData,
                hoverable : false,
                gradientColors: [{
                    offset: 0.4,
                    color: 'green'
                }, {
                    offset: 0.5,
                    color: 'yellow'
                }, {
                    offset: 0.8,
                    color: 'orange'
                }, {
                    offset: 1,
                    color: 'red'
                }],
                minAlpha: 0.2,
                valueScale: 1,
                opacity: 0.6,
                blurSize:15
            },
        ]
    };
    myChart2.setOption(option);
}    
    