package com.weidey.blog.controller;


import com.weidey.blog.domain.BlogArticle;
import com.weidey.blog.domain.param.ArticleParam;
import com.weidey.blog.service.IArticleService;
import com.weidey.common.core.controller.BaseController;
import com.weidey.common.core.domain.AjaxResult;
import com.weidey.common.core.page.TableDataInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping("/article")
@RestController
public class ArticleController extends BaseController {

    @Autowired
    IArticleService articleService;

    @GetMapping("/select/page")
    @ApiOperation("获取文章列表")
    public TableDataInfo getArticlePage(ArticleParam articleParam){
        //分页
        startPage();
        List<BlogArticle> articlePage = articleService.getArticlePage(articleParam);
        return getDataTable(articlePage);
    }

    @GetMapping("/select/detail")
    @ApiOperation("获取文章详情")
    public AjaxResult getArticleDetail(Long id){
        return AjaxResult.success(articleService.getArticleDetail(id));
    }

}
