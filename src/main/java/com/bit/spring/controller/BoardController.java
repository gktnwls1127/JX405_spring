package com.bit.spring.controller;

import com.bit.spring.model.BoardDTO;
import com.bit.spring.model.UserDTO;
import com.bit.spring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board/")
public class BoardController {
    BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }
    @GetMapping("showAll/{pageNo}")
    public String showAll(HttpSession session, RedirectAttributes redirectAttributes, Model model, @PathVariable int pageNo){
        if (session.getAttribute("logIn") == null){
            redirectAttributes.addFlashAttribute("message", "다시 로그인해주세요.");
            return "redirect:/";
        }

        int start = 0;
        int end = 0;

        int lastPage = boardService.selectLastPage();
        if (lastPage < 5){
            start = 1;
            end = lastPage;
        } else if (pageNo < 3){
            start = 1;
            end = 5;
        } else if (pageNo > lastPage - 3){
            start = lastPage - 4;
            end = lastPage;
        } else {
            start = pageNo - 2;
            end = pageNo + 2;
        }

        model.addAttribute("list", boardService.selectAll(pageNo));
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("startPage", start);
        model.addAttribute("endPage", end);

        return "/board/showAll";
    }

    @GetMapping("showOne/{id}")
    public String showOne(HttpSession session, RedirectAttributes redirectAttributes, Model model, @PathVariable int id){
        if (session.getAttribute("logIn") == null){
            redirectAttributes.addFlashAttribute("message", "다시 로그인해주세요.");
            return "redirect:/";
        }

        BoardDTO b = boardService.selectOne(id);
        if (b == null){
            redirectAttributes.addFlashAttribute("message", "존재하지 않는 글 번호입니다.");
            return "redirect:/board/showAll/1";
        }

        model.addAttribute("result", b);
        int logInId = ((UserDTO)session.getAttribute("logIn")).getId();
        model.addAttribute("logInId", logInId);

        return "/board/showOne";
    }

    @GetMapping("write")
    public String showWrite(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null) {
            redirectAttributes.addFlashAttribute("message", "다시 로그인해주세요.");
            return "redirect:/";
        }

        return "/board/write";
    }

    @PostMapping("write")
    public String write(HttpSession session, RedirectAttributes redirectAttributes, BoardDTO boardDTO){
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null){
            redirectAttributes.addFlashAttribute("message", "다시 로그인해주세요.");
            return "redirect:/";
        }
        //System.out.println("insert전 boardDTO : " + boardDTO);
        boardDTO.setWriterId(logIn.getId());
        boardService.insert(boardDTO);
        //System.out.println("insert후 boardDTO : " + boardDTO);

        return "redirect:/board/showOne/"+boardDTO.getId();
    }
        /*

    @GetMapping("update/{id}")
    public String showUpdate(HttpSession session, RedirectAttributes redirectAttributes, Model model, @PathVariable int id){
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null){
            redirectAttributes.addFlashAttribute("message", "다시 로그인해주세요.");
            return "redirect:/";
        }

        BoardDTO b = boardService.selectOne(id);
        if (b == null || b.getWriterId() != logIn.getId()){
            redirectAttributes.addFlashAttribute("message", "유효하지 않은 접근입니다.");
            return "redirect:/board/showAll/1";
        }

        model.addAttribute("result", b);

        return "/board/update";
    }


    @PostMapping("update")
    public String updateBoard(HttpSession session, BoardDTO boardDTO){
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null){
            return "redirect:/";
        }

        BoardDTO origin = boardService.selectOne(boardDTO.getId());
        if (origin == null){
            return "redirect:/board/showAll/1";
        }

        origin.setTitle(boardDTO.getTitle());
        origin.setContent(boardDTO.getContent());
        boardService.update(origin);

        return "redirect:/board/showOne/" + boardDTO.getId();
    }

    @GetMapping("delete/{id}")
    public String delete(HttpSession session, RedirectAttributes redirectAttributes, @PathVariable int id){
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null){
            redirectAttributes.addFlashAttribute("message", "다시 로그인해주세요.");
            return "redirect:/";
        }

        BoardDTO b = boardService.selectOne(id);
        if (b == null || b.getWriterId() != logIn.getId()){
            redirectAttributes.addFlashAttribute("message", "유효하지 않은 접근입니다.");
            return "redirect:/board/showAll/1";
        }

        boardService.delete(id);
        return  "redirect:/board/showAll/1";
    }

    */

}