package com.Debtly10.Services;

import com.Debtly10.DTOS.MortgageRegistrationDTO;
import com.Debtly10.Repository.CustomerRepository;
import com.Debtly10.Repository.MortgageRepository;
import com.Debtly10.models.Customer;
import com.Debtly10.models.Mortgage;
import org.springframework.stereotype.Service;

@Service
public class MortgageService {

    private MortgageRepository mortgageRepository;
    private CustomerRepository customerRepository;

    public MortgageService(MortgageRepository mortgageRepository, CustomerRepository customerRepository) {
        this.mortgageRepository = mortgageRepository;
        this.customerRepository=customerRepository;
    }

    public String addMortgage(MortgageRegistrationDTO mortgageRegistrationDTO, Long id) {
        Mortgage mortgage= new Mortgage();
        mortgage.setGivenAmount(mortgageRegistrationDTO.getGivenAmount());
        mortgage.setLastPaid(mortgageRegistrationDTO.getLastPaid());
        mortgage.setIssueDate(mortgageRegistrationDTO.getIssueDate());
        mortgage.setLeftAmount(mortgageRegistrationDTO.getLeftAmount());
        mortgage.setProductName(mortgageRegistrationDTO.getProductName());
        mortgage.setMarketValue(mortgageRegistrationDTO.getMarketValue());
        Customer customer = customerRepository.findById(id).get();
        mortgage.setCustomer(customer);
        mortgageRepository.save(mortgage);
        return "mortgage added successfully";

    }
}
