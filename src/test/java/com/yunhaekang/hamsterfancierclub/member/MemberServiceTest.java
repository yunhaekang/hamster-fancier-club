package com.yunhaekang.hamsterfancierclub.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author: yunoi
 * date: 2023-11-05
 * description:
 */
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService service;
    @Autowired
    MemberRepository repository;
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("회원가입")
    void join() {
        // given
        Member member = new Member();
        member.registerMember("mem1", "1111", "mem1", "memmem", "01011112222", "test@test.com");

        // when
        Long joinedId = service.join(member);

        // then
        Assertions.assertThat(repository.findOne(joinedId)).isSameAs(member);
    }

    @Test
    @DisplayName("회원 가입 ID 중복 예외")
    void duplicationUserIdException() throws Exception {
        // given
        Member member1 = new Member();
        member1.registerMember("mem1", "1111", "mem1", "memmem", "01011112222", "test@test.com");

        Member member2 = new Member();
        member2.registerMember("mem1", "1111", "mem1", "memmem", "01011112222", "test@test.com");

        // when
        service.join(member1);

        // then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> {
            service.join(member2);
        });
    }
}