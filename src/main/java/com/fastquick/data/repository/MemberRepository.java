package com.fastquick.data.repository;

import com.fastquick.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //로그인
    Member findByIdAndPwd(String id, String pwd);
    List<Member> findAll();
}
