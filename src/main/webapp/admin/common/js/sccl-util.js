/*设置cookie*/
function setCookie(name, value, days){
	if(days == null || days == ''){
		days = 300;
	}
	var exp  = new Date();
	exp.setTime(exp.getTime() + days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + "; path=/;expires=" + exp.toGMTString();
}

/*获取cookie*/
function getCookie(name) {
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr = document.cookie.match(reg))
		return unescape(arr[2]); 
	else 
		return null; 
}

/*ajax请求*/
function ajax(url, param, datat, callback) {
	$.ajax({  
		type: "POST",
		url: url,  
		data: param,  
		dataType: datat,
		success: function(data){
			callback(data);
		},  
		error: function (response) {
			//console.log(response);
			layer.msg("请求失败，status："+response.status);
		}
	});  
}

function ajaxget(url, param, datat, callback) {
	$.ajax({
		type: "get",
		url: url,
		data: param,
		dataType: datat,
		success: function(data){
			callback(data);
		},
		error: function (response){
			console.log(response);
			alert("请求失败，status："+response.status);
		}
	});
}





