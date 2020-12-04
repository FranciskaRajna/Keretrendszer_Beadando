package uni.eszterhazy.keretrendszer.dao.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import uni.eszterhazy.keretrendszer.model.Film;
import uni.eszterhazy.keretrendszer.model.Mufaj;


import java.io.IOException;

public class FilmAdapter {

    private static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());


        public static Film dbObjectFilm(DBObject film){
            try {
                Film obj = mapper.readValue(film.toString(),Film.class);
                return obj;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

    public static DBObject filmToDBObject(Film film){
        String filmString= "";
        try {
            filmString = mapper.writeValueAsString(film);
            BasicDBObject obj = mapper.readValue(filmString, BasicDBObject.class);
            return obj;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BasicDBObject();
    }
}
