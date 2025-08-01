package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.LocalOrderDto;
import com.indiafirstpandit.enums.CartItemType;
import com.indiafirstpandit.enums.OrderItemType;
import com.indiafirstpandit.model.*;
import com.indiafirstpandit.repo.LocalOrderItemRepository;
import com.indiafirstpandit.repo.LocalOrderRepository;
import com.indiafirstpandit.requests.RazorPayVerificationRequest;
import com.indiafirstpandit.response.OptionsResponse;
import com.razorpay.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LocalOrderService {

    @Autowired
    private LocalOrderRepository localOrderRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private RazorpayService razorpayService;

    @Autowired
    private PujaService pujaService;

    @Autowired
    private SamagriService samagriService;

    @Autowired
    private ProductService productService;

    @Autowired
    private LocalOrderItemRepository localOrderItemRepository;

    public List<LocalOrder> getAllOrders() {
        return localOrderRepository.findAll();
    }

    public LocalOrder getOrderById(UUID id) {
        return localOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<LocalOrder> getOrdersByUserId(UUID userId) {
        return localOrderRepository.findByUserId(userId);
    }
//
//    public List<LocalOrder> getOrdersByPujaId(UUID pujaId) {
//        return localOrderRepository.findByPujaId(pujaId);
//    }
//
//    public List<LocalOrder> getOrdersBySamagriId(UUID samagriId) {
//        return localOrderRepository.findBySamagriId(samagriId);
//    }

    public LocalOrder saveOrder(LocalOrder order) {
        return localOrderRepository.save(order);
    }

    public void deleteOrder(UUID id) {
        localOrderRepository.deleteById(id);
    }

    public OptionsResponse placeOrder(User currentUser, UUID AddressId) {
        Cart userCart = currentUser.getCart();
//        if (userCart == null || userCart.getCartItems().isEmpty()) {
//            return ResponseEntity.badRequest().body(null); // Return an error response if the cart is empty
//        }
//        Double totalPrice = userCart.getCartItems().stream().mapToDouble(cartItem -> cartItem.getFinalPrice()).sum();
        Double totalPrice = userCart.getCartItems().stream().reduce(0.0,(sum, cartItem)-> sum + cartItem.getFinalPrice(), Double::sum );
        Address orderAddress = addressService.getAddress(AddressId);
        Order razorPayServerOrder = razorpayService.createRazorpayOrder(totalPrice);
        OptionsResponse options = razorpayService.createOptions(razorPayServerOrder,currentUser, orderAddress);
        LocalOrder localOrder = new LocalOrder();
        List<LocalOrderItem> localOrderItems = new ArrayList<>();
        for( CartItem cartItem : userCart.getCartItems())
        {
            if(cartItem.getCartItemType() == CartItemType.Puja)
            {
//                localOrder.setPuja(pujaService.getPujaById(cartItem.getItemId()));
                LocalOrderItem localOrderItem = new LocalOrderItem();
                localOrderItem.setItemName(pujaService.getPujaById(cartItem.getItemId()).getName());
                localOrderItem.setOrderItemType(OrderItemType.Puja);
                localOrderItem.setOrder(localOrder);
                localOrderItem.setPrice(cartItem.getFinalPrice());
                localOrderItem.setItemId(cartItem.getItemId());
                localOrderItem.setQuantity(cartItem.getQuantity());
                localOrderItemRepository.save(localOrderItem);
                localOrderItems.add(localOrderItem);
            }

            if(cartItem.getCartItemType() == CartItemType.Samagri)
            {
//                localOrder.setSamagri(samagriService.getSamagriById(cartItem.getItemId()));
                LocalOrderItem localOrderItem = new LocalOrderItem();
                localOrderItem.setItemName(samagriService.getSamagriById(cartItem.getItemId()).getName());
                localOrderItem.setOrderItemType(OrderItemType.Samagri);
                localOrderItem.setOrder(localOrder);
                localOrderItem.setPrice(cartItem.getFinalPrice());
                localOrderItem.setItemId(cartItem.getItemId());
                localOrderItem.setQuantity(cartItem.getQuantity());
                localOrderItemRepository.save(localOrderItem);
                localOrderItems.add(localOrderItem);

            }
            if(cartItem.getCartItemType() == CartItemType.Product)
            {
//                localOrder.setProduct(productService.getProductByIdNotProductDto(cartItem.getItemId()));
                LocalOrderItem localOrderItem = new LocalOrderItem();
                localOrderItem.setItemName(productService.getProductByIdNotProductDto(cartItem.getItemId()).getName());
                localOrderItem.setOrderItemType(OrderItemType.Product);
                localOrderItem.setOrder(localOrder);
                localOrderItem.setPrice(cartItem.getFinalPrice());
                localOrderItem.setItemId(cartItem.getItemId());
                localOrderItem.setQuantity(cartItem.getQuantity());
                localOrderItemRepository.save(localOrderItem);
                localOrderItems.add(localOrderItem);

            }
        }
        localOrder.setOrderItems(localOrderItems);
        localOrder.setUser(currentUser);
        localOrder.setAddress(orderAddress);
        localOrder.setTotalAmount(BigDecimal.valueOf(totalPrice));

        RazorpayOrder razorpayOrder = new RazorpayOrder();

        razorpayOrder.setOrder_id(options.getOrder_id());
        razorpayOrder.setAmount(options.getAmount());
        razorpayOrder.setName(options.getName());
        razorpayOrder.setDescription(options.getDescription());
        localOrder.setRazorpayOrder(razorpayOrder);
        localOrderRepository.save(localOrder);
        System.out.println("local order  ->"+new LocalOrderDto(localOrder));
        System.out.println("razorpay order -> "+ razorPayServerOrder);
        return options;
    }

    public boolean verify(User currentUser, RazorPayVerificationRequest razorPayVerificationRequest) {

        LocalOrder localOrder = localOrderRepository.findById(razorPayVerificationRequest.getLocalOrderId()).get();
        RazorpayOrder razorpayOrder = localOrder.getRazorpayOrder();
        razorpayOrder.setRazorpay_payment_id(razorPayVerificationRequest.getRazorpay_payment_id());
        razorpayOrder.setRazorpay_order_id(razorPayVerificationRequest.getRazorpay_order_id());
        razorpayOrder.setRazorpay_signature(razorPayVerificationRequest.getRazorpay_signature());
        localOrderRepository.save(localOrder);

        return razorpayService.verify(localOrder);
    }
}
