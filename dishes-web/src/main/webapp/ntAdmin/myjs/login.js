/**
 * 登陆js
 */
function doLogin(){
	$.post("/user/login",$("form").serialize(),function(data){
		console.log(data);
		if(data.hasError){
			$("#error").remove();
			$("form").prepend("<div id='error' class='alert alert-error'><strong>Error!</strong> "+data.errorMsg+"</div>");
		}else{
			window.location.href="selectBoard.html";
		}
	});
	return false;
}
