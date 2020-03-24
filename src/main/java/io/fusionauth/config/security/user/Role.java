package io.fusionauth.config.security.user;

import org.codehaus.jackson.annotate.JsonValue;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    admin, user;

    @Override
    public String getAuthority() {
        return name();
    }

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
