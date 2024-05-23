package com.fastquick.data.repository;

import com.fastquick.data.entity.Member;
import com.fastquick.data.entity.ShopConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    //로그인
    Member findByIdAndPwd(String id, String pwd);
    List<Member> findAll();
    @Query("select m from Member m where m.memberId=:id")
    Member findMemberById(@Param("id") Integer id);
}
