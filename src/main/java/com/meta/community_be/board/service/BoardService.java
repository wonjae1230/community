package com.meta.community_be.board.service;

import com.meta.community_be.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final ArticleRepository articleRepository;
}
