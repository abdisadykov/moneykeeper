package kz.diploma.basqaru.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class JwtAuthenticationRequest {

    @JsonAlias({"email", "username"})
    private String username;
    private String password;
}
