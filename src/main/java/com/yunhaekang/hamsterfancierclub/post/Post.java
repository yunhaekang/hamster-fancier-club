package com.yunhaekang.hamsterfancierclub.post;

import com.yunhaekang.hamsterfancierclub.board.Board;
import com.yunhaekang.hamsterfancierclub.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * author: yunoi
 * date: 2023-11-27
 * description:
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {

    @Id @GeneratedValue
    @Column(name="post_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;
    private Long parentPostId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String nickname;    // TODO: ERD 수정할 것 (member_name -> nickname)
    private String userId;      // 회원 온라인 ID
    private String subject;
    private String content;
    private LocalDateTime registerDateTime;
}
