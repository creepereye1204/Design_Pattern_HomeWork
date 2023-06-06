package com.Design_Pattern.Board.controller;
import com.Design_Pattern.Board.BoardApplication;
import com.Design_Pattern.Board.User.*;
import com.Design_Pattern.Board.service.Delete;
import com.Design_Pattern.Board.service.Write;
import com.Design_Pattern.Board.utill.UserFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import com.Design_Pattern.Board.entity.Board;
import com.Design_Pattern.Board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.security.NoSuchAlgorithmException;


@Controller
public class boardWrite {


    @Autowired
    private BoardService boardService;
    public boolean switchState(String ip) throws NoSuchAlgorithmException {


        for(State state: BoardApplication.userRecord.currentUser){

            if(BoardApplication.sha256.encrypt(ip).equals(state.getIp())){
                boardService.setState(state);

                return true;
            }
        }
        return false;
    }
    @GetMapping("/") // This maps the HTTP GET request to the root ("/") to this method
    public String init(HttpSession session, HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        if(session.getAttribute("state")!=null || switchState(request.getRemoteAddr())){
            session.setAttribute("state","valid");
            return "redirect:/board/list";
        }


        return "userchoice";
    }
    @GetMapping("/choice/{state}") // This maps the HTTP GET request to the root ("/") to this method
    public String home(@PathVariable String state, HttpSession session, HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        String ip=request.getRemoteAddr().toString();
        State setState;
        ip=BoardApplication.sha256.encrypt(ip);




        if(state.equals("root") && Root.root==null){
            setState=BoardApplication.userFactory.spawnUser(ip,BoardApplication.user.ROOT);

        }else if(state.equals("admin")){
            setState=BoardApplication.userFactory.spawnUser(ip,BoardApplication.user.ADMIN);

        }else {
            setState=BoardApplication.userFactory.spawnUser(ip,BoardApplication.user.USER);

        }



        session.setAttribute("state",state);

        this.boardService.setState(setState);
        return "redirect:/board/list";
    }
    @PostMapping("/board/write") // This maps the HTTP GET request to the root ("/") to this method
    public String boardWrite(Board board,Model model,HttpSession session,HttpServletRequest request) throws NoSuchAlgorithmException {
        switchState(request.getRemoteAddr().toString());
        if(session.getAttribute("state")!=null) {
            boardService.setAction(new Write());
            model.addAttribute("message", boardService.act(board));
            model.addAttribute("searchUrl", "/board/list");
            return "message";
        }
        return "redirect:/";
    }
    @GetMapping("/board/list") // This maps the HTTP GET request to the root ("/") to this method
    public String boardList(Model model,@PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.DESC)
    Pageable pageable,String searchKeyWord,HttpSession session) {
        if(session.getAttribute("state")!=null) {

            Page<Board> list;
            if (searchKeyWord == null) {
                list = boardService.boardList(pageable);
            } else {
                list = boardService.boardSearchList(searchKeyWord, pageable);
            }

            int nowPage = list.getPageable().getPageNumber() + 1;
            int startPage = Math.max(nowPage - 4, 1);
            int endPage = Math.min(nowPage + 5, list.getTotalPages());


            model.addAttribute("list", list);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);

            return "boardlist";
        }
        return "redirect:/";
    }

    @GetMapping("/board/view") // This maps the HTTP GET request to the root ("/") to this method
    public String boardView(Model model,Integer id,HttpSession session) {
        if(session.getAttribute("state")!=null) {
            model.addAttribute("board", boardService.boardView(id));

            return "boardview";
        }
        return "redirect:/";
    }

    @GetMapping("/board/delete") // This maps the HTTP GET request to the root ("/") to this method
    public String boardView(Board board,Model model,HttpSession session,HttpServletRequest request) throws NoSuchAlgorithmException {
        switchState(request.getRemoteAddr().toString());
        if(session.getAttribute("state")!=null) {
            boardService.setAction(new Delete());
            model.addAttribute("message", boardService.act(board));
            model.addAttribute("searchUrl", "/board/list");
            return "message";



        }
        return "redirect:/";
    }

    @GetMapping("/board/modify/{id}")
    public String modifyBoard(@PathVariable("id") Integer id,Model model,HttpSession session,HttpServletRequest request) throws NoSuchAlgorithmException {
        switchState(request.getRemoteAddr().toString());
        if(session.getAttribute("state")!=null) {
            model.addAttribute("board", boardService.boardView(id));
            return "boardmodify";
        }
        return "redirect:/";
    }

    @PostMapping("/board/update/{id}") // This maps the HTTP GET request to the root ("/") to this method
    public String updateBoard(@PathVariable("id") Integer id,Board board,HttpSession session,Model model) {
        if(session.getAttribute("state")!=null){
        Board tempBoard= boardService.boardView(id);
        tempBoard.setTitle(board.getTitle());
        tempBoard.setContent(board.getContent());
        boardService.setAction(new Write());
        this.boardService.act(tempBoard);
        model.addAttribute("message", boardService.act(board));
        model.addAttribute("searchUrl", "/board/list");
        return "message";


        }
        return "redirect:/";
    }

    @GetMapping("/write")
    public String com(HttpSession session){
        if(session.getAttribute("state")!=null){
            return "boardwrite";
        }
        return "redirect:/";
    }

}
