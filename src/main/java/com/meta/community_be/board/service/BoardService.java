package com.meta.community_be.board.service;

import com.meta.community_be.board.domain.Board;
import com.meta.community_be.board.dto.BoardRequestDto;
import com.meta.community_be.board.dto.BoardResponseDto;
import com.meta.community_be.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        Board newBoard = new Board(boardRequestDto);
        Board savedBoard = boardRepository.save(newBoard);
        return new BoardResponseDto(savedBoard);
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(BoardResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public BoardResponseDto getBoardById(Long id) {
        Board foundBoard = findBoardById(id);
        return new BoardResponseDto(foundBoard);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Board foundBoard = findBoardById(id);
        foundBoard.update(boardRequestDto);
        return new BoardResponseDto(foundBoard);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board foundBoard = findBoardById(id);
        boardRepository.delete(foundBoard);
    }

    private Board findBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 id의 게시판은 존재하지 않습니다."));
    }
}
