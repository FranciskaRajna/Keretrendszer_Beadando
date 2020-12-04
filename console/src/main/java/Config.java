import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import uni.eszterhazy.keretrendszer.dao.FilmDAO;
import uni.eszterhazy.keretrendszer.dao.mongo.FilmDAOMongo;
import uni.eszterhazy.keretrendszer.model.Film;
import uni.eszterhazy.keretrendszer.service.FilmService;
import uni.eszterhazy.keretrendszer.service.impl.FilmServiceImpl;

import java.net.UnknownHostException;

@Configuration
@ComponentScan("uni.eszterhazy.keretrendszer.controller")
public class Config {

    @Bean(name = "mongoDBDAO")
    public FilmDAO mongodb(){
        try {
            return new FilmDAOMongo("mongodb://localhost:27018", "filmek","film");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Bean
    public FilmService filmService(FilmDAO mongoDBDAO){
        return new FilmServiceImpl(mongoDBDAO);
    }

}
