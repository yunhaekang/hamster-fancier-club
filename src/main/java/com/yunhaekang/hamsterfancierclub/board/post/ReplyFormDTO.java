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
public class ReplyFormDTO {
    private Long parentPostId;
    private Long postGroupId;
    private Long boardId;
    private Long memberId;
    private String subject;
    private String content;
    private LocalDateTime registerDateTime;

    public void fillContentWithParentPost (String parentSubject, String parentContent) {
        this.content = "원글 제목: " + parentSubject + "\n 원글 내용 \n" + parentContent + "\n ------------------------- \n";
    }

}