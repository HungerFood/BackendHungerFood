package upc.edu.pe.hungerfoodbackend.iservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.hungerfoodbackend.entities.MoneyDonation;
import upc.edu.pe.hungerfoodbackend.repositories.IMoneyDonationRepository;
import upc.edu.pe.hungerfoodbackend.iservices.iMoneyDonationService;

import java.util.List;
import java.util.Optional;

@Service
public class MoneyDonationServiceImpl implements iMoneyDonationService{
    @Autowired
    private IMoneyDonationRepository iMoneyDonationRepository;

    @Override
    public MoneyDonation save(MoneyDonation moneyDonation) { return iMoneyDonationRepository.save(moneyDonation); }

    @Override
    public void delete(Long id) { iMoneyDonationRepository.deleteById(id); }

    @Override
    public List<MoneyDonation> findAll() { return iMoneyDonationRepository.findAll(); }

    @Override
    public MoneyDonation update(MoneyDonation moneyDonation) {
        Optional<MoneyDonation> moneyDonationOptional = iMoneyDonationRepository.findById(moneyDonation.getId());
        if (moneyDonationOptional.isPresent()) {
            MoneyDonation updatedMoneyDonation = moneyDonationOptional.get();
            updatedMoneyDonation.setTotal_amount(moneyDonation.getTotal_amount());
            updatedMoneyDonation.setPayment_date(moneyDonation.getPayment_date());
            return iMoneyDonationRepository.save(updatedMoneyDonation);
        } else {
            throw new RuntimeException("MoneyDonation not found with id: " + moneyDonation.getId());
        }
    }

    @Override
    public MoneyDonation findById(Long id) {
        Optional<MoneyDonation> moneyDonationOptional = iMoneyDonationRepository.findById(id);
        if (moneyDonationOptional.isPresent()) {
            return moneyDonationOptional.get();
        } else {
            throw new RuntimeException("MoneyDonation not found with id: " + id);
        }
    }
}
