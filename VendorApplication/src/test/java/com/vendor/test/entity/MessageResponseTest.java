package com.vendor.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;


public class MessageResponseTest {
	Date date = new Date();
	MessageResponse message1= new MessageResponse("msg",HttpStatus.OK);
	MessageResponse message= new MessageResponse();
	MessageResponse message2= new MessageResponse(date,"msg",HttpStatus.OK);
	
	@Test
	void testMessage()
	{
		message.setMessage("msg");;
		assertEquals(message.getMessage(), "msg");
	}
	@Test
	void testTimeStamp()
	{
		message.setTimeStamp(date);;
		assertEquals(message.getTimeStamp(), date);
	}
	@Test
	void testStatus()
	{
		message.setStatus(HttpStatus.OK);
		assertEquals(message.getStatus(),HttpStatus.OK);
	}
	
}
