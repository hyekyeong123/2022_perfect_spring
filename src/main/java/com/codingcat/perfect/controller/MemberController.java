package com.codingcat.perfect.controller;

import com.codingcat.perfect.controller.MemberForm;
import com.codingcat.perfect.domain.Member;
import com.codingcat.perfect.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // 컨트룰러와 서비스 연결
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 가입 View
    @GetMapping("/members/new")
    public String createView(){
        return "members/createMemberForm";
    }

    // 회원 가입 액션
    @PostMapping("/members/new")
    public String createAction(MemberForm memberForm){

        Member member = new Member();
        member.setName(memberForm.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberListView(Model model){
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("members",memberList);
        return "members/memberList";
    }


}
