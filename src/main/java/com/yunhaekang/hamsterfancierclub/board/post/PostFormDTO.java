package com.yunhaekang.hamsterfancierclub.board.post;

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
public class PostFormDTO {
    private Long postId;
    private Long boardId;
    private Long memberId;
    private String subject;
    private String content;
    private LocalDateTime registerDateTime;
}
