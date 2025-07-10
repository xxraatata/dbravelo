package id.co.dbravelo.repository;

import id.co.dbravelo.model.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {
    Optional<OtpCode> findTopByUsernameOrderByCreatedAtDesc(String username);
}