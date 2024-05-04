package com.notifications.sms.exception;

import com.notifications.common.AlertException;


public class SMSAlertException extends AlertException
{
	
	public SMSAlertException(Exception exception)
	{
		super(exception);
	}
	
	public SMSAlertException(String message)
	{
		super(message);
	}
	
	public SMSAlertException(String message,Exception exception)
	{
		super(message,exception);
	}


}
