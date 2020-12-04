package uni.eszterhazy.keretrendszer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import uni.eszterhazy.keretrendszer.exceptions.FilmAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.FilmNotFound;
import uni.eszterhazy.keretrendszer.exceptions.RosszMegjelenesDatum;
import uni.eszterhazy.keretrendszer.model.Film;
import uni.eszterhazy.keretrendszer.model.Mufaj;
import uni.eszterhazy.keretrendszer.service.FilmService;

import java.io.IOException;

@Controller
public class FilmController {
    @Autowired
    @Qualifier("filmService")
    FilmService service;

    @ModelAttribute(value = "film")
    public  Film create(){
        return new  Film();
    }

    @GetMapping(value = "/filmek")
    public ModelAndView getFilmek(){
        ModelAndView mav = new ModelAndView("filmlist.jsp");
        System.out.println(service.getAllFilm());
        mav.addObject("filmek", service.getAllFilm());
        return mav;
    }
    @GetMapping(value = "/film/{id}")
    public String getFilmById(@PathVariable String id, Model model) throws FilmNotFound {
        model.addAttribute("film", service.getFilmById(id));
        return "filmdetails.jsp";
    }
    @GetMapping(value = "/listByMufaj")
    public String listByMufajForm(Model model){
        model.addAttribute("mufajok", Mufaj.values());
        return "filmlist.jsp";
    }
    @GetMapping(value="/listFilmByMufaj")
    public ModelAndView listFilmByMufaj(@ModelAttribute("mufaj") Mufaj mufaj){
        ModelAndView mav = new ModelAndView("listByMufaj.jsp");
        mav.addObject("filmek", service.readAllFilmByMufaj(mufaj));
        System.out.println(service.readAllFilmByMufaj(mufaj));
        return mav;
    }

    @GetMapping(value = "search")
    public String searchForm(Model model){

        return "searchform.jsp";
    }



    @GetMapping(value = "addFilm")
    public String addFilmForm(Model model){
        model.addAttribute("mufajok", Mufaj.values());
        return "filmform.jsp";
    }
    @PostMapping(value = "addFilm")
    public String addFilm(@ModelAttribute("film") Film film, Model model){
        System.out.println(film);
        try {
            service.addFilm(film);
        } catch (FilmAlreadyAdded |  IOException e) {
            throw new ResponseStatusException(HttpStatus.IM_USED,"Film az adott azonosítóval már létezik: "+ e.getMessage());
        }
        return "redirect:film/"+film.getId();
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView removeFilm(@PathVariable String id) {
        try {
            service.deleteFilm(service.getFilmById(id));
        } catch (FilmNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Film címe nem lehet null:  " + e.getMessage());
        }
        ModelAndView mav = new ModelAndView("filmlist.jsp");
        System.out.println(service.getAllFilm());
        mav.addObject("filmek", service.getAllFilm());
        return mav;
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView updateFilm(@ModelAttribute("film") Film film){
        System.out.println(film);

        try {
            service.updateFilm(film);
        } catch (FilmNotFound  e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Film az adott azonosítóval nem található: "+ e.getMessage());
        }


        ModelAndView mav = new ModelAndView("filmlist.jsp");
        System.out.println(service.getAllFilm());
        mav.addObject("filmek", service.getAllFilm());
        return mav;
    }

    @GetMapping(value = "/edit/{id}")
    public String getFilmByIdForm(@PathVariable String id, Model model) throws FilmNotFound {
        model.addAttribute("film", service.getFilmById(id));
        return "filmupdate.jsp";
    }

}


