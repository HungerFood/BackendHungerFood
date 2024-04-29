package com.example.demo.service.impl;

import com.example.demo.entities.TransactionDetail;
import com.example.demo.repositories.ITransactionDetailRepository;
import com.example.demo.service.ITransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionDetailServiceImpl implements ITransactionDetailService {
    @Autowired
    private ITransactionDetailRepository iTransactionDetailRepository;

    @Override
    public void delete(Long id) {
        iTransactionDetailRepository.deleteById(id);
    }

    @Override
    public Iterable<TransactionDetail> getAll() {
        return iTransactionDetailRepository.findAll();
    }


}
