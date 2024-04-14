package com.weidey.web.controller.blog;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.weidey.common.annotation.Log;
import com.weidey.common.core.controller.BaseController;
import com.weidey.common.core.domain.AjaxResult;
import com.weidey.common.enums.BusinessType;
import com.weidey.blog.domain.BlogArticle;
import com.weidey.blog.service.IBlogArticleService;
import com.weidey.common.utils.poi.ExcelUtil;
import com.weidey.common.core.page.TableDataInfo;

/**
 * 博客文章Controller
 * 
 * @author weidey
 * @date 2024-04-13
 */
@RestController
@RequestMapping("/blog/article")
public class BlogArticleController extends BaseController
{
    @Autowired
    private IBlogArticleService blogArticleService;

    /**
     * 查询博客文章列表
     */
    @PreAuthorize("@ss.hasPermi('blog:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlogArticle blogArticle)
    {
        startPage();
        List<BlogArticle> list = blogArticleService.selectBlogArticleList(blogArticle);
        return getDataTable(list);
    }

    /**
     * 导出博客文章列表
     */
    @PreAuthorize("@ss.hasPermi('blog:article:export')")
    @Log(title = "博客文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogArticle blogArticle)
    {
        List<BlogArticle> list = blogArticleService.selectBlogArticleList(blogArticle);
        ExcelUtil<BlogArticle> util = new ExcelUtil<BlogArticle>(BlogArticle.class);
        util.exportExcel(response, list, "博客文章数据");
    }

    /**
     * 获取博客文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:article:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blogArticleService.selectBlogArticleById(id));
    }

    /**
     * 新增博客文章
     */
    @PreAuthorize("@ss.hasPermi('blog:article:add')")
    @Log(title = "博客文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogArticle blogArticle)
    {
        return toAjax(blogArticleService.insertBlogArticle(blogArticle));
    }

    /**
     * 修改博客文章
     */
    @PreAuthorize("@ss.hasPermi('blog:article:edit')")
    @Log(title = "博客文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogArticle blogArticle)
    {
        return toAjax(blogArticleService.updateBlogArticle(blogArticle));
    }

    /**
     * 删除博客文章
     */
    @PreAuthorize("@ss.hasPermi('blog:article:remove')")
    @Log(title = "博客文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogArticleService.deleteBlogArticleByIds(ids));
    }
}
