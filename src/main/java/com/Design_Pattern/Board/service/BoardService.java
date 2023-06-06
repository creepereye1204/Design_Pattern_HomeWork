package com.Design_Pattern.Board.service;

import com.Design_Pattern.Board.User.State;
import com.Design_Pattern.Board.entity.Board;
import com.Design_Pattern.Board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;



@Service
public class BoardService {
    private State state;
    @Autowired
    private BoardRepository boardRepository;
    private Action action;

    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }
    public Page<Board> boardSearchList(String searchKeyWord,Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyWord,pageable);
    }



    public String act(Board board){
        return this.action.Action(board,boardRepository,this.state);
    }


    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }


    public void setState(State state){
        this.state=state;
    }
    public void setAction(Action action){
        this.action=action;
    }
}

