package com.indiafirstpandit.repo.cartRepository;

import com.indiafirstpandit.model.cartItems.CartPujaItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepoPuja extends JpaRepository<CartPujaItem, UUID> {
}
