//package com.indiafirstpandit.service.cartservice;
//
//import com.indiafirstpandit.model.cartItems.Cart;
//import com.indiafirstpandit.model.cartItems.CartPujaItem;
//import com.indiafirstpandit.repo.cartRepository.CartRepoPuja;
//import com.indiafirstpandit.repo.cartRepository.CartRepository;
//import com.indiafirstpandit.service.PujaService;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
////@Transactional
//public class CartServicePuja {
//
//    @Autowired
//    private CartRepoPuja cartRepoPuja;
//
//    @Autowired
//    private PujaService pujaService;
//
//    @Autowired
//    private CartRepository cartRepository;
//
//
//    public CartPujaItem updatePandits(UUID userCartId, UUID id, int pandits) {
//        // id here is the id of cart item puja
//        Cart userCart = cartRepository.getReferenceById(userCartId);
//        List<CartPujaItem> oldPujaItems = userCart.getPujaCart();
//        Optional<CartPujaItem> itemToBeUpdated = oldPujaItems.stream().filter(item -> item.getId().equals(id)).findFirst();
//
//        if(itemToBeUpdated.isPresent())
//        {
//            CartPujaItem item = itemToBeUpdated.get();
//            item.setPanditAdded(pandits);
//            cartRepoPuja.save(item);
//            return item;
//        }
//        return new CartPujaItem();
//    }
//
//public CartPujaItem addToCart(UUID userCartId, UUID pujaId, int pandits) {
//    // Create a new CartPujaItem
//    CartPujaItem cartPujaItem = new CartPujaItem();
//    cartPujaItem.setPuja(pujaService.getPujaById(pujaId));
//    cartPujaItem.setPanditAdded(pandits);
//    cartPujaItem.setDiscount(0);
//
//    // Save the CartPujaItem to the database
//    cartRepoPuja.save(cartPujaItem);
//
//    // Retrieve the user's cart
//    Cart userCart = cartRepository.getReferenceById(userCartId);
//
//    // Add the new item to the user's PujaCart list
//    userCart.getPujaCart().add(cartPujaItem);
//
//    // Save the updated cart to the database
//    cartRepository.save(userCart);
//
//    return cartPujaItem;
//}
//
//}
