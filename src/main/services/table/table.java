package main.services.table;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
public class table {
    public static void print(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        List<Field> fields = Arrays.asList(object.getClass().getDeclaredFields());

        // Print the table header
        System.out.print("|");
        fields.forEach(field -> System.out.printf(" %s |", field.getName()));
        System.out.println();

        // Print the table row
        System.out.print("|");
        fields.forEach(field -> {
            try {
                field.setAccessible(true);
                if(field.get(object) == null) {
                    System.out.printf(" %s |", "null");
                }else {
                    System.out.printf(" %s |", field.get(object).toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        System.out.println();
        System.out.println();
    }
}
