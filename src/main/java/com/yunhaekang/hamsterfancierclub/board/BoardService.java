package com.yunhaekang.hamsterfancierclub.board;

import com.yunhaekang.hamsterfancierclub.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * author: yunoi
 * date: 2023-11-27
 * description:
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * name: 게시판 등록
     * author: yunoi
     * date: 2023-11-27
     * parameter: 게시판 객체
     * description:
     */
    @Transactional
    public Long create (Board board) {
        board.editMenuOrder(getLastMenuOrder() + 1);
        boardRepository.save(board);
        return board.getId();
    }

    /**
     * name: 전체 게시판 조회
     * author: yunoi
     * date: 2023-11-28
     * parameter:
     * description:
     */
    public List<Board> getAllBoards () {
        return boardRepository.findAll();
    }

    /**
     * name: 가장 마지막 메뉴 순서 가져오기
     * author: yunoi
     * date: 2023-11-27
     * parameter:
     * description:
     */
    private int getLastMenuOrder () {
        return boardRepository.selectLastMenuOrder();
    }

}
