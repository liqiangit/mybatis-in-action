package com.learn.chapter2.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
/**
 * https://www.cnblogs.com/stefanking/articles/5114175.html
 * 关于mybatis解析plugin及plugin使用的理解
 * @author 李强
 *
 */
@Intercepts(
	    {
	        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class}),
	    }
	)
public class PageInterceptor implements Interceptor{
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println(invocation.getArgs());
		System.out.println(invocation.getTarget());
		System.out.println(invocation.getClass());
		System.out.println(invocation.getMethod());
		
		StatementHandler statementHandler=(StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler=SystemMetaObject.forObject(statementHandler);
		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = SystemMetaObject.forObject(object);
		}
		// 分离最后一个代理对象的目标类
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = SystemMetaObject.forObject(object);
		}
		Object object=metaStatementHandler.getValue("delegate.boundSql.parameterObject");
		Object currentPage=metaStatementHandler.getValue("delegate.boundSql.parameterObject.currentPage");
		Object pageSize=metaStatementHandler.getValue("delegate.boundSql.parameterObject.pageSize");
		Object note=metaStatementHandler.getValue("delegate.boundSql.parameterObject.note");

		String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		PageInfo page=new PageInfo();
		page.setCurrentPage(1);
		page.setPageSize(10);
		String limitSql=buildPageSqlForMysql(sql, page).toString();
		metaStatementHandler.setValue("delegate.boundSql.sql", limitSql);
		return invocation.proceed();
	}
	/**
	 * buildPageSqlForMysql:mysql的分页语句 <br/>
	 * 适用条件：mysql数据库<br/>
	 * 执行流程：内部调用<br/>
	 *
	 * @author:zhuyunfei Date: 2014年9月19日 上午10:37:22
	 * @param sql
	 *          sql语句
	 * @param page
	 *          分页信息
	 * @return StringBuilder 拼装过后的sql语句
	 * @since JDK 1.7
	 */
	public StringBuilder buildPageSqlForMysql(String sql, PageInfo page) {
		StringBuilder pageSql = new StringBuilder(100);
		// 计算起始行
		String beginrow = String.valueOf((page.getCurrentPage() - 1) * page.getPageSize());
		pageSql.append(sql);
		pageSql.append(" limit " + beginrow + "," + page.getPageSize());
		return pageSql;
	}

	/**
	 * buildPageSqlForOracle:oracle分页方法 <br/>
	 * 适用条件：oracle数据库<br/>
	 * 执行流程：内部调用<br/>
	 *
	 * @author:zhuyunfei Date: 2015年9月19日 上午10:49:43
	 * @param sql
	 *          sql语句
	 * @param page
	 *          分页信息
	 * @return StringBuilder 拼装过后的sql语句
	 * @since JDK 1.7
	 */
	public StringBuilder buildPageSqlForOracle(String sql, PageInfo page) {
		StringBuilder pageSql = new StringBuilder(100);
		// 计算起始行
		String beginrow = String.valueOf((page.getCurrentPage() - 1) * page.getPageSize());
		// 计算截止行
		String endrow = String.valueOf(page.getCurrentPage() * page.getPageSize());
		pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
		pageSql.append(sql);
		pageSql.append(" ) temp where rownum <= ").append(endrow);
		pageSql.append(") where row_id > ").append(beginrow);
		return pageSql;
	}

	/**
	 * buildPageSqlForDB2:DB2分页方法 <br/>
	 * 适用条件：DB2数据库<br/>
	 * 执行流程：内部调用<br/>
	 *
	 * @author:zhuyunfei Date: 2016年08月31日 上午15:49:43
	 * @param sql
	 *          sql语句
	 * @param page
	 *          分页信息
	 * @return StringBuilder 拼装过后的sql语句
	 * @since JDK 1.7
	 */
	public StringBuilder buildPageSqlForDB2(String sql, PageInfo page) {
		StringBuilder pageSql = new StringBuilder(100);
		// 计算起始行
		String beginrow = String.valueOf((page.getCurrentPage() - 1) * page.getPageSize());
		// 计算截止行
		String endrow = String.valueOf(page.getCurrentPage() * page.getPageSize());
		pageSql.append("select * from (select a.*,rownumber() over() as rowid from (");
		pageSql.append(sql);
		pageSql.append(") a) tmp where tmp.rowid > ").append(beginrow);
		pageSql.append(" and tmp.rowid <= ").append(endrow);
		return pageSql;
	}
	@Override
	public Object plugin(Object target) {
		// 使用默认的Mybatis代理类生成对象
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
