package su.mingchang.blogservice.repository.database;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import su.mingchang.blogservice.model.database.ArticleTag;

@Repository
public interface ArticleTagRepository extends R2dbcRepository<ArticleTag, Integer>{

}
