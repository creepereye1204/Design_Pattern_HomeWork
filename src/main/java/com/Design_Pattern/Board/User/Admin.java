package com.Design_Pattern.Board.User;

import com.Design_Pattern.Board.entity.Board;
import com.Design_Pattern.Board.repository.BoardRepository;

public class Admin implements State{
    private String ip=null;

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String write(Board board, BoardRepository boardRepository) {

        return "어드민은 관여 불가";
    }

    @Override
    public String delete(Board board, BoardRepository boardRepository) {
        System.out.println(board.getId());
        boardRepository.deleteById(board.getId());
        return "삭제완료";
    }


    @Override
    public String getIp() {
        return ip;
    }
}
