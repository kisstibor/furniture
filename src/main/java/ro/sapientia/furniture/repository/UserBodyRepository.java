package ro.sapientia.furniture.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sapientia.furniture.model.UserBody;

@Repository
public interface UserBodyRepository extends JpaRepository<UserBody,Long> {
    // some unique sql querry goes here
    UserBody findUserBodyById(Long id);
}
