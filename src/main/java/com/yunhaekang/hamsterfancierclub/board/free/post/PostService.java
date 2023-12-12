package com.yunhaekang.hamsterfancierclub.board.free.post;

import com.yunhaekang.hamsterfancierclub.board.Board;
import com.yunhaekang.hamsterfancierclub.board.BoardRepository;
import com.yunhaekang.hamsterfancierclub.board.free.post.mapper.PostTestRepository;
import com.yunhaekang.hamsterfancierclub.member.Member;
import com.yunhaekang.hamsterfancierclub.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: yunoi
 * date: 2023-12-05
 * description:
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
     * name: 게시글 등록
     * author: yunoi
     * date: 2023-12-05
     * parameter: 게시판id, 회원id, 제목, 내용
     * description: 등록한 게시글의 번호를 리턴
     */
    @Transactional
    public Long writePost (Long boardId, Long memberId, String subject, String content) {
        // 엔티티 조회
        Board board = boardRepository.findOne(boardId);
        Member member = memberRepository.findOne(memberId);

        // 게시글 생성
        Post post = Post.createPost(board, member, subject, content);

        // 게시글 저장
        postRepository.save(post);

        // 게시글 그룹 번호 지정
        post.setPostGroupId(post.getId());

        return post.getId();
    }

    /**
     * name: 선택한 게시글 조회
     * author: yunoi
     * date: 2023-12-05
     * parameter:
     * description:
     */
    public Post viewPost (Long postId) {
        return postRepository.findOne(postId);
    }

    /**
     * name: 선택한 게시글 수정
     * author: yunoi
     * date: 2023-12-05
     * parameter:
     * description:
     */
    @Transactional
    public void editPost (Long postId, String subject, String content) {
        Post post = postRepository.findOne(postId);
        post.setSubject(subject);
        post.setContent(content);
    }

    /**
     * name: 댓글 등록
     * author: yunoi
     * date: 2023-12-05
     * parameter: 게시글그룹번호, 부모게시글id, 게시판id, 회원id, 제목, 내용
     * description: 댓글로 등록된 게시물 번호 리턴
     */
    @Transactional
    public Long writeReplyPost(Long postGroupId, Long parentPostId, Long boardId, Long memberId, String subject, String content) {
        // 엔티티 조회
        Board board = boardRepository.findOne(boardId);
        Member member = memberRepository.findOne(memberId);

        // 부모 게시글 조회
        Post parentPost = postRepository.findOne(parentPostId);

        // 댓글 순서 지정
        int nextPostOrder = parentPost.getPostOrder() + 1;

        // 기존 게시글 순서 수정
        postRepository.updatePostOrder(postGroupId, nextPostOrder);

        // 댓글 생성
        Post post = Post.createReply(board, member, postGroupId, subject, content, nextPostOrder, parentPost.getDepth() + 1);

        // 댓글 저장
        postRepository.save(post);

        return post.getId();
    }

    /**
     * name: 게시글 전체 조회 (페이지)
     * author: yunoi
     * date: 2023-12-07
     * parameter:
     * description:
     * @param boardId
     */
    public List<Post> getAllPosts (Long boardId) {
        return postRepository.findAll(boardId);
    }
}
