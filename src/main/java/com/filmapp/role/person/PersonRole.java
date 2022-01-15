package com.filmapp.role.person;

import com.filmapp.generic.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document("person-roles")
public class PersonRole extends BaseEntity {

    public PersonRole(String name) {
        super(name);
    }
}
