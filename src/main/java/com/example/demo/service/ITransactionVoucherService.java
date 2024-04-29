package com.example.demo.service;

import com.example.demo.entities.TransactionVoucher;

import java.util.List;

public interface ITransactionVoucherService {
    //save
    public TransactionVoucher save(TransactionVoucher transactionVoucher);

    //update
    public TransactionVoucher update(TransactionVoucher transactionVoucher);

    //delete
    public void delete(Long id);

    //findall
    public List<TransactionVoucher> findAll();
}
