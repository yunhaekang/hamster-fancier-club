package com.yunhaekang.hamsterfancierclub.member;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: yunoi
 * date: 2023-11-05
 * description:
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * name: 회원 가입
     * author: yunoi
     * date: 2023-11-05
     * parameter: 회원 객체
     * description:
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateUserId(member); // 회원 가입 ID 중복 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * name: 회원 가입 ID 중복 검증
     * author: yunoi
     * date: 2023-11-05
     * parameter: 회원 객체
     * description: 사용하려는 회원 가입 ID가 존재하면 예외 던짐
     */
    private void validateDuplicateUserId(Member member) {
        // TODO: MEMBER 테이블의 user_id Unique 제약조건 설정할 것 20231105
        if(memberRepository.countUserId(member.getUserId()) > 0) {
            throw new IllegalStateException("이미 사용 중인 ID입니다.");
        }
    }

    /**
     * name: 회원 조회 (전체)
     * author: yunoi
     * date: 2023-11-05
     * parameter:
     * description:
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * name:  회원 조회 (단건)
     * author: yunoi
     * date: 2023-11-05
     * parameter: 회원 ID (고유값)
     * description:
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
