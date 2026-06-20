package com.gavin.rinnepay.merchant.controllers;

import com.gavin.rinnepay.merchant.dtos.requests.MerchantSignupRequest;
import com.gavin.rinnepay.merchant.dtos.responses.MerchantResponse;
import com.gavin.rinnepay.merchant.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MerchantResponse> signup(@RequestBody @Valid MerchantSignupRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.signup(request));
    }

}
