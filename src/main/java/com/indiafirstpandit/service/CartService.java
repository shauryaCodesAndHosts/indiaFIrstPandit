package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.CartItemDto;
import com.indiafirstpandit.dto.Quantity;
import com.indiafirstpandit.enums.CartItemType;
import com.indiafirstpandit.model.*;
import com.indiafirstpandit.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PujaRepository pujaRepository;

    @Autowired
    private SamagriRepository samagriRepository;

    public Cart addToCart(User user, CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.setItemId(cartItemDto.getItemId());
        cartItem.setCartItemType(cartItemDto.getCartItemType());
        cartItem.setDiscount(cartItemDto.getDiscount());
        if (cartItemDto.getCartItemType() == CartItemType.Product)
        {
            Product product = productRepository.findById(cartItemDto.getItemId()).get();
        cartItem.setFinalPrice(cartItemDto.getQuantity()*(product.getPrice()));
            if (cartItemDto.getQuantity() <= product.getStock()) {
                cartItem.setQuantity(cartItemDto.getQuantity());
            }
            else {
                return new Cart();
            }
        }
        else if (cartItemDto.getCartItemType() == CartItemType.Puja) {
            Puja puja = pujaRepository.findById(cartItemDto.getItemId()).get();
            cartItem.setFinalPrice(puja.getAmount() + cartItemDto.getQuantity()*(puja.getPricePerExtraPandit())
            );
            if (cartItemDto.getQuantity() <= puja.getMaxPandits()) {
                cartItem.setQuantity(cartItemDto.getQuantity());
            }
            else {
                return new Cart();
            }

        } else if (cartItemDto.getCartItemType() == CartItemType.Samagri) {
            Samagri samagri = samagriRepository.findById(cartItemDto.getItemId()).get();
            cartItem.setFinalPrice(
                    cartItemDto.getQuantity()*(samagri.getPrice())
            );

            if (cartItemDto.getQuantity() <= samagri.getStock()) {
                cartItem.setQuantity(cartItemDto.getQuantity());
            }
            else {
                return new Cart();
            }
        }
        cartItem.setCart(user.getCart());
        cartItemRepository.save(cartItem);
        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.add(cartItem);
        cart.setCartItems(cartItems);
        cartRepository.save(cart);
        System.out.println("\n"+cartItemDto);
        return cart;
    }

    public Cart updateCart(User user ,UUID id, Quantity quantity) {
//        return null;
        CartItem cartItem = cartItemRepository.findById(id).get();
        if(cartItem.getCartItemType()==CartItemType.Product)
        {
            Product product = productRepository.findById(cartItem.getItemId()).get();
            if (product.getStock()>=quantity.getQuantity())
            {
                cartItem.setQuantity(quantity.getQuantity());
            }
            else{
                return new Cart();
            }
        }
        else if (cartItem.getCartItemType()== CartItemType.Samagri)
        {
            Samagri samagri = samagriRepository.findById(cartItem.getItemId()).get();
            if (samagri.getStock()>= quantity.getQuantity())
            {
                cartItem.setQuantity(quantity.getQuantity());
            }
            else {
                return new Cart();
            }
        } else if (cartItem.getCartItemType() == CartItemType.Puja) {
            Puja puja = pujaRepository.findById(cartItem.getItemId()).get();
            if (puja.getMaxPandits()>= quantity.getQuantity())
            {
                cartItem.setQuantity(quantity.getQuantity());
            }
            else {
                return new Cart();
            }

        }

        else {
            return new Cart();
        }
        cartItemRepository.save(cartItem);
        return user.getCart();

    }

    public void deleteCartItem(User user, UUID id) {
        Cart cart = user.getCart();

        List<CartItem> cartItems = cart.getCartItems();
        cartItems.removeIf(cartItem -> cartItem.getId().equals(id));
        cart.setCartItems(cartItems);
        cartRepository.save(cart);
        return ;
    }
}
