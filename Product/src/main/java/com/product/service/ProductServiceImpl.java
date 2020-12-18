package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.product.client.AuthClient;
import com.product.exception.InvalidUser;
import com.product.exception.ProductIdNotFoundException;
import com.product.exception.ProductNameNotFoundException;
import com.product.exception.RatingNotValidException;
import com.product.model.AuthResponse;
import com.product.model.ProductItem;
import com.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	AuthClient authClient;

	public List<ProductItem> getAll() {
		return productRepository.findAll();
	}

	@Override
	public AuthResponse getValidity(@RequestHeader("Authorization") final String token) {
		AuthResponse response = authClient.getValidity(token);
		if (response.isValid()) {
			return response;
		} else {
			log.info("unauthorized user");
			throw new InvalidUser("unauthorized user");
		}
	}

	@Override
	public ProductItem searchProductById(int id, String token) throws ProductIdNotFoundException {
		Optional<ProductItem> product = productRepository.findById(id);
		product.orElseThrow(() -> new ProductIdNotFoundException("Product Id not found"));
		return product.get();

	}

	@Override
	public ProductItem searchProductByName(String name, String token) throws ProductNameNotFoundException {
		Optional<ProductItem> product = productRepository.findByProductNameContainingIgnoreCase(name);
		product.orElseThrow(() -> new ProductNameNotFoundException("Product name Not Found"));
		return product.get();
	}

	@Override
	public void addProductRating(String token, int id, int rating)
			throws ProductIdNotFoundException, RatingNotValidException {

		if (rating <= 0 || rating > 5) {
			throw new RatingNotValidException("Enter valid Rating");
		}
		if (productRepository.existsById(id)) {
			ProductItem product = productRepository.findById(id).get();

			int ratedCount = product.getRatedCount() + 1;
			double newRating = (product.getRating() * product.getRatedCount() + rating) / ratedCount;

			product.setRating((double) Math.round(newRating * 100) / 100);

			productRepository.save(product);
		} else {
			throw new ProductIdNotFoundException("Product id not found");
		}

	}

}
