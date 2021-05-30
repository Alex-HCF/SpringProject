package org.example.controller;

import com.sun.istack.NotNull;
import org.example.data.dto.LoginDto;
import org.example.data.dto.PersonCreatedDto;
import org.example.data.dto.RegistrationDto;
import org.example.data.entity.Person;
import org.example.data.entity.Status;
import org.example.data.mapper.PersonMapper;
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

    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final PersonMapper personMapper;

    public AuthController(PersonService personService, PasswordEncoder passwordEncoder,
                          JwtProvider jwtProvider, PersonMapper personMapper) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.personMapper = personMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup( @NotNull @RequestBody RegistrationDto registrationDto){

        Person createdPerson = personService.create(registrationDto);
        PersonCreatedDto personCreatedDto = personMapper.personToPersonCreatedDto(createdPerson);

        return new ResponseEntity<>(personCreatedDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){

        Optional<Person> user = personService.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());

        if(user.isEmpty()){
            return new ResponseEntity<>("Bad credentials", HttpStatus.UNAUTHORIZED);
        } else if (!user.get().getStatus().equals(Status.ACTIVE)){
            return new ResponseEntity<>("Account is disabled", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(jwtProvider.generateToken(user.get().getLogin(), user.get().getRole()), HttpStatus.OK);
    }
}
