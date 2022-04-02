package com.filmapp.person;

import com.filmapp.role.person.PersonRole;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

public class PersonMapper extends ModelMapper {

    public PersonMapper() {
        super();
        getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public <D> D map(PersonRole role, Object source, Class<D> destinationType) {
        Converter<String, PersonRole> converter = ctx -> ctx.getSource() == null ? null : role;

        PropertyMap<PersonDto, Person> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(converter).map(source.getPersonRole(), destination.getPersonRole());
            }
        };

        TypeMap<PersonDto, Person> typeMap = this.getTypeMap(PersonDto.class, Person.class);
        if (typeMap == null) {
            this.addMappings(propertyMap);
        }
        return super.map(source, destinationType);
    }
}
