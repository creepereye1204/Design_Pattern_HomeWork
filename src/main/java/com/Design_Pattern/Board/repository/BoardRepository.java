package com.Design_Pattern.Board.repository;
import org.springframework.data.domain.Page;
import com.Design_Pattern.Board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//Entity 의해 생성된 DB에 접근하는 메서드(ex) findAll()) 들을 사용하기 위한 인터페이스이다
public interface BoardRepository extends JpaRepository<Board,Integer> {
    Page<Board> findByTitleContaining(String searchKeyWord, Pageable pageable);
}
