package com.product.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;



public class MsgTest {
	LocalDateTime date = LocalDateTime.now();
	Msg message1= new Msg(HttpStatus.OK, date, "msg");
	Msg message= new Msg();
	@Test
	void testTimeStamp()
	{
		message.setTimestamp(date);;
		assertEquals(message.getTimestamp(), date);
	}
	@Test
	void testMessage()
	{
		message.setMessage("msg");;
		assertEquals(message.getMessage(), "msg");
	}
	@Test
	void testStatusCode()
	{
		message.setStatusCode(HttpStatus.OK);
		assertEquals(message.getStatusCode(), HttpStatus.OK);
	}
}