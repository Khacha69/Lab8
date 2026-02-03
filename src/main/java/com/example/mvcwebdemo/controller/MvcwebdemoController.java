package com.example.mvcwebdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import com.example.mvcwebdemo.model.RegistrationForm; // Import model ที่เราสร้าง

@Controller
public class MvcwebdemoController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 1. ส่งฟอร์มเปล่าๆ ไปให้หน้าเว็บ
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    // 2. รับค่าจากฟอร์มมากด submit
    @PostMapping("/register")
    public String handleRegistration(@Valid RegistrationForm registrationForm,
                                     BindingResult bindingResult,
                                     Model model) {
        // ถ้ามี Error (เช่น ไม่กรอกข้อมูล, อีเมลผิด) ให้กลับไปหน้าเดิม
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        // ถ้าผ่านหมด ให้ส่งข้อมูลไปหน้า success
        model.addAttribute("firstName", registrationForm.getFirstName());
        model.addAttribute("lastName", registrationForm.getLastName());
        model.addAttribute("country", registrationForm.getCountry());
        model.addAttribute("dob", registrationForm.getDob());
        model.addAttribute("email", registrationForm.getEmail());

        return "success";
    }
}