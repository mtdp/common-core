package me.wanx.common;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import me.wanx.common.core.service.ILoggerAdapter;
import me.wanx.common.core.service.LoggerAdapterDefaultImpl;
import me.wanx.common.core.utils.IdWorker;

public class IdWorkerTest {
	
	ILoggerAdapter logger = new LoggerAdapterDefaultImpl(IdWorkerTest.class);
	
    public static void main(String []args){ 
        IdWorkerTest test = new IdWorkerTest(); 
        test.test2(); 
    } 

    public void test2(){ 
        final IdWorker w = new IdWorker(1,2); 
        final CyclicBarrier cdl = new CyclicBarrier(100); 

        for(int i = 0; i < 100; i++){ 
            new Thread(new Runnable() { 
                @Override 
                public void run() { 
	                try { 
	                    cdl.await(); 
	                } catch (InterruptedException e) { 
	        			Thread.currentThread().interrupt();
	                    e.printStackTrace(); 
	                } catch (BrokenBarrierException e) { 
	                    e.printStackTrace(); 
	                } 
	                logger.info(w.nextId()+"");
                } 
             }).start(); 
        } 
        try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}

    } 
}