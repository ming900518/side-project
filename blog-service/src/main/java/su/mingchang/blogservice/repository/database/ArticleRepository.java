package su.mingchang.blogservice.repository.database;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import su.mingchang.blogservice.model.backend.ArticleList;
import su.mingchang.blogservice.model.database.Article;


@Repository
public interface ArticleRepository extends R2dbcRepository<Article, Integer> {

    @Query(value =
            "select \"articleId\", " +
                    "\"title\", " +
                    "(select string_agg(\"name\", ', ') from \"tag\" where \"tag\".\"tagId\" in (select \"tagId\" from \"articleTag\" where \"articleTag\".\"articleId\" = \"article\".\"articleId\")) as tags, " +
                    "(select \"name\" from \"admin\" where \"adminId\" = \"article\".\"updateBy\") as updateByName " +
                    "from \"blog\".\"article\" " +
                    "where (:title is null or \"title\" like :title) " +
                    "and (:content is null or \"content\" like :content) " +
                    "and (:updateBy is null or \"updateBy\" = :updateBy);"
    )
    Flux<ArticleList> listArticles(
            String title,
            String content,
            Integer updateBy
    );

}
