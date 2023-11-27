package com.yunhaekang.hamsterfancierclub.board;

import com.yunhaekang.hamsterfancierclub.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * author: yunoi
 * date: 2023-11-27
 * description:
 */
@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    /**
     * name: 게시판 등록
     * author: yunoi
     * date: 2023-11-27
     * parameter:
     * description:
     */
    public void save (Board board) {
        em.persist(board);
    }

    /**
     * name: 게시판 조회 (단건)
     * author: yunoi
     * date: 2023-11-27
     * parameter: 게시판 ID (게시판 고유값)
     * description:
     */
    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    /**
     * name: 가장 마지막 메뉴의 순서 가져오기
     * author: yunoi
     * date: 2023-11-27
     * parameter:
     * description:
     */
    public int selectLastMenuOrder () {
        Integer lastMenuOrder = em.createQuery("select MAX(b.menuOrder) from Board b", Integer.class)
                .getSingleResult(); // TODO: Optional 활용하여 변경할 것
        return lastMenuOrder == null ? 0 : lastMenuOrder;
    }
}
