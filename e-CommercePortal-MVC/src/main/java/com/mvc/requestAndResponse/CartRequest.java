package com.mvc.requestAndResponse;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

	@NotBlank(message = "Enter Zip code")
	private String zipcode;

	private int productId;

	@Positive(message = "Quantity should be positive value")
	private int quantity;
	
	@Min(value = 1,message = "Rating should be greater than or equal to 1")
	@Max(value = 5,message = "Rating should be less than or equal to 5")
	private int rating;

}
