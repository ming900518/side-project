package su.mingchang.springwebfluxdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import su.mingchang.springwebfluxdemo.model.Dictionary;
import su.mingchang.springwebfluxdemo.service.BaseService;

@RestController
@RequestMapping(value = "/")
public class BaseController {

    final BaseService baseService;

    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

    @GetMapping("/dictionary")
    public ResponseEntity<Flux<Dictionary>> dictionary() {
        Flux<Dictionary> result = baseService.selectDictionary();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(result.publishOn(Schedulers.elastic()).subscribeOn(Schedulers.elastic()));
    }

}
