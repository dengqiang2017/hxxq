$(function() {
	FastClick.attach(document.body);
	//检查是否实名认证
//	checkUserInfo();
	var now = new Date();
	var nowStr = now.Format("yyyy-MM-ddThh:mm");
	$("input[name='showTime']").val(nowStr);
	$("input[name='beginTime']").val(nowStr);
	$("input[name='showTime']").attr("min",nowStr);
	$("input[name='beginTime']").attr("min",nowStr);
	$("input[name='endTime']").attr("min",nowStr);
	$(".weui-cells_form").scrollTop(0);
	function checkUserInfo(){
		$.get("../releaseManager/checkUserInfo.do",function(data){
			if (!data.success) {
				$.alert("未实名认证,只能浏览不能发布信息!",function(){
					$("#zl_edit").click();
				});
				$(".weui-agree__checkbox").attr("disabled","disabled");
			}
		});
	}
	$("#noticeDesc").bind("input",function(){
		$("#noticeDesc_zs").html($(this).val().length);
	});
	$("#voteDesc").bind("input",function(){
		$("#voteDesc_zs").html($(this).val().length);
	});
	
	$(".weui-input,textarea").focus(function(){
		$(".weui-tabbar").hide();
	});
	$(".weui-input,textarea").blur(function(){
		$(".weui-tabbar").show();
	});
	$("#zl_edit").click(function(){
		$.get("zl_edit.html",function(data){
			$("#zl_html").html(data);
			$("#zl_edit_dia").popup();
		});
	});
	$(".weui-cell_swiped").swipeout();
	//////////////////////////////////////
	//进入公告,投票发布页面前先清空临时文件夹
	function init(type){
		$.post("../releaseManager/removeTempFile.do",{"type":type});
	}
	
	$(".weui-tabbar__item").click(function(){
		$(".weui-tab__bd-item").removeClass("weui-tab__bd-item--active");
		var i=$(".weui-tabbar__item").index(this);
		$(".weui-tab__bd-item").eq(i).addClass("weui-tab__bd-item--active");
	});
	$(".weui-tabbar__item:eq(0)").click(function(){
//		init("notice");
		$(".head-title>span:eq(0)").html("公告发布");
	});
	$(".weui-tabbar__item:eq(1)").click(function(){
//		init("vote");
		$(".head-title>span:eq(0)").html("发起投票");
	});
	$(".weui-tabbar__item:eq(2)").click(function(){
		$(".head-title>span:eq(0)").html("个人中心");
	});
	var type=getQueryString("type");
	if (type=="vote") {
		$(".weui-tabbar__item:eq(1)").click();
	}
	init("notice");
	init("vote");
	$("#actions,.glyphicon-th-large").click(function(){
		$.actions({
			actions: [{
				text: "刷新",
				onClick: function() {
					window.location.href="index.html?ver="+Math.random();
				}
			},{
				text: "预览",
				onClick: function() {
					window.location.href="../show/index.html?ver="+Math.random();
				}
			},{
				text: "建议留言",
				onClick: function() {
					window.location.href="../suggest.html";
				}
			}]
		});
	});
	$(".weui-btn_default:eq(0)").click(function(){
		$("#weuiAgree2").prop("checked",false);
		$("#uploaderFiles,#notice_id").html("");
		$("#saveNotice").attr("disabled","disabled");
		$("#saveNotice").css("background-color","#ccc");
	});
	$(".weui-btn_default:eq(1)").click(function(){
		$("#weuiAgree").prop("checked",false);
		$("#vote_uploaderFiles,#vote_id").html("");
		$("#saveVote").attr("disabled","disabled");
		$("#saveVote").css("background-color","#ccc");
	});
	//////////////公告信息保存////////////////
	$("#weuiAgree2").click(function(){
		if($(this).prop("checked")){
			$("#saveNotice").removeAttr("disabled");
			$("#saveNotice").css("background-color","");
		}else{
			$("#saveNotice").attr("disabled","disabled");
			$("#saveNotice").css("background-color","#ccc");
		}
	});
	$("#saveNotice").click(function(){
		var b=$("#weuiAgree2").prop("checked");
		if(!b){
			return;
		}
		var json={};
		json.noticeTitle=$("#noticeForm input[name='noticeTitle']").val();
		json.noticeDesc=$("#noticeDesc").val();
		json.showTime=$("input[name='showTime']").val();
		json.id=$("#notice_id").html();
		json.isComment=$("#notice #comment").prop("checked");
		json.isShow=$("#notice #isShow").prop("checked");
		json.isTop=$("#notice #isTop").prop("checked");
		json.agree=$("#notice #weuiAgree2").prop("checked");
		json.imgList=[];
		var imgs=$("#uploaderFiles>li>span").html();
		var b=false;
		if (imgs) {
			for (var i = 0; i < imgs.length; i++) {
				json.imgList.push(imgs[i]);
				b=true;
			}
		}
		if (json.noticeDesc&&json.noticeDesc!="") {
			b=true;
		}
		if(!json.noticeTitle){
			$.alert("请输入标题!","系统提示",function(){
				$("body").scrollTop(0);
				$("#noticeForm input[name='noticeTitle']").focus();
			});
		}else if(!b){
			$.alert("请输入内容或者直接上传图片!","系统提示",function(){
				$("#noticeDesc").focus();
			});
		}else if(!json.agree){
			$.alert("请阅读并确认《信息发布条款》！","系统提示");
		}else{
			$.showLoading();
			$.post("../releaseManager/saveNoticeInfo.do",json,function(data){
				 $.hideLoading();
				if(data.success){
					 $.modal({
						 "text":"提交成功,请等待审核!",
						 "buttons":[{"text":"再发布一个", onClick: function(){
							 $("#noticeForm button[type='reset']").click();
							 $("body").scrollTop(0);
							 }},
					         {text: "返回浏览页面", onClick: function(){window.location.href="../show/index.html?ver="+Math.random();}}]});
					 $("#notice_id").html(data.msg);
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
			$("#saveVote").attr("disabled","disabled");
			$("#saveVote").css("background-color","#ccc");
		}
	});
	$("#saveVote").click(function(){
		var b=$("#weuiAgree").prop("checked");
		if(!b){
			return;
		}
		var json={};
		json.voteTitle=$("#voteForm input[name='voteTitle']").val();
		json.beginTime=$("#voteForm input[name='beginTime']").val();
		json.endTime=$("#voteForm input[name='endTime']").val();
		json.voteDesc=$("#voteDesc").val();
		json.id=$("#vote_id").html();
		json.isComment=$("#vote #isComment").prop("checked");
		json.agree=$("#weuiAgree").prop("checked");
		json.imgList=[];
		var imgs=$("#vote_uploaderFiles>li>span").html();
		if (imgs) {
			for (var i = 0; i < imgs.length; i++) {
				json.imgList.push(imgs[i]);
			}
		}
		if(!json.voteTitle){
			$.alert("请输入标题!","系统提示",function(){
				$("body").scrollTop(0);
				$("#voteForm input[name='voteTitle']").focus();
			});
		}else if(!json.endTime){
			$.alert("请选择投票截止时间!","系统提示",function(){
				$("#voteForm input[name='endTime']").focus();
			});
		}else if(!json.voteDesc){
			$.alert("请投票描述!","系统提示",function(){
				$("#voteForm #voteDesc").focus();
			});
		}else if(!json.agree){
			$.alert("请阅读并确认《信息发布条款》！","系统提示");
		}else{
			$.showLoading();
			$.post("../releaseManager/saveVoteInfo.do",json,function(data){
				$.hideLoading();
				if(data.success){
					$.modal({
						 "text":"提交成功,请等待审核!",
						 "buttons":[{"text":"再发布一个", onClick: function(){
							 $("#voteForm button[type='reset']").click();
							 $("body").scrollTop(0);
							}},
					        {text: "返回浏览页面", onClick: function(){
					        	window.location.href="../show/index.html?type=2&ver="+Math.random();
					        }}]});
					 $("#vote_id").html(data.msg);
				}else{
					$.alert(data.msg,"系统提示");
				}
			});
		}
	});
	if(is_weixin()){
		weixinfileup.init();
	}
	$(".weui-uploader__info").html("");
	$(".weui-uploader__files").html("");
	$("#uploaderInput").click(function(){
		var imgPath="/temp/notice/"+Math.random()+".jpg";
		weixinfileup.imguploadToWeixin(this, imgPath, function(){
			var li=$('<li class="weui-uploader__file" style="background-image:url('+imgPath+')"><span class="badge">x</span><span>'+imgPath+'</span></li>');
			$("#uploaderFiles").append(li);
			badgeClick(li, imgUrl);
		});
	});
	$("#voteImgUpload").click(function(){
		var imgPath="/temp/vote/"+Math.random()+".jpg";
		weixinfileup.imguploadToWeixin(this, imgPath, function(){
			var li=$('<li class="weui-uploader__file" style="background-image:url('+imgPath+')"><span class="badge">x</span><span>'+imgPath+'</span></li>');
			$("#vote_uploaderFiles").append(li);
			badgeClick(li, imgUrl);
		});
		$("#vote_img_num").html($("#vote_uploaderFiles li").length);
	});
	//////////////////
});
//移除上传图片
function badgeClick(li,imgUrl){
	li.find(".badge").click({"imgUrl":imgUrl},function(event){
		$.post("../upload/removeTemp.do",{"imgUrl":event.data.imgUrl});
		$(this).parent().remove();
	});
}
//上传图片
function imageUpload(t,fileName,type){
//	$.showLoading();
	var li=$('<li class="weui-uploader__file"><span class="badge">x</span><span></span><div class="weui-uploader__file-content"></div></li>');
	if(type=="vote"){
		$("#vote_uploaderFiles").append(li);
		var len=$("#vote_uploaderFiles li").length;
		$("#vote_img_num").html((len-1)+"/"+len);
	}else{
		$("#uploaderFiles").append(li);
		var len=$("#uploaderFiles li").length;
		$("#img_num").html((len-1)+"/"+len);
	}
	ajaxUploadFile({
		"uploadUrl":"../upload/uploadImage.do?fileName="+fileName+"&type="+type,
		"msgId":"",
		"fileId":fileName,
		"msg":"img",
		"fid":"",
		"uploadFileSize":5
	},t,function(imgUrl){
		$.hideLoading();
		li.css("background-image","url(../"+imgUrl+"_sl)");
		li.find("span:eq(1)").html(imgUrl);
		if(type=="vote"){
			$("#vote_uploaderFiles").append(li);
			var len=$("#vote_uploaderFiles li").length;
			$("#vote_img_num").html(len+"/"+len);
		}else{
			$("#uploaderFiles").append(li);
			var len=$("#uploaderFiles li").length;
			$("#img_num").html(len+"/"+len);
		}
		badgeClick(li, imgUrl);
	});
}