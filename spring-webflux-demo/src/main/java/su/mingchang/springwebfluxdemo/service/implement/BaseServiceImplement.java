package su.mingchang.springwebfluxdemo.service.implement;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import su.mingchang.springwebfluxdemo.mapper.DictionaryMapper;
import su.mingchang.springwebfluxdemo.model.Dictionary;
import su.mingchang.springwebfluxdemo.service.BaseService;

@Service
public class BaseServiceImplement implements BaseService {

    private final DictionaryMapper dictionaryMapper;

    public BaseServiceImplement(DictionaryMapper dictionaryMapper) {
        this.dictionaryMapper = dictionaryMapper;
    }

    @Override
    public Flux<Dictionary> selectDictionary() {
        return Flux.push(fluxSink -> {
            dictionaryMapper.selectAll(resultContext -> fluxSink.next(resultContext.getResultObject()));
            fluxSink.complete();
        });
    }
}
