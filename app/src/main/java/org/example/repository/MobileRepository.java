package org.example.repository;

import org.example.model.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long> {
    Optional<Mobile> findByModelAndVersion(String model, String version);
}
