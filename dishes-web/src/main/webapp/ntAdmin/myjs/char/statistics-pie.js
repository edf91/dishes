// 最受欢迎统计
var topNum = 5;
var initPie = function(obj){
	var timestamp = Date.parse(new Date());
	timestamp += "a";
	$(".box-content").html('<div id="'+timestamp+'" style="min-width: 310px; height: 400px; margin: 0 auto"></div>');
	$("#topNums").text("最热销TOP"+obj.topNum+"饼图");
	// Build the chart
    $('#'+timestamp).highcharts({
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
function toShow(tagA){
	topNum = $("#showNum").val();
	loadStatic(topNum);
}
var loadStatic = function(topNum){
	$.post("/statis/getTopDish",{topNum:topNum},function(data){
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
}
$(function () {
	Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function(color) {
	    return {
	        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
	        stops: [
	            [0, color],
	            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
	        ]
	    };
	});
	loadStatic(topNum);
});
