package com.mvc.requestAndResponse;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Msg {
	private HttpStatus statusCode;
	  private LocalDateTime timestamp;
	  private String message; 
	  
	  public Msg(String message,HttpStatus statusCode) {
			super();
			this.message = message;
			this.statusCode=statusCode;
		}
}
