package com.indiafirstpandit.service.cartservice;

import com.indiafirstpandit.model.cartItems.Cart;
import com.indiafirstpandit.model.cartItems.CartProductItem;
import com.indiafirstpandit.repo.ProductRepository;
import com.indiafirstpandit.repo.cartRepository.CartRepoProduct;
import com.indiafirstpandit.repo.cartRepository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceProducts {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepoProduct cartRepoProduct;

    public CartProductItem updateQuantity(UUID userCartId, int quantity, UUID id) {
        //id is of cart product item
        Cart userCart = cartRepository.getReferenceById(userCartId);
        List<CartProductItem> productsInCart = userCart.getProductCart();
        Optional<CartProductItem> productToBeUpdated = productsInCart.stream().filter(item ->item.getId().equals(id)).findFirst();
        if(productToBeUpdated.isPresent())
        {
            CartProductItem productItem = productToBeUpdated.get();
            productItem.setQuantity(quantity);
            cartRepoProduct.save(productItem);
            return productItem;
        }

        return new CartProductItem();
    }

    public CartProductItem addProductToCart(UUID userCartId, int quantity, UUID id) {
        Cart userCart = cartRepository.getReferenceById(userCartId);
        CartProductItem cartProductItem = new CartProductItem();
        cartProductItem.setProduct(productRepository.getReferenceById(id));
        cartProductItem.setQuantity(quantity);
        cartProductItem.setDiscount(0);
        cartRepoProduct.save(cartProductItem);

        userCart.getProductCart().add(cartProductItem);
        cartRepository.save(userCart);
        return cartProductItem;
    }
}
