package common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CommonMethods {

    public static List<HashMap<String, String>> jsonFileToListOfHashMaps(File file) {
        Gson gson = new Gson();
        return gson.fromJson(
                jsonFileToString(file),
                new TypeToken<List<HashMap<String, String>>>() {
                }
                        .getType());
    }

    private static String jsonFileToString(File file) {
        String json = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String generateString(){
        byte[] bytes = new byte[20];
        new Random().nextBytes(bytes);
//        return new String(bytes, Charset.forName("UTF-8"));
        return "aaa";
    }
}
