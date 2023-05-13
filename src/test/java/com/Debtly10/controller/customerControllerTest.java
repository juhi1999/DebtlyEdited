package com.Debtly10.controller;


import com.Debtly10.DTOS.CustomerRegistrationDTO;
import com.Debtly10.Services.CustomerService;
import com.Debtly10.models.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes={customerController.class})
@ExtendWith(SpringExtension.class)
public class customerControllerTest {
    @Autowired
    private customerController custController;

    @MockBean
    private CustomerService customerService;

    @Test
    void testAddCustomer() throws Exception {
        doNothing().when(customerService).addCustomer(Mockito.<CustomerRegistrationDTO>any());

            CustomerRegistrationDTO customerRegistrationDTO = new CustomerRegistrationDTO();
            customerRegistrationDTO.setFirstname("Fname");
            customerRegistrationDTO.setLastname("Lname");
            customerRegistrationDTO.setAddress("IIITB");
            customerRegistrationDTO.setContact("0987654342");
            String content = (new ObjectMapper()).writeValueAsString(customerRegistrationDTO);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/register_customer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content);
            MockMvcBuilders.standaloneSetup(custController)
                    .build()
                    .perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk());
                   // .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                    //.andExpect(MockMvcResultMatchers.content().string("true"));
    }


    @Test
    void testGetCustomerList() throws Exception {
        when(customerService.getAllCustomer()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/get_customers",
                1L);
        MockMvcBuilders.standaloneSetup(custController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


}
