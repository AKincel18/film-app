package com.filmapp.category;

import com.filmapp.generic.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document("categories")
public class Category extends BaseEntity {

    public Category(String name) {
        super(name);
    }
}
