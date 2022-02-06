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
@Table("admin")
public class Admin {
    @Id
    @Column("\"adminId\"")
    private Integer adminId;

    @Column("\"name\"")
    private String name;

    @Column("\"role\"")
    private Integer role;

    @Column("\"account\"")
    private String account;

    @Column("\"password\"")
    private String password;

    @Column("\"status\"")
    private Boolean status;

}
