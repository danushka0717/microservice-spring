package com.micro.stock.dbservice.repository;

import com.micro.stock.dbservice.model.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quotes, Integer> {
    List<Quotes> findByUsername(String username);
}
