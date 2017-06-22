package cn.jufuns.core.mybatis.paginable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.log4j.Logger;

//org.apache.ibatis.executor.statement 包
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageInterceptor implements Interceptor {
	private Logger LOGGER = Logger.getLogger(PageInterceptor.class);

	public Object intercept(Invocation invocation) throws Throwable {

		if (invocation.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
			MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);

			MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
					.getValue("delegate.mappedStatement");
			// 获取接口方法名
			String selectId = mappedStatement.getId();
			if(selectId.endsWith("Pagination")){//处理以Pagination结尾的方法
				BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
				Object obj = boundSql.getParameterObject();
				Pagination page = null;
				if (obj instanceof ParamMap<?>) {
					obj = (ParamMap<?>) obj;
					if (((ParamMap<?>) obj).containsKey("page")) {
						obj = ((ParamMap<?>) obj).get("page");
					}
				}
				if (obj instanceof Pagination) {
					page = (Pagination) obj;
					String sql = boundSql.getSql();
					Connection connection = (Connection) invocation.getArgs()[0];
					// 重写sql
					String countSql = concatCountSql(sql);
					String pageSql = concatPageSql(sql, page);
					
					PreparedStatement countStmt = null;
					ResultSet rs = null;
					try {
						List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
						BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql,
								parameterMappings, boundSql.getParameterObject());
						ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(),
								countBoundSql);

						countStmt = connection.prepareStatement(countSql);
						parameterHandler.setParameters(countStmt);
						int totalCount = 0;
						rs = countStmt.executeQuery();
						if (rs.next()) {
							totalCount = rs.getInt(1);
						}
						int totalPage = totalCount/page.getPageSize();
						totalPage += (totalCount%page.getPageSize()==0)?0:1;
						page.setTotalPage(totalPage);
						page.setTotalCount(totalCount);
						page.setFirstPage(page.getPageNo()==1);
						page.setLastPage(page.getPageNo()==page.getTotalPage());
						page.setPrePage((page.getPageNo()<=1)?1:(page.getPageNo()-1));
						page.setNextPage((page.getPageNo()>=page.getTotalPage())?page.getTotalPage():(page.getPageNo()+1));

					} catch (SQLException e) {
						LOGGER.error("Ignore this exception" , e);
					} finally {
						try {
							if(rs!=null){rs.close();}
							if(countStmt!=null){countStmt.close();}
						} catch (SQLException e) {
							LOGGER.error("Ignore this exception" , e);
						}
					}

					metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
					
				} else {
					LOGGER.info("not Pagination");
				}
			}
		}
		return invocation.proceed();

	}

	@Override
	public Object plugin(Object arg0) {
		if (arg0 instanceof StatementHandler) {
			return Plugin.wrap(arg0, this);
		} else {
			return arg0;
		}
	}

	@Override
	public void setProperties(Properties p) {

	}

	public String concatCountSql(String sql) {
		StringBuffer sb = new StringBuffer("select count(0) from ");
		sql = sql.toLowerCase();

		if (sql.lastIndexOf("order") > sql.lastIndexOf(")")) {
			sb.append(sql.substring(sql.indexOf("from") + 4, sql.lastIndexOf("order")));
		} else {
			sb.append(sql.substring(sql.indexOf("from") + 4));
		}

		return sb.toString();
	}

	public String concatPageSql(String sql, Pagination pagination) {
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		sb.append(" limit ").append((pagination.getPageNo() - 1) * pagination.getPageSize()).append(" , ")
				.append(pagination.getPageSize());
		return sb.toString();
	}
}