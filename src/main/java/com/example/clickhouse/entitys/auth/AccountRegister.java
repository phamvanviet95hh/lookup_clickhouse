package com.example.clickhouse.entitys.auth;

;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "account_register")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegister {

    @Id
    @GeneratedValue()
    private UUID id;

    private String maCskb;
    private String username;
    private String password;
    private String email;
    private String role;                    // loại người dùng: ADMIN("admin") | BYT("byt") | SYT ("syt")| FACILITY ("facility");

    private LocalDate ngayCap;
    private String maSoThue;
    private String dienThoai;

    @Column
    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    @Nullable
    private Integer maTinh;

    @Nullable
    private String tenTinh;

}