package kz.diploma.basqaru.service.impl;

import kz.diploma.basqaru.domain.repository.UserRepository;
import kz.diploma.basqaru.model.SecurityUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByEmail(username)
                .map(SecurityUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }
}
