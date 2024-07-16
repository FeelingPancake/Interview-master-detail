package com.example.master_detail.service.detail;

import com.example.master_detail.model.Detail;

import java.util.List;

public interface DetailService {
    Detail getDetail(Long id);
    List<Detail> getDetails();
    List<Detail> getDetailsForMaster(Long id);
    Detail create(Detail detail);
    Detail update(Detail detail);
    void delete(Long id);
}
