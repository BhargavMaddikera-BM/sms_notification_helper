package com.notifications.sms;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;


import com.notifications.sms.exception.SMSAlertException;
import com.notifications.sms.exception.SMSAlertRuntimeException;



 class SMSNotifier {
	 
	 private	String url;
	 private	String workingkeyParam;
	 private	String  workingKeyVal;
	 private	String senderVal;		
	 private	String mobileNoParam;
	 private	String messageParam;
	 private	String senderParam;
	 private    static SMSNotifier smsNotifier=null;
	 private    Properties properties;
	
	static SMSNotifier getInstance()
	{
	
		 if(smsNotifier==null)
		 {
			 smsNotifier=new SMSNotifier();
		 }
		 return smsNotifier;
	}
	
	private SMSNotifier()
	{
		setMailProperties();
	}
	
	
	 Properties getProperties() {
		return properties;
	}

	private Properties getProperties(String sourcePath)throws SMSAlertException
	{
		
		 InputStream is = null;		 
	
		 Properties prop=null;
	        try 
	        {
	        	prop = new Properties();
	            is = SMSNotifier.class.getResourceAsStream(sourcePath);
	            prop.load(is);
	            System.out.println(prop);
	        }
	        catch (Exception e) 
	        {
	           throw new SMSAlertException(e);
	        }
	        
	        return prop;
	        

	}
	
	
	private void setMailProperties()throws SMSAlertRuntimeException
	{
	  try
	  {
		  properties =getProperties("/com/notifications/sms/config/config.properties");
		 if (properties != null)
		 {
			 url = properties.getProperty("URL");			
			 workingKeyVal = properties.getProperty("WORKING_KEY_VAL_PARAM");
			 senderVal =properties.getProperty("SENDER_VAL_PARAM");
			 workingkeyParam = properties.getProperty("WORKING_KEY_PARAM");
			 mobileNoParam = properties.getProperty("MOBILE_NO_PARAM");
			 messageParam = properties.getProperty("MESSAGE_PARAM");
			 senderParam =properties.getProperty("SENDER_PARAM");
	
		
			 
		 }
	  }catch(Exception e)
	  {
		  e.printStackTrace();
		  throw new SMSAlertRuntimeException(e);
	  }
	}
	
  void send(String msisdn, String message)throws SMSAlertException
  {
		
		try
		{
			
		
			
			StringBuffer buffer = new StringBuffer( url );
			buffer.append( workingkeyParam + "=" + URLEncoder.encode( workingKeyVal, "UTF-8" ));
			buffer.append( "&" + senderParam + "=" + URLEncoder.encode( senderVal, "UTF-8" ));
			buffer.append( "&" + messageParam + "="+ URLEncoder.encode( message, "UTF-8" ) );
			buffer.append( "&" + mobileNoParam + "=" + URLEncoder.encode( msisdn.trim(), "UTF-8" ));
			
			
			System.out.println( "URL: " + buffer );
			
			HttpURLConnection connection = (HttpURLConnection)new URL( buffer.toString() ).openConnection();
			InputStream in = connection.getInputStream();
			
			System.out.println("ResponseCode: "+connection.getResponseCode() + "	ResponseMessage: "+connection.getResponseMessage());
			
			int ch = 0;
			buffer = new StringBuffer();
			
			while((ch = in.read())!=-1){
				buffer.append((char)ch);
			}
			
			System.out.println("SmsGateway Response: "+buffer.toString());
			
			
		} catch (Exception e) {
			throw new SMSAlertException(e);
		}
		
		
	}

	
}
