package su.mingchang.blogservice.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import su.mingchang.blogservice.handler.BackendHandler;
import su.mingchang.blogservice.handler.FrontendHandler;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * 後台Router
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-fn-router-functions">Spring WebFlux documentation (RouterFunction)</a>
 *
 * @author Ming Chang (<a href="mailto:ming@mingchang137.su">ming@mingchang137.su</a>)
 * @version 1.0
 */

@Configuration(proxyBeanMethods = false)
public class BackendRouter {

    /**
     * 後台 Routing
     * @param backendHandler 處理請求到下面路徑的Handler
     * @return Entry Point (Handler)
     */

    @Bean
    public RouterFunction<ServerResponse> backend(BackendHandler backendHandler) {
        return RouterFunctions.route()
                .POST(("/backend/login"), accept(APPLICATION_JSON), backendHandler::login)
                .GET(("/backend/listArticles"), accept(APPLICATION_JSON), backendHandler::listArticles)
                .GET(("/backend/queryArticle"), accept(APPLICATION_JSON), backendHandler::queryArticle)
                .build();
    }

    /**
     * 前台 Routing
     * @param frontendHandler 處理請求到下面路徑的Handler
     * @return Entry Point (Handler)
     */

    @Bean
    public RouterFunction<ServerResponse> frontend(FrontendHandler frontendHandler) {
        return RouterFunctions.route()
                .GET(("/frontend/listArticles"), accept(APPLICATION_JSON), frontendHandler::listArticles)
                .GET(("/frontend/queryArticle"), accept(APPLICATION_JSON), frontendHandler::queryArticle)
                .build();
    }

}
