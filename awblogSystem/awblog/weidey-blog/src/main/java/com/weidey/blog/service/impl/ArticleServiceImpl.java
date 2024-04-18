package com.weidey.blog.service.impl;


import com.weidey.blog.domain.BlogArticle;
import com.weidey.blog.domain.param.ArticleParam;
import com.weidey.blog.mapper.ArticleMapper;
import com.weidey.blog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

     @Autowired
     private ArticleMapper articleMapper;


    @Override
    public List<BlogArticle> getArticlePage(ArticleParam articleParam) {
        int pageNum = articleParam.getPageNum();
        int pageSize = articleParam.getPageSize();
        //TODO  Sql 查询文章分页
        return articleMapper.getArticlePage();
    }

    @Override
    public BlogArticle getArticleDetail(Long id) {
        //TODO  Sql 查询文章详细
        return articleMapper.getArticleDetail(id);
    }


}
