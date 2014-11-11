package com.test;

import java.io.IOException;  
import java.util.*;  

public class TestProperties {  
  
    /** 
     * @param args 
     */  
    /*一个java虚拟机对应一个Runtime对象  
     * 不能通过new直接创建Runtime对象 只能通过Runtime.getRuntime静态方法来获得实例对象引用  
     *java虚拟机本身就是windows的一个进程   
     *这个进程中可以启动windows程序中的一些运行实例 通过这种方法启动的windows的实例叫做子进程 
     *java虚拟机通过调用Runtime的exec方法可以启动子进程 
     * 
     *本程序目的打印出java虚拟机的所有属性 
     *在java程序中启动一个windows记事本程序的运行实例 
     *并在该运行实例中打开这个记事本程序的源文件，启动的记事本程序5秒后被关闭 
    */  
      
    public static void main(String[] args) {  
        Properties pro = System.getProperties();  
        //取出当前 虚拟机的所有属性  
        Enumeration<?> e = pro.propertyNames();  
        //得到系统中所有属性的名称  
        while(e.hasMoreElements()){  
            String key = (String)e.nextElement();  
            System.out.println(key + "=" + pro.getProperty(key));  
        }  
          
        Process p = null;  
        //定义一个子进程  
        try {  
            p = Runtime.getRuntime().exec("notepad.exe");  
        } catch (IOException e1) {  
            e1.printStackTrace();  
        }  
        //exec方法说明（要启动的windows的程序  以及程序要打开的文件的名称）  
        try {  
            Thread.sleep(5000);  
            // 等待5 秒钟  
        } catch (InterruptedException e1) {  
            e1.printStackTrace();  
        }  
        p.destroy();  
        //关闭 p所对应的那个进程  
    }  
  
}  
