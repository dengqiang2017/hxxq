$(function() {
	FastClick.attach(document.body);
	$(".weui-input,textarea").focus(function(){
		$(".weui-tabbar").css("position","relative");
	});
	$(".weui-input,textarea").blur(function(){
		$(".weui-tabbar").css("position","absolute");
	});
	$("#zl_edit").click(function(){
		$.get("zl_edit.html",function(data){
			$("#zl_html").html(data);
			$("#zl_edit_dia").popup();
		});
	});
	$(".weui-cell_swiped").swipeout();
	// $("#city-picker").picker({
	// 	  title: "请选择小区位置",
	// 	  cols: [
	// 	    {
	// 	      textAlign: "center",
	// 	      values: ["金牛区","锦江区","青羊区","武侯区","成华区","温江区"]
	// 	    }
	// 	  ]
	// 	});
	//////////////////////////////////////
	//进入公告,投票发布页面前先清空临时文件夹
	init("notice");
	function init(type){
		$.post("../releaseManager/removeTempFile.do",{"type":type});
	}
	
	$(".weui-tabbar__item").click(function(){
		$(".weui-tab__bd-item").removeClass("weui-tab__bd-item--active");
	});
	$(".weui-tabbar__item:eq(0)").click(function(){
		init("notice");
		$(".head-title").html("公告发布");
	});
	$(".weui-tabbar__item:eq(1)").click(function(){
		init("vote");
		$(".head-title").html("发起投票");
	});
	$(".weui-tabbar__item:eq(2)").click(function(){
		$(".head-title").html("个人中心");
	});
	//////////////公告信息保存////////////////
	$("#weuiAgree2").click(function(){
		if($(this).prop("checked")){
			$("#saveNotice").removeAttr("disabled");
			$("#saveNotice").css("background-color","");
		}else{
			$("#saveNotice").attr("disabled");
			$("#saveNotice").css("background-color","#ccc");
		}
	});
	$("#saveNotice").click(function(){
		var b=$("#weuiAgree2").prop("checked");
		if(!b){
			return;
		}
		var serializeJson = $("#noticeForm").serialize();
		var params=serializeJson.split("&");
		var json={};
		for (var i = 0; i < params.length; i++) {
			var keys=params[i].split("=");
			json[keys[0]]=keys[1];
		}
		json.comment=$("#notice #comment").prop("checked");
		json.agree=$("#notice #weuiAgree2").prop("checked");
		json.imgList=[];
		var imgs=$("#uploaderFiles>li>span").html();
		for (var i = 0; i < imgs.length; i++) {
			json.imgList.push(imgs[i]);
		}
		console.log(JSON.stringify(json));
		if(!json.noticeTitle){
			$.alert("请输入标题!","系统提示");
		}else if(!json.agree){
			$.alert("请阅读并确认《信息发布条款》！","系统提示");
		}else{
			$.post("../releaseManager/saveNoticeInfo.do",json,function(data){
				if(data.success){
					 $.toast("操作成功");
				}else{
					$.alert(data.msg,"系统提示");
				}
			});
		}
	});
	$("#weuiAgree").click(function(){
		if($(this).prop("checked")){
			$("#saveVote").removeAttr("disabled");
			$("#saveVote").css("background-color","");
		}else{
			$("#saveVote").attr("disabled");
			$("#saveVote").css("background-color","#ccc");
		}
	});
	$("#saveVote").click(function(){
		var b=$("#weuiAgree").prop("checked");
		if(!b){
			return;
		}
		var serializeJson = $("#voteForm").serialize();
		var params=serializeJson.split("&");
		var json={};
		for (var i = 0; i < params.length; i++) {
			var keys=params[i].split("=");
			json[keys[0]]=keys[1];
		}
		json.comment=$("#vote #comment").prop("checked");
		json.agree=$("#vote #weuiAgree2").prop("checked");
		json.imgList=[];
		var imgs=$("#vote_uploaderFiles>li>span").html();
		for (var i = 0; i < imgs.length; i++) {
			json.imgList.push(imgs[i]);
		}
		console.log(JSON.stringify(json));
		if(!json.voteTitle){
			$.alert("请输入标题!","系统提示");
		}else if(!json.endTime){
			$.alert("请选择投票截止时间!","系统提示");
		}else if(!json.voteDesc){
			$.alert("请投票描述!","系统提示");
		}else if(!json.agree){
			$.alert("请阅读并确认《信息发布条款》！","系统提示");
		}else{
			$.post("../releaseManager/saveVoteInfo.do",json,function(data){
				if(data.success){
					 $.toast("操作成功");
				}else{
					$.alert(data.msg,"系统提示");
				}
			});
		}
	});
	weixinfileup.init();
	$(".weui-uploader__info").html("");
//	$(".weui-uploader__files").html("");
	$("#uploaderInput").click(function(){
		var imgPath="/temp/notice/"+Math.random()+".jpg";
		weixinfileup.imguploadToWeixin(this, imgPath, function(){
			$("#uploaderFiles").append('<li class="weui-uploader__file" style="background-image:url('+imgPath+')"><span>'+imgPath+'</span></li>');
		});
		$("#img_num").html($("#uploaderFiles li").length);
	});
	$("#voteImgUpload").click(function(){
		var imgPath="/temp/vote/"+Math.random()+".jpg";
		weixinfileup.imguploadToWeixin(this, imgPath, function(){
			$("#vote_uploaderFiles").append('<li class="weui-uploader__file" style="background-image:url('+imgPath+')"><span>'+imgPath+'</span></li>');
		});
		$("#vote_img_num").html($("#vote_uploaderFiles li").length);
	});
	
	//////////////////
});