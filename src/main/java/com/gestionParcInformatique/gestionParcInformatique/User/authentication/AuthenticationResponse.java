package com.gestionParcInformatique.gestionParcInformatique.User.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestionParcInformatique.gestionParcInformatique.User.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("user")
    private User user;
}
