package cn.jufuns.saas.aop;

import java.sql.SQLException;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Controller层异常捕获
 * @author ipple1986
 *
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerExceptionAdvice {

	/**
	 * 注解@RequireRoles等方法，未授权时抛出e
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public void processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) throws UnauthorizedException  {
		throw e; 
	}
	@ExceptionHandler(SQLException.class)
	public void processUncategorizedSQLException(NativeWebRequest request,SQLException e)throws SQLException{
		throw e;
	}
	
}
