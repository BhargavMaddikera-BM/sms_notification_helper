package com.notifications.sms;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 class SMSThreadPoolExecutor {

	private static SMSThreadPoolExecutor smsThreadPoolExecutor;
	private ExecutorService executor;
	
	
	
	private SMSThreadPoolExecutor()
	{

		
        Properties p=SMSNotifier.getInstance().getProperties();
        String threadPoolSize = p.getProperty("SMS_THREAD_POOL_SIZE");
        int poolSize=25;
        if(threadPoolSize!=null)
        {
        	poolSize=Integer.parseInt(threadPoolSize);
        }
		
		executor = Executors.newFixedThreadPool(poolSize);	
	}
	
	
	 ExecutorService getExecutor() 
	 {		
		return executor;
     }


	 static SMSThreadPoolExecutor getInstance()
	{
	
		 if(smsThreadPoolExecutor==null)
		 {
			 smsThreadPoolExecutor=new SMSThreadPoolExecutor();
		 }
		
		 return smsThreadPoolExecutor;
	}
	

	
	 

}
