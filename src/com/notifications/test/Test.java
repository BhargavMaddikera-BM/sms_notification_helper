package com.notifications.test;

import java.util.Map;

import com.notifications.sms.SMSHelper;
import com.notifications.sms.exception.SMSAlertException;


public class Test
{

	public static void main(String[] args) throws Exception
	{
		testSMS("9003238714", "Hello World1");
		
	}
	
	private static void testSMS(String number, String message)throws SMSAlertException
	{
		SMSHelper.getInstance().send(number,message);
	}
	

}
