var housingEstate=[];
window.uexOnload = function(type){
	////自动登录注册////
	var moblieMac=uexDevice.getInfo("10");//用户手机唯一识别码
	init(moblieMac);
};
$.get("../login/autoLogin.do",{"moblieMac":"1231464"},function(data){
	init("1231464");
});
function init(moblieMac){
	$.get("../user/getUserHousing.do",{"moblieMac":moblieMac},function(data){
		if (data&&data.length>0) {
			housingEstate=data;
			loadNoticeData(0, 0, data[0].housingEstate.id);
			loadVoteData(0, 0, data[0].housingEstate.id);
		}
	});
}

var noticeLoad={
		page:0,
		count:0,
		total:0,
		loading:false//状态标记
};
var voteLoad={
		page:0,
		count:0,
		total:0,
		loading:false//状态标记
};
$(function() {
	FastClick.attach(document.body);
	$("#noticeList").html("");
//	$("#voteList").html("");
	$("#tab1").infinite();
	$("#tab2").infinite();
	
	$("#tab1").infinite().on("infinite", function() {
	  if(noticeLoad.loading) return;
	  noticeLoad.loading = true;
	  loadNoticeData(noticeLoad.page, noticeLoad.count, housingEstate);
	});
	$("#tab2").infinite().on("infinite", function() {
		if(voteLoad.loading) return;
		voteLoad.loading = true;
		loadVoteData(voteLoad.page, voteLoad.count, housingEstate);
	});
	
	$("input,textarea").focus(function(){
		$(".weui-tabbar").css("position","relative");
	});
	$("input,textarea").blur(function(){
		$(".weui-tabbar").css("position","absolute");
	});
	$(".weui-tabbar__item:eq(0)").click(function(){
		$(".head-title>span:eq(0)").html("信息列表");
	});
	$(".weui-tabbar__item:eq(1)").click(function(){
		$(".head-title>span:eq(0)").html("投票列表");
	});
	$(".weui-tabbar__item:eq(2)").click(function(){
		$(".head-title>span:eq(0)").html("个人中心");
	});
	$("#zl_edit").click(function(){
		$.get("zl_edit.html?ver="+Math.random(),function(data){
			$("#zl_html").html(data);
			$("#zl_edit_dia").popup();
		});
	});
	$("article").css("height",window.height-100);
	/////////////////////
	$("#actions,.glyphicon-th-large").click(function(){
		$.actions({
			actions: [{
				text: "刷新",
				onClick: function() {
					window.location.href="index.html?ver="+Math.random();
				}
			},{
				text: "发布公告",
				onClick: function() {
					window.location.href="../release/index.html?type=notice";
				}
			},{
				text: "发起投票",
				onClick: function() {
					window.location.href="../release/index.html?type=vote";
				}
			},{
				text: "意见建议",
				onClick: function() {
//					$("#myModal").modal("toggle");
					$.prompt({
						  title: "留言",
						  text: "意见建议",
						  input:"",
						  empty: false, // 是否允许为空
						  onOK: function (text) {
						    //点击确认
							  $.confirm(text);
							  $.toast("操作成功");
						  },
						  onCancel: function () {
						    //点击取消
						  }
						});
				}
			}]
		});
	});
	////////////////
});

function loadNoticeData(page,count,housingEstate){
	$.get("../releaseManager/getNoticePage.do",{
		"rows":10,"page":page,"count":count,"housingEstate":housingEstate
		},function(data){
		if (data.rows&&data.rows.length) {
			for (var i = 0; i < data.rows.length; i++) {
				var n = data.rows[i];
				var item=$($("#noticeItem").html());
				$("#noticeList").append(item);
				if(n.top){
					item.find(".weui-media-box__title").html("【置顶】"+n.noticeTitle);
				}else{
					item.find(".weui-media-box__title").html(n.noticeTitle);
				}
				item.find(".weui-media-box__desc").html(n.noticeDesc.substr(0,150)+"...");
				item.click(n,function(event){
					$("#about").popup();
					$("#noticeTitle").html(event.data.noticeTitle);
					var time=new Date(event.data.creationTime);
					$("#noticeInfo").html("发布人:"+event.data.founder.userName+"&ensp;发布时间:"+time.Format("yyyy-MM-dd"));
					$(".weui-article").html(event.data.noticeDesc);
					if (event.data.fileList&&event.data.fileList.length>0) {
						for (var i = 0; i < event.data.fileList.length; i++) {
							var img=event.data.fileList[i].filePath;
							if (/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(img)) {
								$(".weui-article").append("<img src='../"+img+"'>");
							}else{
								if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(img)&&img.indexOf("_sl")==-1) {
									var name=event.data.fileList[i].fileName;
									$(".weui-article").append("<a href='../"+img+"'>"+name+"</a>");
								}
							}
						}
					}
				});
			}
			noticeLoad.loading=false;
		}else{
			noticeLoad.loading=true;
		}
		$("#tab1").find(".weui-loadmore").hide();
	});
}
function loadVoteData(page,count,housingEstate){
	$.get("../releaseManager/getVotePage.do",{
		"rows":10,"page":page,"count":count,"housingEstate":housingEstate
		},function(data){
		if (data.rows&&data.rows.length) {
			for (var i = 0; i < data.rows.length; i++) {
				var n = data.rows[i];
				var item=$($("#voteItem").html());
				$("#voteList").append(item);
				item.find("#voteTitle").html(n.voteTitle);
				item.find("#tpty").click(function(){
					tpyj($(this).parents(".item"),"ty");
				});
				item.find("#tpfd").click(function(){
					tpyj($(this).parents(".item"),"fd");
				});
				item.find("#voteTitle").click(n,function(event){
					$("#about").popup();
					$("#noticeTitle").html(event.data.voteTitle);
					var time=new Date(event.data.creationTime);
					$("#noticeInfo").html("发布人:"+event.data.founder.userName+"&ensp;发布时间:"+time.Format("yyyy-MM-dd"));
					$(".weui-article").html(event.data.voteDesc);
				});
			}
			voteLoad.loading=false;
		}else{
			voteLoad.loading=true;
		}
		$("#tab2").find(".weui-loadmore").hide();
	});
}
function tpyj(t,type){
	$.prompt({
		  title: "投票留言",
		  text: "请文明发表意见并严格遵守国家相关法律规定!",
		  input:"",
		  empty: false, // 是否允许为空
		  onOK: function (text) {
		    //点击确认
//			  $.confirm(text);
			  $.toast("操作成功");
			  t.find(".tpbtn").hide();
			  t.find("#tpjg").show();
		  },
		  onCancel: function () {
		    //点击取消
			  t.find(".tpbtn").hide();
			  t.find("#tpjg").show();
		  }
		});
}