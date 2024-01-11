package com.insurance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.insurance.domain.MembershipDTO;
import com.insurance.service.MembershipService;

import jakarta.inject.Inject;

@Controller
@RequestMapping("/membership")
public class MembershipController {
    
    private static final Logger logger = LoggerFactory.getLogger(MembershipController.class);

    @Inject
    private MembershipService membershipService;

    @GetMapping("/register")
    public String getMember(Model model) {
        logger.info("get register");
        model.addAttribute("membershipDTO", new MembershipDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerMember(MembershipDTO params) {
        logger.info("post register");

        try {
            membershipService.registerMember(params);
            // 회원가입 성공 시 리다이렉트 또는 다른 처리를 수행할 수 있습니다.
            return "redirect:/membership/success";
        } catch (Exception e) {
            logger.error("회원가입 중 오류 발생", e);
            // 오류 발생 시 에러 페이지로 리다이렉트 또는 다른 처리를 수행할 수 있습니다.
            return "redirect:/membership/error";
        }
    }
}
