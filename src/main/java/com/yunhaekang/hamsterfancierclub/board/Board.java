package com.yunhaekang.hamsterfancierclub.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * author: yunoi
 * date: 2023-11-05
 * description:
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Board {

    @Id @GeneratedValue
    private Long id;
    private String boardName;
    private int menuOrder;
    private int rewardPoint;
}
