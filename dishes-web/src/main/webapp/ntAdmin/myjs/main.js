/**
 * 选择餐桌，点菜js
 */
// 初始化表格
var isToInit = false;
// 选择的桌子id
var boardId = "";
// 订单id
var oldOrderId = "";
// 加载常规料理
var loadAllDish = function(){
	$(".normal").html("");
	$.get("/ntAdmin/template/normalDish.html").done(function(data){
		$(".normal").append(data);
		$(data).find(".normal table:first").attr("id",new Date().getTime());
	})
	$.post("/dish/list",{},function(data,status){
		if(!data.hasError){
			var dishes = data.data;
			for(var i = 0;dishes[i]; i++){
				var dish = dishes[i];
				var status = "正常";
				if(dish.activity) {
					status = "处于活动";
				}
				if(dish.disabled) status = "冻结";
				var str = "<tr><td class='center'>"+dish.name+"</td>"+
						  "<td class='center'>"+dish.price+"</td>"+
						  "<td class='center'>"+dish.dishTypeName+"</td>"+
						  "<td class='center'>"+status+"</td>"+
						  "<td class='center'>"+dish.activityPrice+"</td>"+
						  "<td class='center'>"+dish.description+"</td>"+
						  "<td><a activity-id='"+dish.id+"' href=javascript:selectActivity('"+dish.name+"','"+dish.price+"','"+dish.id+"',this,'add')><img alt='' src='img/icons/essen/16/plus.png'></a>&nbsp;&nbsp;<a  href=javascript:selectActivity('"+dish.name+"','"+dish.price+"','"+dish.id+"',this,'minus')><img alt='' src='img/icons/essen/16/busy.png'></a></td></tr>";
				$(".normal tbody:first").append(str);
			}
		}
		if(isToInit){
			initDatables();
		}else{
			isToInit = true;
		}
	});
}
// 加载今日活动
var loadTodayActivityInfo = function(){
	$(".todayActivity").html("");
	$.get("/ntAdmin/template/todayActivityTemplate.html").done(function(data){
		$(".todayActivity").append(data);
		$(data).find(".todayActivity table:first").attr("id",new Date().getTime());
	})
	$.post("/active/list",{},function(data,status){
		if(!data.hasError){
			var activities = data.data;
			console.log(activities);
			for(var i = 0;activities[i]; i++){
				var active = activities[i];
				if(active.disabled) continue;
				var str = "<tr><td class='center'>"+active.name+"</td>"+
						  "<td class='center'>"+active.dishNames+"</td>"+
						  "<td class='center'>"+active.originalSumPrice+"</td>"+
						  "<td class='center'>"+active.activitySumPrice+"</td>"+
						  "<td class='center'>"+active.description+"</td>"+
						  "<td><a activity-id='"+active.id+"' href=javascript:selectActivity('"+active.name+"','"+active.activitySumPrice+"','"+active.id+"',this,'add')><img alt='' src='img/icons/essen/16/plus.png'></a>&nbsp;&nbsp;<a  href=javascript:selectActivity('"+active.name+"','"+active.activitySumPrice+"','"+active.id+"',this,'minus')><img alt='' src='img/icons/essen/16/busy.png'></a></td></tr>";
				$("tbody:first").append(str);
			}
		}
		// 加载常规料理
		loadAllDish();
	});
}
// 提交订单处理
function subOrder(){
	var d = dialog({
	    title: '提交订单',
	    content: '请输入备注:<input id="order-remark" autofocus />',
	    okValue: '确定',
	    ok: function () {
	        this.title('提交中…');
	        var activitiesId = "";
	        var dishIds = "";
	        $(".active-detail").find("li").each(function(index,item){
	        	activitiesId += $(this).attr("beselect") + ",";
	        });
	        $(".normal-detail").find("li").each(function(index,item){
	        	dishIds += $(this).attr("beselect") + ",";
	        });
	        if(activitiesId.length) activitiesId = activitiesId.substring(0,activitiesId.length - 1);
	        if(dishIds.length) dishIds = dishIds.substring(0,dishIds.length - 1);
	        var orderMark = $("#order-remark").val();
	        $.post("/order/makeOrder",{boardId:boardId,activeIds:activitiesId,dishIds:dishIds,remark:orderMark,orderId:oldOrderId},function(data){
	        	if(!data.hasError){
	        		var result = data.data;
	        		var contentValue = "您的单号为:" + result.id + "</br></br>餐桌号为:" + result.board.name + "</br></br>应付款为:" + result.bePay + "</br></br>";
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
	        		    title: '下单成功',
	        		    content: contentValue,
	        		    okValue: '确定',
	        		    ok: function(){
	        		    	window.location.reload();
	        		    }
	        		}).showModal();
	        	}
	        });
	        return false;
	    },
	    cancelValue: '取消',
	    cancel: function () {}
	});
	d.showModal();
}
function openActive(){
	$("#activeBeOpen").addClass("open");
}
function openNormal(){
	$("#normalBeOpen").addClass("open");
}
function delNormal(id,price,tagA){
	$("span[activity-id='"+id+"']").text(Number($("span[activity-id='"+id+"']").text()) - 1);
	if(Number($("span[activity-id='"+id+"']").text()) == 0){
		$("span[activity-id='"+id+"']").remove();
	}
	$("li[beselect='"+id+"']").each(function(index,item){
		if(index == 0){
			$(item).remove();
		}
	});
	var totlaNum = $("#normal-totalNum").text();
	$("#normal-totalNum").text(Number(totlaNum) - 1);
	var totalPrice = $("#normal-totalPrice").text();
	$("#normal-totalPrice").text(Number(totalPrice) - Number(price));
	openNormal();
}
function delActive(id,price,tagA){
	$("span[activity-id='"+id+"']").text(Number($("span[activity-id='"+id+"']").text()) - 1);
	if(Number($("span[activity-id='"+id+"']").text()) == 0){
		$("span[activity-id='"+id+"']").remove();
	}
	$("li[beselect='"+id+"']").each(function(index,item){
		if(index == 0){
			$(item).remove();
		}
	});
	var totlaNum = $("#active-totalNum").text();
	$("#active-totalNum").text(Number(totlaNum) - 1);
	var totalPrice = $("#active-totalPrice").text();
	$("#active-totalPrice").text(Number(totalPrice) - Number(price));
	openActive();
}

// 选择活动<span class='label label-info'>0</span>
function selectActivity(name,price,activityId,tagA,flag){
	console.log(price);
	if(!$("span[activity-id='"+activityId+"']").attr("activity-id") && flag == 'add'){
		$("a[activity-id='"+activityId+"']").prepend("<span activity-id='"+activityId+"' class='label label-info'>1</span>");
	}else{
		var num = $("span[activity-id='"+activityId+"']").text();
		if(flag == 'add'){
			$("span[activity-id='"+activityId+"']").text(Number(num) + 1);
		}else{
			if(num == '1'){
				$.jGrowl("数量已经为0",{header:'选择料理'});
				$("span[activity-id='"+activityId+"']").remove();
			}
			if(num >= 1){
				$("span[activity-id='"+activityId+"']").text(Number(num) - 1);
			}
		}
	}
	if(flag == 'add'){
		// 添加到头顶
		if($("#todayActive-li").hasClass("active")){
			var totalPrice = $("#active-totalPrice").text();
			$("#active-totalPrice").text(Number(totalPrice) + Number(price));
				$("#activeBeOpen").addClass("open");
				var totlaNum = $("#active-totalNum").text();
				$("#active-totalNum").text(Number(totlaNum) + 1);
				var str = "<li beSelect="+activityId+" data-price='"+price+"' class='custom'><div class='title'>"+
				  		"活动:<font color='red'>"+name+"&nbsp;&nbsp;</font>价格:<font color='red'>"+price+
				  		"￥</font></div><div class='action'><div class='btn-group'>"+
				  		"<a href='#' onclick=delActive('"+activityId+"','"+price+"',this) class='tip btn btn-mini' title='Delete ticket'><img src='img/icons/fugue/cross.png' alt=''></a>"+
				  		"</div></div></li>"
			  		$(".active-detail").append(str);
				
		}else{
			var totalPrice = $("#normal-totalPrice").text();
			$("#normal-totalPrice").text(Number(totalPrice) + Number(price));
			$("#normalBeOpen").addClass("open");
			var totlaNum = $("#normal-totalNum").text();
			$("#normal-totalNum").text(Number(totlaNum) + 1);
			var str = "<li beSelect="+activityId+" data-price='"+price+"' class='custom'><div class='title'>"+
			  		"常规:<font color='red'>"+name+"&nbsp;&nbsp;</font>价格:<font color='red'>"+price+
			  		"￥</font></div><div class='action'><div class='btn-group'>"+
			  		"<a href='#' onclick=delNormal('"+activityId+"','"+price+"',this) class='tip btn btn-mini' title='Delete ticket'><img src='img/icons/fugue/cross.png' alt=''></a>"+
			  		"</div></div></li>"
			  		$(".normal-detail").append(str);
		}
	}else{
		if($("#todayActive-li").hasClass("active")){
				$("#activeBeOpen").addClass("open");
				var totlaNum = $("#active-totalNum").text();
				if(Number(totlaNum) >= 1)
				$("#active-totalNum").text(Number(totlaNum) - 1);
				var totalPrice = $("#active-totalPrice").text();
				var num = Number(totalPrice) - Number(price);
				if(num < 0) num = 0;
				$("#active-totalPrice").text(num);
				$("li[beselect='"+activityId+"']").each(function(index,item){
					if(index == 0){
						$(item).remove();
					}
				});
		}else{
			$("#normalBeOpen").addClass("open");
			var totlaNum = $("#normal-totalNum").text();
			if(Number(totlaNum) >= 1)
			$("#normal-totalNum").text(Number(totlaNum) - 1);
			var totalPrice = $("#normal-totalPrice").text();
			var num = Number(totalPrice) - Number(price);
			if(num < 0) num = 0;
			$("#normal-totalPrice").text(num);
			$("li[beselect='"+activityId+"']").each(function(index,item){
				if(index == 0){
					$(item).remove();
				}
			});
		}
	}
}
// 列出所有餐桌
var listBoard = function(){
	$("#selectBoard").html("");
	$.post("/board/list",{},function(data){
		var results = data.data;
		for(var i = 0; results[i]; i++){
			var board = results[i];
			var tempStr = "<img src='myimg/board-notUse.png' alt=''><span>"+board.name+"</span><span>未有人</span></a>";;
			if(board.use){
				tempStr = "<img src='myimg/board-use.png' alt=''><span>"+board.name+"</span><span>正进餐</span></a>";
			}
			if(!board.use){
				board.lastOrderId = "";
			}
			var str = "<li><a data-orderId='"+board.lastOrderId+"' data-id='"+board.id+"' href=javascript:selectBoard(this,'"+board.id+"','"+board.name+"','"+board.lastOrderId+"')>"+tempStr+"</a></li>";
			$("#selectBoard").append(str);
		}
		if(isToInit){
			initDatables();
		}else{
			isToInit = true;
		}
	});
}
// 选择餐桌显示菜
function selectBoard(tagA,id,name,orderId){
	// 如果已经有人
	if(orderId != ''){
		dialog({
		    title: '注意',
		    content: "<font color='red'>" + name + "</font>已经有客人，其单号为<font color='red'>" + orderId + "</font>是否进行加餐?",
		    okValue: '确定加餐',
		    ok: function(){
		    	oldOrderId = orderId;
		    	boardId = id;
		    	jQuery.fx.interval = 100;
		    	$("#board-select").slideUp(500);
		    	$("#dish-select").fadeTo("fast", 1);
		    },
		    cancelValue: '取消',
		    cancel: function () {
		    }
		}).showModal();
	}else{
		boardId = id;
    	jQuery.fx.interval = 100;
    	$("#board-select").slideUp(500);
    	$("#dish-select").fadeTo("fast", 1);
	}
}
// 返回选择餐桌
function backSelectBoard(){
	$("#dish-select").hide();
	$("#board-select").fadeTo("fast", 1);
}
$(function(){
	// 列出所有餐桌
	listBoard();
	loadTodayActivityInfo();
});
