package com.example.demo.service.impl;

import com.example.demo.entities.TransactionVoucher;
import com.example.demo.repositories.ITransactionVoucherRepository;
import com.example.demo.service.ITransactionVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionVoucherServiceImpl implements ITransactionVoucherService {
    @Autowired
    private ITransactionVoucherRepository iTransactionVoucherRepository;
    @Override
    public TransactionVoucher save(TransactionVoucher transactionVoucher) {
        return iTransactionVoucherRepository.save(transactionVoucher);
    }
    @Override
    public TransactionVoucher update(TransactionVoucher transactionVoucher) {
        Optional<TransactionVoucher> existingTransactionVoucher = iTransactionVoucherRepository.findById(transactionVoucher.getId());
        if(existingTransactionVoucher.isPresent()){
            TransactionVoucher updatedTransactionVoucher = existingTransactionVoucher.get();
            updatedTransactionVoucher.setPaymentMethod(transactionVoucher.getPaymentMethod());
            updatedTransactionVoucher.setTotal_amount(transactionVoucher.getTotal_amount());
            return iTransactionVoucherRepository.save(updatedTransactionVoucher);
        } else {
            throw new RuntimeException("TransactionVoucher not found with id: " + transactionVoucher.getId());
        }
    }
    @Override
    public void delete(Long id) {
        iTransactionVoucherRepository.deleteById(id);
    }

    @Override
    public List<TransactionVoucher> findAll() {
        return iTransactionVoucherRepository.findAll();
    }
}
