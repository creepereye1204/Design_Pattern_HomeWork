package com.Design_Pattern.Board.User;

import com.Design_Pattern.Board.entity.Board;
import com.Design_Pattern.Board.repository.BoardRepository;

public class User implements State{
    private String ip=null;

    public User(){

    }
    @Override
    public String write(Board board, BoardRepository boardRepository) {
        boardRepository.save(board);
        return "글쓰기 성공";
    }

    @Override
    public String delete(Board board, BoardRepository boardRepository) {

        return "유저는 삭제 불가";
    }
    public void setIp(String ip) {
        this.ip = ip;
    }


    @Override
    public String getIp() {
        return ip;
    }
}
