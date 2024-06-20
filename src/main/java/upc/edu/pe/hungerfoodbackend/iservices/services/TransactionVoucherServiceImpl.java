package upc.edu.pe.hungerfoodbackend.iservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.hungerfoodbackend.entities.TransactionVoucher;
import upc.edu.pe.hungerfoodbackend.entities.User;
import upc.edu.pe.hungerfoodbackend.iservices.ITransactionVoucherService;
import upc.edu.pe.hungerfoodbackend.repositories.ITransactionVoucherRepository;
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

    @Override
    public Optional<TransactionVoucher> findFirstByUser(Optional<User> user) {
        return iTransactionVoucherRepository.findFirstByUser(user);
    }
}
