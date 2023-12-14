package com.yunhaekang.hamsterfancierclub.board.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * author: yunoi
 * date: 2023-12-05
 * description:
 */
@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    /**
     * name: 게시글 등록
     * author: yunoi
     * date: 2023-12-05
     * parameter:
     * description:
     */
    public void save (Post post) {
        em.persist(post);
    }

    /**
     * name: 게시글 조회 (단건)
     * author: yunoi
     * date: 2023-12-05
     * parameter: 게시글 ID
     * description:
     */
    public Post findOne (Long id) {
        return em.find(Post.class, id);
    }

    /**
     * name: 게시글 전체 조회 (페이지)
     * author: yunoi
     * date: 2023-12-07
     * parameter:
     * description:
     * @return
     */
    public List findAll (Long boardId) {
        TypedQuery<PostDTO> query = em.createQuery("select new com.yunhaekang.hamsterfancierclub.board.post.PostDTO(p.id" +
                        ", p.postGroupId" +
                        ", p.postOrder" +
                        ", p.depth" +
                        ", p.board.id" +
                        ", p.member.id" +
                        ", p.subject" +
                        ", p.content" +
                        ", p.registerDateTime" +
                        ", p.modifiedDateTime)" +
                        " from Post p where p.board.id = :boardId order by p.postGroupId desc, p.postOrder", PostDTO.class)
                .setParameter("boardId", boardId);
        return query.getResultList();
    }

    /**
     * name: 게시글 순서 변경
     * author: yunoi
     * date: 2023-12-12
     * parameter: 게시글그룹번호, 다음게시글순서
     * description: 댓글 삽입을 위해 다음게시글순서보다 크거나 같은 기존 게시글들의 순서를 한칸 뒤로 가도록 변경한다
     */
    public void updatePostOrder (Long postGroupId, int nextPostOrder) {
        em.createQuery("update Post p " +
                                "set p.postOrder = p.postOrder + 1 " +
                                "where p.postGroupId = :postGroupId " +
                                "and p.postOrder >= :nextPostOrder")
                .setParameter("postGroupId", postGroupId)
                .setParameter("nextPostOrder", nextPostOrder)
                .executeUpdate();
    }
}
