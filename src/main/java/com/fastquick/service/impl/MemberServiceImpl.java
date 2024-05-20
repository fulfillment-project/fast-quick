package com.fastquick.service.impl;

import com.fastquick.data.dto.request.MemberRequestDTO;
import com.fastquick.data.dto.response.MemberResponseDTO;
import com.fastquick.data.entity.Member;
import com.fastquick.data.repository.MemberRepository;
import com.fastquick.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Integer login(String id, String pwd) {
        Member member = memberRepository.findByIdAndPwd(id, pwd);
        return member.getMemberId();
    }
}
