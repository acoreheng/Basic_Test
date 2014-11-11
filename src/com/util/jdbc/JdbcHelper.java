package com.util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.rowset.CachedRowSetImpl;

/**
 * Jdbc应用类
 * Description: 
 * 1、需要在调用项目中引用c3p0数据源jar包
 * 2、需要把DBInfo.properties文件放在classes文件夹中
 * 3、DBInfo.properties文件的配置前序为数据源名称。例如：配置为webdemo.Driver,则引用时JdbcHelper.executeUpdate("webdemo", sql, params);
 * @create date: 2012-9-1 
 * */
public final class JdbcHelper {
	/**
	 * 获取一个数据库连接
	 * @return 一个数据库连接
	 * */
	private static synchronized Connection getConnection(String dataSourceName){
		try {
			ComboPooledDataSource ds = DataSourceManager.getDataSource(dataSourceName);
			return ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 关闭数据库连接释放资源
	 * */
	private static void close(Connection conn, PreparedStatement pstm, ResultSet rs){
		try {
			if (conn != null){
				conn.close();
			}
			if (pstm != null){
				pstm.close();
			}
			if (rs != null){
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 执行单个数据库操作 Insert,Update,Delete
	 * @return 成功执行的记录数
	 * */
	public static Integer executeUpdate(String dsName, String sql, String[] params){
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = getConnection(dsName);
			pstm = conn.prepareStatement(sql);
			if (params != null){
				for (int i=0; i<params.length; i++){
					pstm.setString(i+1, params[i]);
				}
			}
			
			return pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		finally{
			close(conn, pstm, null);
		}
	}
	
	/**
	 * 执行多个数据库操作，包含事务处理功能
	 * @return 如果事务执行成功返回1，如果事务执行不成功返回0
	 * */
	public static Integer executeUpdate(String dsName, String[] sqls, String[][] params){
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn=getConnection(dsName);
			//禁止自动提交事务
			conn.setAutoCommit(false);
			for (int i=0; i<sqls.length; i++){
				pstm = conn.prepareStatement(sqls[i]);
				if (params != null){
					for (int j=0; j<params[i].length; j++){
						pstm.setString(j+1, params[i][j]);
					}
				}
				pstm.executeUpdate();
			}
			
			conn.commit();
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return 0;
		}
		finally{
			close(conn, pstm, null);
		}
	}
	
	/**
	 * 执行数据库查询操作
	 * @return 查询结果的离线RowSet
	 * */
	public static RowSet executeQuery(String dsName, String sql, String[] params){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CachedRowSet crs = null;
		
		try {
			conn = getConnection(dsName);
			pstm = conn.prepareStatement(sql);
			if (params != null){
				for (int i=0; i<params.length; i++){
					pstm.setString(i+1, params[i]);
				}
			}
			rs = pstm.executeQuery();
			
			//创建CacheRowSet
			crs = new CachedRowSetImpl();
			crs.populate(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			close(conn, pstm, rs);
		}
		
		return crs;
	}
	
	/**
	 * 执行需要分页的数据库查询操作
	 * @return 查询结果的离线RowSet
	 * */
	public static RowSet executeQuery(String dsName, String sql, String[] params, Integer pageIndex, Integer pageSize){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CachedRowSet crs = null;
		
		try {
			conn = getConnection(dsName);
			pstm = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (params != null){
				for (int i=0; i<params.length; i++){
					pstm.setString(i+1, params[i]);
				}
			}
			rs = pstm.executeQuery();
			
			//创建CacheRowSet
			crs = new CachedRowSetImpl();
			crs.setPageSize(pageSize);
			crs.populate(rs, (pageIndex-1)*pageSize+1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			close(conn, pstm, rs);
		}
		
		return crs;
	}
	
	/**
	 * 执行查询的存储过程"{ call addUser(?,?,?,?) }"
	 * @return 返回查询结果的RowSet集合
	 * */
	public static RowSet executeStoredProcedure(String dsName, String sp_name, String[] params){
		Connection conn = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		CachedRowSet crs = null;
		
		try {
			conn = getConnection(dsName);
			cstm = conn.prepareCall(sp_name);
			if (params != null){
				for (int i=0; i<params.length; i++){
					cstm.setString(i+1, params[i]);
				}
			}
			rs = cstm.executeQuery();
			
			//创建CacheRowSet
			crs = new CachedRowSetImpl();
			crs.populate(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			close(conn, cstm, rs);
		}
		
		return crs;
	}
}