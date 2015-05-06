// 统计js
var nearDay = 10;
var initChar = function(obj){
	$('#container').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: '近'+obj.nearDay+'日销售额'
        },
        subtitle: {
            text: ' '
        },
        xAxis: {
            categories: obj.time
        },
        yAxis: {
            title: {
                text: '销售额 (￥)'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        series: [ {
            name: '日销售额',
            data: obj.num
        }]
    });
	$(".highcharts-button").remove();
	$(".highcharts-tooltip").next().remove();
}
$(function () {
	var obj = {};
	obj.time = new Array();
	obj.num = new Array();
	$.post("/statis/nearDayStatis",{nearDay:nearDay},function(data){
		var statis = data.data;
		obj.nearDay = statis.nearDay;
		for(var i = 0;i < statis.nearDay; i++){
			var date = new Date(statis.time[i + 1]);
			obj.time.push(( date.getMonth() + 1) +"月"+date.getDate()+"日");
			obj.num.push(statis.salesCount[i]);
		}
		initChar(obj);
	});

	
});