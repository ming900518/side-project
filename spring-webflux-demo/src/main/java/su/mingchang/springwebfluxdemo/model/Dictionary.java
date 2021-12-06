package su.mingchang.springwebfluxdemo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Dictionary {
    private Integer dictionary_Id;

    private Integer type;

    private Integer code;

    private String name;

    private Date creation_Date;

    private Integer created_By;

    private Date update_Date;

    private Integer updated_By;

    private Integer backup_Int_1;

    private Integer backup_Int_2;

    private Integer backup_Int_3;

    private String backup_Varchar_1;

    private String backup_Varchar_2;

    private String backup_Varchar_3;

    private String note;

}
