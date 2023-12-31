package com.yunhaekang.hamsterfancierclub.board;

import com.yunhaekang.hamsterfancierclub.board.post.Post;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * author: yunoi
 * date: 2023-11-05
 * description:
 */
@Entity
@Getter
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long id;
    private String boardName;
    private int menuOrder;
    private int rewardPoint;
    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList<>(); // 관례상 컬렉션 객체 초기화 해줌
    private String boardPath;

    /**
     * name: 새 게시판 등록
     * author: yunoi
     * date: 2023-11-27
     * parameter: 게시판명, 적립 포인트
     * description:
     */
    public void createBoard (String boardName, int rewardPoint, String boardPath) {
        this.boardName = boardName;
        this.rewardPoint = rewardPoint;
        this.boardPath = boardPath;
    }

    /**
     * name: 게시판 이름 변경
     * author: yunoi
     * date: 2023-11-27
     * parameter: boardName (게시판명)
     * description:
     */
    public void editBoardName (String newBoardName) {
        this.boardName = newBoardName;
    }

    /**
     * name: 게시판 순서 변경
     * author: yunoi
     * date: 2023-11-27
     * parameter: menuOrder (메뉴 순서)
     * description: 작은 숫자일수록 앞에 위치한다.
     */
    public void editMenuOrder (int newMenuOrder) {
        this.menuOrder = newMenuOrder;
    }

    /**
     * name: 게시판 글 작성 적립 포인트 변경
     * author: yunoi
     * date: 2023-11-27
     * parameter: rewardPoint (적립포인트)
     * description:
     */
    public void editRewardPoint (int newRewardPoint) {
        this.rewardPoint = newRewardPoint;
    }

    /**
     * name: 게시판 경로 변경
     * author: yunoi
     * date: 2023-11-29
     * parameter:
     * description:
     */
    public void editBoardPath (String newBoardPath) {
        this.boardPath = newBoardPath;
    }
}
