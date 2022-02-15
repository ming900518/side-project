package su.mingchang.blogservice.model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("article")
public class Article {
    @Id
    @Column("article_id")
    private Integer articleId;

    @Column("title")
    private String title;

    @Column("content")
    private String content;

    @Column("created_by")
    private Integer createdBy;

    @Column("creation_date")
    @CreatedDate
    private Timestamp creationDate;

    @Column("update_by")
    private Integer updateBy;

    @Column("update_date")
    @LastModifiedDate
    private Timestamp updateDate;
}
