/**
 * 用户管理js脚本
 */
// 加载用户信息列表
var loadDishTypeInfo = function(){
	$("#dishTypeTablesContent").html("");
	$.get("/ntAdmin/template/dishesTypeTemplate.html").done(function(data){
		$("#dishTypeTablesContent").append(data);
		$(data).find("table:first").attr("id",new Date().getTime());
	})
	$.post("/dishesType/list",{},function(data,status){
		if(!data.hasError){
			var dishesTypes = data.data;
			for(var i = 0;dishesTypes[i]; i++){
				var dish = dishesTypes[i];
				var status = "正常";
				if(dish.disabled) status = "冻结";
				var str = "<tr><td>"+ dish.name +"</td><td class='center'>"+status+"</td>"+
						  "<td><a onclick=editDish('"+dish.id+"') class='btn btn-info' href='#'><i class='glyphicon glyphicon-edit icon-white'></i>编辑</a>"+
						  "<a onclick=deleteDish('"+dish.id+"','"+dish.name+"',this) class='btn btn-danger' href='#'><i class='glyphicon glyphicon-trash icon-white'></i>删除</a></td></tr>";
				$("tbody:first").append(str);
			}
			// 初始化表
			initDatables();
		}
	});
}
// 编辑分类
// TODO 分类编辑 
function editDish(dishId){
	$.post('/dishesType/get',{dishId:dishId},function(data){
		if(data.hasError){
			dialog({
    			title: '编辑分类',
    			content: data.errorMsg,
    			cancelValue: '取消'}).showModal();
		}else{
			$.get("/ntAdmin/template/dishesTypeEditTemplate.html").done(function(data){
				dialog({
					title:'编辑用户',
					content:data
				}).showModal();
			});
		}
	});
}
// 删除用户
function deleteDish(dishId,name,aTag){
	var d = dialog({
	    title: '删除分类',
	    content: '确定删除'+name,
	    okValue: '确定',
	    ok: function () {
	    	var that = this;
	    	that.title('提交中…');
	        $.post("/dishesType/delete",{dishId:dishId},function(data){
	        	if(data.hasError){
	        		that.remove();
	        		dialog({
	        			title: '删除分类',
	        			content: data.errorMsg,
	        			cancelValue: '取消'}).showModal();
	        	}else{
	        		dialog({
	        			title: '删除分类',
	        			content: data.data,
	        			cancelValue: '取消'}).showModal();
	        		that.remove();
	        		loadDishTypeInfo();
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
	loadDishTypeInfo();
});