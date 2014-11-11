package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 这里申请了1000个线程，并且执行1000次任务。当所有任务完成后，main退出。
 * @author AcoreHeng
 *
 */
public class ExecutorServiceTest {
     public static void main(String[] args) {
          int count = 1000;
          ExecutorService executorService = Executors.newFixedThreadPool(count);
          for (int i = 0; i < count; i++)
               executorService.execute(new ExecutorServiceTest().new Task());

          executorService.shutdown();
          while (!executorService.isTerminated()) {
               try {
                    Thread.sleep(10);
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }
          }
     }

     public class Task implements Runnable {

          @Override
          public void run() {
               try {
                    // 测试内容
            	   System.out.println("Test");
               } catch (Exception e) {
                    e.printStackTrace();
               }
          }
     }
}