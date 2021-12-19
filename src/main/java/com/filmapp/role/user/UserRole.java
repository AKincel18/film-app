package com.filmapp.role.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("user-roles")
public class UserRole {
    @Id
    private ObjectId id;
    private UserRoleEnum name;

    public UserRole(UserRoleEnum name) {
        this.name = name;
    }
}
