package su.mingchang.blogservice.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import su.mingchang.blogservice.model.backend.BackendArticleList;
import su.mingchang.blogservice.model.database.Admin;
import su.mingchang.blogservice.model.database.Article;
import su.mingchang.blogservice.repository.database.AdminRepository;
import su.mingchang.blogservice.repository.database.ArticleRepository;

/**
 * 後台Service
 *
 * @author Ming Chang (<a href="mailto:ming@mingchang137.su">ming@mingchang137.su</a>)
 * @version 1.0
 */

@Service
public record BackendService(AdminRepository adminRepository,
                             ArticleRepository articleRepository) {

    public Mono<Admin> login(Mono<Admin> request) {
        return request.flatMap(body -> {
            ExampleMatcher matcher = ExampleMatcher
                    .matchingAll()
                    .withMatcher("account", ExampleMatcher.GenericPropertyMatchers.exact())
                    .withMatcher("password", ExampleMatcher.GenericPropertyMatchers.exact());
            Example<Admin> example = Example.of(body, matcher);
            return adminRepository.findOne(example);
        });
    }

    public Flux<BackendArticleList> listArticles(Mono<Article> request) {
        return request.flatMapMany(body -> articleRepository.listArticlesBackend(body.getTitle(), body.getContent(), body.getUpdateBy()));
    }

    public Mono<Article> queryArticle(Mono<Article> request) {
        return request.flatMap(body -> articleRepository.findById(body.getArticleId()));
    }
}


