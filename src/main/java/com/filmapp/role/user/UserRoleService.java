package com.filmapp.role.user;

import com.filmapp.generic.GenericServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService extends GenericServiceImpl<UserRole> {

    private final UserRoleRepository userRoleRepository;

    public UserRole findUserRole(String roleName) {
        try {
            UserRoleEnum.valueOf(roleName);
        } catch (IllegalArgumentException e) {
            return null;
        }
        Optional<UserRole> userRole = userRoleRepository.findByName(roleName);
        return userRole.orElse(null);
    }
}
