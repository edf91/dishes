/**
 * 菜添加js脚本
 */
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
var loadDishType = function(){
	$("#dishType").html("");
	$.post("/dishesType/listAvailableDishType",{},function(data,status){
		if(!data.hasError){
			var dishesTypes = data.data;
			for(var i = 0;dishesTypes[i]; i++){
				var dish = dishesTypes[i];
				var str = "<option value='"+dish.id+"'>" +dish.name+ "</option>";
				$("#dishType").append(str);
			}
		}
	});
}
function addDish(){
	$.post("/dish/add",$("form").serialize(),function(data){
		if(data.hasError){
			$.jGrowl(data.errorMsg,{header:'编辑菜谱'});
		}else{
			$.jGrowl(data.data,{header:'编辑菜谱'});
		}
	});
	return false;
}

$(function(){
	loadDishType();
	isActive();
});
