package com.filmapp.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("categories")
public class Category {
    @Id
    private ObjectId id;
    private CategoryEnum name;

    public Category(CategoryEnum name) {
        this.name = name;
    }
}
