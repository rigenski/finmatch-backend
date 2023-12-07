// ArticleEntityListener.java
package com.rigenski.finmatch.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

public class ArticleEntityListener {

    @PrePersist
    public void prePersist(Article article) {
        LocalDateTime now = LocalDateTime.now();
        article.setCreatedAt(now);
        article.setUpdatedAt(now);
    }

    @PreUpdate
    public void preUpdate(Article article) {
        article.setUpdatedAt(LocalDateTime.now());
    }
}
