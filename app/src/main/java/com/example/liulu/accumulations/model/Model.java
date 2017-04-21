package com.example.liulu.accumulations.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liulu on 2017/4/21
 */

public class Model {

    private ExecutorService pool = Executors.newCachedThreadPool();
    private volatile static  Model uniqueInstance;
    private Model(){}
    public static Model getInstance(){
        if(uniqueInstance==null){ // 避免多次上锁
            synchronized(Model.class){
                if(uniqueInstance == null){ // 避免重复new
                    uniqueInstance = new Model();
                }
            }
        }
        return uniqueInstance;
    }

    // 线程池对象
    public ExecutorService getGolbalThreadPool() {
        return pool;
    }


}
