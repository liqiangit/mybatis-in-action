package com.learn.chapter2.interceptor;

import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
/**
 * https://www.cnblogs.com/stefanking/articles/5114175.html
 * 关于mybatis解析plugin及plugin使用的理解
 * @author 李强
 *
 */
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
@Intercepts(
	    {
	        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
	        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
	    }
	)
public class PageInterceptor4 implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println(invocation.getArgs());
		System.out.println(invocation.getTarget());
		System.out.println(invocation.getClass());
		System.out.println(invocation.getMethod());
		return invocation.proceed();
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
