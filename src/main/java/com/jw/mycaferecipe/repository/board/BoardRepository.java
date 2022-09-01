package com.jw.mycaferecipe.repository.board;

import com.jw.mycaferecipe.entity.BoardDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardDTO, Long> {

    @Override
    List<BoardDTO> findAll();

    @Override
    BoardDTO save(BoardDTO boardDTO);
}
