package uni.eszterhazy.keretrendszer.exceptions;

public class FilmNotFound extends  Throwable {
    public FilmNotFound(String id){
        super(id);
    }
}
