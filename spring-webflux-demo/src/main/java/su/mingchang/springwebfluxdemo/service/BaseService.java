package su.mingchang.springwebfluxdemo.service;

import reactor.core.publisher.Flux;
import su.mingchang.springwebfluxdemo.model.Dictionary;


public interface BaseService {

    Flux<Dictionary> selectDictionary();

}
