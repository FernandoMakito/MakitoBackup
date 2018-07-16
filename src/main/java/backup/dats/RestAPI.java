/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup.dats;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.File;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author fernando
 */
public class RestAPI {


    public RestAPI() {
//        try {
//            this.host = host;
//            setRetorno(sendRequest());
//        } catch (UnirestException ex) {
//            Logger.getLogger(RestAPI.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public JSONObject enviaGet(String host) throws UnirestException {
        return sendGetRequest(host);
    }

    private JSONObject sendGetRequest(String host) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.get(host)
                .basicAuth("admin", "serveralac432")
                .header("accept", "application/json")
                .asJson();
        return jsonResponse.getBody().getObject();
    }

    private JSONObject sendPostRequest(String host, Map<String, Object> parameters) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post(host)
                .header("accept", "application/json")
                //.fields(Map<String, Object> parameters)
                //.field("file", new File("/tmp/file"))
                .asJson();
        return jsonResponse.getBody().getObject();
    }
}
