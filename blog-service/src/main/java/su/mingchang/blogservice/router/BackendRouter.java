package su.mingchang.blogservice.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import su.mingchang.blogservice.handler.BackendHandler;

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
     * @return Spring內部的entry point
     */

    @Bean
    public RouterFunction<ServerResponse> backend(BackendHandler backendHandler) {
        return RouterFunctions.route()
                .POST(("/backend/login"), accept(APPLICATION_JSON), backendHandler::login)
                .GET(("/backend/listArticles"), accept(APPLICATION_JSON), backendHandler::listArticles)
                .build();
    }

}
