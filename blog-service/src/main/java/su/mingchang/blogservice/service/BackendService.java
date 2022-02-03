package su.mingchang.blogservice.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import su.mingchang.blogservice.model.backend.ArticleList;
import su.mingchang.blogservice.model.database.Article;
import su.mingchang.blogservice.repository.database.ArticleRepository;

/**
 * 後台Service
 *
 * @author Ming Chang (<a href="mailto:ming@mingchang137.su">ming@mingchang137.su</a>)
 * @version 1.0
 */

@Service
public class BackendService {

    private final ArticleRepository articleRepository;

    public BackendService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Flux<ArticleList> listArticles(Mono<Article> request) {
        return request.flatMapMany(body -> articleRepository.listArticles(body.getTitle(), body.getContent(), body.getUpdateBy()));
    }
}


