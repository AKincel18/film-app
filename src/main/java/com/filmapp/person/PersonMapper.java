package com.filmapp.person;

import com.filmapp.role.person.PersonRole;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

public class PersonMapper extends ModelMapper {

    public PersonMapper() {
        super();
    }

    public <D> D map(PersonRole personRole, Object source, Class<D> destinationType) {
        Converter<String, PersonRole> converter = ctx -> ctx.getSource() == null ? null : personRole;

        PropertyMap<PersonDto, Person> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(converter).map(source.getRole(), destination.getRole());
            }
        };

        TypeMap<PersonDto, Person> typeMap = this.getTypeMap(PersonDto.class, Person.class);
        if (typeMap == null) {
            this.addMappings(propertyMap);
        }
        return super.map(source, destinationType);
    }
}
