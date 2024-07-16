package com.example.master_detail.service.master;

import com.example.master_detail.model.Master;

import java.util.List;

public interface MasterService {
    Master getMaster(Long id);
    List<Master> getMasters();
    Master create(Master master);
    Master update(Master master);
    void delete(Long id);
}
