package org.example.utils;

import org.example.data.entity.Person;
import org.example.service.PersonService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class PersonUtils {

    private final PersonService personService;

    public PersonUtils(PersonService personService) {
        this.personService = personService;
    }

    public Person getCurrentPerson(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return personService.findByUserDetails(principal).get();
    }
}
