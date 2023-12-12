package com.yunhaekang.hamsterfancierclub.board.free.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * author: yunoi
 * date: 2023-12-07
 * description:
 */
@Getter
@Setter
@AllArgsConstructor
public class PostDTO {
    private Long postId;
    private Long postGroupId;
    private int postOrder;
    private int depth;
    private Long boardId;
    private Long memberId;
    private String subject;
    private String content;
    private LocalDateTime registerDateTime;
    private LocalDateTime modifiedDateTime;
}
