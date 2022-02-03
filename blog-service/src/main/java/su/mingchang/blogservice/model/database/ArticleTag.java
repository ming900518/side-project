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
@Table("articleTag")
public class ArticleTag {
    @Id
    @Column("articleTagId")
    private Integer articleTagId;

    @Column("articleId")
    private Integer articleId;

    @Column("tagId")
    private Integer tagId;
}
