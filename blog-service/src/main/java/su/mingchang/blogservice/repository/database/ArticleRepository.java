package su.mingchang.blogservice.repository.database;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import su.mingchang.blogservice.model.backend.BackendArticleList;
import su.mingchang.blogservice.model.backend.FrontendArticleList;
import su.mingchang.blogservice.model.database.Article;

@Repository
public interface ArticleRepository extends R2dbcRepository<Article, Integer> {

    @Query("""
            select article_id,
                   title,
                   (select string_agg(name, ', ')
                    from tag
                    where tag.tag_id in (select tag_id from article_tag where article_id = article.article_id)) as tags,
                   (select name from admin where admin_id = article.update_by)                                  as updateByName
            from blog.article
            where (:title is null or title like :title)
              and (:content is null or content like :content)
              and (:updateBy is null or update_by = :updateBy);
            """)
    Flux<BackendArticleList> listArticlesBackend(
            String title,
            String content,
            Integer updateBy
    );

    @Query("""
            select article_id,
                   title,
                   (select string_agg(name, ', ')
                    from tag
                    where tag.tag_id in (select tag_id from article_tag where article_tag.article_id = article.article_id)) as tags,
                   case
                       when length(content) > 150 then concat(substring(content, 0, 150), '......')
                       else content end                                                                                     as shortContent,
                   (select name from admin where admin_id = article.update_by)                                              as updateByName,
                   creation_date
            from blog.article
            where article_id in (select article_id from article_tag where (:tagId is null or tag_id = :tagId));
            """)
    Flux<FrontendArticleList> listArticlesFrontend(
            Integer tagId
    );
}
