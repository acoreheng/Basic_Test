package com.code.demo;
/**
 * @author AcoreHeng
 * @version 创建时间：2012-10-15 下午4:18:57
 */
public abstract class SerialNumber {
	/**
	 * 
	 * @return
	 */
	public synchronized String getSerialNumber() {
        return process();
    }
    protected abstract String process();
}
