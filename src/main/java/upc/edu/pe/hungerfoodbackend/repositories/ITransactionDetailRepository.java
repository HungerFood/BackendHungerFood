package upc.edu.pe.hungerfoodbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.hungerfoodbackend.entities.TransactionDetail;

@Repository
public interface ITransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
}
