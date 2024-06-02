package com.miguel.project_travel.infraestructure.helpers;

import com.miguel.project_travel.util.exceptions.ForbiddenCustomerException;
import org.springframework.stereotype.Component;

@Component
public class BlackListHelper {

    public void isInBlackListCustomer(String customerId){
        if(customerId.equals("idbloqueada")){

            throw new ForbiddenCustomerException();

        }
    }
}
