package ee.avastaeesti.gameback.service.user;

import ee.avastaeesti.gameback.controller.user.dto.NewUser;
import ee.avastaeesti.gameback.infrastructure.exception.ForbiddenException;
import ee.avastaeesti.gameback.persistence.role.Role;
import ee.avastaeesti.gameback.persistence.role.RoleRepository;
import ee.avastaeesti.gameback.persistence.user.User;
import ee.avastaeesti.gameback.persistence.user.UserMapper;
import ee.avastaeesti.gameback.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static ee.avastaeesti.gameback.infrastructure.Error.EMAIL_EXISTS;
import static ee.avastaeesti.gameback.infrastructure.Error.USERNAME_EXISTS;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public void addNewUser(NewUser newUser) {

        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new ForbiddenException(USERNAME_EXISTS.getMessage(), USERNAME_EXISTS.getErrorCode());
        }
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new ForbiddenException(EMAIL_EXISTS.getMessage(), EMAIL_EXISTS.getErrorCode());
        }

        Role role = roleRepository.getReferenceById(2);
        User user = userMapper.toUser(newUser);
        user.setRole(role);
        userRepository.save(user);
    }
}
