package su.mingchang.springwebfluxdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("video_files")
public class VideoFiles {

    @Id
    @Column("videoFilesId")
    private Integer videoFilesId;
    @Column("fileName")
    private String fileName;

}
