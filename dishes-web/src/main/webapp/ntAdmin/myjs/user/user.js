/**
 * 用户管理js脚本
 */
// 加载用户信息列表
var loadUserInfo = function(){
	$("#userTablesContent").html("");
	$.get("/ntAdmin/template/userTableTemplate.html").done(function(data){
		$("#userTablesContent").append(data);
		$(data).find("table:first").attr("id",new Date().getTime());
	})
	$.post("/user/list",{},function(data,status){
		if(!data.hasError){
			var users = data.data;
			for(var i = 0;users[i]; i++){
				var user = users[i];
				var status = "正常";
				if(user.disabled) status = "冻结";
				var roleName = "";
				if(user.roleName == 'WAITER_USER'){
					roleName = "服务员";
				}else if(user.roleName == 'CASHIER_USER'){
					roleName = "收银员";
				}else if(user.roleName == 'ADMIN_USER'){
					roleName = "管理员";
				}
				var str = "<tr><td>"+ user.userAccount +"</td><td class='center'>"+user.name+"</td>"+
						  "<td class='center'>"+roleName+"</td><td class='center'>"+status+"</td>"+
						  "<td><a onclick=resetPassword('"+user.id+"') class='btn btn-info' href='#'><i class='glyphicon glyphicon-edit icon-white'></i>重置密码</a>"+
						  "<a onclick=deleteUser('"+user.id+"','"+user.userAccount+"',this) class='btn btn-danger' href='#'><i class='glyphicon glyphicon-trash icon-white'></i>删除</a></td></tr>";
				$("tbody:first").append(str);
			}
			// 初始化表
			initDatables();
		}
	});
}
// 重置密码
function resetPassword(userId){
	$.post('/user/resetPassword',{userId:userId},function(data){
		if(data.hasError){
			$.jGrowl(data.errorMsg,{header:'重置密码'});
		}else{
			dialog({
    			title: '重置密码',
    			content: data.data,
    			cancelValue: '取消'}).showModal();
    		that.remove();
		}
	});
}
// 编辑用户
function editUser(userId){
	$.post('/user/get',{userId:userId},function(data){
		if(data.hasError){
			dialog({
    			title: '编辑用户',
    			content: data.errorMsg,
    			cancelValue: '取消'}).showModal();
		}else{
			$.get("/ntAdmin/template/userEditTemplate.html").done(function(backHtml){
				var d = dialog({
					title:'编辑用户',
					content:backHtml,
					okValue:'提交',
					ok:function(){
						alert(123);
					}
				})
				var result = data.data;
				d.showModal();
				$("div[i='dialog']").find("#edit-userAccount").val(result.userAccount);
				$("div[i='dialog']").find("#edit-name").val(result.name);
			});
		}
	});
}
// 删除用户
function deleteUser(userId,userAccount,aTag){
	var d = dialog({
	    title: '删除用户',
	    content: '确定删除账号'+userAccount,
	    okValue: '确定',
	    ok: function () {
	    	var that = this;
	    	that.title('提交中…');
	        $.post("/user/delete",{userId:userId},function(data){
	        	if(data.hasError){
	        		that.remove();
	        		dialog({
	        			title: '删除用户',
	        			content: data.errorMsg,
	        			cancelValue: '取消'}).showModal();
	        	}else{
	        		dialog({
	        			title: '删除用户',
	        			content: data.data,
	        			cancelValue: '取消'}).showModal();
	        		that.remove();
	        		loadUserInfo();
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
	loadUserInfo();
});