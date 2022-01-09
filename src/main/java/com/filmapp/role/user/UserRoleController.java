package com.filmapp.role.user;

import com.filmapp.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user-roles")
public class UserRoleController extends GenericControllerImpl<UserRole> {
}
