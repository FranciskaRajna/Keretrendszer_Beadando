import uni.eszterhazy.keretrendszer.dao.FilmDAO;
import uni.eszterhazy.keretrendszer.dao.mongo.FilmDAOMongo;
import uni.eszterhazy.keretrendszer.exceptions.*;
import uni.eszterhazy.keretrendszer.model.Film;
import uni.eszterhazy.keretrendszer.model.Mufaj;
import uni.eszterhazy.keretrendszer.service.FilmService;
import uni.eszterhazy.keretrendszer.service.impl.FilmServiceImpl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDate;


public class Proba {
    public static  void main(String[] args) throws UnknownHostException, RosszMegjelenesDatum, RosszErtekeles, CimNemLehetNull, FilmAlreadyAdded, IOException,FilmNotFound {

        FilmDAO dao = new FilmDAOMongo("mongodb://localhost:27017", "filmek","film");
        FilmService service = new FilmServiceImpl(dao);

        Film film = new Film();
        film.setCim("Alien");
        film.setErtekeles(4.4);
        film.setMufaj(Mufaj.Horror);
        film.setMegjelenes(LocalDate.ofYearDay(1999,1));
        service.addFilm(film);
        System.out.println(service.getAllFilm());
        System.out.println(service.atlagErtekeles());
    }

}
