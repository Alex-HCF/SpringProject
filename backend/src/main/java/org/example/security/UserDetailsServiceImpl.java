package org.example.security;

import org.example.data.entity.Status;
import org.example.data.entity.Person;
import org.example.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonService personService;

    public UserDetailsServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return SecurityUser.builder()
                .username(person.getLogin())
                .password(person.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority(person.getRole().toString())))
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(!person.getStatus().equals(Status.BANNED))
                .isEnabled(person.getStatus().equals(Status.ACTIVE))
                .build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        return this;
    }
}
