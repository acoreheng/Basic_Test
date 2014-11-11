package com.thread;
public abstract class ThreadObject
{
    /**
     * 是否多线程
     */
    private boolean multiThread = false;

    /**
     * 是否处理完成
     */
    private boolean isOk = false;

    /**
     * 线程处理操作
     * 
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public abstract Object handleOperation();

    public boolean isMultiThread() {
        return multiThread;
    }

    public void setMultiThread(boolean multiThread) {
        this.multiThread = multiThread;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean isOk) {
        this.isOk = isOk;
    }

}