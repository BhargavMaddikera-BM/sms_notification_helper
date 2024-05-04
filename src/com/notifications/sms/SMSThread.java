package com.notifications.sms;



 class SMSThread implements Runnable {

	private String mobileNo;
	private String message;
	private int retry_count;
	private int retry_lc=0;
	
	
	SMSThread(String mobileNo,String message)
	{
		this.mobileNo=mobileNo;
		this.message=message;
		String retry_str=SMSNotifier.getInstance().getProperties().getProperty("RETRY_COUNT");
		if(retry_str==null)
		{
			this.retry_count=3;
		}
		else
		{
			this.retry_count=Integer.parseInt(retry_str);
		}
		
	}
	@Override
	public void run() 
	{
		try 
		{
			   SMSNotifier mailNotifier=SMSNotifier.getInstance();			
				mailNotifier.send(mobileNo,message);
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			retry_lc++;
			if(retry_lc==retry_count)
			{
				e.printStackTrace();
			}
			else
			{
				run();
			}
		}
	}
	

}
