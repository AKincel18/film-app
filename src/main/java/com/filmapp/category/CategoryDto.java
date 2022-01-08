package com.filmapp.category;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class CategoryDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
}
