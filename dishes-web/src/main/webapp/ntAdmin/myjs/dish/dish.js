/**
 * 用户管理js脚本
 */
// 加载用户信息列表
var loadBoardInfo = function(){
	$("#dishTablesContent").html("");
	$.get("/ntAdmin/template/dishTemplate.html").done(function(data){
		$("#dishTablesContent").append(data);
		$(data).find("table:first").attr("id",new Date().getTime());
	})
	$.post("/dish/list",{},function(data,status){
		if(!data.hasError){
			var dishes = data.data;
			for(var i = 0;dishes[i]; i++){
				var dish = dishes[i];
				var status = "正常";
				if(dish.activity) status = "处于活动";
				if(dish.disabled) status = "冻结";
				var str = "<tr><td class='center'>"+dish.name+"</td>"+
						  "<td class='center'>"+dish.price+"</td>"+
						  "<td class='center'>"+dish.num+"</td>"+
						  "<td class='center'>"+dish.dishTypeName+"</td>"+
						  "<td class='center'>"+status+"</td>"+
						  "<td class='center'>"+dish.activityPrice+"</td>"+
						  "<td class='center'>"+dish.description+"</td>"+
						  "<td><a onclick=editBoard('"+dish.id+"') class='btn btn-info' href='#'><i class='glyphicon glyphicon-edit icon-white'></i>编辑</a>"+
						  "<a onclick=deleteDish('"+dish.id+"','"+dish.name+"',this) class='btn btn-danger' href='#'><i class='glyphicon glyphicon-trash icon-white'></i>删除</a></td></tr>";
				$("tbody:first").append(str);
			}
			// 初始化表
			initDatables();
		}
	});
}
// 编辑菜
// TODO 菜编辑
function editBoard(id){
	$.post('/board/get',{boardId:id},function(data){
		if(data.hasError){
			dialog({
    			title: '编辑餐桌',
    			content: data.errorMsg,
    			cancelValue: '取消'}).showModal();
		}else{
			var result = data.data;
			$.get("/ntAdmin/template/boardEditTemplate.html").done(function(backHtml){
				console.log($(backHtml).find("#board-name"));
				console.log(result.name);
				$(backHtml).find("#board-name").eq(0).value=result.name;
				
				dialog({
					title:'编辑餐桌',
					content:backHtml
				}).showModal();
			});
		}
	});
}
// 删除菜
function deleteDish(id,name,aTag){
	var d = dialog({
	    title: '删除菜',
	    content: '确定删除'+name,
	    okValue: '确定',
	    ok: function () {
	    	var that = this;
	    	that.title('提交中…');
	        $.post("/dish/delete",{id:id},function(data){
	        	if(data.hasError){
	        		that.remove();
	        		dialog({
	        			title: '删除菜',
	        			content: data.errorMsg,
	        			cancelValue: '取消'}).showModal();
	        	}else{
	        		dialog({
	        			title: '删除菜',
	        			content: data.data,
	        			cancelValue: '取消'}).showModal();
	        		that.remove();
	        		loadBoardInfo();
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
	loadBoardInfo();
});