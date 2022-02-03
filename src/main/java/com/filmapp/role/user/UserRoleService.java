package com.filmapp.role.user;

import com.filmapp.role.user.excpetion.CannotAddUserRoleException;
import com.filmapp.role.user.payload.CreateUserRoleRequest;
import com.filmapp.role.user.payload.UpdateUserRoleRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final ModelMapper mapper;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.mapper = new ModelMapper();
    }

    public List<UserRoleDto> getAll() {
        return userRoleRepository.findAll()
                .stream()
                .map(c -> mapper.map(c, UserRoleDto.class))
                .collect(Collectors.toList());
    }

    public UserRoleDto save(CreateUserRoleRequest request) throws CannotAddUserRoleException {
        UserRole userRole = mapper.map(request, UserRole.class);
        if (userRole == null || userRole.getName() == null) {
            throw new CannotAddUserRoleException();
        }
        UserRole savedUserRole = userRoleRepository.save(userRole);
        return mapper.map(savedUserRole, UserRoleDto.class);
    }

    public UserRoleDto update(UpdateUserRoleRequest request) throws CannotAddUserRoleException {
        if (request == null)
            throw new CannotAddUserRoleException();
        UserRole userRole = findUserRole(request.getName());
        if (userRole == null) {
            throw new CannotAddUserRoleException();
        }
        return mapper.map(userRoleRepository.save(userRole), UserRoleDto.class);
    }

    public boolean delete(Long id) {
        Optional<UserRole> userRole = userRoleRepository.findById(id);
        if (userRole.isEmpty())
            return false;

        userRoleRepository.delete(userRole.get());
        return true;
    }

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
