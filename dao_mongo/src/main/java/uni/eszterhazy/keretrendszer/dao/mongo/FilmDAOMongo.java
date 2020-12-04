package uni.eszterhazy.keretrendszer.dao.mongo;

import com.mongodb.*;
import uni.eszterhazy.keretrendszer.dao.FilmDAO;
import uni.eszterhazy.keretrendszer.exceptions.FilmAlreadyAdded;
import uni.eszterhazy.keretrendszer.exceptions.FilmNotFound;
import uni.eszterhazy.keretrendszer.model.Film;
import uni.eszterhazy.keretrendszer.model.Mufaj;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

public class FilmDAOMongo implements FilmDAO {
    private MongoClient client;
    private DB db;
    private DBCollection collection;

    public FilmDAOMongo(String uri, String database, String collection) throws UnknownHostException {
        this.client = new MongoClient(new MongoClientURI(uri));
        this.db = client.getDB(database);
        this.collection = db.getCollection(collection);
    }

    public void createFilm(Film film) throws FilmAlreadyAdded {
        try {
            readFilm(film.getId());
        } catch (FilmNotFound filmNotFound) {

            collection.insert(FilmAdapter.filmToDBObject(film));
            return;
        }

        throw new FilmAlreadyAdded(film.getId());
    }

    public Collection<Film> readAllFilm() {
        DBCursor cursor = collection.find(new BasicDBObject(), new BasicDBObject().append("_id",0));
        Collection<Film> result = new ArrayList<>();
        cursor.forEach(o->{
            result.add(FilmAdapter.dbObjectFilm(o));
        });
        return result ;
    }


    public Film readFilm(String id) throws FilmNotFound {
        DBCursor cursor = collection.find(new BasicDBObject().append("id",id), new BasicDBObject().append("_id",0));
        if(cursor.length()==0){
            throw new FilmNotFound(id);
        }
        return FilmAdapter.dbObjectFilm(cursor.one());
    }

    public Collection<Film> readAllFilmByMufaj(Mufaj mufaj) {
        DBCursor cursor = collection.find(new BasicDBObject().append("mufaj",mufaj.toString()), new BasicDBObject().append("_id",0));
        Collection<Film> result = new ArrayList<>();
        cursor.forEach(o->{
            result.add(FilmAdapter.dbObjectFilm(o));
        });
        return result ;
    }


    public void updateFilm(Film film) throws FilmNotFound {
        collection.update(FilmAdapter.filmToDBObject(readFilm(film.getId())),FilmAdapter.filmToDBObject(film));
    }

    public void deleteFilm(Film film) throws FilmNotFound {

        readFilm(film.getId());
        collection.remove(FilmAdapter.filmToDBObject(film));
    }

    @Override
    public void deleteFilmById(String id) throws FilmNotFound {

        Film film = readFilm(id);
        collection.remove(FilmAdapter.filmToDBObject(film));
    }


}
