package lk.ijse.task1.service.impl;

import lk.ijse.task1.dto.UserDTO;
import lk.ijse.task1.entity.User;
import lk.ijse.task1.repository.UserRepository;
import lk.ijse.task1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        log.info("UserServiceImpl saveUser");
        try {
            if (userDTO.getName() == null) {
                throw new RuntimeException("user name is null");
            }
            if (userDTO.getRole() == null) {
                throw new RuntimeException("user role is null");
            }
            User user = new User();
            user.setName(userDTO.getName());
            user.setRole(userDTO.getRole());
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDTO> getUsers() {
        log.info("UserServiceImpl getUsers");
        try {
            List<User> users = userRepository.findAll();
            List<UserDTO> userDTOS = new ArrayList<>();
            for (User user : users) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getId());
                userDTO.setName(user.getName());
                userDTO.setRole(user.getRole()); // Mapped the forgotten role
                userDTOS.add(userDTO);
            }
            return userDTOS;
        } catch (Exception e) {
            log.error("Error retrieving users: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        log.info("UserServiceImpl updateUser");
        try {
            if (userDTO.getId() == null) {
                throw new RuntimeException("user id is null");
            }
            if (userDTO.getName() == null) {
                throw new RuntimeException("user name is null");
            }
            if (userDTO.getRole() == null) {
                throw new RuntimeException("user role is null");
            }
            Optional<User> optionalUser = userRepository.findById(userDTO.getId());
            if (!optionalUser.isPresent()) {
                throw new RuntimeException("user not found");
            }
            User user = optionalUser.get();
            user.setName(userDTO.getName());
            user.setRole(userDTO.getRole());
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}