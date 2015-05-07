/**
 * 餐桌管理js脚本
 */
// 加载餐桌信息列表
var loadBoardInfo = function(){
	$("#boardTablesContent").html("");
	$.get("/ntAdmin/template/boardTemplate.html").done(function(data){
		$("#boardTablesContent").append(data);
		$(data).find("table:first").attr("id",new Date().getTime());
	})
	$.post("/board/list",{},function(data,status){
		if(!data.hasError){
			var boards = data.data;
			for(var i = 0;boards[i]; i++){
				var board = boards[i];
				var status = "正常";
				if(board.use) status = "有客";
				var str = "<tr><td class='center'>"+board.name+"</td>"+
						  "<td class='center'>"+status+"</td>"+
						  "<td><a onclick=deleteBoard('"+board.id+"','"+board.name+"',this) class='btn btn-danger' href='#'><i class='glyphicon glyphicon-trash icon-white'></i>删除</a></td></tr>";
				$("tbody:first").append(str);
			}
			// 初始化表
			initDatables();
		}
	});
}
// 删除餐桌
function deleteBoard(id,name,aTag){
	var d = dialog({
	    title: '删除餐桌',
	    content: '确定删除'+name,
	    okValue: '确定',
	    ok: function () {
	    	var that = this;
	    	that.title('提交中…');
	        $.post("/board/delete",{boardId:id},function(data){
	        	if(data.hasError){
	        		that.remove();
	        		dialog({
	        			title: '删除餐桌',
	        			content: data.errorMsg,
	        			cancelValue: '取消'}).showModal();
	        	}else{
	        		dialog({
	        			title: '删除餐桌',
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