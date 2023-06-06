package com.Design_Pattern.Board.User;

import com.Design_Pattern.Board.entity.Board;
import com.Design_Pattern.Board.repository.BoardRepository;

public interface State {


    default String write(Board board, BoardRepository boardRepository) {
        return null;
    }
    default String delete(Board board, BoardRepository boardRepository) {
        return null;
    }


    default void setIp(String ip){};
    default String getIp(){return null;};
}
