package test.tek.demo1.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.tek.demo1.db.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
