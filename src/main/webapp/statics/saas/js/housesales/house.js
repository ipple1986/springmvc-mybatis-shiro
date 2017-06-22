/**验证是否正确的公式公式必须包含变量大写A**/
function isFormula(exp){
	var index=exp.indexOf("A");
	if(index==-1){
		return false;//公式不正确
	}
	var reg=/^\d+A|A\d+|\d+A\d+$/;//过滤掉  1A ;A1这些情况
	var  ff=reg.test(exp);
	if(ff){
		return false;
	}
	
	 reg=/^\D+0+$/;//过滤掉  前面是非数字后面是0的情况
	 ff=reg.test(exp);
	 if(ff){
		return false;
	 }
	 
	 reg=/^0+\D*$/;//过滤掉  前面0后是非数子的情况
	 ff=reg.test(exp);
	 if(ff){
		return false;
	 }
	
	
	var a=exp.replace("A",1);
	try{
		eval(a)
		return true;//公式正确
	}catch(e){
		return false;//公式不正确
	}
}