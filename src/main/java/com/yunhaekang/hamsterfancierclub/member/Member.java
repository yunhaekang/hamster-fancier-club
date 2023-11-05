package com.yunhaekang.hamsterfancierclub.member;

import com.yunhaekang.hamsterfancierclub.level.Level;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * author: yunoi
 * date: 2023-10-30
 * description:
 */
@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String userId;
    private String password;
    private String name;
    private LocalDateTime registerDatetime;
    private String nickname;
    private String phone;
    private String email;
    private int point;
    @Enumerated(EnumType.STRING)
    private Level level;    // ENTRY, REGULAR, ADMIN

    /**
     * name: 회원 등록
     * author: yunoi
     * date: 2023-10-30
     * description:
     */
    public void registerMember(String userId, String password, String name, String nickname, String phone, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.registerDatetime = LocalDateTime.now();
        this.point = 0;
        this.level = Level.ENTRY;
    }
}
