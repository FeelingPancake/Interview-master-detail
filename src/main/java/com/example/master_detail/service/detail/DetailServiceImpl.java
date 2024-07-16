package com.example.master_detail.service.detail;

import com.example.master_detail.error.EntityNotExistsExeption;
import com.example.master_detail.model.Detail;
import com.example.master_detail.storage.DetailJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DetailServiceImpl implements DetailService {
    private final DetailJpaRepository detailJpaRepository;

    @Override
    public Detail getDetail(Long id) {
        return detailJpaRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsExeption("Спецификации с id = " + id + " не существует."));
    }

    @Override
    public List<Detail> getDetails() {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        return detailJpaRepository.findAll(sort);
    }

    @Override
    public List<Detail> getDetailsForMaster(Long id) {
        return detailJpaRepository.findByMasterId(id);
    }

    @Override
    public Detail create(Detail detail) {
        return detailJpaRepository.save(detail);
    }

    @Override
    public Detail update(Detail detail) {
        Detail updatedDetail = detailJpaRepository.findById(detail.getId()).orElseThrow(() ->
                new EntityNotExistsExeption("Спецификации с id = " + detail.getId() + "не существует"));

        updatedDetail = updatedDetail.toBuilder()
                .sum(detail.getSum() == null ? updatedDetail.getSum() : detail.getSum())
                .name(detail.getName() == null ? updatedDetail.getName() : detail.getName())
                .build();

        return detailJpaRepository.save(updatedDetail);
    }

    @Override
    public void delete(Long id) {
        detailJpaRepository.deleteById(id);
    }
}
