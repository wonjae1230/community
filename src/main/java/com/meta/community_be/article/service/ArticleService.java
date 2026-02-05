package com.meta.community_be.article.service;

import com.meta.community_be.article.repository.ArticleRepository;
import com.meta.community_be.article.domain.Article;
import com.meta.community_be.article.dto.ArticleRequestDto;
import com.meta.community_be.article.dto.ArticleResponseDto;
import com.meta.community_be.board.domain.Board;
import com.meta.community_be.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public ArticleResponseDto createArticle(ArticleRequestDto articleRequestDto, Long boardId) {
        Board foundBoard = getfindBoardById(boardId);

        Article newArticle = new Article(articleRequestDto, foundBoard);
        Article savedArticle = articleRepository.save(newArticle);
        ArticleResponseDto articleResponseDto = new ArticleResponseDto(savedArticle);
        return articleResponseDto;
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDto> getArticles() {
        return articleRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(ArticleResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto getArticleById(Long id, Long boardId) {
        Article foundArticle = getValidBoardAndArticleById(id, boardId);
        return new ArticleResponseDto(foundArticle);
    }

    @Transactional
    public ArticleResponseDto updateArticle(Long id, ArticleRequestDto articleRequestDto, Long boardId) {
        Article foundArticle = getValidBoardAndArticleById(id, boardId);
        foundArticle.update(articleRequestDto);
        return new ArticleResponseDto(foundArticle);
    }

    @Transactional
    public void deleteArticle(Long id, Long boardId) {
        Article foundArticle = getValidBoardAndArticleById(id, boardId);
        articleRepository.delete(foundArticle);
    }
//helper 메서드
    public Article getValidBoardAndArticleById(Long id, Long boardId) {
        return articleRepository.findByIdAndBoardId(id, boardId).orElseThrow(() ->
                new IllegalArgumentException("선택한 id의 게시글과 boardId의 게시판은 존재하지 않습니다."));
    }
    public Board getfindBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("선택한 id의 게시판은 존재하지 않습니다."));
    }
}
