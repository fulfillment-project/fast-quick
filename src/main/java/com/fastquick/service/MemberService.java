package com.fastquick.service;

import com.fastquick.data.dto.request.MemberRequestDTO;

public interface MemberService {
    Integer login(String id, String pwd);
}
