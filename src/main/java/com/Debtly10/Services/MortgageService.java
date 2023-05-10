package com.Debtly10.Services;

import com.Debtly10.DTOS.MortgageRegistrationDTO;
import com.Debtly10.DTOS.MortgageUpdateDto;
import com.Debtly10.Repository.CustomerRepository;
import com.Debtly10.Repository.MortgageRepository;
import com.Debtly10.models.Customer;
import com.Debtly10.models.Mortgage;
import org.springframework.stereotype.Service;

import javax.lang.model.type.NullType;
import java.util.List;


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

    public List<Mortgage> getMortgageByCustomer(Long cid) {
        Customer customer = customerRepository.findById(cid).get();
        return customer.getMortgageList();
    }

    public String deleteMortgage(Long mid) {
        mortgageRepository.deleteById(mid);
        return "mortgage deleted successfully";
    }

    public String updateMortgage(MortgageUpdateDto mortgageUpdateDto, Long mid) {
        Mortgage mortgage= mortgageRepository.findById(mid).get();
        if(mortgageUpdateDto.getGivenAmount() != 0){
            mortgage.setGivenAmount(mortgageUpdateDto.getGivenAmount());
        }

        if(mortgageUpdateDto.getLeftAmount()!= 0){
            mortgage.setLeftAmount(mortgageUpdateDto.getLeftAmount());
        }
        if(mortgageUpdateDto.getIssueDate() != null) {
            mortgage.setIssueDate(mortgageUpdateDto.getIssueDate());
        }
        if(mortgageUpdateDto.getLastPaid() != null)
        {
            mortgage.setLastPaid(mortgageUpdateDto.getLastPaid());
        }
        if(mortgageUpdateDto.getMarketValue() != 0){
            mortgage.setMarketValue(mortgageUpdateDto.getMarketValue());
        }

        if(mortgageUpdateDto.getGivenAmount() != 0){
            mortgage.setGivenAmount(mortgageUpdateDto.getGivenAmount());
        }
        return "mortgage updated successfully";
    }

    public List<Mortgage> getAllMortgage() {
        return mortgageRepository.findAll();
    }


}
