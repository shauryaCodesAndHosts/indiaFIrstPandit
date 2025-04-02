package com.indiafirstpandit.repo;

import com.indiafirstpandit.model.User;
import com.indiafirstpandit.model.UsersDeleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersDeletedRepository extends JpaRepository<UsersDeleted, UUID> {

}
