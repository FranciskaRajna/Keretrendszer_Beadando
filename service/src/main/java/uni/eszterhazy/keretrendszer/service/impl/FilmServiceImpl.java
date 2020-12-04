package uni.eszterhazy.keretrendszer.service.impl;

import uni.eszterhazy.keretrendszer.dao.FilmDAO;
import uni.eszterhazy.keretrendszer.exceptions.FilmAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.FilmNotFound;
import uni.eszterhazy.keretrendszer.model.Film;
import uni.eszterhazy.keretrendszer.model.Mufaj;
import uni.eszterhazy.keretrendszer.service.FilmService;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class FilmServiceImpl implements FilmService{

    private FilmDAO dao;

    public FilmServiceImpl(FilmDAO dao) {
        this.dao = dao;
    }

    @Override
    public void addFilm(Film film) throws FilmAlreadyAdded, IOException {
        dao.createFilm(film);
    }
    @Override
    public Collection<Film> getAllFilm() {
      Collection<Film> result = dao.readAllFilm();
      return result;
    }
    @Override
    public Collection<Film> readAllFilmByMufaj(Mufaj mufaj) {
        return  dao.readAllFilmByMufaj(mufaj);
    }
    @Override
    public Film getFilmById(String id) throws FilmNotFound {
        return dao.readFilm(id);
    }
    @Override
    public void updateFilm(Film film) throws FilmNotFound {
        dao.updateFilm(film);
    }
    @Override
    public void deleteFilm(Film film) throws FilmNotFound {
       dao.deleteFilm(film);

    }

    @Override
    public void deleteFilmById(String id) throws FilmNotFound {
        dao.deleteFilmById(id);
    }


    @Override
    public double atlagErtekeles() {
        Collection<Film> filmek = getAllFilm();
        return filmek.stream().mapToDouble(d->d.getErtekeles()).average().getAsDouble();
    }
    @Override
    public Map<Mufaj, Double> atlagErtekelesMufajonkent() {
        return null;
    }




}
