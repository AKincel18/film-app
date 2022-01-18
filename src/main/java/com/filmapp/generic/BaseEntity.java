package com.filmapp.generic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class BaseEntity {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    protected ObjectId id;

    protected String name;

    public BaseEntity(String name) {
        this.name = name;
    }
}
