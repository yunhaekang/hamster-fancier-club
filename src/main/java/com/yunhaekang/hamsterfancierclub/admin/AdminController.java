package com.yunhaekang.hamsterfancierclub.admin;

import com.yunhaekang.hamsterfancierclub.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * author: yunoi
 * date: 2023-12-07
 * description:
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;

    @GetMapping("/admin/main")
    public String retrieveAdminMainInfo (Model model) {
        model.addAttribute("memberCount", memberService.countAllMembers());
        model.addAttribute("members", memberService.findMembers());
        return "admin/main";
    }
}
