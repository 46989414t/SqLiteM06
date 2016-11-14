

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import javax.xml.transform.Transformer;
import java.io.*;
import java.net.*;

/**
 * http://api.themoviedb.org/3/movie/600/casts?api_key=18cae3b3818a484b1bf732d10321342b
 */

/**{"original_language":"en","imdb_id":"tt0093058","video":false,"title":"Full Metal Jacket","backdrop_path":"\/nfm6JCKUaM6dT0mATmShLY6cpmc.jpg",
 * "revenue":46357676,"genres":[{"name":"Drama","id":18},{"name":"War","id":10752}],"popularity":2.824746,"production_countries":[{"iso_3166_1":"GB","name":"United Kingdom"},
 * {"iso_3166_1":"US","name":"United States of America"}],"id":600,"vote_count":1237,"budget":17000000,
 * "overview":"A pragmatic U.S. Marine observes the dehumanizing effects the U.S.-Vietnam War has on his fellow recruits from their brutal boot camp training to the bloody
 * street fighting in Hue.","original_title":"Full Metal Jacket","runtime":116,"poster_path":"\/29veIwD38rVL2qY74emXQw4y25H.jpg",
 * "spoken_languages":[{"name":"English","iso_639_1":"en"},{"name":"Tiếng Việt","iso_639_1":"vi"}],"production_companies":[{"name":"Stanley Kubrick Productions","id":385},
 * {"name":"Warner Bros.","id":6194},{"name":"Natant","id":50819}],
 * "release_date":"1987-06-26","vote_average":7.6,"belongs_to_collection":null,"tagline":"Vietnam can kill me, but it can\u2019t make me care.","adult":false,"homepage":"",
 * "status":"Released"}
 */


//para el Cast: http://api.themoviedb.org/3/movie/49521/casts?api_key=###

public class themovieDBproject {

    public static void main(String[] args){
        String s = "";
        String a = "";
        String api_key = "18cae3b3818a484b1bf732d10321342b";

        for (int i = 0; i < 10; i++) {
            int peli = 600 +i;
            String film = String.valueOf(peli);
            String peticio = "https://api.themoviedb.org/3/movie/"+film+"?api_key="+api_key; //Generara la tabla de pelis
            String peticioCast = "http://api.themoviedb.org/3/movie/"+film+"/casts?api_key="+api_key;//Generara la tabla actores (los dos estaran linkados por el id de pelicula)
            try {
                s = getHTML(peticio);
                SJS(s);

                a = getHTML(peticioCast);
                SJS(a);
            } catch (Exception e) {
                System.out.println("La peli "+film+" no existeix");
            }
        }


    }

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    /**
     * Este hará los inserts en la tabla de pelis
     * @param cadena
     */
    public static void SJS (String cadena){
        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
        //System.out.println("INFORMACIO DE LA PELI: "+arra02.get("title")+" - "+ arra02.get("release_date") + " - ");
        System.out.println(arra02.get("id")+" - "+arra02.get("original_title")+" - "+arra02.get("release_date"));
        //System.out.println(arra02.get("original_title"));
        //System.out.println(arra02.get("release_date"));

    }

    /**
     * Esta funcion insertará en tabla actores
     * @param cadena
     */
     public static void isertsActores(String cadena){

     }


    public static void SJC (String cadena){
        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
        JSONArray arra03 = (JSONArray)arra02.get("cast");

        for (int i = 0; i < arra03.size(); i++) {

            JSONObject jb= (JSONObject)arra03.get(i);
            System.out.println(jb.get("character")+"<-->"+jb.get("name"));

        }

    }

}
