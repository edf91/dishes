/**
 * 用户添加js脚本
 */
// 添加用户
function addDishesType(){
	$.post("/dishesType/add",$("form").serialize(),function(data){
		if(data.hasError){
			$.jGrowl(data.errorMsg,{header:'添加分类'});
		}else{
			$.jGrowl(data.data,{header:'添加分类'});
		}
	});
	return false;
}
