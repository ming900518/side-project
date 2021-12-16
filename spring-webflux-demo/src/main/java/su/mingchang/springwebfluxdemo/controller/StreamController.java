package su.mingchang.springwebfluxdemo.controller;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import su.mingchang.springwebfluxdemo.service.StreamService;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "stream")
public class StreamController {

    private final Path filePath = Paths.get("./demo");
    private final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @PostMapping("upload")
    public Mono<Void> upload(@RequestPart("file") Flux<FilePart> filePartFlux) {
        return filePartFlux.flatMap(filePart -> filePart.transferTo(filePath.resolve(filePart.filename()))).then();
    }

    @GetMapping(value = "play/{videoFilesId}", produces = "video/mp4")
    public Mono<Path> play(@PathVariable Integer videoFilesId) {
        return streamService.play(videoFilesId);
    }

    @GetMapping(value = "getVideoName/{videoFilesId}", produces = "video/mp4")
    public Mono<String> getVideoName(@PathVariable Integer videoFilesId) {
        return streamService.getVideoName(videoFilesId);
    }
}
