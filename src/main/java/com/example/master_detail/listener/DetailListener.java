package com.example.master_detail.listener;

import com.example.master_detail.model.Detail;
import com.example.master_detail.model.Master;
import com.example.master_detail.storage.DetailJpaRepository;
import com.example.master_detail.storage.MasterJpaRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Aspect
@Component
@AllArgsConstructor
public class DetailListener {

    private final MasterJpaRepository masterJpaRepository;
    private final DetailJpaRepository detailJpaRepository;

    @AfterReturning(pointcut = "execution(* com.example.master_detail.storage.DetailJpaRepository.save(..)) && args(detail)")
    @Transactional
    public void updateMasterSumAfterSave(Detail detail) {
        updateMasterSum(detail.getMaster());
    }

    @After("execution(* com.example.master_detail.storage.DetailJpaRepository.deleteById(..)) && args(detailId)")
    @Transactional
    public void updateMasterSumAfterDelete(Long detailId) {
        Detail detail = detailJpaRepository.findById(detailId)
                .orElseThrow(() -> new RuntimeException("Detail not found: " + detailId));
        updateMasterSum(detail.getMaster());
    }

    private void updateMasterSum(Master master) {
        BigInteger sum = detailJpaRepository.calculateSumForMaster(master.getId());
        master.setSum(sum != null ? sum : BigInteger.ZERO);
        masterJpaRepository.save(master);
    }
}