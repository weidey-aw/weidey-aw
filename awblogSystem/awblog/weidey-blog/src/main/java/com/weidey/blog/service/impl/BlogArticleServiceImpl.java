package com.weidey.blog.service.impl;

import java.util.List;
import com.weidey.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.weidey.blog.mapper.BlogArticleMapper;
import com.weidey.blog.domain.BlogArticle;
import com.weidey.blog.service.IBlogArticleService;

/**
 * 博客文章Service业务层处理
 * 
 * @author weidey
 * @date 2024-04-13
 */
@Service
public class BlogArticleServiceImpl implements IBlogArticleService 
{
    @Autowired
    private BlogArticleMapper blogArticleMapper;

    /**
     * 查询博客文章
     * 
     * @param id 博客文章主键
     * @return 博客文章
     */
    @Override
    public BlogArticle selectBlogArticleById(Long id)
    {
        return blogArticleMapper.selectBlogArticleById(id);
    }

    /**
     * 查询博客文章列表
     * 
     * @param blogArticle 博客文章
     * @return 博客文章
     */
    @Override
    public List<BlogArticle> selectBlogArticleList(BlogArticle blogArticle)
    {
        return blogArticleMapper.selectBlogArticleList(blogArticle);
    }

    /**
     * 新增博客文章
     * 
     * @param blogArticle 博客文章
     * @return 结果
     */
    @Override
    public int insertBlogArticle(BlogArticle blogArticle)
    {
        blogArticle.setCreateTime(DateUtils.getNowDate());
        return blogArticleMapper.insertBlogArticle(blogArticle);
    }

    /**
     * 修改博客文章
     * 
     * @param blogArticle 博客文章
     * @return 结果
     */
    @Override
    public int updateBlogArticle(BlogArticle blogArticle)
    {
        blogArticle.setUpdateTime(DateUtils.getNowDate());
        return blogArticleMapper.updateBlogArticle(blogArticle);
    }

    /**
     * 批量删除博客文章
     * 
     * @param ids 需要删除的博客文章主键
     * @return 结果
     */
    @Override
    public int deleteBlogArticleByIds(Long[] ids)
    {
        return blogArticleMapper.deleteBlogArticleByIds(ids);
    }

    /**
     * 删除博客文章信息
     * 
     * @param id 博客文章主键
     * @return 结果
     */
    @Override
    public int deleteBlogArticleById(Long id)
    {
        return blogArticleMapper.deleteBlogArticleById(id);
    }
}
