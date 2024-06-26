package com.fastquick.controller;

import com.fastquick.data.dto.request.MemberRequestDTO;
import com.fastquick.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    //로그인 화면
    @GetMapping("/login")
    public String loginPage(){
        return "member/login";
    }

    @GetMapping("/register")
    public String register() {
    	return "member/register";
    }
    
    //로그인
    @PostMapping("/login")
    public String login(HttpServletRequest request, @RequestParam("id") String id, @RequestParam("pwd") String pwd){
        Integer memberId = memberService.login(id, pwd);
        HttpSession session = request.getSession();
        session.setAttribute("memberId", memberId);
        session.setAttribute("id", id);
        return "redirect:/index";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 세션 무효화
        return "redirect:/index";  // 로그인 페이지로 리다이렉트
    }
}
