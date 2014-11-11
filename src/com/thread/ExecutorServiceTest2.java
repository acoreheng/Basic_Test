package com.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 如果要实现真正的并发同时执行，可通过CyclicBarrier来控制。
 * @author AcoreHeng
 *
 */
public class ExecutorServiceTest2 {
     public static void main(String[] args) {
          int count = 1000;
          CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
          ExecutorService executorService = Executors.newFixedThreadPool(count);
          for (int i = 0; i < count; i++)
               executorService.execute(new ExecutorServiceTest2().new Task(cyclicBarrier));

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
          private CyclicBarrier cyclicBarrier;

          public Task(CyclicBarrier cyclicBarrier) {
               this.cyclicBarrier = cyclicBarrier;
          }

          @Override
          public void run() {
               try {
                    // 等待所有任务准备就绪
                    cyclicBarrier.await();
                    // 测试内容
               } catch (Exception e) {
                    e.printStackTrace();
               }
          }
     }
}