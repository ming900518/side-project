package su.mingchang.blogservice.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import su.mingchang.blogservice.model.database.Article;
import su.mingchang.blogservice.model.database.Tag;
import su.mingchang.blogservice.service.FrontendService;

/**
 * 前台Handler
 *
 * @author Ming Chang (<a href="mailto:ming@mingchang137.su">ming@mingchang137.su</a>)
 * @version 1.0
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-fn-handler-functions">Spring WebFlux documentation (HandlerFunction)</a>
 */

@Component
public record FrontendHandler(FrontendService frontendService) {

    /**
     * 查詢文章列表
     *
     * @param request 請求，後台傳進來的查詢條件
     * @return 所有符合條件的文章
     */

    public Mono<ServerResponse> listArticles(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_NDJSON).body(frontendService.listArticles(request.bodyToMono(Tag.class)), Article.class);
    }

    /**
     * 查詢文章內容
     *
     * @param request 請求，後台傳進來的查詢條件
     * @return 所有符合條件的文章
     */

    public Mono<ServerResponse> queryArticle(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_NDJSON).body(frontendService.queryArticle(request.bodyToMono(Article.class)), Article.class);
    }
}
