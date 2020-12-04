package uni.eszterhazy.keretrendszer.dao;

import uni.eszterhazy.keretrendszer.exceptions.FilmAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.FilmNotFound;
import uni.eszterhazy.keretrendszer.model.Film;
import uni.eszterhazy.keretrendszer.model.Mufaj;

import java.io.IOException;
import java.util.Collection;

public interface FilmDAO {
    void createFilm(Film film) throws FilmAlreadyAdded, IOException;
    Collection<Film> readAllFilm();
    Film readFilm(String id) throws FilmNotFound;
    void updateFilm(Film film) throws FilmNotFound;
    void deleteFilm(Film film) throws FilmNotFound;
    Collection<Film> readAllFilmByMufaj(Mufaj mufaj);


    void deleteFilmById(String id) throws FilmNotFound;
}
