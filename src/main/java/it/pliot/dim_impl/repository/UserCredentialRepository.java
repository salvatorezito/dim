package it.pliot.dim_impl.repository;

import it.pliot.dim_impl.data.UserCredentials;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserCredentialRepository extends JpaRepository<UserCredentials, String> {
}
