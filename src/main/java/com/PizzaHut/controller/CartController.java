package com.PizzaHut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PizzaHut.dto.CartDto;
import com.PizzaHut.dto.CartDtoWithoutTopping;
import com.PizzaHut.entities.Cart;
import com.PizzaHut.services.CartService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	// add to cart with topping
	@PostMapping("/addWithToppings")
	public ResponseEntity<?> addToCart(@RequestBody CartDto cartdto) {
		try {
			System.out.println(cartdto);
			Cart addTocart = cartService.addTocart(cartdto);
			if (addTocart != null) {
				return Response.success(addTocart);
			} else {
				return Response.error("failed to add to cart");
			}
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}

	// add without topping
	@PostMapping("/addWithoutToppings")
	public ResponseEntity<?> addToCartNoTopping(@RequestBody CartDtoWithoutTopping cartWithoutTopping) {
		try {
			System.out.println(cartWithoutTopping);
			Cart addTocart = cartService.addTocart(cartWithoutTopping);
			if (addTocart != null) {
				return Response.success(addTocart);
			} else {
				return Response.error("failed to add to cart");
			}
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}

	// Delete Cart by cartId
	@DeleteMapping("/deleteById/{cartId}")
	public ResponseEntity<?> deleteCart(@PathVariable("cartId") int cartId) {
		int count = cartService.deleteByCartId(cartId);
		if (count == 1) {
			return Response.success("Deleted");
		} else
			return Response.error("id not found");
	}

	// show all cart of a particular user
	@GetMapping("/{userId}/{status}")
	public ResponseEntity<?> showAllcartOfAUser(@PathVariable("userId") Integer userid,
			@PathVariable("status") Integer statusid) {
		try {
			System.out.println("--()--()" + statusid);
			List<Cart> cartsOfUser = cartService.getAllCartOfUser(userid, statusid);
			if (!cartsOfUser.isEmpty()) {
				return Response.success(cartsOfUser);
			} else {
				return Response.error("Cart is Empty");
			}
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}

	// Get by cartId
	@GetMapping("/{cartid}")
	public ResponseEntity<?> getcartById(@PathVariable("cartid") int cartid) {
		try {
			List<Cart> cartById = cartService.fetchByid(cartid);
			if (!cartById.isEmpty()) {
				return Response.success(cartById);
			} else {
				return Response.error("Cart Item Not Found");
			}
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}

	// Get by deliveryId
	@GetMapping("/deliveryid/{deliveryid}")
	public ResponseEntity<?> getcartByDeliveryId(@PathVariable Integer deliveryid) {
		try {
			System.out.println(deliveryid);
			Cart cartByDeliveryId = cartService.fetchByDeliveryid(deliveryid);
			System.out.println(cartByDeliveryId);
			if (cartByDeliveryId!=null) {
				return Response.success(cartByDeliveryId);
			} else {
				return Response.error("Cart with delivery id Not Found");
			}
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}

	// update cartStatus
	@PatchMapping("/updateCartStatus/{userId}/{deliveryId}")
	public ResponseEntity<?> updateCartStatus(@PathVariable Integer userId,@PathVariable Integer deliveryId) {
		try {
			System.out.println("int updatecart");
			System.out.println("userId: "+userId+"deliveryID: "+deliveryId);
			String changed = cartService.addForeignKey(deliveryId,userId);
			if (changed != null) {
				return Response.success(changed);
			} else {
				return Response.error("Cart is Empty");
			}
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}

}
