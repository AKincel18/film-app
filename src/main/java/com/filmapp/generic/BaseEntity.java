package com.filmapp.generic;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Column
    protected String name;
}
