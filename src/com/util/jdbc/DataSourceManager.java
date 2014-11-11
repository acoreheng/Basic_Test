package com.util.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * c3p0数据源管理类
 * Description: 把多个c3p0数据源放进容器中管理
 * @create date: 2012-9-1 
 * */
public final class DataSourceManager {
	//保存数据库连接池的容器
	public static HashMap<String, ComboPooledDataSource> dataSourceMap = new HashMap<String, ComboPooledDataSource>();
	//数据库信息的配置文件
	private static Properties pp = null;
	private static InputStream fs = null;
	
	private DataSourceManager(){
		
	}
	
	/**
	 * 从连接池容器中返回连接池对象
	 * @return 连接池的名称
	 * */
	public static ComboPooledDataSource getDataSource(String dataSourceName){
		//如果指定数据源不存在，则创建
		if (!dataSourceMap.containsKey(dataSourceName.toString())){
			try {
				ComboPooledDataSource ds = new ComboPooledDataSource();
				//读取数据库配置文件
				pp = new Properties();
				fs = DataSourceManager.class.getClassLoader().getResourceAsStream("DBInfo.properties");
				pp.load(fs);
				
				//配置数据源
				ds.setDriverClass(pp.getProperty(dataSourceName + "." + "Driver"));
				ds.setJdbcUrl(pp.getProperty(dataSourceName + "." + "Url"));
				ds.setUser(pp.getProperty(dataSourceName + "." + "User"));
				ds.setPassword(pp.getProperty(dataSourceName + "." + "Password"));
				ds.setMaxPoolSize(Integer.parseInt(pp.getProperty(dataSourceName + "." + "MaxPoolSize")));
				ds.setMinPoolSize(Integer.parseInt(pp.getProperty(dataSourceName + "." + "MinPoolSize")));
				ds.setMaxIdleTime(Integer.parseInt(pp.getProperty(dataSourceName + "." + "MaxIdleTime")));
				ds.setInitialPoolSize(Integer.parseInt(pp.getProperty(dataSourceName + "." + "InitialPoolSize")));
				ds.setAcquireIncrement(Integer.parseInt(pp.getProperty(dataSourceName + "." + "AcquireIncrement")));
				ds.setAcquireRetryAttempts(Integer.parseInt(pp.getProperty(dataSourceName + "." + "AcquireRetryAttempts")));
				ds.setAcquireRetryDelay(Integer.parseInt(pp.getProperty(dataSourceName + "." + "AcquireRetryDelay")));
				ds.setMaxStatements(Integer.parseInt(pp.getProperty(dataSourceName + "." + "MaxStatements")));
				ds.setIdleConnectionTestPeriod(Integer.parseInt(pp.getProperty(dataSourceName + "." + "IdleConnectionTestPeriod")));
				ds.setCheckoutTimeout(Integer.parseInt(pp.getProperty(dataSourceName + "." + "CheckoutTimeout")));
				ds.setTestConnectionOnCheckin(Boolean.parseBoolean(pp.getProperty(dataSourceName + "." + "TestConnectionOnCheckin")));
				ds.setTestConnectionOnCheckout(Boolean.parseBoolean(pp.getProperty(dataSourceName + "." + "TestConnectionOnCheckout")));
				
				//把数据源放进容器中
				dataSourceMap.put(dataSourceName, ds);
				
				return ds;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("无法根据配置文件创建连接池对象", e);
			}
			finally{
				try {
					fs.close();
				} catch (IOException e2) {
					e2.printStackTrace();
					throw new RuntimeException("无法找到配置文件", e2);
				}
			}

		}
		else {
			return (ComboPooledDataSource)dataSourceMap.get(dataSourceName.toString());
		}
	}
}