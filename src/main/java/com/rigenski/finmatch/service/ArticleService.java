package com.rigenski.finmatch.service;

import com.rigenski.finmatch.entity.Article;
import com.rigenski.finmatch.exception.ResourceNotFoundException;
import com.rigenski.finmatch.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + articleId));
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Article updatedArticle) {
        Long articleId = updatedArticle.getId();

        if (!articleRepository.existsById(articleId)) {
            throw new ResourceNotFoundException("Article not found with id: " + articleId);
        }

        return articleRepository.save(updatedArticle);
    }

    public void deleteArticle(Long articleId) {
        if (!articleRepository.existsById(articleId)) {
            throw new ResourceNotFoundException("Article not found with id: " + articleId);
        }
        articleRepository.deleteById(articleId);
    }
}