package com.etf.qa.tests;


import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;
import com.etf.qa.common.Helper;
import com.etf.qa.common.TestBase;

//import com.etf.qa.mailinator.api.ApiClient;
//import com.etf.qa.mailinator.email.*;
//import com.etf.qa.mailinator.inbox.*;




public class MailTest extends TestBase {
	
//	@Test( invocationCount = 1, threadPoolSize=1)
//	public void SendMessage()
//	{
//		Helper.SendEmail("sample@somedomain.com", "sample@mailinator.com", "Test Subject", "Test Message Body");
//	}
//	
//	String strMessageId;
//	
//	
//	@Test()
//	public void CheckMailinatorInbox()
//	{
//		
//		
//		ApiClient apiClient = new ApiClient("LONG_GUID_HERE");
//		
//		Inbox inbox = apiClient.FetchMailinatorInbox("sample@mailinator.com");
//		
//		List<InboxMessageHeader> lsMessageHeaders = inbox.getMessages();
//		
//		for (Iterator<InboxMessageHeader> m = lsMessageHeaders.iterator(); m.hasNext();)
//		{
//			InboxMessageHeader message = m.next();
//			logger.info("Subject: " + message.getSubject());
//			logger.info("Opened: " + message.getBeen_read().toString());
//			strMessageId = message.getId();
//		}
//	
//	}
//	
//	@Test()
//	public void CheckPrivateInbox()
//	{
////		logger.info(MailinatorApi.FetchPrivateInbox());
//	}
//	
//	@Test()
//	public void GetMessage()
//	{
//		
//		ApiClient apiClient = new ApiClient("LONG_GUID_HERE");
//		
//		Email email = apiClient.FetchEmail(strMessageId);
//		
//		logger.info("Subject: " + email.getData().getSubject());
//		logger.info("Opened: " + email.getData().getBeenRead());
//		logger.info("From: "+ email.getData().getFrom());
//		logger.info("To: "+ email.getData().getTo());
//		logger.info("Message Body: "+ email.getData().getParts().get(0).getBody());
//	
//		
//	}
	
	

}//class
