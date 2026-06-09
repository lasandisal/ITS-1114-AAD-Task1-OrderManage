package lk.ijse.task1.service;

import lk.ijse.task1.dto.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getUsers();
    void updateUser(UserDTO userDTO);
}
