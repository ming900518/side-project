package su.mingchang.blogservice.model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("article_tag")
public class ArticleTag {
    @Id
    @Column("article_tag_id")
    private Integer articleTagId;

    @Column("article_id")
    private Integer articleId;

    @Column("tag_id")
    private Integer tagId;
}
