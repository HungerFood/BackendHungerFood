package upc.edu.pe.hungerfoodbackend.iservices;

import upc.edu.pe.hungerfoodbackend.entities.MoneyDonation;

import java.util.List;

public interface iMoneyDonationService {
    //save
    public MoneyDonation save(MoneyDonation moneyDonation);

    //delete
    public void delete(Long id);

    //findall
    public MoneyDonation update(MoneyDonation moneyDonation);

    //list
    public List<MoneyDonation> findAll();

    //findbyid
    public MoneyDonation findById(Long id);
}
