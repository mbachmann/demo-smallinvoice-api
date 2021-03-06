package com.example.demosmallinvoiceapi.smallinvoiceservice;

import com.example.demosmallinvoiceapi.model.AuthenticatedRemoteUser;
import com.example.demosmallinvoiceapi.utils.FieldMapper;
import com.example.demosmallinvoiceapi.utils.HasLogger;
import com.example.smallinvoicespringfeign.model.AuthOwnerGET;
import com.example.smallinvoicespringfeign.service.SmallInvoiceApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService implements HasLogger {
    @Autowired
    SmallInvoiceApiService apiService;


    public AuthenticatedRemoteUser getAuthenticatedRemoteUser() throws IOException {
        AuthOwnerGET owner = apiService.getAuthenticatedResourceOwner();
        getLogger().info("AuthOwnerGET received " + owner);
        return FieldMapper.mapFromJson(FieldMapper.mapToJson(owner), AuthenticatedRemoteUser.class);
    }

}
