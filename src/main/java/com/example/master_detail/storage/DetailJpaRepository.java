package com.example.master_detail.storage;

import com.example.master_detail.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface DetailJpaRepository extends JpaRepository<Detail, Long> {
    List<Detail> findByMasterId(Long masterId);
    @Query("SELECT COALESCE(SUM(d.sum), 0) FROM Detail d WHERE d.master.id = :masterId")
    BigInteger calculateSumForMaster(Long masterId);
}
