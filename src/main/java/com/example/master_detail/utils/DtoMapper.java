package com.example.master_detail.utils;

import com.example.master_detail.dto.DetailDto;
import com.example.master_detail.dto.MasterDto;
import com.example.master_detail.model.Detail;
import com.example.master_detail.model.Master;

public abstract class DtoMapper {
    public static Master toMaster(MasterDto masterDto) {
        return Master.builder().date(masterDto.getDate()).remark(masterDto.getRemark()).build();
    }

    public static Detail toDetail(DetailDto detailDto, Master master) {
        return Detail.builder().master(master).sum(detailDto.getSum()).name(detailDto.getName()).build();
    }
}
