import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserJsonToClass {
    private static final String fileName = "new_data.json";

    private static List<Employee> jsonToList(String json) throws ParseException {

        List<Employee> employees = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        JSONArray lang = (JSONArray) jsonParser.parse(json);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        for (Object jsonObj : lang) {

            Employee employee = gson.fromJson(jsonObj.toString(), Employee.class);
            employees.add(employee);
        }

        return employees;
    }

    private static String readString(String file) {
        String line;
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json.toString().replaceAll("\\s+", "");
    }

    public static void main(String[] args) throws ParseException {

        List<Employee> list = jsonToList(readString(fileName));

        list.forEach((value) -> System.out.println("Employee" + value.toString()));

    }
}
