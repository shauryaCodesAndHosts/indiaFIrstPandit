package com.indiafirstpandit.repo.cartRepository;


import com.indiafirstpandit.model.cartItems.CartProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepoProduct extends JpaRepository<CartProductItem, UUID> {
}
