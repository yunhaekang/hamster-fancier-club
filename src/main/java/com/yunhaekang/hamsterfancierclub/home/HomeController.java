package com.yunhaekang.hamsterfancierclub.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: yunoi
 * date: 2023-09-12
 * description:
 */
@Slf4j
@Controller
public class HomeController {

    /**
     * name: 홈 화면
     * author: yunoi
     * date: 2023-09-12
     * description:
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
