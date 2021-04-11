package org.example.controller;

import com.github.dozermapper.core.Mapper;
import com.sun.istack.NotNull;
import org.example.data.dto.LoginDto;
import org.example.data.dto.RegistrationDto;
import org.example.data.entity.Role;
import org.example.data.entity.Status;
import org.example.data.entity.Person;
import org.example.security.JwtProvider;
import org.example.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    final PersonService personService;
    final Mapper dozerMapper;
    final PasswordEncoder passwordEncoder;
    final JwtProvider jwtProvider;

    public AuthController(PersonService personService, Mapper dozerMapper, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.personService = personService;
        this.dozerMapper = dozerMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup( @NotNull @RequestBody RegistrationDto registrationDto){

        Optional<Person> checkingUser = personService.findByLogin(registrationDto.getLogin());

        if(checkingUser.isPresent()){
            return new ResponseEntity<>(String.format("Login %s already exists", registrationDto.getLogin()), HttpStatus.CONFLICT);
        }

        Person person = Person.builder()
                .login(registrationDto.getLogin())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .name(registrationDto.getName())
                .surname(registrationDto.getSurname())
                .role(Role.ROLE_USER)
                .status(Status.ACTIVE)
                .build();

        personService.save(person);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){

        Optional<Person> user = personService.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());

        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(jwtProvider.generateToken(user.get().getLogin(), user.get().getRole()), HttpStatus.OK);
    }
}
