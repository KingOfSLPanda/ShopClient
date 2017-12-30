package main.java.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.controller.SupportController;
import main.java.model.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 27.11.2017.
 */
public class UserService {

    public ObservableList<User> getAllUsers() throws JSONException, IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "getUsers");
        MessageTransmissionService.sendMessage(object);
        String request = MessageTransmissionService.getMessage("request");
        List<User> users = getUsersFromJSON(request);
        return FXCollections.observableArrayList(users);
    }

    public String deleteUser(int id) throws JSONException, IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "deleteUser");
        object.put("idUser", String.valueOf(id));
        MessageTransmissionService.sendMessage(object);
        if(!MessageTransmissionService.getMessage("request").equals("true")){
            (new SupportController()).showMessage("Ошибка удаления.");
        }
        return "../../view/users.fxml";
    }

    public List<User> getUsersFromJSON(String string) throws JSONException {
        JSONObject object = new JSONObject(string);
        List<User> users = new ArrayList<User>();
        for (int i=0; i < Integer.parseInt((String) object.get("sizeUser")); i++){
            User user = new User();
            user.setId(Integer.parseInt((String) object.get("idUser" + String.valueOf(i))));
            user.setUsername((String) object.get("userNameUser" + String.valueOf(i)));
            user.setPassword((String) object.get("passwordUser" + String.valueOf(i)));
            user.setFirstName((String) object.get("firstNameUser" + String.valueOf(i)));
            user.setLastName((String) object.get("lastNameUser" + String.valueOf(i)));
            user.setEmail((String) object.get("emailUser" + String.valueOf(i)));
            user.setRole((String) object.get("roleUser" + String.valueOf(i)));
            user.setAge(Integer.parseInt((String) object.get("ageUser" + String.valueOf(i))));
            user.setAdress((String) object.get("adressUser" + String.valueOf(i)));
            user.setGender((String) object.get("genderUser" + String.valueOf(i)));
            users.add(user);
        }
        return users;
    }

    public JSONObject convertUserToJSONobject(User user) throws JSONException {
        JSONObject object =  new JSONObject();
        object.put("id", String.valueOf(user.getId()));
        object.put("userName", user.getUsername());
        object.put("password", user.getPassword());
        object.put("firstName", user.getFirstName());
        object.put("lastName", user.getLastName());
        object.put("email", user.getEmail());
        object.put("adress", user.getAdress());
        object.put("role", user.getRole());
        object.put("gender", user.getGender());
        object.put("age", String.valueOf(user.getAge()));
        return object;
    }

    public JSONObject convertUserFieldToJSONobject(String userName, String password, String firstName, String lastName,
                                                   String email, String age, String adress, String gender) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("id", "0");
        object.put("userName", userName);
        object.put("password", password);
        object.put("firstName", firstName);
        object.put("lastName", lastName);
        object.put("email", email);
        object.put("age", age);
        object.put("role", "user");
        object.put("adress", adress);
        object.put("gender", gender);
        return object;
    }

    public User getUser(JSONObject object) throws JSONException {
        User user = new User();
        user.setId(Integer.parseInt((String)object.get("id")));
        user.setUsername((String)object.get("userName"));
        user.setPassword((String)object.get("password"));
        user.setFirstName((String)object.get("firstName"));
        user.setLastName((String)object.get("lastName"));
        user.setEmail((String)object.get("email"));
        user.setRole((String)object.get("role"));
        user.setAdress((String)object.get("adress"));
        user.setGender((String)object.get("gender"));
        user.setAge(Integer.parseInt((String)object.get("age")));
        return user;
    }
}
