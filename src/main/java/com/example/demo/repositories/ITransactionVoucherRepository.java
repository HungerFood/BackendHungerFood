package com.example.demo.repositories;

import com.example.demo.entities.TransactionVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionVoucherRepository extends JpaRepository<TransactionVoucher, Long>{
}
