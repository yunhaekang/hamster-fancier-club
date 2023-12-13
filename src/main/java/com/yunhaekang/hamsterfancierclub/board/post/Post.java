package com.yunhaekang.hamsterfancierclub.board.post;

import com.yunhaekang.hamsterfancierclub.board.Board;
import com.yunhaekang.hamsterfancierclub.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * author: yunoi
 * date: 2023-11-27
 * description:
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;
    private Long postGroupId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;
    private int postOrder;
    private int depth;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String subject;
    private String content;
    private LocalDateTime registerDateTime;
    private LocalDateTime modifiedDateTime;

    public static Post createPost (Board board, Member member, String subject, String content) {
        Post post = new Post();
        post.setPostGroupId(0L);
        post.setPostOrder(0);
        post.setDepth(0);
        post.setMember(member);
        post.setBoard(board);
        post.setSubject(subject);
        post.setContent(content);
        post.setRegisterDateTime(LocalDateTime.now());
        post.setModifiedDateTime(LocalDateTime.now());
        return post;
    }

    public static Post createReply (Board board, Member member, Long postGroupId, String subject, String content, int postOrder, int depth) {
        Post post = new Post();
        post.setPostGroupId(postGroupId);
        post.setPostOrder(postOrder);
        post.setDepth(depth);
        post.setMember(member);
        post.setBoard(board);
        post.setSubject(subject);
        post.setContent(content);
        post.setRegisterDateTime(LocalDateTime.now());
        post.setModifiedDateTime(LocalDateTime.now());
        return post;
    }

    public void setPostGroupId(Long postGroupId) {
        this.postGroupId = postGroupId;
    }

    public void setPostOrder(int postOrder) {
        this.postOrder = postOrder;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setMember (Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    public void setBoard (Board board) {
        this.board = board;
        board.getPosts().add(this);
    }

    public void setSubject (String subject) {
        this.subject = subject;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public void setRegisterDateTime (LocalDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    public void setModifiedDateTime (LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

}
