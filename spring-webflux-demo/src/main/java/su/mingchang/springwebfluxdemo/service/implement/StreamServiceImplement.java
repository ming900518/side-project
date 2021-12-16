package su.mingchang.springwebfluxdemo.service.implement;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import su.mingchang.springwebfluxdemo.model.VideoFiles;
import su.mingchang.springwebfluxdemo.repositories.VideoFilesRepository;
import su.mingchang.springwebfluxdemo.service.StreamService;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StreamServiceImplement implements StreamService {

    private static final String FORMAT = "./demo/%s.mp4";
    private final VideoFilesRepository videoFilesRepository;

    public StreamServiceImplement(VideoFilesRepository videoFilesRepository) {
        this.videoFilesRepository = videoFilesRepository;
    }

    @Override
    public Mono<String> getVideoName(Integer videoFilesId) {
        return this.videoFilesRepository.findById(videoFilesId).flatMap(vf -> Mono.just(vf.getFileName()));
    }

    @Override
    public Mono<Path> play(Integer videoFilesId) {
        Mono<VideoFiles> videoFiles = this.videoFilesRepository.findById(videoFilesId);
        String videoName = String.valueOf(videoFiles.subscribe(vf -> vf.getFileName()));
        return Mono.fromSupplier(() -> Paths.get("./demo" + videoName));
    }
}
