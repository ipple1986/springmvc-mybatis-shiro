<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName()	+ ":" + request.getServerPort() + contextPath + "/";
	long v = System.currentTimeMillis();
%>

<script>
	var contextPath="<%= contextPath %>";
</script>

<link rel="stylesheet" href="<%= contextPath %>/statics/saas/css/reset.css?_v=<%=v%>" />
<link rel="stylesheet" href="<%= contextPath %>/statics/saas/css/main.css?_v=<%=v%>" />
<link rel="stylesheet" href="<%= contextPath %>/statics/saas/css/smain.css?_v=<%=v%>" />
<link rel="stylesheet" href="<%= contextPath %>/statics/saas/css/rig.css?_v=<%=v%>" />

<link rel="stylesheet" href="<%= contextPath %>/statics/saas/css/saas.css?_v=<%=v%>" />

<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/jquery-1.8.3.min.js?_v=<%=v%>" ></script>
<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/saas.js?_v=<%=v%>" ></script>
<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/index.js?_v=<%=v%>" ></script>
<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/SlidingLayer.js?_v=<%=v%>" ></script>
<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/ajaxfileupload.js?_v=<%=v%>" ></script>
<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/Validform_v5.3.2.js?_v=<%=v%>"></script>
<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/sessioncheck.js?_v=<%=v%>"></script>
<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/echarts.common.min.js?_v=<%=v%>"></script>
<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/report.js?_v=<%=v%>"></script>
<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/mask.js?_v=<%=v%>"></script>
<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/color.js?_v=<%=v%>"></script>
<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/iColorPicker.js?_v=<%=v%>"></script>

<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/saas_version_config.js?_v=<%=v%>"></script>

<script type="text/javascript" src="<%= contextPath %>/statics/saas/js/zUI.js?_v=<%=v%>"></script>

