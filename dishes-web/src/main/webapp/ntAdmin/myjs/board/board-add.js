/**
 * 餐桌添加js脚本
 */
// 添加餐桌
function addBoard(){
	$.post("/board/add",$("form").serialize(),function(data){
		if(data.hasError){
			$.jGrowl(data.errorMsg,{header:'添加餐桌'});
		}else{
			$.jGrowl(data.data,{header:'添加餐桌'});
		}
	});
	return false;
}
