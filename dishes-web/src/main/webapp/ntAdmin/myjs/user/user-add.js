/**
 * 用户添加js脚本
 */
// 添加用户
function addUser(){
	$.post("/user/add",$("form").serialize(),function(data){
		if(data.hasError){
			$.jGrowl(data.errorMsg,{header:'添加用户'});
		}else{
			$.jGrowl(data.data,{header:'添加用户'});
		}
	});
	return false;
}
