package uni.eszterhazy.keretrendszer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uni.eszterhazy.keretrendszer.exceptions.CimNemLehetNull;
import uni.eszterhazy.keretrendszer.exceptions.FilmAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.FilmNotFound;
import uni.eszterhazy.keretrendszer.model.Film;
import uni.eszterhazy.keretrendszer.model.Mufaj;
import uni.eszterhazy.keretrendszer.service.FilmService;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping(value = "/rest/")
public class RESTFilmController {
    @Autowired
    FilmService filmService;

    @GetMapping(value = "filmek")
    public Collection<Film> getAllFilm(){
        return filmService.getAllFilm();
    }

    @GetMapping(value = "film/{id:[A-Za-z0-9]{8}-[A-Za-z0-9]{4}-[A-Za-z0-9]{4}-[A-Za-z0-9]{4}-[A-Za-z0-9]{12}}")
    public Film getFilmById(@PathVariable(name ="id")String id)  {
        try{
            return filmService.getFilmById(id);
        }
        catch (FilmNotFound e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Dolgozo az adott azonosítóval nem található: "+ e.getMessage());
        }
    }

    /*
    @GetMapping(value = "filmek")
    public Collection<Film> getAllFilmOfMufaj(@RequestParam(value = "mufaj",required = false)Mufaj mufaj){
        if(mufaj != null ){
            return filmService.readAllFilmByMufaj(mufaj);
        }
        return  filmService.getAllFilm();
    }
*/
    @PostMapping(value = "filmek", consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/json;charset=utf-8")
    public String addFilm(@RequestBody Film film) throws IOException, FilmAlreadyAdded {
        filmService.addFilm(film);
        return "Új film került hozzádásra a következő azonosítóval: "+film.getId();

    }
    /*
    @DeleteMapping(value = "delete",produces = "application/json;charset=utf-8")
    public String deleteFilm(@RequestBody Film film) throws FilmNotFound {
        filmService.deleteFilm(film);
        return "A következő azonosítóval ellátott film törölve: "+film.getId();
    }
    */
    @DeleteMapping(value = "/delete/{id}")
    public void removeFilm(@PathVariable String id) throws FilmNotFound {

        filmService.deleteFilm(filmService.getFilmById(id));
    }



    @ExceptionHandler(FilmNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String unknownFilmId(FilmNotFound e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Film az adott azonosítóval nem található: "+ e.getMessage());
        //  return "Film az adott azonosítóval nem található: "+ e.getMessage();
    }

    @ExceptionHandler(CimNemLehetNull.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String wronglength(CimNemLehetNull e){
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Film címe nem lehet null:  " + e.getMessage());
        //return "Film címe nem lehet null:  " + e.getMessage();
    }

    @ExceptionHandler(FilmAlreadyAdded.class)
    @ResponseStatus(HttpStatus.IM_USED)
    public String usedFilmId(FilmAlreadyAdded e){
       // throw new ResponseStatusException(HttpStatus.IM_USED,"Film az adott azonosítóval már létezik: "+ e.getMessage());
         return "Film az adott azonosítóval már létezik: "+ e.getMessage();
    }



}
