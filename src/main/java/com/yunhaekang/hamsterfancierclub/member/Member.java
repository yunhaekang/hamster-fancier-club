package com.yunhaekang.hamsterfancierclub.member;

import com.yunhaekang.hamsterfancierclub.level.Level;
import com.yunhaekang.hamsterfancierclub.board.free.post.Post;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * author: yunoi
 * date: 2023-10-30
 * description:
 */
@Entity
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;
    private String memberJoinId;
    private String password;
    private String name;
    private LocalDateTime registerDateTime;
    private String nickname;
    private String phone;
    private String email;
    private int point;
    @Enumerated(EnumType.STRING)
    private Level level;    // ENTRY, REGULAR, ADMIN
    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>(); // 관례상 컬렉션 객체 초기화 해줌

    /**
     * name: 회원 등록
     * author: yunoi
     * date: 2023-10-30
     * description:
     */
    public void registerMember(String memberJoinId, String password, String name, String nickname, String phone, String email) {
        this.memberJoinId = memberJoinId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.registerDateTime = LocalDateTime.now();
        this.point = 0;
        this.level = Level.ENTRY;
    }
}
