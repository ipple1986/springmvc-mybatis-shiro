<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>${logoName }</title>
	<%@ include file="/statics/saas/inc/head.inc" %>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv=“X-UA-Compatible” content=“IE=8″ />
	<meta name="renderer" content="webkit" />
	
	<style>
		.select_box_2 .select_box_qy {
			background: none;
			padding-left: 10px;
		}
		
		.select_box_2 .select_box_city_on {
			background: none;
			padding-left: 25px;
		}
		
		.cont_search_select_cont {
			max-height: none;
			min-height: 420px;
		}
		
		iframe[src="system/password_doInCfg.do"] {
			height: 350px;
		}
		
		/*以下为新增样式*/
		iframe.content {
			margin-left: 200px;
			padding-top: 80px;
			display: block;
			width: 1080px;
			min-height: calc(100vh - 80px);
			background: #f2f2f2 url(<%= basePath %>/statics/saas/images/bg_main_left.png) repeat-y;
		}
		
		input::-ms-clear {
			display: none;
		}
		
		.index_nav_wrap {
			width: 100%;
		}
		
		.index_main_nav {
			border-top: 1px solid #dfdfdf;
		}
		
		#select-box {
			width: 170px;
			background: #fff;
			margin: 20px auto;
			position: relative;
		}
		
		.select-mask {
			position: fixed;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			z-index: 998;
			display: none;
		}
		
		#search-text {
			width: 138px;
			position: relative;
			z-index: 999;
			background: #fff url(<%= basePath %>/statics/saas/images/arrow1.png) no-repeat 96% center;
			margin: 0 auto;
			display: block;
			padding: 0 25px 0 5px;
			height: 34px;
			border: 1px solid #ccc;
			outline: none;
			font-size: 14px;
			color: #666;
			font-family: "微软雅黑";
			cursor: pointer;
		}
		
		#search-text.on {
			background-image: url(<%= basePath %>/statics/saas/images/arrow3.png) !important;
		}
		
		#search-text.orange {
			color: #ff5400;
		}
		
		#search-text:hover {
			border-color: #ff5400;
		}
		
		#search-text:focus {
			border-color: #ff5400;
		}
		
		#select-tree {
			background: #fff;
			overflow-x: hidden;
			overflow-y: auto;
			display: none;
			position: absolute;
			left: 0;
			width: 170px;
			z-index: 999;
		}
		
		#select-tree ul {
			width: 100%;
		}
		
		#select-tree li {
			line-height: 30px;
		}
		
		#select-tree>ul>li:first-child b {
			display: none;
		}
		
		#select-tree>ul>li:first-child span {
			margin: 0;
		}
		
		#select-tree>ul>li:first-child a {
			padding-left: 22px;
		}
		
		#select-tree li p {
			padding-right: 10px;
		}
		
		#select-tree li p:hover {
			background: #fff3ed;
		}
		
		#select-tree li p:hover a {
			color: #ff5500;
		}
		
		#select-tree li i {
			color: #ff5400;
			font-style: normal;
		}
		
		#select-tree li b {
			display: block;
			width: 22px;
			height: 30px;
			float: left;
			background: url(<%= basePath %>/statics/saas/images/arrow2.png) no-repeat 8px center;
		}
		
		#select-tree li b.on {
			background-image: url(<%= basePath %>/statics/saas/images/arrow1.png);
			background-position-x: 6px;
		}
		
		#select-tree li li b.on {
			background-position-x: 20px;
		}
		
		#select-tree li li li b.on {
			background-position-x: 34px;
		}
		
		#select-tree li span {
			margin-left: 22px;
			display: block;
		}
		
		#select-tree li li b {
			width: 36px;
			background-position-x: 22px;
		}
		
		#select-tree li li span {
			margin-left: 36px;
		}
		
		#select-tree li li li b {
			width: 50px;
			background-position-x: 36px;
		}
		
		#select-tree li li li span {
			margin-left: 50px;
		}
		
		#select-tree li li li li a {
			padding-left: 64px;
			display: block;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
		}
		
		#select-tree ul ul {
			display: none;
		}
		
		#select-tree li a {
			font-size: 14px;
			display: block;
			color: #333;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
		}
		
		#select-tree a.on {
			color: #ff5500;
		}
		
		.nav-sub-box {
			padding: 20px 0 0;
			display: none;
		}
		
		.nav-sub-box ul li {
			line-height: 40px;
		}
		
		.nav-sub-box li p {
			padding: 0 15px;
		}
		
		.nav-sub-box li p b {
			display: block;
			width: 22px;
			height: 40px;
			float: left;
			background: url(<%= basePath %>/statics/saas/images/arrow2.png) no-repeat 8px center;
		}
		
		.nav-sub-box li a {
			font-size: 14px;
			display: block;
			padding-left: 22px;
		}
		
		.nav-sub-box li li b {
			width: 36px;
			background-position-x: 22px;
		}
		
		.nav-sub-box li li a {
			padding-left: 36px;
		}
		
		.nav-sub-box li li li a {
			padding-left: 50px;
		}
		
		.nav-sub-box li b.on {
			background-image: url(<%= basePath %>/statics/saas/images/arrow1.png);
			background-position-x: 6px;
		}
		
		.nav-sub-box li li b.on {
			background-position-x: 20px;
		}
		
		.nav-sub-box li li li b.on {
			background-position-x: 34px;
		}
		
		.nav-sub-box ul ul {
			display: none;
		}
		
		.nav-sub-box a.on {
			color: #ff5500;
		}
		
		.nav-sub-box li p:hover {
			background: #fff3ed;
		}
		
		.nav-sub-box li p:hover a {
			color: #ff5500;
		}
		
		.zUIpanelScrollBox, .zUIpanelScrollBar {
			width: 6px;
			top: 4px;
			right: 0px;
		}
		
		.zUIpanelScrollBar {
			background: #d7d7d7;
		}
		
		#select-tree>ul>li>p {
			position: relative;
		}
		
		#select-tree>ul>li>p>b.b_first {
			position: absolute;
			height: 30px;
			width: 100%;
			top: 0;
			left: 0;
		}
	</style>
	
	<script>
		$(function() {
			new SlidingLayer($("#sl_1"),$("#sl_2"));
		});
	</script>
	
	<script>
		$(function() {
			var href = window.location.href;
			var arr = /^.*\?(.*)$/.exec(href);
			frames[0].location.href = "<%= contextPath %>/saas/jsp/souyeiframe.jsp";
			
			//将默认iframe显示，删除其他所有iframe
			$('#indexIframe').show().siblings('iframe').remove();
			
			new SlidingLayer($("a.login_name"),$("a.changepass"));
		});
		
		function doiframe(url){
			frames[0].location.href = "<%= contextPath %>/"+url;
			
			//将默认iframe显示，删除其他所有iframe
			$('#indexIframe').show().siblings('iframe').remove();
		}
		
		function dosouye(){
			if($("#shouye").attr("class")=='a_on')
				return;
			frames[0].location.href = "<%= contextPath %>/saas/jsp/souyeiframe.jsp";
			
			//将默认iframe显示，删除其他所有iframe
			$('#indexIframe').show().siblings('iframe').remove();
		}
	</script>
	
	<script type="text/javascript">
		function changepassword(){
			new SaasPopupLayer('<%= contextPath %>/user/password',{width:'520px',height:'900px'});
		}
	</script>
</head>

<body>
<!-- 	<form action="login.do" method="post" id="tempform" style="display: none;">
		<input type="hidden" id="user"  name="user.oprcode" value ="" />
		<input type="hidden" id="password" name="user.password" value =""/>
		<input type="hidden" id="telephone" name="telephone" value =""/>
	</form> -->
	
	<div class="header_warp" style="background: ${background};">
		<div class="header" style="background: ${background};"> 
			<div class="header_title"><img src="<%= contextPath %>/statics/saas/images/logo/afterLogo.png" id="imgId" <%-- width="${logoLength }" height="${logoWidth }" --%> width="70" height="40" ><span style="color:${textcolor};">
			<c:if test="${isShow==1}">${logoName }</c:if>聚房云服务平台
			</span></div>
			<div class="login_list">
				<a href="javascript:;" class="login_name" style="border-right: 1px solid ${background};color:${textcolor};background:url(<%= contextPath %>/statics/saas/images/icon_t_pic_<c:choose><c:when test="${textcolor=='#333333' }">333333</c:when><c:otherwise>FFFFFF</c:otherwise></c:choose>.png) no-repeat 0 2px;" >${sessionScope.loginUser.account}
				<span style="background:url(<%= contextPath %>/statics/saas/images/icon_t_down_<c:choose><c:when test="textcolor=='#333333'">333333</c:when><c:otherwise>FFFFFF</c:otherwise></c:choose>.png) no-repeat 0 2px;" ></span></a> 
				<a href="<%= contextPath %>/logout" class="logout" style="color:${textcolor};background:url(<%= contextPath %>/statics/saas/images/icon_t_close_<c:choose><c:when test="textcolor=='#333333'">333333</c:when><c:otherwise>FFFFFF</c:otherwise></c:choose>.png) no-repeat 0 2px;">退出</a> <a class="changepass" href="javascript:;" onclick="changepassword();">修改密码</a> </div>
		</div>
	</div>
        
	<div class="body_warp">
		<div class="conter">
			<div class="index_nav">
				<div class="index_nav_wrap">
					<!-- 新版下拉 start -->
					<div id="select-box">
						<div class="select-mask"></div>
						<!-- <s:hidden name="#topOrgVo.orgName" /> -->
						<input type="text" id="search-text" value="${topOrgVo.orgName}" <c:if test='${sessionScope.session_onlyOneEstate}'>disabled="true" style="cursor: default; border-color: #ccc;"</c:if> />
						<div id="select-tree">
							<ul>
								<!-- <s:if test='#session.operator.newOrgCode != null && #session.operator.newOrgCode == "001"'>
									<li><p><b></b><span><a id="orgTree_001" href="javascript:;" onclick="doInOrg('001')">平台</a></span></p></li>
								</s:if>
								<s:else>
									<li><p><b></b><span></span></p></li>
								</s:else> -->
								
<c:choose> 
  <c:when test="${sessionScope.operator.newOrgCode != null && sessionScope.operator.newOrgCode=='001'}" >   
  	 <li><p><b></b><span><a id="orgTree_001" href="javascript:;" onclick="doInOrg('001')">平台</a></span></p></li>
  </c:when> 
  <c:otherwise>   
  	<li><p><b></b><span></span></p></li>
  </c:otherwise> 
</c:choose> 
								
								<%-- 
								<s:iterator value="orgList">
									<li>
										<p><s:if test='orgTypeCode < 5'><b></b></s:if><span><a id="orgTree_${orgCode}" href="javascript:;" onclick="doInOrg('${orgCode}')" title="${orgName}">${orgName}</a></span></p>
										
										<ul>
											<s:iterator value="listOrg">
												<li>
													<p><s:if test='orgTypeCode < 5'><b></b></s:if><span><a id="orgTree_${orgCode}" href="javascript:;" onclick="doInOrg('${orgCode}')" title="${orgName}">${orgName}</a></span></p>
													
													<ul>
														<s:iterator value="listOrg">
															<li>
																<p><s:if test='orgTypeCode < 5'><b></b></s:if><span><a id="orgTree_${orgCode}" href="javascript:;" onclick="doInOrg('${orgCode}')" title="${orgName}">${orgName}</a></span></p>
																
																<ul>
																	<s:iterator value="listOrg">
																		<li>
																			<p><a id="orgTree_${orgCode}" href="javascript:;" onclick="doInOrg('${orgCode}')" title="${orgName}">${orgName}</a></p>
																		</li>
																	</s:iterator>
																</ul>
															</li>
														</s:iterator>
													</ul>
												</li>
											</s:iterator>
										</ul>
									</li>
								</s:iterator> --%>
								
							</ul>
						</div>
					</div>
					<!-- 新版下拉 end -->
			
					<div class="index_main_nav">
						<p><a id="shouye" href="" onclick="dosouye();" style="background: #F2F2F2;" class="a_on"><span class="main_nav_icon_1"></span>首页</a></p>
		                
						<%-- <s:iterator value="dp.datas">
							<s:iterator value="moduleVOList">
								<p><a href=""><span class="main_nav_icon_4"></span><s:property value="moduleName" /></a></p>
								
								<ul class="sub_nav hide">
									<s:iterator value="moduleVOList">
										<c:if test="${moduleView==1}">
							                		<li><a onclick="hrefLink('<s:property value="moduleUri" />')"  href="" target="0"><span class="<s:property value="icoPath" />"></span><s:property value="moduleName" /></a></li>
										</c:if>
										<c:if test="${qhorg.orgTypeCode==5 && moduleView==2}">
							                		<li><a onclick="hrefLink('<s:property value="moduleUri" />')"  href="" target="0"><span class="<s:property value="icoPath" />"></span><s:property value="moduleName" /></a></li>
										</c:if>
										<c:if test="${qhorg.orgTypeCode==1 && moduleView==0}">
							                		<li><a onclick="hrefLink('<s:property value="moduleUri" />')"  href="" target="0"><span class="<s:property value="icoPath" />"></span><s:property value="moduleName" /></a></li>
										</c:if>
										<c:if test="${qhorg.orgTypeCode>4 && moduleView==3}">
							                		<li><a onclick="hrefLink('<s:property value="moduleUri" />')"  href="" target="0"><span class="<s:property value="icoPath" />"></span><s:property value="moduleName" /></a></li>
										</c:if>
									</s:iterator>
								</ul>
							</s:iterator>
						</s:iterator> --%>
						<!--<p><a href=""><span class="main_nav_icon_5"></span>消息中心</a></p>-->
					</div>
				</div>
			</div>
		
    		<iframe id="indexIframe" class="content" name="content" src="about:blank" scrolling="no" frameborder="0"> </iframe>
		</div>
	
		<div class="copyright">
		    <p>广州聚房宝网络科技股份有限公司</p>
		    <p><script type="text/javascript">document.write(saas_version_data[saas_version]['index_page_msg']);</script>热线：<br>
		              400-066-3988</p>
		</div>
	</div>
</body>


<script type="text/javascript">
	function hrefLink(link){
		if(link!=""){
			$("#indexIframe").attr("src",'<%=request.getContextPath()%>/'+link);
			
			//将默认iframe显示，删除其他所有iframe
			$('#indexIframe').show().siblings('iframe').remove();
		}
		$("body").scrollTop(0);
	}
	
	function doInOrg(orgCode){
		window.location.href='<%=request.getContextPath()%>/dochangeorg.do?orgCode='+orgCode;
	}
</script>

<!-- 新版导航下拉js start -->
<script type="text/javascript">
	//计算iframe高度
	$("#indexIframe").load(function(){
		var iframH = $(this).contents().find("html").outerHeight(true);
		$(this).css("height", iframH+"px");
	});
	$(function(){
		//自定义滚动条调用
		$("#select-tree").panel({ iWheelStep:32 });
		$(".index_nav").panel({ iWheelStep:32 });

		//动态计算侧边栏高度
		$("#select-tree").css("height", $(window).height()-220+"px");
		$(".index_nav").css("height", $(window).height()-148+"px");
		$("#indexIframe").css("min-height", $(window).height()-80+"px");
		$(window).resize(function(){
			$("#select-tree").css("height", $(window).height()-220+"px");
			$(".index_nav").css("height", $(window).height()-148+"px");
			$("#indexIframe").css("min-height", $(window).height()-80+"px");
		});
		
		//平台显示隐藏
		$("#search-text").click(function(){
			if($("#select-tree").is(":hidden")){
				$("#select-tree").show();
				$("#search-text").addClass("on");
				$(".select-mask").show();
			}else{
				$("#select-tree").hide();
				$("#search-text").removeClass("on");
				$(".select-mask").hide();
			}
		});
		
		//点击其他区域隐藏平台下拉
		$(".select-mask").click(function(){
			$("#select-tree").hide();
			$("#search-text").removeClass("on");
			$(".select-mask").hide();
		});
		
		//平台下拉展开
		$("#select-tree>ul>li>p>b").addClass("b_first");
		$("#select-tree li b").click(function(){
			var _this = $(this);
			if(_this.hasClass("b_first")){
				//全部展开
				_this.parents("li").find("b").addClass("on");
				_this.parents("li").find("ul").slideDown(200, function(){
					$("#select-tree").trigger("mouseleave").trigger("mouseenter");
					_this.removeClass("b_first");
				});
				_this.parents("li").siblings("li").children("p").find("b").removeClass("on");
				_this.parents("li").siblings("li").children("ul").slideUp(200, function(){
					$("#select-tree").trigger("mouseleave").trigger("mouseenter");
					_this.parents("li").siblings("li").children("p").find("b").addClass("b_first");
				});
			}else{
				if(_this.parent("p").next("ul").is(":visible")){
					_this.removeClass("on");
					_this.parent().next().slideUp(200, function(){
						$("#select-tree").trigger("mouseleave").trigger("mouseenter");	//触发滚动条计算
					});
				}else{
					_this.parents("li").siblings("li").children("ul").stop(false, true).slideUp(200);
					_this.parents("li").siblings("li").children("p").find("b").removeClass("on");
					
					_this.addClass("on");
					_this.parent().next().slideDown(200, function(){
						$("#select-tree").trigger("mouseleave").trigger("mouseenter");
					});
				}
			}
			
		});
		
		//平台下拉选择
		var timer2 = null;
		$("#select-tree").on("click", "a", function(){
			$("#search-text").removeClass("on");
			$("#select-tree a").removeClass("on");
			$(this).addClass("on");
			$("#select-tree").hide();
			$(".select-mask").hide();
			
			var _text = $(this).text();
			$("#search-text").val(_text);
			$("#search-text").addClass("orange");
			clearTimeout(timer2);
			timer2 = setTimeout(function(){
				$("#search-text").removeClass("orange");
			}, 2000);
			
			$("#select-tree li i").each(function(){
				$(this).parent().text($(this).parent().text());
			});
		});
		
		//平台搜索
		var timer = null;
		$("#search-text").on("input propertychange", function(e){		
			var _this = $(this);
			clearTimeout(timer);
			timer = setTimeout(function(){
				$("#select-tree").show();
				var _value = _this.val();
				if(_value != ""){
					$("#select-tree ul ul").stop(false, true).slideUp(200);
					$("#select-tree b").removeClass("on");
					$("#select-tree li a").each(function(i){
						var valObj = $(this).filter(":contains("+_value+")");
						var _text = valObj.text();
						var rt = _text.replace(_value, "<i>"+_value+"</i>");
						valObj.html(rt);
						valObj.parents("ul").stop(false, true).slideDown(200, function(){
							$("#select-tree").trigger("mouseleave").trigger("mouseenter");
						});
						valObj.parents("ul").prev("p").find("b").addClass("on");
					});
				}else{
					$("#select-tree li a i").each(function(i){
						var parText = $(this).parent().text();
						$(this).parent().text(parText);
						$("#select-tree b").removeClass("on");
						$("#select-tree ul ul").stop(false, true).slideUp(200, function(){
							$("#select-tree").trigger("mouseleave").trigger("mouseenter");
						});
					});
				}
			}, 100);
		});
			
		//二级导航展开
		$(".nav-sub-box li p").click(function(){
			var _this = $(this);
			if(_this.next("ul").length <= 0){
				$(".nav-sub-box li a").removeClass("on");
				_this.find("a").addClass("on");
				var iframeUrl = $(this).find("a").attr("url");
				$("#indexIframe").attr("src", iframeUrl);
				
				//将默认iframe显示，删除其他所有iframe
				$('#indexIframe').show().siblings('iframe').remove();
			}else{
				if(_this.next("ul").is(":visible")){
					_this.find("b").removeClass("on");
					_this.next().slideUp(200, function(){
						$(".index_nav").trigger("mouseleave").trigger("mouseenter");
					});
				}else{
					_this.find("b").addClass("on");
					_this.parent().siblings("li").children("ul").stop(false, true).slideUp(200);
					_this.parent().siblings("li").children("p").find("b").removeClass("on");
					_this.next().slideDown(200, function(){
						$(".index_nav").trigger("mouseleave").trigger("mouseenter");
					});
				}
			}
		});
		
		//二级导航没有下级就隐藏箭头
		$(".nav-sub-box li p").each(function(){
			if($(this).next("ul").length <= 0){
				$(this).find("b").hide();
			}else{
				$(this).find("a").css({
					"cursor": "default",
					"color": "#333333"
				});
			}
		});
		
		//将被选择的组织字体设为红色，并且打开展开效果
		var thisTreeOrgCode = '${topOrgVo.orgCode}';
		var $thisTreeOrgObj = $("#orgTree_"+thisTreeOrgCode);
		
		if ($thisTreeOrgObj.length > 0) {
			$thisTreeOrgObj.attr("class", "on");
			
			$("#select-tree ul ul").stop(false, true).slideUp(200);
			$("#select-tree b").removeClass("on");
			
			$thisTreeOrgObj.parents("ul").stop(false, true).slideDown(200, function() {
				$("#select-tree").trigger("mouseleave").trigger("mouseenter");
			});
			
			$thisTreeOrgObj.parents("ul").prev("p").find("b").addClass("on");
		}
		
		//输入框绑定失去焦点事件
		$("#search-text").bind({ 
			blur: function() {
				var orgName = document.all['#topOrgVo.orgName'].value;
				
				if (this.value == "" || this.value != orgName) {
					this.value = orgName;
				}
			}
		});
	});
</script>
<!-- 新版导航下拉js end -->
</html>