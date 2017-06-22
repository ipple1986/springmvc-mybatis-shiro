$(function() {
	if ($.browser.msie) {
		if ($.browser.version==8.0) {
			top.$("iframe[name='content']").height("0px");
			top.$("iframe[name='content']").height($(document).height()-23);
		}
	}
	if (top !== window && top.frames['content'] === window) {
		top.$("iframe[name='content']").height("0px");
	}
	if (top !== window && top.frames['content'] === window) {
		top.$("iframe[name='content']").height($(document).height()-23);//@auther zhenye 减去23去掉显示内容完全之后依然存在进度条问题
		
	}
$("body").scrollTop(0); 
});
function SaasPopupLayer(url) {
	var opt = {width: "100%", height: "100%"};
	if (arguments.length > 1) {
		var _opt = arguments[1];
		for (var k in _opt) {
			if (opt[k] !== undefined) {
				opt[k] = _opt[k];
			}
		}
	}
	this.src = window;
	this.wd = top !== window ? top : window;
	this.wd.$("body").append('<table id="saas_pop"><tr><td><iframe name="saas_pop" id="saas_pop_id" onload="saas_pop_id.focus()" src="' + url + '" scrolling="auto" frameborder="0" allowTransparency="true" style="background: transparent"></iframe></td></tr></table>');
	var $this=$(this);
	/*console.log($this.attr("src"));*/
	if(url=="system/password_in.do"){
		this.wd.$("iframe[name='saas_pop']").css("width", opt.width).css("height", "350px");
	}else{
		this.wd.$("iframe[name='saas_pop']").css("width", opt.width).css("height", opt.height);
	}
	this.wd.frames['saas_pop'].saasPopInstance = this;
	this.wd.saasPopInstance = this;
}

//设置弹窗里DIV垂直居中
$(window).load(function() {
	if (top !== window && top.frames['saas_pop'] == window) {
		$(document).find('body').css('overflow','hidden');
		$(document).find('body>div').css('position','absolute').myCenter();
	}
});

SaasPopupLayer.prototype.close = function() {
	//IE8 会出错
	//delete this.wd.frames['saas_pop'].saasPopInstance;
	this.wd.$("#saas_pop").remove();
};

SaasPopupLayer.prototype.close3 = function(url) {
	//IE8 会出错
	//delete this.wd.frames['saas_pop'].saasPopInstance;
	this.wd.$("#saas_pop").remove();
	
	url=encodeURI(encodeURI(url));
	
	window.location.href=url;
};

SaasPopupLayer.prototype.closeResetPass = function() {
	//IE8 会出错
	//delete this.wd.frames['saas_pop'].saasPopInstance;
	this.wd.$("#saas_pop").remove();
	
	var url = contextPath + '/system/operatorsaas_hint.do?messageNO=3';
	new SaasPopupLayer(url, {width:'420px', height:'200px'});
};

SaasPopupLayer.prototype.closeRoleSet = function() {
	//IE8 会出错
	//delete this.wd.frames['saas_pop'].saasPopInstance;
	this.wd.$("#saas_pop").remove();
	
	var url = contextPath + '/system/operatorsaas_hint.do?messageNO=4';
	new SaasPopupLayer(url, {width:'420px', height:'200px'});
};

SaasPopupLayer.prototype.closeHaveSubOrg = function() {
	//IE8 会出错
	//delete this.wd.frames['saas_pop'].saasPopInstance;
	this.wd.$("#saas_pop").remove();
	
	var url = contextPath + '/system/operatorsaas_hint.do?messageNO=5';
	new SaasPopupLayer(url, {width:'420px', height:'200px'});
};

SaasPopupLayer.prototype.closeHaveOper = function() {
	//IE8 会出错
	//delete this.wd.frames['saas_pop'].saasPopInstance;
	this.wd.$("#saas_pop").remove();
	
	var url = contextPath + '/system/operatorsaas_hint.do?messageNO=6';
	new SaasPopupLayer(url, {width:'420px', height:'200px'});
};

SaasPopupLayer.prototype.closeDelete = function(messageNO) {
	//IE8 会出错
	//delete this.wd.frames['saas_pop'].saasPopInstance;
	this.wd.$("#saas_pop").remove();
	
	var url = contextPath + '/system/houseSource_doHint.do?messageNO=' + messageNO;
	new SaasPopupLayer(url, {width:'420px', height:'200px'});
};

SaasPopupLayer.prototype.closeStatusRemove = function(messageNO) {
	//IE8 会出错
	//delete this.wd.frames['saas_pop'].saasPopInstance;
	this.wd.$("#saas_pop").remove();
	
	var url = contextPath + '/bee/beeMonitor_doHint.do?messageNO=' + messageNO;
	new SaasPopupLayer(url, {width:'420px', height:'200px'});
};

SaasPopupLayer.prototype.close2 = function() {
	//IE8 会出错
	//delete this.wd.frames['saas_pop'].saasPopInstance;
	this.wd.$("#saas_pop").remove();
	 window.location.reload();
};

/* 新组织架构提示信息框 */
SaasPopupLayer.prototype.closeNewOrg = function(messageNO) {
	//IE8 会出错
	//delete this.wd.frames['saas_pop'].saasPopInstance;
	this.wd.$("#saas_pop").remove();
	
	var url = contextPath + '/system/newOrg_doHint.do?messageNO=' + messageNO;
	new SaasPopupLayer(url, {width:'420px', height:'200px'});
};

/* 新组织架构提示信息框，包含点击确定后的处理方法 */
function showHint_newOrg(messageNO, submitNO) {
	var url = contextPath + '/system/newOrg_doHint.do?messageNO=' + messageNO +'&submitNO=' + submitNO;
	new SaasPopupLayer(url, {width:'420px', height:'200px'});
}

function saasAlert(a,b){
	a=encodeURI(a);
	var url = contextPath + '/saas/jsp/common/alert.jsp?saasAlert='+a+'&b='+b;
	new SaasPopupLayer(url, {width:'420px',height:'200px'});
}
function saasConfirm(a,url,cs,b){
	a=encodeURI(a);
	url=encodeURI(url);
	cs=encodeURI(cs);
	var url = contextPath + '/saas/jsp/common/confirm.jsp?confirm='+a+'&url='+url+'&cs='+cs+'&b='+b;
	new SaasPopupLayer(url, {width:'420px',height:'200px'});
}


//iframe弹窗
function alertOK(msg){
	msg = encodeURI(msg);
	new SaasPopupLayer(contextPath + '/alert/ok?msg='+msg,{width:'420px',height:'200px'});
}
function alertOKAndDo(msg){
	msg = encodeURI(msg);
	new SaasPopupLayer(contextPath + '/alert/okAndDo.jsp?msg='+msg,{width:'420px',height:'200px'});
}

//iframe弹窗
function alertOK2(msg, url){
	msg = encodeURI(msg);
	url = encodeURI(url);
	new SaasPopupLayer(contextPath + '/alert/ok?msg='+msg+'&url='+url,{width:'420px',height:'200px'});
}

//iframe弹窗
function alertChoose(msg, callback, params){
	msg = encodeURI(msg);
	callback = encodeURI(callback);
	
	var paramsStr = "";
	for(var i=0; i<params.length; i++){
		paramsStr += "'"+params[i]+"'"+",";
	}
	if(paramsStr != ''){
		paramsStr = paramsStr.substring(0, paramsStr.length-1);
	}
	
	params = encodeURI(paramsStr);
	
	new SaasPopupLayer(contextPath + '/alert/ok_or_no.jsp?msg='+msg+'&callback='+callback+'&params='+params,{width:'420px',height:'200px'});
}

//iframe二次弹出提示窗
SaasPopupLayer.prototype.closeAndAlertOKTwo = function(msg) {
	//IE8 会出错
	//delete this.wd.frames['saas_pop'].saasPopInstance;
	this.wd.$("#saas_pop").remove();
	
	msg = encodeURI(msg);
	new SaasPopupLayer(contextPath + '/alert/ok?msg='+msg,{width:'420px',height:'200px'});
};

/* 新增iframe */
newWin = function(opt) {
    //默认设置
    var src = opt.src,
        width = opt.width || 1080,
        height = opt.height || 863,
        callback = opt.callback;

    //最外层body
    var topBody = $('body',parent.document);

    //获取当前iframe数量
    var count = topBody.find('.conter').find('iframe').length;
    var ifId = count;

    //生成iframe
    var winHtml = '';
    winHtml += '<iframe id="newIframe'+ifId+'" class="content" name="newIframe'+ifId+'" src="'+src+'" scrolling="no" frameborder="0" style="min-height: 850px; height: '+height+'px;width:'+width+'px"> </iframe>'
    topBody.find('.conter').append(winHtml);
    topBody.find('#newIframe'+ifId).siblings('iframe').hide();

    if (callback != null && callback != undefined) {
        callback();
    }
};

/* 关闭iframe */
closeWin = function(opt) {
    var callback = opt.callback || null;
    
    if (callback != null && callback != undefined) {
        callback();
    }

    var topBody = $('body',parent.document);
    
    topBody.find('iframe').last().remove();
    topBody.find('iframe').last().show();
};

/* 弹出选择当前页或者全部页窗口 */
function alertSelectCurrOrAll() {
	var url = contextPath + '/saas/jsp/common/alertSelectCurrOrAll.jsp';
	new SaasPopupLayer(url, {width:'420px', height:'200px'});
}
