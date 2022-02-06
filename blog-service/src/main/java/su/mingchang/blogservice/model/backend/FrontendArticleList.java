package su.mingchang.blogservice.model.backend;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.sql.Timestamp;

@Data
public class FrontendArticleList {

    @Column("articleId")
    private Integer articleId;
    @Column("title")
    private String title;
    @Column("tags")
    private String tags;
    @Column("shortContent")
    private String shortContent;
    @Column("updateByName")
    private String updateByName;
    @Column("creationDate")
    private Timestamp creationDate;

}
