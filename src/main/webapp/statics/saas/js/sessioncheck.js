$.ajaxSetup({
    contentType : "application/x-www-form-urlencoded;charset=utf-8",   
    cache : false,   
    complete : function (XMLHttpRequest, textStatus) {
    	var sessionStatus = XMLHttpRequest.getResponseHeader("sessionStatus");
    	var uri = XMLHttpRequest.getResponseHeader("uri");
    	
    	if (sessionStatus == "timeout") {
    		var top = getTopWinow();
        	top.location.href = contextPath + uri;
    	}
    }
});

function getTopWinow() {
	var p = window;
	
	while (p != p.parent) {
		p = p.parent;
	}
	
	return p;
}