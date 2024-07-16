package com.example.master_detail.controller;

import com.example.master_detail.dto.MasterDto;
import com.example.master_detail.model.Master;
import com.example.master_detail.service.master.MasterService;
import com.example.master_detail.utils.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/master")
public class MasterController {
    private final MasterService masterService;

    @GetMapping()
    public String getMasters(Model model) {
        List<Master> masters = masterService.getMasters();
        model.addAttribute("masters", masters);
        model.addAttribute("newMaster", MasterDto.builder().build());
        return "masters";
    }

    @PostMapping("/create")
    public String createMaster(@ModelAttribute MasterDto masterDto) {
        Master master = DtoMapper.toMaster(masterDto);
        masterService.create(master);
        return "redirect:/master";
    }

    @PostMapping("/update/{id}")
    public String updateMaster(@PathVariable Long id, @ModelAttribute MasterDto masterDto) {
        Master master = DtoMapper.toMaster(masterDto);
        master.setId(id);
        masterService.update(master);
        return "redirect:/master";
    }

    @PostMapping("/delete/{id}")
    public String deleteMaster(@PathVariable Long id) {
        masterService.delete(id);
        return "redirect:/master";
    }
}
