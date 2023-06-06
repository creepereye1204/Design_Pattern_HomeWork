package com.Design_Pattern.Board.User;
import com.Design_Pattern.Board.entity.Board;
import com.Design_Pattern.Board.repository.BoardRepository;
import org.springframework.stereotype.Service;



@Service
public class Root implements State {
    public static Root root=null;

    private String ip=null;
    private Root(){};
    public synchronized static Root getRoot() {

        if(Root.root==null){
            Root.root=new Root();
        }
        return Root.root;
    }

    @Override
    public String write(Board board,BoardRepository boardRepository) {
        boardRepository.save(board);
        return "글쓰기 성공";
    }

    @Override
    public String delete(Board board, BoardRepository boardRepository) {
        boardRepository.deleteById(board.getId());
        return "삭제완료";
    }



    @Override
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip=ip;
    }
}
