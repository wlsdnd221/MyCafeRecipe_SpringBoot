package com.jw.mycaferecipe.service.board;

import com.jw.mycaferecipe.entity.BoardDTO;
import com.jw.mycaferecipe.repository.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //게시판목록 로직
    public List<BoardDTO> boardList() {
        return boardRepository.findAll();
    }

    //게시판등록 로직
    public void boardEnroll(BoardDTO boardDTO) {
        boardRepository.save(boardDTO);

    }
}
