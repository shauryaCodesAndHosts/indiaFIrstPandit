package com.indiafirstpandit.repo.cartRepository;

import com.indiafirstpandit.model.cartItems.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID>
{

}
