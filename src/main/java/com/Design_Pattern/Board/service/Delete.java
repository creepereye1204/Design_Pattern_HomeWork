package com.Design_Pattern.Board.service;

import com.Design_Pattern.Board.User.State;
import com.Design_Pattern.Board.entity.Board;
import com.Design_Pattern.Board.repository.BoardRepository;

public class Delete implements Action{
    @Override
    public String Action(Board board, BoardRepository boardRepository, State state) {

        return state.delete(board,boardRepository);

    }
}
