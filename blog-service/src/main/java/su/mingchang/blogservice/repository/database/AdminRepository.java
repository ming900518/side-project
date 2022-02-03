package su.mingchang.blogservice.repository.database;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import su.mingchang.blogservice.model.database.Admin;

@Repository
public interface AdminRepository extends R2dbcRepository<Admin, Integer>{

    @Query("select * from admin where account = :account and password = :password;")
    Mono<Admin> findByAccountAndPassword(String account, String password);

}
