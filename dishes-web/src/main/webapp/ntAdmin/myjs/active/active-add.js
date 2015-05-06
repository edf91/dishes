/**
 * 活动添加js脚本
 */
// 添加菜到活动中
function selectDishs(){
	var dishes = new Array();
	$("#activity-addDish input:checked").each(function(index,item){
		var dish = {};
		dish.id = $(item).val();
		dish.name = $(item).attr("data-name");
		dish.price = $(item).attr("data-price");
		dishes.push(dish);
	});
	return dishes;
}
// 选择所有菜
function selectAll(check){
	if($(check).attr("checked")){
		$("#activity-addDish input[type=checkbox]").each(function(index,item){
			item.checked = true;
		});
	}else{
		$("#activity-addDish input[type=checkbox]").each(function(index,item){
			item.checked = false;
		});
	}
}
function addDishToActivity(){
	// 列出所有有效的菜
	$.post("/dish/listAvailable",{},function(data){
		var dishs = data.data;
		$("tbody:first").html("");
			for(var i = 0; dishs[i]; i++){
				var dish = dishs[i];
				var str = "<tr><td class='table-checkbox'><input value='"+dish.id+"' data-name='"+dish.name+"' data-price='"+dish.price+"' type='checkbox' class='selectable-checkbox'></td>"+
							"<td>"+dish.name+"</td><td>"+dish.price+"</td></tr>";
				$("tbody:first").append(str);
			}
			dialog({
				title:'选择菜',
				width:500,
				content:$(".selectDish").html(),
				okValue: '添加',
				ok: function () {
					var that = this;
					var dishes = selectDishs();
					// 去除重复元素
					var results = new Array();
					var hash = {};
					for(var i = 0,dishId;dishes[i]; i++){
						var dish = dishes[i];
						if(dish.id == 'on') continue;
						if(!hash[dish.id]){
							results.push(dish);
							hash[dish.id] = true;
						}
					}
					// 添加到页面
					$(".addResultDish").html("");
					for(var i = 0; results[i]; i++){
						var str = "<div class='alert alert-block'><a data-id='"+results[i].id+"' class='close' data-dismiss='alert' href='#'>×</a>"+
								  results[i].name + "----"+results[i].price+"$</div>";
						$(".addResultDish").append(str);
					}
			    },
			    cancelValue: '取消',
			    cancel: function () {}
			}).showModal();
		
	});
}
function addActive(){
	var dishIds = "";
	$(".addResultDish").find("a").each(function(index,item){
		dishIds += $(item).attr("data-id") + ",";
	});
	if(dishIds.length){
		dishIds = dishIds.substring(0,dishIds.length - 1);
	}
	$("input[name='dishIds']").val(dishIds);
	$.post("/active/add",$("form").serialize(),function(data){
		if(data.hasError){
			$.jGrowl(data.errorMsg,{header:'添加活动'});
		}else{
			$.jGrowl(data.data,{header:'添加活动'});
		}
	});
	return false;
}

