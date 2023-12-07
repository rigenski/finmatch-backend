package com.rigenski.finmatch.controller;

import com.rigenski.finmatch.entity.Article;
import com.rigenski.finmatch.exception.ResourceNotFoundException;
import com.rigenski.finmatch.response.ApiResponse;
import com.rigenski.finmatch.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Article>>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(new ApiResponse<>("success", "Articles retrieved successfully", articles));
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ApiResponse<Article>> getArticleById(@PathVariable Long articleId) {
        try {
            Article article = articleService.getArticleById(articleId);
            return ResponseEntity.ok(new ApiResponse<>("success", "Article retrieved successfully", article));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", ex.getMessage(), null));
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Article>> createArticle(@RequestBody Article article) {
        Article createdArticle = articleService.createArticle(article);
        return new ResponseEntity<>(new ApiResponse<>("success", "Article created successfully", createdArticle),
                HttpStatus.CREATED);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<ApiResponse<Article>> updateArticle(@PathVariable Long articleId, @RequestBody Article updatedArticle) {
        try {
            Article existingArticle = articleService.getArticleById(articleId);

            // Update properties of existingArticle with values from updatedArticle
            existingArticle.setTitle(updatedArticle.getTitle());
            existingArticle.setAuthor(updatedArticle.getAuthor());
            existingArticle.setImage(updatedArticle.getImage());
            existingArticle.setUrl(updatedArticle.getUrl());

            // Save the updated article
            Article savedArticle = articleService.updateArticle(existingArticle);

            return ResponseEntity.ok(new ApiResponse<>("success", "Article updated successfully", savedArticle));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", ex.getMessage(), null));
        }
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<ApiResponse<Void>> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Article deleted successfully", null));
    }
}