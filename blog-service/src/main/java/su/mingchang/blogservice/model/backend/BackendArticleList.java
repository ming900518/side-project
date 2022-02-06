package su.mingchang.blogservice.model.backend;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

@Data
public class BackendArticleList {

    @Column("articleId")
    private Integer articleId;
    @Column("title")
    private String title;
    @Column("tags")
    private String tags;
    @Column("updateByName")
    private String updateByName;

}
