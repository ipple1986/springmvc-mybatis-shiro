/**
 * 多选
 */
function doShow() {
	$(".selectMain-row").attr("style","display:block");
}
//去掉全选按钮勾选
function clearCheckbox(obj){
	$("input[name='checkbox_all']").each(function(){
		$(this).removeAttr("checked");
	});
}

//隐藏多选
function hideSelect(){
	$(".selectMain-row").removeAttr("style");
}

$(function(){

	var selectFlag;
	$(".showMain-row").on('mouseleave',function  () {
		 selectFlag = setTimeout("hideSelect()",200);
       
        
    });
	 $(".selectMain-row").on('mouseleave',function  () {
		 setTimeout("hideSelect()",200);
         
     });
	 $(".selectMain-row").on('mouseenter',function  () {
		 clearTimeout(selectFlag);
         
     });
	
//全选按钮
$("input[name='checkbox_all']").bind("click",function(){
	var checked_all = $(this).attr("checked");
	$(this).parent().parent().next().find("input[type=radio],input[type=checkbox]").each(function(){
		if(checked_all == "checked"){
			$(this).attr("checked", "checked");
		}else{
			$(this).removeAttr("checked");
		}
	});
});

//清除按钮
$('.saasBtn03').click(function(){
	$(this).parent().parent().next().find("input[type=radio]").each(function(){
		$(this).removeAttr("checked");
	});
});

//确定按钮
//确定按钮
$('.saasBtn01').click(function(){
	var parentvalues = "";
	var parentTextValues = "";
	var values = "";
	var textValues = "";
	$('.saasSelect01-body .parent').children().each(function(){
		
		var checked = $(this).find('input').attr('checked');
		var value = $(this).text();
		var textValue = $(this).find('input').val();
		//子项
		var subvalues = "";
		var subTextValues = "";
		$(this).children().next('.son').children().each(function(){
			var subchecked = $(this).children().attr('checked');
			if(subchecked == "checked"){
				var subvalue = $(this).text();
				subvalues += subvalue+",";
			}
		});
		
		if(subvalues != ""){
			subvalues = "("+subvalues.substring(0,subvalues.length-1)+")";
			values += value+subvalues+";";
			parentvalues += value+",";
			
		}else if(checked == "checked"){
			values += value+";";
			parentvalues += value+",";
		}
		
		if(checked == "checked"){
			textValue = textValue+",";
			parentTextValues += textValue;
		}
	});

	if(values != ""){
		values = values.substring(0,values.length-1);
		if(values.indexOf("(") == -1){
			values = values.replace(/;/g, ",");
		}
		parentvalues = parentvalues.substring(0,parentvalues.length-1);
	}
	
	if(parentTextValues != ""){
		parentTextValues = parentTextValues.substring(0,parentTextValues.length-1);
	}
	
	var valuediv = $(this).parent().parent().parent().prev().children().eq(0);
	$(valuediv).attr("title", textValues);
	$(valuediv).text(values);
	
	$("#showflwTpyes").val(values);
	$("#p_flwTpyes").attr("title", parentTextValues);
	$("#flwTpyes").val(parentTextValues);
	$('.selectMain-row').slideUp(100);
	
	//跟进方式不包含到访，到访类型显示为空
	if(parentTextValues.indexOf('1')<0){
		$("#p_visitTimes").text("");
	}else{
		$("#p_visitTimes").text("全部");
	}
	
});
});

//隐藏弹窗
function hide(arg){
	$("#div_fade").hide();
	$("#div_pop").hide();
	args = arg;
}

//全选、取消全选、反选---列表
function chkAll(type) {
    ///  type: 0-全选/全不选    1-反选  
    if (type==0) {	
		var  eatAll  =   document.getElementsByName("checkbox");
		    for(var  i  =0;  i<eatAll.length;i++){
		    	if(eatAll[i].checked){
		    		eatAll[i].checked=  false;  
		    	}else{
		    		eatAll[i].checked=  true;  
		    		//弹出对话框
		    		$("#div_fade").show();
		 			$("#div_pop").show();
		    	}
		              
		    }
	  } else  if(type==1){
	     var  eatAll  =   document.getElementsByName("checkbox");
	     	for ( var i1 = 0; i1 < eatAll.length; i1++) {
	     		eatAll[i1].checked=!eatAll[i1].checked;     
	     	}
    }

}


