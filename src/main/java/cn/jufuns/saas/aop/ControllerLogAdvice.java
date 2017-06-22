package cn.jufuns.saas.aop;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 微信 聚房宝 拦截器
 * @author kevin
 *
 */
@Component
@Aspect
public class ControllerLogAdvice{
	private static final Logger LOG = Logger.getLogger(ControllerLogAdvice.class);
	
	
	
    @Around("execution(public * cn.jufuns.saas.controller.*Controller.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        
    	//获取当前方法名
    	MethodSignature signature = (MethodSignature) point.getSignature();
    	String methodName = signature.getMethod().getName();
    	String controllerName = point.getTarget().getClass().getName();
    	
    	methodName = controllerName.concat(".").concat(methodName);
    	LOG.info(String.format("调用Controller方法名:%s",methodName));
    	//参数判断拦截
        Object[] paramValues = point.getArgs();
        String[] paramNames = signature.getParameterNames();
        LOG.info(Arrays.toString(paramNames));
       
        Object obj = null;
        try{
        	Long start = System.currentTimeMillis();
        	//调用业务方法
            obj = point.proceed(paramValues);       
            LOG.info(String.format("耗时：%sms",System.currentTimeMillis()-start));
        }catch (Throwable e) {
        	LOG.error(String.format("调用方法%s时报错>>>>>>>>>>>>",methodName),e);
			throw e;
		}
        return obj;
    }
        
}