<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/statics/saas/inc/head.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>确定</title>
	<style>
		.tips_butt a {
			margin: 0px 60px;
		}
	</style>
</head>
<body style="background:rgba(0,0,0,0)">
<div class="pop_tips">
	<div class="tips_title">提示信息</div>
    <div class="tips_cont">
    	<div class="tips_cont_p"><%=new String(request.getParameter("msg").toString().getBytes("iso8859-1"),"utf-8")%></div>
        <div class="tips_butt"><a href="javascript:;" onclick="doOk();" class="butt_red">确认</a></div>
    </div>
</div>
</body>

<script type="text/javascript">
	function doOk(){
		var url = '<%= request.getParameter("url") == null ? "" : new String(request.getParameter("url").toString().getBytes("iso8859-1"),"utf-8")%>';
		var frame = parent.window.frames['content'];
		var str = navigator.userAgent;
		if(str.indexOf("MSIE") > -1 || (str.toLowerCase().indexOf("trident") > -1 && str.indexOf("rv") > -1)) {
			$(parent.document).find('#saas_pop').remove();
			if(url != ''){
				frame.window.location.href = url;
			}
		}else{
			if(url != ''){
				frame.window.location.href = url;
			}
			saasPopInstance.close();
		}
	}
</script>

</html>
