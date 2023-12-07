package com.rigenski.finmatch.repository;

import com.rigenski.finmatch.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}