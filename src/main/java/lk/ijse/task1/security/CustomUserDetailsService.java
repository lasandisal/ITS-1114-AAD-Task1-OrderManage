package lk.ijse.task1.security;

import lk.ijse.task1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<lk.ijse.task1.entity.User> optionalUser = userRepository.findByName(username);

        if(optionalUser.isEmpty()) throw new UsernameNotFoundException("Cannot find " + username);

        return User.builder()
                .username(optionalUser.get().getName())
                .password(optionalUser.get().getPassword())
                .roles(String.valueOf(optionalUser.get().getRole()))
                .build();
    }
}
