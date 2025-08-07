package com.example.clickhouse.repositories;


import com.example.clickhouse.entitys.auth.AccountRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRegisterRepository extends JpaRepository<AccountRegister, UUID>, JpaSpecificationExecutor<AccountRegister> {
    @Query("SELECT ar.maTinh FROM AccountRegister ar WHERE ar.username = :userNameLogin")
    String getMaTinhByUserName(String userNameLogin);
}
