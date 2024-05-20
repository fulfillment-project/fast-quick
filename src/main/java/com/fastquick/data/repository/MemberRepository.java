package com.fastquick.data.repository;

import com.fastquick.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    //로그인
    Member findByIdAndPwd(String id, String pwd);
}
