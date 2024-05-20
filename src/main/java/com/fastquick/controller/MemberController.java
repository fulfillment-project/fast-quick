package com.fastquick.controller;

import com.fastquick.data.dto.request.MemberRequestDTO;
import com.fastquick.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String login(@RequestParam("id") String id, @RequestParam("pwd") String pwd){
        Integer memberId = memberService.login(id, pwd);
        return "/index";
    }
}
