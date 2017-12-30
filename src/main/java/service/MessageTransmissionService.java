package main.java.service;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

/**
 * Created by User on 16.11.2017.
 */
public class MessageTransmissionService {

    public  static void sendMessage(String key, String message) throws JSONException, IOException {
        ConnectionService.getOutputStream().writeObject((new JSONObject()).put(key, message).toString());
    }

    public  static void sendMessage(JSONObject object) throws JSONException, IOException {
        ConnectionService.getOutputStream().writeObject(object.toString());
    }

    public  static String getMessage(String key) throws JSONException, IOException, ClassNotFoundException {
        return (String)(new JSONObject((String) ConnectionService.getInputStream().readObject())).get(key);
    }

    public  static JSONObject getMessage() throws JSONException, IOException, ClassNotFoundException {
        return new JSONObject((String) ConnectionService.getInputStream().readObject());
    }
}
