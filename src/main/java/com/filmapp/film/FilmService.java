package com.filmapp.film;

import com.filmapp.category.Category;
import com.filmapp.category.CategoryService;
import com.filmapp.category.exceptions.CategoryNotExistException;
import com.filmapp.commons.exception.NotExistException;
import com.filmapp.commons.pagination.PaginationResult;
import com.filmapp.dictionary.DictionaryType;
import com.filmapp.film.exception.FilmNotExistException;
import com.filmapp.film.payload.CreateFilmRequest;
import com.filmapp.film.payload.UpdateFilmRequest;
import com.filmapp.person.Person;
import com.filmapp.person.PersonService;
import com.filmapp.person.exception.PersonNotExistsException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final CategoryService categoryService;
    private final PersonService personService;
    private final FilmMapper mapper;

    public FilmService(FilmRepository filmRepository, CategoryService categoryRepository, PersonService personService) {
        this.filmRepository = filmRepository;
        this.categoryService = categoryRepository;
        this.personService = personService;
        this.mapper = new FilmMapper();
    }

    public PaginationResult<FilmDto> getPaginatedFilms(int pageSize, int pageIndex) {
        Sort orderByNameAsc = Sort.by(Sort.Order.asc("name"));
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, orderByNameAsc);
        List<FilmDto> films = filmRepository.findAll(pageRequest)
                .stream()
                .map(f -> mapper.map(f.getCategory(), f, FilmDto.class))
                .collect(Collectors.toList());
        long sizeAll = filmRepository.findAll().size();
        return new PaginationResult<>(films, sizeAll);
    }

    public FilmDto createFilm(CreateFilmRequest request) throws NotExistException {
        if (request.getCategoryId() == null)
            throw new CategoryNotExistException();
        Category category = categoryService.findById(request.getCategoryId(), DictionaryType.CATEGORY);

        if (request.getDirectorId() == null)
            throw new PersonNotExistsException();
        Person person = personService.findPersonById(request.getDirectorId());

        Film filmToSave = mapper.map(request, Film.class);
        filmToSave.setCategory(category);
        filmToSave.setDirector(person);

        Film filmSaved = filmRepository.save(filmToSave);
        return mapper.map(filmSaved, FilmDto.class);
    }

    public FilmDto findFilmDtoById(Long id) throws FilmNotExistException {
        return mapper.map(findFilmById(id), FilmDto.class);
    }

    public Film findFilmById(Long id) throws FilmNotExistException {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isEmpty())
            throw new FilmNotExistException();
        return film.get();
    }

    public List<FilmDto> findFilmsByCategory(Long id) throws NotExistException {
        Category category = categoryService.findById(id, DictionaryType.CATEGORY);
        return filmRepository.findFilmsByCategory(category)
                        .stream()
                        .map(f -> mapper.map(f, FilmDto.class))
                        .collect(Collectors.toList());
    }

    public List<FilmDto> findFilmsByCurrentMonth() {
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        return findFilmsByMonth(year, month);
    }

    public List<FilmDto> findFilmsByMonth(int year, int month) {
        LocalDate dateFrom = LocalDate.of(year, month, 1);
        LocalDate dateTo = LocalDate.of(year, month, 1).with(lastDayOfMonth());
        return findFilmsByReleaseDateBetween(dateFrom, dateTo);
    }

    public List<FilmDto> findFilmsByDates(LocalDate dateFrom, LocalDate dateTo) {
        return findFilmsByReleaseDateBetween(dateFrom, dateTo);
    }

    private List<FilmDto> findFilmsByReleaseDateBetween(LocalDate dateFrom, LocalDate dateTo) {
        return filmRepository.findFilmsByReleaseDateBetween(dateFrom, dateTo)
                .stream()
                .map(f -> mapper.map(f, FilmDto.class))
                .collect(Collectors.toList());
    }

    public FilmDto updateFilm(UpdateFilmRequest request) throws NotExistException {
        if (request.getId() == null)
            throw new FilmNotExistException();
        Film film = findFilmById(request.getId());

        if (request.getName() != null) {
            film.setName(request.getName());
        }
        if (request.getReleaseDate() != null) {
            film.setReleaseDate(request.getReleaseDate());
        }
        if (request.getRunningTime() != null) {
            film.setRunningTime(request.getRunningTime());
        }
        if (request.getBudget() != null) {
            film.setBudget(request.getBudget());
        }
        if (request.getCategoryId() != null) {
            Category category = categoryService.findById(request.getCategoryId(), DictionaryType.CATEGORY);
            film.setCategory(category);
        }
        if (request.getDirectorId() != null) {
            Person director = personService.findPersonById(request.getDirectorId());
            film.setDirector(director);
        }
        return mapper.map(filmRepository.save(film), FilmDto.class);
    }

    public void deleteFilm(Long id) throws FilmNotExistException {
        Film film = findFilmById(id);
        filmRepository.delete(film);
    }
}
