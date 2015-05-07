/**
 * 活动管理js脚本
 */
// 加载活动列表
var loadActivityInfo = function(){
	$("#activityTablesContent").html("");
	$.get("/ntAdmin/template/activityTemplate.html").done(function(data){
		$("#activityTablesContent").append(data);
		$(data).find("table:first").attr("id",new Date().getTime());
	})
	$.post("/active/list",{},function(data,status){
		if(!data.hasError){
			var activities = data.data;
			console.log(activities);
			for(var i = 0;activities[i]; i++){
				var active = activities[i];
				var status = "正常";
				if(active.disabled) status = "活动结束";
				var str = "<tr><td class='center'>"+active.name+"</td>"+
						  "<td class='center'>"+active.activitySumPrice+"</td>"+
						  "<td class='center'>"+active.originalSumPrice+"</td>"+
						  "<td class='center'>"+status+"</td>"+
						  "<td class='center'>"+active.dishNames+"</td>"+
						  "<td class='center'>"+active.description+"</td>"+
						  "<td><a onclick=deleteActivity('"+active.id+"','"+active.name+"',this) class='btn btn-danger' href='#'><i class='glyphicon glyphicon-trash icon-white'></i>删除</a></td></tr>";
				$("tbody:first").append(str);
			}
			// 初始化表
			initDatables();
		}
	});
}
// 删除活动
function deleteActivity(id,name,aTag){
	var d = dialog({
	    title: '删除活动',
	    content: '确定删除'+name,
	    okValue: '确定',
	    ok: function () {
	    	var that = this;
	    	that.title('提交中…');
	        $.post("/active/delete",{id:id},function(data){
	        	if(data.hasError){
	        		that.remove();
	        		dialog({
	        			title: '删除活动',
	        			content: data.errorMsg,
	        			cancelValue: '取消'}).showModal();
	        	}else{
	        		dialog({
	        			title: '删除活动',
	        			content: data.data,
	        			cancelValue: '取消'}).showModal();
	        		that.remove();
	        		loadActivityInfo();
	        	}
	        });
	        return false;
	    },
	    cancelValue: '取消',
	    cancel: function () {}
	});
	d.showModal();
}
$(function(){
	loadActivityInfo();
});