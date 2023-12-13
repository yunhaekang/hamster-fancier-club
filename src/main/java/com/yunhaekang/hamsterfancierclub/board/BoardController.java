package com.yunhaekang.hamsterfancierclub.board.free;

import com.yunhaekang.hamsterfancierclub.board.free.post.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * author: yunoi
 * date: 2023-12-07
 * description: 자유게시판 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class FreeBoardController {

    private final PostService postService;

    /**
     * name: 자유게시판 메인화면
     * author: yunoi
     * date: 2023-12-05
     * description: 자유게시판의 게시글을 작성일 기준 내림차순으로 최근 10개를 가져온다
     */
    @GetMapping("/board/{boardId}")
    public String retrieveAllPostsInFreeBoard (@PathVariable("boardId") Long boardId, Model model) {
        model.addAttribute("posts", postService.getAllPosts(boardId));
        return "board/free";
    }

    /**
     * name: 자유게시판 글쓰기 폼
     * author: yunoi
     * date: 2023-12-05
     * description: 글쓰기 폼으로 이동한다
     */
    @GetMapping("/board/{boardId}/posts")
    public String writePostInFreeBoard (@PathVariable("boardId") Long boardId, Model model) {
        PostFormDTO post = new PostFormDTO();
        post.setBoardId(boardId);
        post.setMemberId(3L);   // TODO: 임시 처리로 회원 ID 하드 코딩
        model.addAttribute("post", post);
        return "board/postForm";
    }

    /**
     * name: 자유게시판 글쓰기 저장
     * author: yunoi
     * date: 2023-12-05
     * description: 게시글을 저장하고 자유게시판 메인으로 이동한다
     */
    @PostMapping("/board/{boardId}/posts")
    public String savePostInFreeBoard (@PathVariable("boardId") Long boardId, @RequestParam("memberId") Long memberId, @RequestParam("subject") String subject, @RequestParam String content) {

        postService.writePost(boardId, memberId, subject, content);

        return "redirect:";
    }

    /**
     * name: 자유게시판 글 내용 보기
     * author: yunoi
     * date: 2023-12-05
     * description: 자유게시판의 글을 조회한다
     */
    @GetMapping("/board/{boardId}/posts/{postId}")
    public String viewPostInFreeBoard (@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId, Model model) {
        Post post = postService.viewPost(postId);
        PostViewDTO dto = new PostViewDTO();
        dto.setPostId(post.getId());
        dto.setWriter(post.getMember().getNickname());
        dto.setSubject(post.getSubject());
        dto.setContent(post.getContent());
        dto.setRegisterDateTime(post.getRegisterDateTime());
        model.addAttribute("post", dto);
        return "/board/post";
    }

    /**
     * name: 자유게시판 글 수정 폼
     * author: yunoi
     * date: 2023-12-05
     * description: 현재 글을 수정하는 폼으로 이동한다
     */
    @GetMapping("/board/{boardId}/posts/{postId}/edit")
    public String getEditPostFormInFreeBoard (@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId, Model model) {
        Post post = postService.viewPost(postId);

        PostFormDTO dto = new PostFormDTO();
        dto.setBoardId(boardId);
        dto.setPostId(post.getId());
        dto.setMemberId(post.getMember().getId());
        dto.setSubject(post.getSubject());
        dto.setContent(post.getContent());

        model.addAttribute("form", dto);

        return "board/editPostForm";
    }

    /**
     * name: 자유게시판 글 수정
     * author: yunoi
     * date: 2023-12-05
     * description: 해당 글을 수정한 후 자유게시판 메인으로 이동한다
     */
    @PostMapping("/board/{boardId}/posts/{postId}/edit")
    public String editPostInFreeBoard (@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId, @ModelAttribute("form") PostFormDTO form) {
        postService.editPost(postId, form.getSubject(), form.getContent());
        return "redirect:/board/free";
    }

    /**
     * name: 자유게시판 댓글 쓰기 폼
     * author: yunoi
     * date: 2023-12-05
     * description: 자유게시판의 댓글 쓰기 폼으로 이동
     */
    @GetMapping("/board/{boardId}/posts/{parentPostId}/reply")
    public String getReplyFormInFreeBoard (@PathVariable("parentPostId") Long parentPostId, Model model) {
        Post post = postService.viewPost(parentPostId);
        ReplyFormDTO dto = new ReplyFormDTO();
        dto.setBoardId(post.getBoard().getId());
        dto.setMemberId(6L);  // TODO 추후 수정
        dto.setParentPostId(post.getId());
        dto.setPostGroupId(post.getPostGroupId());
        dto.fillContentWithParentPost(post.getSubject(), post.getContent());

        model.addAttribute("form", dto);

        return "board/replyPostForm";
    }

    /**
     * name: 자유게시판 댓글 저장
     * author: yunoi
     * date: 2023-12-05
     * description: 해당 글의 댓글 저장
     */
    @PostMapping("/board/{boardId}/posts/{parentPostId}/reply")
    public String writeReplyPostInFreeBoard (@PathVariable("parentPostId") Long parentPostId, @ModelAttribute("form") ReplyFormDTO form) {
        postService.writeReplyPost(form.getPostGroupId(), parentPostId, form.getBoardId(), form.getMemberId(), form.getSubject(), form.getContent());
        return "redirect:/board/free";
    }
}
