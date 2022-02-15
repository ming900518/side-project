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
@Table("tag")
public class Tag {
    @Id
    @Column("tag_id")
    private Integer tagId;

    @Column("name")
    private String name;
}
