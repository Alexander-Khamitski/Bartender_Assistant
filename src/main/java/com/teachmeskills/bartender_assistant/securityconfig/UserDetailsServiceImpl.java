package com.teachmeskills.bartender_assistant.securityconfig;

import java.util.HashSet;
import java.util.Set;

import com.teachmeskills.bartender_assistant.entity.User;
import com.teachmeskills.bartender_assistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
            grantedAuthoritySet.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthoritySet);
        } else {
            throw new UsernameNotFoundException("User with login " + login + " does not exist!");
        }
    }
}
