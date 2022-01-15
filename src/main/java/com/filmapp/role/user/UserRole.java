package com.filmapp.role.user;

import com.filmapp.generic.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document("user-roles")
public class UserRole extends BaseEntity {

    public UserRole(String name) {
        super(name);
    }
}
