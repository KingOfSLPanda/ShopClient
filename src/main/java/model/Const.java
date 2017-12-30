package main.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 03.12.2017.
 */
public class Const {

    public final static List<String> GENDER = new ArrayList<String>(){
        {
            add("мужской");
            add("женский");
        }
    };

    public final static List<String> ROLE = new ArrayList<String>(){
        {
            add("администратор");
            add("пользователь");
        }
    };
}
