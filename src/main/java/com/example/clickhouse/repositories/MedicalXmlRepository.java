package com.example.clickhouse.repositories;

import com.example.clickhouse.entitys.auth.MedicalXml;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalXmlRepository extends JpaRepository<MedicalXml, Long> {
    Optional<MedicalXml> findByMaCSKCBAndMaLK(String maCSKCB, String maLK);
}
