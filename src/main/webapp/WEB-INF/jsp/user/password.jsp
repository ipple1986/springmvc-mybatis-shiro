<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/statics/saas/inc/head.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改密码</title>
<script type="text/javascript">
/* 	function dosave(){
		$.post("passord_old.do",
			{
				old:$("#old").val(),
			},
			function(data,status) {
				alert(data+status);
			});
	} */
	function dosave(){
		$.getJSON("<%=request.getContextPath()%>/user/doChangePassword",
				{
					oldpwd:$("#old").val(),
					newpwd:$("#newp").val(),
					accept:$("#accept").val()
				},function(json){
					/* alert(json.msg+json.status); */
					if (json.status!='0') {
					$("#excelError").html(json.msg);
					} else {
						saasPopInstance.closeAndAlertOKTwo('修改密码成功！');
					}
				}
				
		);
	}
</script>
<style>
	#pop_box{
		height:350px;
	}
	#excelError{
		height:30px;
	}
	.form_jg p .form_span_right{
		overflow:hidden;
	}
</style>
</head>
<body class="saas_pop">
<div id="pop_box" class="pop_box xgmm">
	<div class="main_ul_title"><strong>修改密码</strong><div class="main_ul_title_btnbox"><button class="bg_back" onclick="saasPopInstance.close();">返回</button><button type="button" onclick="dosave();">保存</button></div></div>
    <div class="pop_xz">
   		       <div  class="dr_cont">
    <div id="excelError" class="dr_ps"></div></div>
      <form class="form_jg">

    	<p><span class="form_span_left"><b></b>旧密码</span><span class="form_span_right">
        <input style="height:30px" name="old" id="old" type="password" class="input_text">
        </span></p>
        <p><span class="form_span_left"><b></b>新密码</span><span class="form_span_right">
        <input style="height:30px" name="new" id="newp" type="password" class="input_text">
        </span></p>
        <p><span class="form_span_left"><b></b>确认密码</span><span class="form_span_right">
        <input style="height:30px" name="accept" id="accept" type="password" class="input_text">
        </span></p>
      </form>
    </div>
</div>
</body>
</html>
