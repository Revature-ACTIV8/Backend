package com.revature.training.backend.service;

import com.revature.training.backend.model.Cart;
import com.revature.training.backend.model.CartItem;
import com.revature.training.backend.repository.CartRepository;
import com.revature.training.backend.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> getCartById(Long orderId) {
        return cartRepository.findById(orderId);
    }

    public Optional<Cart> getCartByCustomerId(Long customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart updateCart(Long orderId, Cart cartDetails) {
        Cart cart = cartRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + orderId));

        cart.setCustomerId(cartDetails.getCustomerId());
        return cartRepository.save(cart);
    }

    public void deleteCart(Long orderId) {
        cartRepository.deleteById(orderId);
    }

    public CartItem addItemToCart(Long orderId, CartItem cartItem) {
        Cart cart = cartRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + orderId));

        cartItem.setCart(cart);
        return cartItemRepository.save(cartItem);
    }

    public void removeItemFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public List<CartItem> getCartItems(Long orderId) {
        return cartItemRepository.findByCart_OrderId(orderId);
    }
}