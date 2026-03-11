package com.lumiera.shop.lumierashop.controller;

import com.lumiera.shop.lumierashop.dto.request.RegisterForm;
import com.lumiera.shop.lumierashop.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final String REGISTER_VIEW = "auth/register";
    private static final String LOGIN_VIEW = "auth/login";
    private static final String REDIRECT_LOGIN = "redirect:/auth/login";

    private final AuthService authService;

    @GetMapping("/login")
    public String loginForm() {
        return LOGIN_VIEW;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());

        return REGISTER_VIEW;
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return REGISTER_VIEW;

        if (!registerForm.isPasswordMatched()) {
            bindingResult.rejectValue("confirmPassword", "mismatch", "비밀번호가 일치하지 않습니다.");
            return REGISTER_VIEW;
        }

        if (authService.isUsernameDuplicated(registerForm.getUsername())) {
            bindingResult.rejectValue("username", "duplicate", "이미 사용 중인 아이디입니다.");
            return REGISTER_VIEW;
        }

        authService.register(registerForm);

        return REDIRECT_LOGIN;
    }
}
