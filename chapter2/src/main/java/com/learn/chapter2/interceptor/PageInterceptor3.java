package com.learn.chapter2.interceptor;

import java.sql.PreparedStatement;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
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

@Intercepts(
	    {
	        @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class}),
	    }
	)
public class PageInterceptor3 implements Interceptor{

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
