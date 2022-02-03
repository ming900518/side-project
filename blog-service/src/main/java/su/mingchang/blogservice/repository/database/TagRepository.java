package su.mingchang.blogservice.repository.database;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import su.mingchang.blogservice.model.database.Tag;

@Repository
public interface TagRepository extends R2dbcRepository<Tag, Integer>{

}
