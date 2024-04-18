package com.weidey.blog.mapper;

import com.weidey.blog.domain.BlogArticle;

import java.util.List;

public interface ArticleMapper {

    /**
     *  文章列表查询
     * @return  文章列表
     */
   public List<BlogArticle> getArticlePage();


    BlogArticle getArticleDetail(Long id);
}
