package com.jw.mycaferecipe.service.board;

import com.jw.mycaferecipe.entity.BoardDTO;
import com.jw.mycaferecipe.repository.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * 게시판목록 로직
     */
    public Page<BoardDTO> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    /**
     * 게시판검색목록
    */
    public Page<BoardDTO> boardSearchList(String category, String search, Pageable pageable) {
        Page<BoardDTO> pageList = null;

        if(category.equals("title")) {
            pageList = boardRepository.findByTitleContaining(search, pageable); // 제목으로 검색
        }
        else {
            pageList = boardRepository.findByWriterContaining(search, pageable); // 작성자로 검색
        }
        return pageList;
    }

    /**
     * 게시판등록 로직
     */
    public void boardEnroll(BoardDTO boardDTO) {
        boardRepository.save(boardDTO);
    }

    /**
     * 게시판상세 로직
     */
    public Optional<BoardDTO> boardDetail(Long num) {
        return boardRepository.findById(num);
    }

    @Transactional
    /**
     * 조회수증가 로직
     */
    public void readCount(Long num) {
        boardRepository.updateReadcnt(num);
    }

    /**
     * 게시판삭제 로직
     */
    public void boardDelete(Long num) {
        boardRepository.deleteById(num);
    }

}
