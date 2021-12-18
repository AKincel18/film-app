package com.filmapp.role.person;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("person-roles")
public class PersonRole {
    @Id
    private ObjectId id;
    private PersonRoleEnum role;

    public PersonRole(PersonRoleEnum role) {
        this.role = role;
    }
}
