package upc.edu.pe.hungerfoodbackend.iservices;

import upc.edu.pe.hungerfoodbackend.entities.TransactionVoucher;
import upc.edu.pe.hungerfoodbackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface ITransactionVoucherService {
    //save
    public TransactionVoucher save(TransactionVoucher transactionVoucher);

    //update
    public TransactionVoucher update(TransactionVoucher transactionVoucher);

    //delete
    public void delete(Long id);

    //findall
    public List<TransactionVoucher> findAll();
    Optional<TransactionVoucher> findFirstByUser(Optional<User> user);
}
