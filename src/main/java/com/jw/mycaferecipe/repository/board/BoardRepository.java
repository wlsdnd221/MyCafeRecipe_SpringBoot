package com.jw.mycaferecipe.repository.board;

import com.jw.mycaferecipe.entity.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardDTO, Long> {

    //게시판목록
    @Override
    List<BoardDTO> findAll();

    //게시판등록
    @Override
    BoardDTO save(BoardDTO boardDTO);

    //게시판상세
    @Override
    Optional<BoardDTO> findById(Long num);

    //조회수증가
    @Modifying
    @Query("update BoardDTO b set b.readcnt = b.readcnt+1 where b.num = :num")
    int updateReadcnt(@Param("num") Long num);

    //게시판삭제
    @Override
    void deleteById(Long num);

    //게시판검색
    Page<BoardDTO> findByTitleContaining(String search, Pageable pageable);
    Page<BoardDTO> findByWriterContaining(String search, Pageable pageable);
}
