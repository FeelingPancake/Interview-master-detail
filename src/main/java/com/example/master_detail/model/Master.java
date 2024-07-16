package com.example.master_detail.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "master", schema = "public")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_id")
    Long id;
    @Column(name = "master_date", nullable = false)
    LocalDateTime date;
    @Column(name = "master_sum", nullable = false)
    BigInteger sum;
    @Column(name = "master_remark", nullable = false)
    String remark;

    @PrePersist
    protected void onCreate() {
        if (date == null) {
            date = LocalDateTime.now();
        }
        if (sum == null) {
            sum = BigInteger.ZERO;
        }
    }
}
