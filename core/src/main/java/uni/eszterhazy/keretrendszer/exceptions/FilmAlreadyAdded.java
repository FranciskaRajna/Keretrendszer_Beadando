package uni.eszterhazy.keretrendszer.exceptions;

import uni.eszterhazy.keretrendszer.model.Film;

public class FilmAlreadyAdded extends Throwable{
    public FilmAlreadyAdded(String id) {
        super(id);
    }
}
