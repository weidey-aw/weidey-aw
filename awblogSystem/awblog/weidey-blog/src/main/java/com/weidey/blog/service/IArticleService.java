package com.weidey.blog.service;

import com.weidey.blog.domain.BlogArticle;
import com.weidey.blog.domain.param.ArticleParam;

import java.util.List;

public interface IArticleService {

    /**
     * 获取文章列表
     */
     public List<BlogArticle> getArticlePage(ArticleParam articleParam);


      public BlogArticle getArticleDetail(Long id);
}
