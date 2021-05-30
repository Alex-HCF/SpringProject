package org.example.service;

import org.example.data.dto.PersonInfoDto;
import org.example.data.dto.RegistrationDto;
import org.example.data.dto.UpdatePersonDto;
import org.example.data.entity.Person;
import org.example.data.entity.Role;
import org.example.data.entity.Status;
import org.example.exception.EntityAlreadyExists;
import org.example.exception.EntityNotFound;
import org.example.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person create(RegistrationDto registrationDto){
        Optional<Person> checkingUser = personRepository.findByLogin(registrationDto.getLogin());

        if(checkingUser.isPresent()){
            throw new EntityAlreadyExists(Person.class, registrationDto);
        }

        Person person = Person.builder()
                .login(registrationDto.getLogin())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .name(registrationDto.getName())
                .surname(registrationDto.getSurname())
                .role(Role.ROLE_USER)
                .status(Status.ACTIVE)
                .build();

        return save(person);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(()-> new EntityNotFound(Person.class, id));
    }

    public Person findByLogin(String login) {
        return personRepository.findByLogin(login).orElseThrow(()-> new EntityNotFound(Person.class, login));
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return personRepository.existsById(id);
    }

    public Optional<Person> findByLoginAndPassword(String login, String password) {
        Person person = personRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFound(Person.class, login));

        if (passwordEncoder.matches(password, person.getPassword())) {
            return Optional.of(person);
        }

        return Optional.empty();
    }

    public Person findByUserDetails(UserDetails userDetails) {
        String login = userDetails.getUsername();
        return findByLogin(login);
    }


    public Integer countNotesForPersonId(Long id){
        return personRepository.findCountNotesForPerson(id);
    }

    public Integer countOpenNotesForPersonId(Long id){
        return personRepository.findCountOpenNotesForPerson(id);
    }

    public List<Person> personsFromIdRange(Long startId, Long endId){
        return personRepository.findPersonsByRange(startId, endId);
    }

    public Integer countMessages(Long id) {
        return personRepository.countMessagesByPerson(id);
    }

    public List<Person> findPersonsByIdRange(Long startId, Long endId) {
        return personRepository.findPersonsByRange(startId, endId);
    }

    @Transactional
    public Person disable(Long id) {
        Person person = personRepository.findById(id).orElseThrow(()-> new EntityNotFound(Person.class, id));
        person.setStatus(Status.DISABLED);
        return personRepository.save(person);
    }
    @Transactional
    public Person ban(Long id) {
        Person person = personRepository.findById(id).orElseThrow(()-> new EntityNotFound(Person.class, id));
        person.setStatus(Status.BANNED);
        return personRepository.save(person);
    }
    @Transactional
    public Person activate(Long id) {
        Person person = personRepository.findById(id).orElseThrow(()-> new EntityNotFound(Person.class, id));
        person.setStatus(Status.ACTIVE);
        return personRepository.save(person);
    }

    public Person update(Long id, UpdatePersonDto updatePersonDto) {
        Person person = findById(id);

        if(updatePersonDto.getLogin() != null)    person.setLogin(updatePersonDto.getLogin());
        if(updatePersonDto.getPassword() != null) person.setPassword(passwordEncoder.encode(updatePersonDto.getPassword()));
        if(updatePersonDto.getName() != null)     person.setName(updatePersonDto.getName());
        if(updatePersonDto.getName() != null)     person.setSurname(updatePersonDto.getSurname());
        if(updatePersonDto.getStatus() != null)   person.setStatus(updatePersonDto.getStatus());
        if(updatePersonDto.getRole() != null)     person.setRole(updatePersonDto.getRole());

        return personRepository.save(person);
    }
}
