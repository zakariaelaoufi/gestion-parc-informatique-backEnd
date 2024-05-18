package com.gestionParcInformatique.gestionParcInformatique.User.authentication;


import com.gestionParcInformatique.gestionParcInformatique.User.config.JwtService;
import com.gestionParcInformatique.gestionParcInformatique.User.model.User;
import com.gestionParcInformatique.gestionParcInformatique.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserNumber(),
                        request.getPassword()
                )
        );
        User user = repository.findByUserNumber(request.getUserNumber())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .user(user)
                .build();
    }
}
