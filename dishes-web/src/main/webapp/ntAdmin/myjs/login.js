/**
 * 登陆js
 */
// 获取验证码
function flushImg(btn){
	//点击图片更换验证码
	$(btn).children().attr("src","/index/getCheckImg?timestamp="+new Date().getTime());
}
// 记住用户名密码
if(localStorage.getItem("account")&&localStorage.getItem("psw")){
	$("#user").val(localStorage.getItem("account"));
	$("#pw").val(localStorage.getItem("psw"));
	$("#remember").attr('checked',true);
}
//保存用户名和密码
var rememberMe = function (){
	var remember = document.getElementById("remember");
	if(remember.checked){
		var account = document.getElementById("user").value;
		var psw = document.getElementById("pw").value;
		localStorage.setItem("account",account);
		localStorage.setItem("psw",psw);
	}else{
		localStorage.removeItem("account");
		localStorage.removeItem("psw");
	}
}
// 登陆
function doLogin(){
	rememberMe();
	$.post("/user/login",$("form").serialize(),function(data){
		if(data.hasError){
			$("#error").remove();
			$("form").prepend("<div id='error' class='alert alert-error'><font color='red'><h2>Error!</h2></font> "+data.errorMsg+"</div>");
		}else{
			var userInfo = data.data;
			if(userInfo.roleName == 'WAITER_USER') window.location.href="waiterIndex.html";
			if(userInfo.roleName == 'CASHIER_USER') window.location.href="cashierIndex.html";
			if(userInfo.roleName == 'ADMIN_USER') window.location.href="adminIndex.html";
		}
	});
	return false;
}
