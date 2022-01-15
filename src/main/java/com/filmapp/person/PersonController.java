package com.filmapp.person;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("persons")
@PreAuthorize("hasRole('ROLE_MODERATOR')")
public class PersonController {

    @PostMapping
    ResponseEntity<Void> createPerson() {
        return null;
    }
}
