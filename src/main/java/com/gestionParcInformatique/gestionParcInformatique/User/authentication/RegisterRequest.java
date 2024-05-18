package com.gestionParcInformatique.gestionParcInformatique.User.authentication;

import com.gestionParcInformatique.gestionParcInformatique.User.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String userNumber;
    private String fullName;
    private String password;
    private List<Role> role;
}
