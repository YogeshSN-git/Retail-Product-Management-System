package com.mvc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.feign.AuthFeign;
import com.mvc.feign.ProceedToBuyFeign;
import com.mvc.feign.ProductFeign;
import com.mvc.feign.VendorFeign;
import com.mvc.model.ProductItem;
import com.mvc.requestAndResponse.CartRequest;

@Service
public class ProceedToBuyServiceImpl implements ProceedToBuyService {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@Autowired
	AuthFeign authFeign;

	@Autowired
	VendorFeign vendorFeign;

	@Autowired
	ProductFeign productFeign;

	@Autowired
	ProceedToBuyFeign proceedToBuyFeign;

	@Override
	public ModelAndView addToWishList(int productid, HttpSession session, String referer) {
		String token = (String) session.getAttribute("token");

		String customerid = (String) session.getAttribute("name");

		proceedToBuyFeign.addProductToWishlist(token, customerid, productid);

		return new ModelAndView("redirect:" + referer);
	}

	@Override
	public ModelAndView addToCart(@Valid CartRequest cartRequest, BindingResult br, HttpSession session)
			throws ParseException {
		String token = (String) session.getAttribute("token");

		if (cartRequest.getQuantity() > vendorFeign.getProductStock(cartRequest.getProductId(), token)) {
			br.addError(new FieldError("cartRequest", "quantity", "Quantity should be less than stock"));
		}

		if (br.hasErrors()) {

			ModelAndView errorModelView = proceedToBuy(cartRequest, cartRequest.getProductId(), session);

			return errorModelView;
		}

		String customerid = (String) session.getAttribute("name");

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 7);
		Date date = c.getTime();

		proceedToBuyFeign.addProductToCart(token, customerid, cartRequest.getProductId(), cartRequest.getZipcode(),
				dateFormat.format(date), cartRequest.getQuantity());

		productFeign.addProductRating(token, cartRequest.getProductId(), cartRequest.getRating());

		ModelAndView modelView = new ModelAndView("redirect:cart");

		return modelView;
	}

	@Override
	public ModelAndView proceedToBuy(CartRequest cartRequest, int productid, HttpSession session) {
		String token = (String) session.getAttribute("token");

		ModelAndView modelView = new ModelAndView("proceedToBuy");

		ProductItem productItem = productFeign.searchProductById(token, productid);
		modelView.addObject("productItem", productItem);

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 7);
		Date date = c.getTime();

		modelView.addObject("deliveryDate", dateFormat.format(date));

		modelView.addObject("stock", vendorFeign.getProductStock(productid, token));
		return modelView;
	}
}
