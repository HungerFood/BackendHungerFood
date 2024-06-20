package upc.edu.pe.hungerfoodbackend.iservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.hungerfoodbackend.entities.TransactionDetail;
import upc.edu.pe.hungerfoodbackend.iservices.ITransactionDetailService;
import upc.edu.pe.hungerfoodbackend.repositories.ITransactionDetailRepository;

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
