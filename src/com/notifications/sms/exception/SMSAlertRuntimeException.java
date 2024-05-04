package com.notifications.sms.exception;

import com.notifications.common.AlertRuntimeException;


public class SMSAlertRuntimeException extends AlertRuntimeException
{
	
	public SMSAlertRuntimeException(Exception exception)
	{
		super(exception);
	}
	
	public SMSAlertRuntimeException(String message)
	{
		super(message);
	}
	
	public SMSAlertRuntimeException(String message,Exception exception)
	{
		super(message,exception);
	}


}
