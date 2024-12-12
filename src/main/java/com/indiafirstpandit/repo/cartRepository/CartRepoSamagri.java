package com.indiafirstpandit.repo.cartRepository;

import com.indiafirstpandit.model.cartItems.CartSamagriItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepoSamagri extends JpaRepository<CartSamagriItem, UUID> {
}
