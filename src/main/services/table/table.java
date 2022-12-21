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
    private static final int CELL_WIDTH = 20;

    public static void print(Object object) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> map = gson.fromJson(gson.toJson(object), type);

        List<String> keys = new ArrayList<>(map.keySet());
        int[] columnWidths = new int[keys.size()];

        // Determine the width of each column
        for (int i = 0; i < keys.size(); i++) {
            columnWidths[i] = Math.max(keys.get(i).length(), getCellWidth(map.get(keys.get(i))));
        }

        // Print the header row
        for (int i = 0; i < keys.size(); i++) {
            String cell = keys.get(i);
            System.out.printf("%-" + columnWidths[i] + "s", cell);
        }
        System.out.println();

        // Print the separator row
        for (int i = 0; i < keys.size(); i++) {
            for (int j = 0; j < columnWidths[i]; j++) {
                System.out.print("-");
            }
        }
        System.out.println();

        // Print the data rows
        for (int i = 0; i < keys.size(); i++) {
            String cell = getCellValue(map.get(keys.get(i)));
            System.out.printf("%-" + columnWidths[i] + "s", cell);
        }
        System.out.println();
    }

    private static int getCellWidth(Object value) {
        if (value == null) {
            return CELL_WIDTH;
        }

        if (value instanceof List) {
            return CELL_WIDTH;
        }

        return value.toString().length();
    }

    private static String getCellValue(Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof List) {
            return String.join(", ", (List) value);
        }

        return value.toString();
    }
}
