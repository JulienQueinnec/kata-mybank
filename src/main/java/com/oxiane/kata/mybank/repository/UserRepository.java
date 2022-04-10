package com.oxiane.kata.mybank.repository;

import com.oxiane.kata.mybank.domain.BankUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<BankUser, Integer> {
    Optional<BankUser> getUserByName(String userConnectedName);
}
