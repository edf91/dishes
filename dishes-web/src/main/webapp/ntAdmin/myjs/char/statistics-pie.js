// 最受欢迎统计
var topNum = 5;
var initPie = function(obj){
	Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function(color) {
	    return {
	        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
	        stops: [
	            [0, color],
	            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
	        ]
	    };
	});
	// Build the chart
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '最热销食物TOP'+obj.topNum+'销量百分比'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    },
                    connectorColor: 'silver'
                }
            }
        },
        series: [{
            type: 'pie',
            name: obj.name,
            data: obj.results
        }]
    });
    $(".highcharts-button").remove();
	$(".highcharts-tooltip").next().remove();
}
$(function () {
	$.post("/statis/getTopDish",{topNum:topNum},function(data){
		console.log(data);
		var obj = {};
		obj.topNum = topNum;
		obj.results = new Array();
		var callBack = data.data;
		for(var i = 0; callBack[i]; i++){
			var dishTop = callBack[i];
			var dish = new Array();
			dish.push(dishTop.dishType + "--" + dishTop.dishName + "--" + dishTop.num);
			dish.push(dishTop.scale);
			obj.results.push(dish);
			obj.name = '总量'+dishTop.sum+'##占分比';
		}
		initPie(obj);
	});
	
});
