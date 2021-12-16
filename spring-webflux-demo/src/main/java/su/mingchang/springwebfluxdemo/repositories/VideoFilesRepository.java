package su.mingchang.springwebfluxdemo.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import su.mingchang.springwebfluxdemo.model.VideoFiles;

public interface VideoFilesRepository extends ReactiveCrudRepository<VideoFiles, Integer> {
}
