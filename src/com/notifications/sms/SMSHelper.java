package com.notifications.sms;

import java.util.Properties;

import com.notifications.sms.exception.SMSAlertException;

public class SMSHelper
{
	private  String isThreadPoolNeeded;
	private static SMSHelper smsHelper;
	
	public static SMSHelper getInstance()
		{
		
			 if(smsHelper==null)
			 {
				 smsHelper=new SMSHelper();
			 }
			
			 return smsHelper;
		}
		 
	private SMSHelper()
	{
		 Properties p=SMSNotifier.getInstance().getProperties();
         isThreadPoolNeeded = p.getProperty("SMS_THREAD_POOL_REQUIRED");
         if(isThreadPoolNeeded==null)
         {
        	 isThreadPoolNeeded="N";
         }
	}	
public  void send(String number, String message)throws SMSAlertException
{

	try
	{
		SMSThread smsThread=new SMSThread(number,message);
			
		if(isThreadPoolNeeded.equals("Y"))
		{
			SMSThreadPoolExecutor.getInstance().getExecutor().execute(smsThread);
		}
		else
		{
			smsThread.run();
		}
	}catch(Exception e)
	{
		throw new SMSAlertException(e);
	}
	
	
	
	
}

}
