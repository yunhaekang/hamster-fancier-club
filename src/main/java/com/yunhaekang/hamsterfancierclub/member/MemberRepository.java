package com.yunhaekang.hamsterfancierclub.member;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * author: yunoi
 * date: 2023-11-05
 * description:
 */
@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * name: 회원 등록
     * author: yunoi
     * date: 2023-11-05
     * description:
     */
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * name: 회원 조회 (단건)
     * author: yunoi
     * date: 2023-11-05
     * parameter: 회원 ID (회원 고유값)
     * description:
     */
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    /**
     * name: 회원 조회 (전체)
     * author: yunoi
     * date: 2023-11-05
     * description:
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    /**
     * name: 회원 조회 (단건)
     * author: yunoi
     * date: 2023-11-05
     * parameter: 회원 가입 ID
     * description: 이미 사용 중인 ID 확인 등
     */
    public List<Member> findUserId(String userId) {
        return em.createQuery("select m from Member m where m.userId = :userId", Member.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
