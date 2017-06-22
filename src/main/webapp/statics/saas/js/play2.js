//播放音频
function play(path,fileAddr,customerID){
	console.log("voice file path is : "+fileAddr);
	var strRegex = "(.mp3|.ogg)$"; //用于验证音频扩展名的正则表达式
	  var re=new RegExp(strRegex);
	  if (re.test(fileAddr.toLowerCase())){
		  var url = path + '/saas/jsp/call/calloutlist/play2.jsp?fileAddr='+fileAddr + '&customerID=' + customerID;
			/*  new SaasPopupLayer(url, {width:'400px',height:'100px'}); */
			window.open(url,"download","width=400px,height=150px,top=" + (window.screen.height/2-75) + ",left=" + (window.screen.width/2-200));
	  } 
	  else{
	    alert("不支持该文件格式，无法播放"); 
	  }
}