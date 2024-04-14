package com.weidey.blog.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.weidey.common.annotation.Excel;
import com.weidey.common.core.domain.BaseEntity;

/**
 * 博客文章对象 blog_article
 * 
 * @author weidey
 * @date 2024-04-13
 */
public class BlogArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long uid;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 简介 */
    @Excel(name = "简介")
    private String synopsis;

    /** 内容 */
    private String content;

    /** 封面 */
    @Excel(name = "封面")
    private String covers;

    /** 推流 */
    @Excel(name = "推流")
    private Long publicize;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long views;

    /** 标签 */
    @Excel(name = "标签")
    private String tags;

    /** 分组 */
    @Excel(name = "分组")
    private String artGroup;

    /** 审核 */
    @Excel(name = "审核")
    private Long audit;

    /** 用于存储JSON数据 */
    private String json;

    /** 用于存储文本数据 */
    private String text;

    /** 删除时间 */
    private Date deleteTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setSynopsis(String synopsis) 
    {
        this.synopsis = synopsis;
    }

    public String getSynopsis() 
    {
        return synopsis;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setCovers(String covers) 
    {
        this.covers = covers;
    }

    public String getCovers() 
    {
        return covers;
    }
    public void setPublicize(Long publicize) 
    {
        this.publicize = publicize;
    }

    public Long getPublicize() 
    {
        return publicize;
    }
    public void setViews(Long views) 
    {
        this.views = views;
    }

    public Long getViews() 
    {
        return views;
    }
    public void setTags(String tags) 
    {
        this.tags = tags;
    }

    public String getTags() 
    {
        return tags;
    }
    public void setArtGroup(String artGroup) 
    {
        this.artGroup = artGroup;
    }

    public String getArtGroup() 
    {
        return artGroup;
    }
    public void setAudit(Long audit) 
    {
        this.audit = audit;
    }

    public Long getAudit() 
    {
        return audit;
    }
    public void setJson(String json) 
    {
        this.json = json;
    }

    public String getJson() 
    {
        return json;
    }
    public void setText(String text) 
    {
        this.text = text;
    }

    public String getText() 
    {
        return text;
    }
    public void setDeleteTime(Date deleteTime) 
    {
        this.deleteTime = deleteTime;
    }

    public Date getDeleteTime() 
    {
        return deleteTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("title", getTitle())
            .append("synopsis", getSynopsis())
            .append("content", getContent())
            .append("covers", getCovers())
            .append("publicize", getPublicize())
            .append("views", getViews())
            .append("tags", getTags())
            .append("artGroup", getArtGroup())
            .append("remark", getRemark())
            .append("audit", getAudit())
            .append("json", getJson())
            .append("text", getText())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deleteTime", getDeleteTime())
            .toString();
    }
}
