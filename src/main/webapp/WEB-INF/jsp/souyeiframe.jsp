<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>SaaS平台</title>
<%@ include file="/statics/saas/inc/head.inc" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit" />
</head>
<style>
	html{
		background: url(../images/bg_main_left.png) repeat-y #f5f5f5;
	}
	.main_ul_p{
		overflow:hidden;
	}
</style>
<script type="text/javascript">
$(function(){ 
	$.get("shouye_IsHint.do" , function(data) {
		if (data=="close") {
			$(".main_notice").css("display","none");
		} else {
			
		}
		
	});
	
	
})
function pop_hint(){
	new SaasPopupLayer("<%= contextPath %>/saas/jsp/common/hint.jsp?msgcode=not_open", {width:'520px', height:'300px'});
}
function hiddenNotice(){
	$.get("shouye_Hint.do", function(data) {
		$(".main_notice").css("display","none");
	});
}

//弹出聚客宝窗口
function openJKB(){
	window.open("http://juke8.cn/");
}


</script>
<body>
<div class="main_cont">
<div class="main_notice" style="display:none;">
<span></span><p>欢迎加入聚房云服务平台，我们将为您提供更优质的服务！</p><a href="javascript:;" onclick="hiddenNotice();"></a>
</div>
<div class="main_ad"><img src="<%= contextPath %>/saas/images/index_ad.png"></div>

<div class="main_ul_p">

	<div class="main_ul_p_left">
		<div class="main_ul_title"><strong>运营管控</strong></div>
		<div class="main_ul_cont">
			<a href="<%= contextPath %>/system/module_dotoModular.do?moduleCode=YPT_YUNYING_KEEPER&&subMenu=YPT_WORKSPACE" target="_parent"><strong><img src="<%= contextPath %>/saas/images/icon_index_list/work.png"></strong><span>工作台</span></a>
			<a href="<%= contextPath %>/system/module_dotoModular.do?moduleCode=YPT_YUNYING_KEEPER&&subMenu=YPT_MAG_CLIENT" target="_parent"><strong><img src="<%= contextPath %>/saas/images/icon_index_list/icon_10.png"></strong><span>客户管理</span></a>
			
			<script type="text/javascript">
			    function sjbb_diy(){
			    	document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
					document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_11.png'>");
					document.write("</strong><span>数据报表</span></a>");
			    }
			    function sjbb(){
			    	document.write("<a href='<%= contextPath%>/system/module_dotoModular.do?moduleCode=YPT_YUNYING_KEEPER&&subMenu=YPT_DATA_REPORT' target='_parent'><strong>");
					document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/icon_11.png'>");
					document.write("</strong><span>数据报表</span></a>");
			    }
				function isWrite1(){
					<%-- if(saas_version == 1){
						document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
						document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_11.png'>");
						document.write("</strong><span>数据报表</span></a>");
					}else{
						document.write("<a href='<%= contextPath%>/system/module_dotoModular.do?moduleCode=YPT_YUNYING_KEEPER&&subMenu=YPT_DATA_REPORT' target='_parent'><strong>");
						document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_11.png'>");
						document.write("</strong><span>数据报表</span></a>");
					} --%>
					switch(saas_version)
					{
					case 1:
						sjbb_diy();
					  break;
					default:
						sjbb();
					}
				}
				isWrite1();
				
				
			</script>
			<!-- <a href="javascript:;" onclick="pop_hint();"><strong> -->
			<!--<a href="<%= contextPath %>/system/module_dotoModular.do?moduleCode=YPT_YUNYING_KEEPER&&subMenu=YPT_DATA_REPORT" target="_parent"><strong>
				<script type="text/javascript">document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_11.png'>");</script>
			</strong><span>数据报表</span></a>-->
		</div>
	</div>

	<div class="main_ul_p_right">
		<div class="main_ul_title"><strong>案场管控</strong></div>
		<div class="main_ul_cont">
		
			
			<a href="<%= contextPath %>/system/module_dotoModular.do?moduleCode=YPT_ANCHANG_KEEPER&&subMenu=YPT_ANCHANG_MAG" target="_parent"><strong><img src="<%= contextPath %>/saas/images/icon_index_list/icon_1.png"></strong><span>移动销售</span></a>
			<a href="<%= contextPath %>/system/module_dotoModular.do?moduleCode=YPT_ANCHANG_KEEPER&&subMenu=YPT_CONTACT_CENTER" target="_parent"><strong><img src="<%= contextPath %>/saas/images/icon_index_list/icon_2.png"></strong><span>呼叫中心</span></a>
			<%-- <a href="javascript:;" onclick="pop_hint();"><strong>
				<script type="text/javascript">document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_12.png'>");</script>
			</strong><span>MORE</span></a>	 --%>
			<script type="text/javascript">
			
			  function moreButton12_diy(){
			    	document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
			    	document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_12.png'>");
					document.write("</strong><span>MORE</span></a>");
			    }			    
			    function moreButton12(){
			    	document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
			    	document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/icon_12.png'>");
					document.write("</strong><span>MORE</span></a>");
			    }
			function isWrite4(){
				switch(saas_version)
				{
				case 1:				
					moreButton12_diy();
				  break;
				case 3:				
					moreButton12_diy();
				  break; 
				default:				
				    moreButton12();
				}
			}
			isWrite4();
			</script>
		</div>
	</div>

</div>

<div class="main_ul_p">

	<div class="main_ul_p_left">
		<div class="main_ul_title"><strong>拓客助手</strong></div>
		<div class="main_ul_cont">
		
			<script type="text/javascript">
			
			    function jkb_diy(){
			    	document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
					document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_7.png'>");
					document.write("</strong><span>聚客宝</span></a>");
			    }
			    function jkb(){
			    	document.write("<a href='<%= contextPath%>/system/module_dotoModular.do?moduleCode=YPT_CHANNEL_CONTROL&&subMenu=JKB_MANAGER' target='_parent'><strong>");
					document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/icon_7.png'>");
					document.write("</strong><span>聚客宝</span></a>");
			    }
			    function wtywxt_diy(){
			    	document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");			    
					document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_9.png'>");
					document.write("</strong><span>外拓展业系统</span></a>");
			    }
			    function wtywxt(){
			    	document.write("<a href='<%= contextPath%>/system/module_dotoModular.do?moduleCode=YPT_CHANNEL_CONTROL&&subMenu=YPT_LITTLE_BEE' target='_parent'><strong>");
					document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/icon_9.png'>");
					document.write("</strong><span>外拓展业系统</span></a>");
			    }			   
			    function moreButton6_diy(){
			    	document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
			    	document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_6.png'>");
					document.write("</strong><span>MORE</span></a>");
			    }			    
			    function moreButton6(){
			    	document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
			    	document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/icon_6.png'>");
					document.write("</strong><span>MORE</span></a>");
			    }
				function isWrite2(){
					<%-- if(saas_version == 1){
						document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
						document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_7.png'>");
						document.write("</strong><span>聚客宝</span></a>");
						document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
						document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_9.png'>");
						document.write("</strong><span>外拓展业系统</span></a>");
					}else{
						document.write("<a href='<%= contextPath%>/system/module_dotoModular.do?moduleCode=YPT_CHANNEL_CONTROL&&subMenu=JKB_MANAGER' target='_parent'><strong>");
						document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_7.png'>");
						document.write("</strong><span>聚客宝</span></a>");
						document.write("<a href='<%= contextPath%>/system/module_dotoModular.do?moduleCode=YPT_CHANNEL_CONTROL&&subMenu=YPT_LITTLE_BEE' target='_parent'><strong>");
						document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_9.png'>");
						document.write("</strong><span>外拓展业系统</span></a>");
					} --%>
					switch(saas_version)
					{
					case 1:
						jkb_diy();
						wtywxt_diy();
						moreButton6_diy();
					  break;
					case 3:
						jkb();
					    wtywxt();
						moreButton6_diy();
					  break;
					default:
						jkb();
					    wtywxt();
					    moreButton6();
					}
					
				}
				isWrite2();
			</script>
		
			<!--  <a href="<%= contextPath%>/system/module_dotoModular.do?moduleCode=YPT_CHANNEL_CONTROL&&subMenu=JKB_MANAGER" target="_parent"><strong>
				<script type="text/javascript">document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_7.png'>");</script>
			</strong><span>聚客宝</span></a>
			<a href="<%= contextPath%>/system/module_dotoModular.do?moduleCode=YPT_CHANNEL_CONTROL&&subMenu=YPT_LITTLE_BEE" target="_parent"><strong>
				<script type="text/javascript">document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_9.png'>");</script>
			</strong><span>小蜜蜂</span></a>-->
			
			<%-- <a href="javascript:;" onclick="pop_hint();"><strong>
				<script type="text/javascript">document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_6.png'>");</script>
			</strong><span>MORE</span></a> --%>
		</div>
	</div>

	<div class="main_ul_p_right">
		<div class="main_ul_title"><strong>互动营销</strong></div>
		<div class="main_ul_cont">
		
			<script type="text/javascript">
			
				function dxgl_diy(){
					document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
					document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_3.png'>");
					document.write("</strong><span>短信管理</span></a>");
				}
				function dxgl(){
					document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
					document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/icon_3.png'>");
					document.write("</strong><span>短信管理</span></a>");
				}
				
				function ibeacon_diy(){
					document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
					document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"ibeacon.png'>");
					document.write("</strong><span>iBeacon</span></a>");
				}
				
				function ibeacon(){
					document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
					document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/ibeacon.png'>");
					document.write("</strong><span>iBeacon</span></a>");
				}
				function isWrite3(){
					<%-- if(saas_version == 1){
						document.write("<a href='javascript:;' onclick='pop_hint();'><strong>");
						document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_3.png'>");
						document.write("</strong><span>短信管理</span></a>");
					}else{
						document.write("<a href='<%= contextPath%>/system/module_dotoModular.do?moduleCode=YPT_YINGXIAO&&subMenu=YPT_MESSAGE_CONFIG' target='_parent'><strong>");
						document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_3.png'>");
						document.write("</strong><span>短信管理</span></a>");
					} --%>
					
					switch(saas_version)
					{
					case 1:
						ibeacon_diy();
						dxgl_diy();
						moreButton6_diy();
					  break;
					case 3:
						ibeacon_diy();
						dxgl_diy();
						moreButton6_diy();
					  break;
					default:
						ibeacon();
						dxgl();
					    moreButton6();
					}
				}
				isWrite3();
			</script>
		
			<!--<a href="<%= contextPath %>/system/module_dotoModular.do?moduleCode=YPT_YINGXIAO&&subMenu=YPT_MESSAGE_CONFIG" target="_parent"><strong>
				<script type="text/javascript">document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_3.png'>");</script>
			</strong><span>短信管理</span></a>-->
			
			<%-- <a href="javascript:;" onclick="alertOK('由管理员设置，如需使用请联系聚房宝平台管理员<br>联系电话：400-066-3988。');"><strong>
				<script type="text/javascript">document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"ibeacon.png'>");</script>
			</strong><span>iBeacon</span></a>
			<a href="javascript:;" onclick="pop_hint();"><strong>
				<script type="text/javascript">document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"icon_6.png'>");</script>
			</strong><span>MORE</span></a> --%>
			<!--<a href="javascript:;" onclick="pop_hint();"><strong><img src="<%= contextPath %>/saas/images/icon_index_list/icon_5.png"></strong><span>活动游戏</span></a>-->
		</div>
	</div>

</div>

<div class="main_ul_p main_ul_p_bottom" style="height:230px;">
	<div class="main_ul_title"><strong>解决方案</strong></div>
	<div class="main_ul_p_bottom_cont" style="overflow:hidden;">
		<%-- <a href="http://www.jufangbao.cn/front/solution/solution_call?typeId=6&tagId=4" target="_blank" style="width:255px;"><img src="<%= contextPath %>/saas/images/icon_index_list/1.png"></a>
		<a href="http://www.jufangbao.cn/front/solution/solution_call?typeId=6&tagId=4" target="_blank" style="width:255px;"><img src="<%= contextPath %>/saas/images/icon_index_list/2.png"></a>
		<a href="http://www.jufangbao.cn/front/solution/solution_call?typeId=6&tagId=4" target="_blank" style="width:255px;"><img src="<%= contextPath %>/saas/images/icon_index_list/3.png"></a>
		<a href="http://www.jufangbao.cn/front/solution/solution_call?typeId=6&tagId=4" target="_blank" style="width:255px;"><img src="<%= contextPath %>/saas/images/icon_index_list/4.png"></a> --%>
		<a href="http://www.jufangbao.cn/front/solution/solution_call?typeId=6&tagId=4" target="_blank" style="width: 33.3333%;display: inline-block;">
			<script type="text/javascript">document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"5.png'>");</script>
		</a>
		<a href="http://www.jufangbao.cn/front/solution/solution_call?typeId=7&tagId=4" target="_blank" style="width: 33.3333%;display: inline-block;">
			<script type="text/javascript">document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"6.png'>");</script>
		</a>
		<a href="http://www.jufangbao.cn/front/solution/solution_call?typeId=8&tagId=4" target="_blank" style="width: 33.3333%;display: inline-block;">
			<script type="text/javascript">document.write("<img src='<%= contextPath %>/saas/images/icon_index_list/"+saas_version_data[saas_version]['index_page_image']+"7.png'>");</script>
		</a>
	</div>
</div>

</div>
</body>
</html>