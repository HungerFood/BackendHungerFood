package upc.edu.pe.hungerfoodbackend.iservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.hungerfoodbackend.entities.PaymentMethod;
import upc.edu.pe.hungerfoodbackend.iservices.IPaymentMethodService;
import upc.edu.pe.hungerfoodbackend.repositories.IPaymentMethodRepository;


import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService {
    @Autowired
    private IPaymentMethodRepository iPaymentMethodRepository;

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return iPaymentMethodRepository.save(paymentMethod);
    }

    @Override
    public List<PaymentMethod> findAll() {
        return iPaymentMethodRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        iPaymentMethodRepository.deleteById(id);
    }

    @Override
    public PaymentMethod update(PaymentMethod paymentMethod) {
        Optional<PaymentMethod> existingPaymentMethod = iPaymentMethodRepository.findById(paymentMethod.getId());
        if (existingPaymentMethod.isPresent()) {
            PaymentMethod updatedPaymentMethod = existingPaymentMethod.get();
            updatedPaymentMethod.setType(paymentMethod.getType());
            return iPaymentMethodRepository.save(updatedPaymentMethod);
        } else {
            throw new RuntimeException("PaymentMethod not found with id: " + paymentMethod.getId());
        }
    }

}
