package com.product.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class MessageTest {
	Date date = new Date();
	Message message1= new Message(404, date, "msg");
	Message message= new Message();
	
	@Test
	void testStatusCode()
	{
		message.setStatusCode(404);
		assertEquals(message.getStatusCode(), 404);
	}
	@Test
	void testTimeStamp()
	{
		message.setTimestamp(date);
		assertEquals(message.getTimestamp(), date);
	}
	@Test
	void testMessage()
	{
		message.setMessage("msg");;
		assertEquals(message.getMessage(), "msg");
	}
}