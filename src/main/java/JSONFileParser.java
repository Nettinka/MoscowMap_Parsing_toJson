import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class JSONFileParser {


    public static void linesWithStationscount(String filePath) {
        HashMap<String, String> lines = new HashMap<>();

        try {
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray linesArray = (JSONArray) jsonObject.get("lines");
            for (int i = 0; i < linesArray.size(); i++) {
                JSONObject obj = (JSONObject) linesArray.get(i);
                lines.put(obj.get("number").toString(), obj.get("name").toString());
            }
            JSONArray stations = (JSONArray) jsonObject.get("stations");
            for (int i = 0; i < stations.size(); i++) {
                JSONObject oneLine = (JSONObject) stations.get(i);
                String numb = oneLine.toString().replaceAll("[^0-9a-zA-Z]", "").trim();
                JSONArray arStationsonLine = (JSONArray) oneLine.get(numb);
                System.out.println("Линия: " + numb + " " + lines.get(numb) + "\nКоличество станций: " + arStationsonLine.size());
            }

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    public static void connectionsCount(String filePath) {
        ArrayList<JSONArray> connections = new ArrayList<>();
        try {
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray connectionsArray = (JSONArray) jsonObject.get("connections");
            for (int i = 0; i < connectionsArray.size(); i++) {
                JSONArray obj = (JSONArray) connectionsArray.get(i);
                connections.add(obj);
            }
            System.out.println("Общее число пересадок: " + connections.size());

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }
}
