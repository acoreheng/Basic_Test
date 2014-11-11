package com.thread;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * TODO 请求处理线程池
 * @author liwen
 * @version 创建时间：2012-12-29 下午04:20:09
 */

public class RequestServerPool {
	private static Logger logger = Logger.getLogger(RequestServerPool.class);
	private static Logger taskLogger = Logger.getLogger("TaskLogger");
	private static boolean debug = taskLogger.isDebugEnabled();
	public static final int SYSTEM_BUSY_TASK_COUNT = 150;
	/* 默认池中线程数 */
	private static int worker_num = 15;
	/* 已经处理的任务数 */
    private static int taskCounter = 0;
	public static boolean systemIsBusy = false;
	private static List<RequestTask> taskQueue = Collections
			.synchronizedList(new LinkedList<RequestTask>());
	private static RequestServerPool instance;
	/* 池中的所有线程 */
	public RequestWorker[] requestWorkers;

	private RequestServerPool() {
		requestWorkers = new RequestWorker[worker_num];
		for (int i = 0; i < requestWorkers.length; i++) {
			requestWorkers[i] = new RequestWorker(i);
		}
	}

	private RequestServerPool(int pool_worker_num) {
		worker_num = pool_worker_num;
		requestWorkers = new RequestWorker[worker_num];
		for (int i = 0; i < requestWorkers.length; i++) {
			requestWorkers[i] = new RequestWorker(i);
		}
	}

	public static synchronized RequestServerPool getInstance() {
		if (instance == null)
			return new RequestServerPool();
		return instance;
	}

	/**
     * 增加新的任务
     * 每增加一个新任务，都要唤醒任务队列
     * @param requestTask
     */
     public void addTask(RequestTask requestTask) {
         synchronized (taskQueue) {
             requestTask.setTaskId(++taskCounter);
             requestTask.setSubmitTime(new Date());
             taskQueue.add(requestTask);
             /* 唤醒队列, 开始执行 */
             taskQueue.notifyAll();
         }
         logger.info("Submit Task<" + requestTask.getTaskId() + ">: "
                 + requestTask.info());
     }
     /**
     * 批量增加新任务
     * @param requestTasks
     */
     public void batchAddTask(RequestTask[] requestTasks) {
         if (requestTasks == null || requestTasks.length == 0) {
             return;
         }
         synchronized (taskQueue) {
             for (int i = 0; i < requestTasks.length; i++) {
                 if (requestTasks[i] == null) {
                     continue;
                 }
                 requestTasks[i].setTaskId(++taskCounter);
                 requestTasks[i].setSubmitTime(new Date());
                 taskQueue.add(requestTasks[i]);
             }
             /* 唤醒队列, 开始执行 */
             taskQueue.notifyAll();
         }
         for (int i = 0; i < requestTasks.length; i++) {
             if (requestTasks[i] == null) {
                 continue;
             }
             logger.info("Submit Task<" + requestTasks[i].getTaskId() + ">: "
                     + requestTasks[i].info());
         }
     }
     /**
     * 线程池信息
     * @return
     */
     public String getInfo() {
         StringBuffer sb = new StringBuffer();
         sb.append("\nTask Queue Size:" + taskQueue.size());
         for (int i = 0; i < requestWorkers.length; i++) {
             sb.append("\nWorker " + i + " is "
                     + ((requestWorkers[i].isWaiting()) ? "Waiting." : "Running."));
         }
         return sb.toString();
     }
     /**
     * 销毁线程池
     */
     public synchronized void destroy() {
         for (int i = 0; i < worker_num; i++) {
        	 requestWorkers[i].stopWorker();
        	 requestWorkers[i] = null;
         }
         taskQueue.clear();
     }
     
	private class RequestWorker extends Thread {
		private int index = -1;
		/* 该工作线程是否有效 */
		private boolean isRunning = true;
		/* 该工作线程是否可以执行新任务 */
		private boolean isWaiting = true;

		public RequestWorker(int index) {
			this.index = index;
			start();
		}

		public void stopWorker() {
			this.isRunning = false;
		}

		public boolean isWaiting() {
			return this.isWaiting;
		}

		/**
		 * 循环执行任务 这也许是线程池的关键所在
		 */
		public void run() {
			while (isRunning) {
				RequestTask requestTask = null;
				synchronized (taskQueue) {
					while (taskQueue.isEmpty()) {
						try {
							/* 任务队列为空，则等待有新任务加入从而被唤醒 */
							taskQueue.wait(20);
						} catch (InterruptedException ie) {
							logger.error(ie);
						}
					}
					/* 取出任务执行 */
					requestTask = (RequestTask) taskQueue.remove(0);
				}
				if (requestTask != null) {
					isWaiting = false;
					try {
						if (debug) {
							requestTask.setBeginExceuteTime(new Date());
							taskLogger.debug("Worker<" + index
									+ "> start execute Task<"
									+ requestTask.getTaskId() + ">");
							if (requestTask.getBeginExceuteTime().getTime()
									- requestTask.getSubmitTime().getTime() > 1000)
								taskLogger.debug("longer waiting time. "
										+ requestTask.info()
										+ ",<"
										+ index
										+ ">,time:"
										+ (requestTask.getFinishTime()
												.getTime() - requestTask
												.getBeginExceuteTime()
												.getTime()));
						}
						/* 该任务是否需要立即执行 */
						if (requestTask.needExecuteImmediate()) {
							new Thread(requestTask).start();
						} else {
							requestTask.run();
						}
						if (debug) {
							requestTask.setFinishTime(new Date());
							taskLogger.debug("Worker<" + index
									+ "> finish task<"
									+ requestTask.getTaskId() + ">");
							if (requestTask.getFinishTime().getTime()
									- requestTask.getBeginExceuteTime()
											.getTime() > 1000)
								taskLogger.debug("longer execution time. "
										+ requestTask.info()
										+ ",<"
										+ index
										+ ">,time:"
										+ (requestTask.getFinishTime()
												.getTime() - requestTask
												.getBeginExceuteTime()
												.getTime()));
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e);
					}
					isWaiting = true;
					requestTask = null;
				}
			}
		}
	}
}
