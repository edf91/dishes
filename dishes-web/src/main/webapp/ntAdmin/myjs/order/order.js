/**
 * 订单管理js脚本
 */
// 加载订单信息列表
var loadOrderInfo = function(){
	$("#orderTablesContent").html("");
	$.get("/ntAdmin/template/orderTemplate.html").done(function(data){
		$("#orderTablesContent").append(data);
		$(data).find("table:first").attr("id",new Date().getTime());
	})
	$.post("/order/list",{},function(data,status){
		if(!data.hasError){
			var orders = data.data;
			for(var i = 0;orders[i]; i++){
				var order = orders[i];
				var status = "已付款";
				var btns = "<a onclick=orderDetail('"+order.id+"') class='btn btn-info' href='#'><i class='glyphicon glyphicon-edit icon-white'></i>查看</a>";
				if(order.disabled){
					status = "未付款";
					btns += " <a onclick=pay('"+order.id+"','"+order.bePay+"') class='btn btn-info' href='#'><i class='glyphicon glyphicon-edit icon-white'></i>付款</a>";
				}
				var str = "<tr><td>"+ order.id +"</td><td>"+ order.board.name +"</td><td>"+ order.bePay +"</td><td>"+ order.realPay +"</td><td>"+ order.orderTime +"</td><td class='center'>"+status+"</td><td>"+ order.remark +"</td>"+
						  "<td>"+btns+"</td></tr>";
				$("tbody:first").append(str);
			}
			// 初始化表
			initDatables();
		}
	});
}

function orderDetail(orderId){
	$.post("/order/get",{orderId:orderId},function(data){
		if(!data.hasError){
    		var result = data.data;
    		var status = "已付款";
    		if(result.disabled) status = "未付款";
    		var contentValue = "单号为:" + result.id + "</br></br>状态为:" + status + "</br></br>餐桌号为:" + result.board.name + "</br></br>应付款为:" + result.bePay + "</br></br>";
    		var activeStr = "点的活动有:</br>";
    		for(var i = 0; result.activities[i]; i++){
    			var active = result.activities[i];
    			var dishStr = "";
    			for(var j = 0; active.dishNames[j]; j++){
    				dishStr += active.dishNames[j] + " ";
    			}
    			activeStr += active.name + " " + dishStr + " 价格:" + active.activitySumPrice + "￥</br>"
    		}
    		activeStr += "</br>";
    		contentValue += activeStr;
    		
    		var dishStr = "点的常规餐有:</br>";
    		for(var i = 0; result.dishes[i]; i++){
    			var dish = result.dishes[i];
    			dishStr += "类型:" + dish.dishTypeName + " " + dish.name + " 价格:" + dish.price + "￥</br>"
    		}
    		dishStr += "</br>";
    		contentValue += dishStr;
    		contentValue += "说明:" + result.remark;
    		dialog({
    		    title: '订单详情',
    		    content: contentValue,
    		    okValue: '确定',
    		    ok: function(){
    		    }
    		}).showModal();
    	}
	});
}
// 付款
function pay(orderId,bePay){
	var d = dialog({
	    title: '收款',
	    content: orderId+'应收'+bePay+',请输入实收款:<input id="order-realPay" value='+bePay+' autofocus/>',
	    okValue: '确定',
	    ok: function () {
	    	var that = this;
	    	var realPay = $("#order-realPay").val();
	    	$.post("/order/pay",{orderId:orderId,realPay:realPay},function(data){
	    		if(data.hasError){
	        		that.remove();
	        		dialog({
	        			title: '收款',
	        			content: data.errorMsg,
	        			cancelValue: '取消'}).showModal();
	        	}else{
	        		dialog({
	        			title: '收款',
	        			content: data.data,
	        			cancelValue: '取消'}).showModal();
	        		that.remove();
	        		loadOrderInfo();
	        	}
	    	});
	    },
	    cancelValue: '取消',
	    cancel: function () {}
	});
	d.showModal();
}
$(function(){
	loadOrderInfo();
});