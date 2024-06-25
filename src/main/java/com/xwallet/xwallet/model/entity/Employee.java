package com.xwallet.xwallet.model.entity;

import com.xwallet.xwallet.model.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees", schema = "public", catalog = "xcore")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "password", nullable = false, length = 30)
    private String password;

    public EmployeeDTO toDTO() {
        return EmployeeDTO.builder().email(this.email).build();
    }
}

