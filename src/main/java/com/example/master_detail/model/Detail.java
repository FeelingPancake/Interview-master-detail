package com.example.master_detail.model;

import com.example.master_detail.listener.DetailListener;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "detail", schema = "public")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(DetailListener.class)
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id", nullable = false)
    Master master;
    @Column(name = "detail_name", nullable = false)
    String name;
    @Column(name = "detail_sum", nullable = false)
    BigInteger sum;
}
