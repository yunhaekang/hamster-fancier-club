package com.yunhaekang.hamsterfancierclub.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author: yunoi
 * date: 2023-11-27
 * description:
 */
@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardRepository repository;
    @Autowired
    private BoardService service;
    @Autowired
    private EntityManager em;

    @Test
    void create_test () {
        // given
        Board board = new Board();
        board.createBoard("test", 5);
        // when
        Long id = service.create(board);
        // then
        Assertions.assertThat(repository.findOne(id)).isSameAs(board);
    }

    @Test
    void menu_order_test () {
        // given
        Board board1 = new Board();
        Board board2 = new Board();
        Board board3 = new Board();
        board1.createBoard("test1", 6);
        board2.createBoard("test2", 5);
        board3.createBoard("test3", 5);
        Long id1 = service.create(board1);
        Long id2 = service.create(board2);

        // when
        Long id = service.create(board3);

        // then
        Assertions.assertThat(repository.findOne(id).getMenuOrder()).isEqualTo(3);
    }
}