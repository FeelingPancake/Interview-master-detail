package com.example.master_detail.service.master;

import com.example.master_detail.error.EntityNotExistsExeption;
import com.example.master_detail.model.Detail;
import com.example.master_detail.model.Master;
import com.example.master_detail.storage.DetailJpaRepository;
import com.example.master_detail.storage.MasterJpaRepository;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MasterServiceImpl implements MasterService{
    private final MasterJpaRepository masterJpaRepository;
    private final DetailJpaRepository detailJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Master getMaster(Long id) {
        return masterJpaRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsExeption("Документа с id = " + id + " не существует."));
    }

    @Override
    public List<Master> getMasters() {
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        return masterJpaRepository.findAll(sort);
    }

    @Override
    public Master create(Master master) {
        return masterJpaRepository.save(master);
    }

    @Override
    public Master update(Master master) {
        Master updatedMaster = masterJpaRepository.findById(master.getId()).orElseThrow(() ->
                new EntityNotExistsExeption("Документа с id = " + master.getId() + "не существует"));

        updatedMaster = updatedMaster.toBuilder()
                .date(master.getDate() != null ? master.getDate() : updatedMaster.getDate())
                .remark(master.getRemark() != null ? master.getRemark() : updatedMaster.getRemark())
                .build();
        log.info(updatedMaster.toString());
        return masterJpaRepository.save(updatedMaster);
    }

    @Override
    public void delete(Long id) {
        masterJpaRepository.deleteById(id);
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    public void updateMasterSum(Detail detail) {
        Master master = masterJpaRepository.findById(detail.getMaster().getId())
                .orElseThrow(() -> new RuntimeException("Мастер не найден: " + detail.getMaster().getId()));
        BigInteger sum = detailJpaRepository.calculateSumForMaster(master.getId());
        master.setSum(sum);
        masterJpaRepository.save(master);
    }
}
