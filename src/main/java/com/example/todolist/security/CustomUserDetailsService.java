package com.example.todolist.security;

import com.example.todolist.model.User;
import com.example.todolist.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Adjust to match the repository method you actually have.
        // If your UserRepository has findByUsername, use it. Otherwise, create it.
        User user = userRepo.findByUsername(username)
        		.orElseThrow(new java.util.function.Supplier<UsernameNotFoundException>() {
        		    @Override
        		    public UsernameNotFoundException get() {
        		        return new UsernameNotFoundException("User not found: " + username);
        		    }
        		});

        return new CustomUserDetails(user);
    }
}
