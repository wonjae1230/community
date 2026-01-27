package com.meta.community_be.board.dto;

import com.meta.community_be.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardResponseDto {
    private String title;
    private Long id;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
    }
}
