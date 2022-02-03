package com.filmapp.role.user;

import com.filmapp.role.user.excpetion.CannotAddUserRoleException;
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

    public UserRoleDto save(UserRoleDto userRoleToCreate) throws CannotAddUserRoleException {
        UserRole userRole = mapper.map(userRoleToCreate, UserRole.class);
        if (userRole == null || userRole.getName() == null) {
            throw new CannotAddUserRoleException();
        }
        UserRole savedUserRole = userRoleRepository.save(userRole);
        return mapper.map(savedUserRole, UserRoleDto.class);
    }

    public UserRoleDto update(UserRoleDto userRoleToUpdate) throws CannotAddUserRoleException {
        if (userRoleToUpdate == null)
            throw new CannotAddUserRoleException();
        Optional<UserRole> userRole = userRoleRepository.findById(userRoleToUpdate.getId());
        if (userRole.isEmpty())
            throw new CannotAddUserRoleException();
        return save(mapper.map(userRole.get(), UserRoleDto.class));
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
