package com.gestionParcInformatique.gestionParcInformatique.User.repository;

import com.gestionParcInformatique.gestionParcInformatique.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserNumber(String userNumber);
    User findUserById(long id);
    User findByFullName(String username);

}
