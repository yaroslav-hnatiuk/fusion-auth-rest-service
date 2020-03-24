package io.fusionauth.service.impl;

import com.inversoft.error.Errors;
import com.inversoft.rest.ClientResponse;
import io.fusionauth.client.FusionAuthClient;
import io.fusionauth.domain.api.LoginRequest;
import io.fusionauth.domain.api.LoginResponse;
import io.fusionauth.dto.CredentialsDto;
import io.fusionauth.dto.ResponseDto;
import io.fusionauth.service.IUserService;
import io.fusionauth.util.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Value("${fusionAuth.applicationId}")
    private String applicationId;

    @Value("${fusionAuth.baseUrl}")
    private String baseUrl;

    private FusionAuthClient authClient;

    public UserServiceImpl(FusionAuthClient authClient) {
        this.authClient = authClient;
    }

    @Override
    public ResponseDto login(CredentialsDto credentials) {
        LoginRequest loginRequest = new LoginRequest(UUID.fromString(applicationId), credentials.getEmail(), credentials.getPassword(), baseUrl);
        ClientResponse<LoginResponse, Errors> loginResp = authClient.login(loginRequest);
        if (loginResp.wasSuccessful()) {
            Map<String, Object> data = new HashMap<>();
            data.put("token", loginResp.successResponse.token);
            return ResponseDto.builder()
                    .data(data)
                    .message(Util.SUCCESS_LOGIN)
                    .success(Boolean.TRUE)
                    .build();
        }
        return ResponseDto.builder()
                .data(null)
                .message(Util.FAILED_LOGIN)
                .success(Boolean.FALSE)
                .build();
    }
}
