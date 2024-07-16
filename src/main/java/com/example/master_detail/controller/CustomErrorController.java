package com.example.master_detail.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        Object status = model.getAttribute("jakarta.servlet.error.status_code");
        Object message = model.getAttribute("jakarta.servlet.error.message");
        Object error = model.getAttribute("jakarta.servlet.error.exception");

        if (status != null) {
            model.addAttribute("statusCode", status.toString());
        }

        if (message != null) {
            model.addAttribute("message", message.toString());
        }

        if (error instanceof Exception) {
            model.addAttribute("error", ((Exception) error).getMessage());
        }

        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
