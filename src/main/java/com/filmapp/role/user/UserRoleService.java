package com.filmapp.role.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRole findUserRole(String roleName) {
        UserRoleEnum role;
        try {
            role = UserRoleEnum.valueOf(roleName);
        } catch (IllegalArgumentException e) {
            return null;
        }
        Optional<UserRole> userRole = userRoleRepository.findByName(role);
        return userRole.orElse(null);
    }
}
