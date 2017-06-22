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
        <div class="tips_butt"><a href="javascript:doOk();" class="butt_red">确认</a><a href="javascript:doCancel();" class="butt_gray">取消</a></div>
    </div>
</div>

<script type="text/javascript">

	function doOk(){
		var params =  new Array(<%=new String(request.getParameter("params").toString().getBytes("iso8859-1"),"utf-8")%>);
		var callback = eval("parent.window.frames['content']."+'<%=new String(request.getParameter("callback").toString().getBytes("iso8859-1"),"utf-8")%>');
		
		if(params.length == 0){
			callback();
		}else if(params.length == 1){
			callback(params);
		}else if(params.length == 2){
			callback(params[0], params[1]);
		}else if(params.length == 3){
			callback(params[0], params[1], params[2]);
		}else if(params.length == 4){
			callback(params[0], params[1], params[2], params[3]);
		}else if(params.length == 5){
			callback(params[0], params[1], params[2], params[3], params[4]);
		}else if(params.length == 6){
			callback(params[0], params[1], params[2], params[3], params[4], params[5]);
		}else{
			callback(params[0], params[1], params[2], params[3], params[4], params[5], params[6]);
		}
		saasPopInstance.close();
	}

	function doCancel(){
		saasPopInstance.close();
	}
</script>
</body>
</html>
