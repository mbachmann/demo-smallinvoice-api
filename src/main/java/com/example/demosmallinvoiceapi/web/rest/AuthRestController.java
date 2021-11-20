package com.example.demosmallinvoiceapi.web.rest;


import com.example.demosmallinvoiceapi.model.AuthenticatedRemoteUser;
import com.example.demosmallinvoiceapi.smallinvoiceservice.AuthService;
import com.example.demosmallinvoiceapi.web.exception.InternalServerError;
import com.example.demosmallinvoiceapi.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthRestController {

    @Autowired
    private AuthService authService;

    @GetMapping("/resource-owner")
    public AuthenticatedRemoteUser getAuthenticatedRemoteUser() {
        try {
            AuthenticatedRemoteUser resourceOwner = authService.getAuthenticatedRemoteUser();
            if (resourceOwner == null) throw new ResourceNotFoundException("Authenticated remote user not found");
            return resourceOwner;
        } catch (Exception ex) {
            throw new InternalServerError("Internal Error at get authenticated remote user");
        }


    }
}
