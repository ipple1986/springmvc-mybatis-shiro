package cn.jufuns.core.util;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;

public final class Constants {
	public static enum UnauthorizedExceptionMsg{
		IncorrectCredentialsException("IncorrectCredentialsException","登录密码错误！"),
		ExcessiveAttemptsException("ExcessiveAttemptsException","登录失败次数过多！"),
		LockedAccountException("LockedAccountException","帐号已被锁定！"),
		DisabledAccountException("DisabledAccountException","帐号已被禁用！"),
		ExpiredCredentialsException("ExpiredCredentialsException","帐号已过期！"),
		UnknownAccountException("UnknownAccountException","帐号不存在！" ),
		UnauthorizedException("UnauthorizedException","您没有得到相应的授权！")
		;
		private String exceptionName;
		private String msg;
		private UnauthorizedExceptionMsg(String en,String msg){
			this.exceptionName = en;
			this.msg = msg;
		}
		public String getExceptionName() {
			return exceptionName;
		}
		public void setExceptionName(String exceptionName) {
			this.exceptionName = exceptionName;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public static String getMsg(String exceptionStackStr) {
			return getMsg(exceptionStackStr,"");
		}
		public static String getMsg(String exceptionStackStr,String placeHolderStr) {
			if(null == exceptionStackStr)return "";
			for(UnauthorizedExceptionMsg msg:values()){
				if(exceptionStackStr.contains(msg.getExceptionName())){
					if(null != placeHolderStr){
						return String.format(msg.getMsg(), placeHolderStr);
					}else{
						return msg.getMsg(); 
					}
				}
			}
			return "";
		}
	}
	
	
	//工具类方法
		public static boolean isAjax(ServletRequest request){
			return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
		}
		public static void out(ServletResponse response, Map<String, String> resultMap){
			
			PrintWriter out = null;
			try {
				response.setCharacterEncoding("UTF-8");
				out = response.getWriter();
				JSONWrappedObject json = new JSONWrappedObject("","", resultMap,null);
				out.println(json.getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(null != out){
					out.flush();
					out.close();
				}
			}
		}
}
