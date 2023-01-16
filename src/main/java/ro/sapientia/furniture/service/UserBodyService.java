package ro.sapientia.furniture.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.UserBody;
import ro.sapientia.furniture.repository.UserBodyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBodyService {
    private final UserBodyRepository userBodyRepository;

    public List<UserBody> findAllUserBodies() {
        log.info("finding all user");
        return this.userBodyRepository.findAll();
    }

    public UserBody findUserBodyById(final Long id) {
        return this.userBodyRepository.findUserBodyById(id);
    }

    public UserBody create(UserBody userBody) {
        return this.userBodyRepository.saveAndFlush(userBody);
    }

    public UserBody update(UserBody userBody) {
        return this.userBodyRepository.saveAndFlush(userBody);
    }

    public void delete(Long id) {
        this.userBodyRepository.deleteById(id);
    }

}