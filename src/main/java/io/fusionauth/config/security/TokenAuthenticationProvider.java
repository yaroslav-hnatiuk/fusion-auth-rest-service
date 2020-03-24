package io.fusionauth.config.security;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.inversoft.error.Errors;
import com.inversoft.rest.ClientResponse;
import io.fusionauth.client.FusionAuthClient;
import io.fusionauth.config.security.user.Role;
import io.fusionauth.config.security.user.UserProfile;
import io.fusionauth.domain.UserRegistration;
import io.fusionauth.domain.api.user.RegistrationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Value("${fusionAuth.applicationId}")
    private String applicationId;
    private FusionAuthClient fusionAuthClient;

    public TokenAuthenticationProvider(FusionAuthClient fusionAuthClient) {
        this.fusionAuthClient = fusionAuthClient;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken token) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        final Object token = authentication.getCredentials();
        boolean tokenValid = fusionAuthClient.validateJWT(String.valueOf(token)).wasSuccessful();
        if (tokenValid) {
            String userId = fusionAuthClient.validateJWT(String.valueOf(token)).successResponse.jwt.subject;
            ClientResponse<RegistrationResponse, Errors> response = fusionAuthClient.retrieveRegistration(UUID.fromString(userId), UUID.fromString(applicationId));
            if (response.wasSuccessful()) {
                UserRegistration userRegistration = response.successResponse.registration;

                UserProfile userProfile = new UserProfile();
                List<Role> roles = userRegistration.roles
                        .stream()
                        .map(Role::valueOf)
                        .collect(Collectors.toList());
                userProfile.setRoles(roles);
                userProfile.setUuid(UUID.fromString(userId));
                return userProfile;
            } else {
                throw new RuntimeException("The token is not valid.");
            }
        } else {
            throw new RuntimeException("The token is not valid.");
        }
    }
}
