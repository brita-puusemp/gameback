package ee.avastaeesti.gameback.service.user;

import ee.avastaeesti.gameback.controller.user.dto.NewUser;
import ee.avastaeesti.gameback.persistence.role.Role;
import ee.avastaeesti.gameback.persistence.role.RoleRepository;
import ee.avastaeesti.gameback.persistence.user.User;
import ee.avastaeesti.gameback.persistence.user.UserMapper;
import ee.avastaeesti.gameback.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public void addNewUser(NewUser newUser) {
        Role role = roleRepository.getReferenceById(2);
        User user = userMapper.toUser(newUser);
        user.setRole(role);
        userRepository.save(user);
    }
}
