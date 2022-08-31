package com.jw.mycaferecipe.repository.board;

import com.jw.mycaferecipe.entity.BoardDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardDTO, Long> {

}
