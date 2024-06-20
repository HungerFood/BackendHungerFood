package upc.edu.pe.hungerfoodbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.hungerfoodbackend.entities.TransactionVoucher;
import upc.edu.pe.hungerfoodbackend.entities.User;

import java.util.Optional;

@Repository
public interface ITransactionVoucherRepository extends JpaRepository<TransactionVoucher, Long> {
    Optional<TransactionVoucher> findFirstByUser(Optional<User> user);
}
