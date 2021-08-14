package com.mingchang.BlogService.model.database;

import java.util.Date;

public class TypeReg {
    private Integer typeRegId;

    private Integer articleId;

    private Integer articleTypeId;

    private Integer status;

    private Date creationDate;

    private Date updateDate;

    public Integer getTypeRegId() {
        return typeRegId;
    }

    public void setTypeRegId(Integer typeRegId) {
        this.typeRegId = typeRegId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(Integer articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}