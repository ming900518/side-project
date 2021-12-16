package su.mingchang.springwebfluxdemo.service;

import reactor.core.publisher.Mono;

import java.nio.file.Path;

public interface StreamService {

    Mono<String> getVideoName (Integer videoFilesId);

    Mono<Path> play(Integer videoFilesId);

}
