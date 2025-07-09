package com.example.offline_transfer.repository;

import com.example.offline_transfer.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderIdAndSyncedFalse(Long senderId);
}
