package com.yunhaekang.hamsterfancierclub.board.free.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * author: yunoi
 * date: 2023-12-05
 * description:
 */
@Getter
@Setter
public class PostViewDTO {
    private Long postId;
    private Long boardId;
    private Long memberId;
    private String writer;
    private String subject;
    private String content;
    private LocalDateTime registerDateTime;
}
