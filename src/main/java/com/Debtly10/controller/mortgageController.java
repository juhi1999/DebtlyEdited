package com.Debtly10.controller;

import com.Debtly10.DTOS.CustomerRegistrationDTO;
import com.Debtly10.DTOS.MortgageRegistrationDTO;
import com.Debtly10.Services.MortgageService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/mortgage")
public class mortgageController {
    private MortgageService mortgageService;

    public mortgageController(MortgageService mortgageService) {
        this.mortgageService = mortgageService;
    }

    @PostMapping("/register_mortgage/{cid}")
    public String addMortgage(@RequestBody MortgageRegistrationDTO mortgageRegistrationDTO,@PathVariable Long cid){
        return mortgageService.addMortgage(mortgageRegistrationDTO, cid);
    }


}
