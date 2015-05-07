/**
 * 菜谱管理js脚本
 */
// 加载菜谱信息列表
var loadDishInfo = function(){
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
				if(dish.disabled) continue;
				var str = "<tr><td class='center'>"+dish.name+"</td>"+
						  "<td class='center'>"+dish.price+"</td>"+
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
function editBoard(id){
	$.post('/dish/get',{id:id},function(data){
		if(data.hasError){
			dialog({
    			title: '编辑菜谱',
    			content: data.errorMsg,
    			cancelValue: '取消'}).showModal();
		}else{
			var result = data.data;
			$.get("/ntAdmin/template/dishEdit.html").done(function(backHtml){
				var d = dialog({
					title:'编辑菜谱',
					content:backHtml,
					okValue: '提交',
					ok: function(){
						var that = this;
						$.post("/dish/update",$("form").serialize(),function(data){
							if(data.hasError){
								$.jGrowl(data.errorMsg,{header:'编辑菜谱'});
							}else{
								dialog({
				        			title: '编辑菜谱',
				        			content: data.data,
				        			cancelValue: '取消'}).showModal();
				        		that.remove();
								loadDishInfo();
							}
						});
					}
				})
				d.showModal();
				$("div[i='dialog']").find("#dishType-edit").html("");
				$.post("/dishesType/listAvailableDishType",{},function(data,status){
					if(!data.hasError){
						var dishesTypes = data.data;
						for(var i = 0;dishesTypes[i]; i++){
							var dish = dishesTypes[i];
							var selected = ""
							if(result.dishTypeName == dish.name) selected = "selected";
							var str = "<option "+selected+" value='"+dish.id+"'>" +dish.name+ "</option>";
							$("#dishType-edit").append(str);
						}
					}
				});
				$("div[i='dialog']").find("#name-edit").val(result.name);
				$("div[i='dialog']").find("#id-edit").val(result.id);
				$("div[i='dialog']").find("#price-edit").val(result.price);
				if(result.activity){
					$("div[i='dialog']").find("#isActivity-edit").attr("checked",true);
					$("div[i='dialog']").find("#activityPrice-edit").attr("disabled",false);
					$("div[i='dialog']").find("#activityPrice-edit").val(result.activityPrice);
				}
				$("div[i='dialog']").find("#basictext-edit").val(result.description);
				$("div[i='dialog']").find("#basictext-edit").val(result.description);
				isActive();
			});
		}
	});
}
var isActive = function(){
	$("input[type='checkbox']").on("click",function(){
		if($(this).attr("checked")){
			$("input[name='activityPrice']").attr("disabled",false);
		}else{
			$("input[name='activityPrice']").val("");
			$("input[name='activityPrice']").attr("disabled",true);
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
	        		loadDishInfo();
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
	loadDishInfo();
});