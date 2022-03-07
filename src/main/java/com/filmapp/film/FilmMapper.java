package com.filmapp.film;

import com.filmapp.category.Category;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

public class FilmMapper extends ModelMapper {

    public FilmMapper() {
        super();
    }

    public <D> D map(Category category, Object source, Class<D> destinationType) {
        Converter<String, Category> converter = ctx -> ctx.getSource() == null ? null : category;

        PropertyMap<FilmDto, Film> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(converter).map(source.getCategory(), destination.getCategory());
            }
        };

        TypeMap<FilmDto, Film> typeMap = this.getTypeMap(FilmDto.class, Film.class);
        if (typeMap == null) {
            this.addMappings(propertyMap);
        }
        return super.map(source, destinationType);
    }
}
