package com.gpro.consulting.tms.mstiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gpro.consulting.tms.mstiers.entity.PartieInteresseEntite;

import java.util.List;

@Repository
public interface PartieInteresseRepository extends JpaRepository<PartieInteresseEntite, Long> {
    List<PartieInteresseEntite> findByTypeClient(String typeClient);
}