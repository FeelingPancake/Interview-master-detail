package com.example.master_detail.controller;

import com.example.master_detail.dto.DetailDto;
import com.example.master_detail.model.Detail;
import com.example.master_detail.service.detail.DetailService;
import com.example.master_detail.service.master.MasterService;
import com.example.master_detail.utils.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/details")
public class DetailController {
    private final DetailService detailService;
    private final MasterService masterService;

    @GetMapping()
    public String getAllDetails(Model model) {
        List<Detail> details = detailService.getDetails();
        model.addAttribute("details", details);
        model.addAttribute("newDetail", DetailDto.builder().build());
        return "details";
    }

    @PostMapping("/create")
    public String createDetail(@ModelAttribute DetailDto detailDto) {
        Detail detail = DtoMapper.toDetail(detailDto, masterService.getMaster(detailDto.getMasterId()));
        detailService.create(detail);
        return "redirect:/details";
    }

    @PostMapping("/update/{id}")
    public String updateDetail(@PathVariable Long id, @ModelAttribute DetailDto detailDto) {
        Detail detail = DtoMapper.toDetail(detailDto, masterService.getMaster(detailDto.getMasterId()));
        detail.setId(id);
        detailService.update(detail);
        return "redirect:/details";
    }

    @PostMapping("/delete/{id}")
    public String deleteDetail(@PathVariable Long id) {
        detailService.delete(id);
        return "redirect:/details";
    }
}
