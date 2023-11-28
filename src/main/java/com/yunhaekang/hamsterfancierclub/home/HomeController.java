package com.yunhaekang.hamsterfancierclub.home;

import com.yunhaekang.hamsterfancierclub.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: yunoi
 * date: 2023-09-12
 * description:
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final BoardService boardService;

    /**
     * name: 홈 화면
     * author: yunoi
     * date: 2023-09-12
     * description:
     */
    @RequestMapping("/")
    public String index (Model model) {
        // 메뉴에 표시할 게시판 리스트 조회
        model.addAttribute("boards", boardService.getAllBoards());
        return "index";
    }
}
