<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="j" uri="/jop-tags" %>
<%@ taglib prefix="n" uri="/wagon-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName()	+ ":" + request.getServerPort() + contextPath + "/";
%>

<script>
	var contextPath="<%= contextPath %>";
</script>

<link rel="stylesheet" href="<%= contextPath %>/saas/css/reset.css" />
<link rel="stylesheet" href="<%= contextPath %>/saas/css/main.css" />
<link rel="stylesheet" href="<%= contextPath %>/saas/css/smain.css" />
<link rel="stylesheet" href="<%= contextPath %>/saas/css/rig.css" />

<link rel="stylesheet" href="<%= contextPath %>/saas/css/saas.css" />

<script type="text/javascript" src="<%= contextPath %>/saas/js/jquery-1.8.3.min.js" ></script>
<script type="text/javascript" src="<%= contextPath %>/saas/js/saas.js" ></script>
<script type="text/javascript" src="<%= contextPath %>/saas/js/index.js" ></script>
<script type="text/javascript" src="<%= contextPath %>/saas/js/SlidingLayer3.js" ></script>
<script type="text/javascript" src="<%= contextPath %>/saas/js/ajaxfileupload.js" ></script>
<script type="text/javascript" src="<%= contextPath %>/saas/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="<%= contextPath %>/saas/js/sessioncheck.js"></script>
<script type="text/javascript" src="<%= contextPath %>/saas/js/echarts.common.min.js"></script>
<script type="text/javascript" src="<%= contextPath %>/saas/js/report.js"></script>
<script type="text/javascript" src="<%= contextPath %>/saas/js/mask.js"></script>
<script type="text/javascript" src="<%= contextPath %>/saas/js/color.js"></script>
<script type="text/javascript" src="<%= contextPath %>/saas/js/iColorPicker.js"></script>

<script type="text/javascript" src="<%= contextPath %>/saas/js/saas_version_config.js"></script>

<script type="text/javascript" src="<%= contextPath %>/saas/js/zUI.js"></script>