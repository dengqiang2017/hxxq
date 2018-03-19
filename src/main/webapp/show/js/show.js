$(function() {
	FastClick.attach(document.body);
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
	$("#tpty,#tp_ly_edit").click(function(){
		$.prompt({
			  title: "留言",
			  text: "请文明发表意见并严格遵守国家相关法律规定,审核通过后显示!",
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
		});
	$("#wz1").click(function(){
		$("#about").popup();
		$("article").css("height",$("#about .weui-popup__modal").get(0).offsetHeight-100);
	});
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