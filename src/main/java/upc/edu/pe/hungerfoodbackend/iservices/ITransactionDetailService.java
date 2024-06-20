package upc.edu.pe.hungerfoodbackend.iservices;

import upc.edu.pe.hungerfoodbackend.entities.TransactionDetail;

public interface ITransactionDetailService {
    //delete
    public void delete(Long id);

    //findall
    public Iterable<TransactionDetail> getAll();



}
