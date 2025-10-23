package com.revature.training.backend.controller;

import com.revature.training.backend.model.Cart;
import com.revature.training.backend.model.CartItem;
import com.revature.training.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Start here for requests
@RequestMapping("/api/carts")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart createdCart = cartService.createCart(cart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long orderId) {
        return cartService.getCartById(orderId)
                .map(cart -> new ResponseEntity<>(cart, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Cart> getCartByCustomerId(@PathVariable Long customerId) {
        return cartService.getCartByCustomerId(customerId)
                .map(cart -> new ResponseEntity<>(cart, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long orderId, @RequestBody Cart cart) {
        try {
            Cart updatedCart = cartService.updateCart(orderId, cart);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long orderId) {
        cartService.deleteCart(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<CartItem> addItemToCart(@PathVariable Long orderId, @RequestBody CartItem cartItem) {
        try {
            CartItem addedItem = cartService.addItemToCart(orderId, cartItem);
            return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartItemId) {
        cartService.removeItemFromCart(cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long orderId) {
        List<CartItem> items = cartService.getCartItems(orderId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}