package su.mingchang.blogservice.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import su.mingchang.blogservice.model.database.Admin;
import su.mingchang.blogservice.model.database.Article;
import su.mingchang.blogservice.service.BackendService;

/**
 * 後台Handler
 *
 * @author Ming Chang (<a href="mailto:ming@mingchang137.su">ming@mingchang137.su</a>)
 * @version 1.0
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-fn-handler-functions">Spring WebFlux documentation (HandlerFunction)</a>
 */

@Component
@RequiredArgsConstructor
public record BackendHandler(BackendService backendService) {

    /**
     * 後台登入
     *
     * @param request 請求，後台傳進來的管理員帳號密碼
     * @return 成功回傳使用者資訊，失敗回傳  {@linkplain HttpStatus#UNAUTHORIZED 401 Unauthorized.}
     */

    public Mono<ServerResponse> login(ServerRequest request) {
        return backendService.login(request.bodyToMono(Admin.class)).flatMap(data -> {
            if (Boolean.TRUE.equals(data.getStatus())) {
                return ServerResponse.ok().body(Mono.just(data), Admin.class);
            } else {
                return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).switchIfEmpty(ServerResponse.status(HttpStatus.UNAUTHORIZED).build());
    }

    /**
     * 查詢文章列表
     *
     * @param request 請求，後台傳進來的查詢條件
     * @return 所有符合條件的文章
     */

    public Mono<ServerResponse> listArticles(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_NDJSON).body(backendService.listArticles(request.bodyToMono(Article.class)), Article.class);
    }

    /**
     * 查詢文章內容
     *
     * @param request 請求，後台傳進來的查詢條件
     * @return 所有符合條件的文章
     */

    public Mono<ServerResponse> queryArticle(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_NDJSON).body(backendService.queryArticle(request.bodyToMono(Article.class)), Article.class);
    }

}

