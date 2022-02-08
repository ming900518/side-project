package su.mingchang.blogservice.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import su.mingchang.blogservice.model.backend.FrontendArticleList;
import su.mingchang.blogservice.model.database.Article;
import su.mingchang.blogservice.model.database.Tag;
import su.mingchang.blogservice.repository.database.ArticleRepository;

/**
 * 前台Service
 *
 * @author Ming Chang (<a href="mailto:ming@mingchang137.su">ming@mingchang137.su</a>)
 * @version 1.0
 */

@Service
public record FrontendService(ArticleRepository articleRepository) {

    public Flux<FrontendArticleList> listArticles(Mono<Tag> request) {
        return request.flatMapMany(body -> articleRepository.listArticlesFrontend(body.getTagId()));
    }

    public Mono<Article> queryArticle(Mono<Article> request) {
        return request.flatMap(body -> articleRepository.findById(body.getArticleId()));
    }

}
