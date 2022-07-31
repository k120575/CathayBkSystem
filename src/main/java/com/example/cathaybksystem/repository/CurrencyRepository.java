package com.example.cathaybksystem.repository;

import com.example.cathaybksystem.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query("SELECT c FROM Currency c")
    List<Currency> getAllCurrency();

    Currency findByCurrencyName(String currencyName);
}
