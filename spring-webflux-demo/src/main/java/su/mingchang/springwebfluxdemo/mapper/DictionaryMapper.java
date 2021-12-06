package su.mingchang.springwebfluxdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.ResultHandler;
import su.mingchang.springwebfluxdemo.model.Dictionary;

@Mapper
public interface DictionaryMapper {

    @Select("select * from dictionary;")
    @ResultType(Dictionary.class)
    void selectAll(ResultHandler<Dictionary> handler);
}
