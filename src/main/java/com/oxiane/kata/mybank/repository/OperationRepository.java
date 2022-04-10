package com.oxiane.kata.mybank.repository;

import com.oxiane.kata.mybank.domain.Operation;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<Operation, Integer> {
}
