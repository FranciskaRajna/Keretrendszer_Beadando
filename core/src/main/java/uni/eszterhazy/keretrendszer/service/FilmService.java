package uni.eszterhazy.keretrendszer.service;

import uni.eszterhazy.keretrendszer.exceptions.FilmAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.FilmNotFound;
import uni.eszterhazy.keretrendszer.model.Film;
import uni.eszterhazy.keretrendszer.model.Mufaj;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public interface FilmService {

    void addFilm(Film film) throws FilmAlreadyAdded, IOException;
    Collection<Film> getAllFilm();
    Film getFilmById(String id) throws FilmNotFound;
    void updateFilm(Film film ) throws FilmNotFound;
    void deleteFilm(Film film ) throws FilmNotFound;
    void deleteFilmById(String id) throws FilmNotFound;
    double atlagErtekeles();
    Map<Mufaj,Double> atlagErtekelesMufajonkent();
    Collection<Film> readAllFilmByMufaj(Mufaj mufaj);

}
