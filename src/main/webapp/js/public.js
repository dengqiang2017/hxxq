Date.prototype.Format = function(fmt){ //author: meizz   
	var o = {
	"M+" : this.getMonth()+1,                 //月份   
	"d+" : this.getDate(),                    //日   
	"h+" : this.getHours(),                   //小时   
	"m+" : this.getMinutes(),                 //分   
	"s+" : this.getSeconds(),                 //秒   
	"q+" : Math.floor((this.getMonth()+3)/3), //季度   
	"S"  : this.getMilliseconds()             //毫秒   
	};   
	if(/(y+)/.test(fmt))
	fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	for(var k in o)   
	if(new RegExp("("+ k +")").test(fmt))
	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	return fmt;
};
function getQueryString(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
    var url=window.location.search.substr(1);
    url=decodeURIComponent(url);
    var r = url.match(reg);
    if (r != null) {///decodeURIComponent
    	return unescape(r[2]); 
    }
    return null;
}
var fan=1;
$(function(){
	window.uexOnload = function(type){
		if(!type){
			var plat = uexWidgetOne.getPlatform();
			//如果是Android平台，则监听返回按钮事件
			if(plat){
				uexWindow.onKeyPressed=function(keyCode){
					if(keyCode==0){
						if(fan==2){
							uexWidgetOne.exit(0);//退出
//							uexWidget.moveToBack();//返回桌面
						}else{
							++fan;
							$.toast("再按一次退出程序","text");
							setTimeout(function(){
								fan=0;
							}, 5000);
						}
					}
				};
				uexWindow.setReportKey(0,1);
			}
		}
		try {
//			var id=uexJPush.getRegistrationID();
//			alert(id);
		} catch (e) {
		}
		uexWidgetOne.cleanCache();
	};
});
/**
 * 判断是否是微信浏览器  
 * @returns true为微信浏览器,false为其它浏览器
 */
function is_weixin(){
	var ua = navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i)=="micromessenger") {
		return true;
 	} else {
		return false;
	}
}
function autoLogin(){
	window.uexOnload = function(type){
		////自动登录注册////
		var moblieMac=uexDevice.getInfo("10");//用户手机唯一识别码
		$.get("login/autoLogin.do",{"moblieMac":moblieMac},function(data){
			if(data.msg=="1"){
				window.location.href="release";
			}else if(data.msg=="0"){
				window.location.href="show";
			}
		});
	};
}