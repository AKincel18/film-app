package com.filmapp.role.person;

import com.filmapp.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/person-roles")
public class PersonRoleController extends GenericControllerImpl<PersonRole> {
}
