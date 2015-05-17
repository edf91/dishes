// 统计js
var nearDay = 10;
function toShow(tagA){
	var date = $("#datepicker").val();
	var selectDate = new Date(date);
	var currentTime = new Date();
	var currentDate = new Date(currentTime.getFullYear() + "-" + (currentTime.getMonth() + 1) + "-" + currentTime.getDate());
	
	var delTime = currentDate - selectDate;
	if(delTime < 0) {
		dialog({title:"注意",content:"日期不能超过当前日期"}).showModal();
		return ;
	}
	// 计算离当前的天数
	var nearDay = 1;
	while(currentDate > selectDate){
		currentDate -= 86400000;
		nearDay ++;
	}
	showStatics(nearDay);
}
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

var showStatics = function(day){
	$("#dayText").text("近"+day+"日销售额曲线统计");
	var obj = {};
	obj.time = new Array();
	obj.num = new Array();
	$.post("/statis/nearDayStatis",{nearDay:day},function(data){
		var statis = data.data;
		obj.nearDay = statis.nearDay;
		for(var i = 0;i < statis.nearDay; i++){
			var date = new Date(statis.time[i + 1]);
			obj.time.push(( date.getMonth() + 1) +"月"+date.getDate()+"日");
			obj.num.push(statis.salesCount[i]);
		}
		initChar(obj);
	});
}
$(function () {
	showStatics(nearDay);
});