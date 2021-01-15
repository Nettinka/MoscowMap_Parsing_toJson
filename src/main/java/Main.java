import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;


public class Main {

    private static String url = "https://www.moscowmap.ru/metro.html#lines";


    public static void main(String[] args) throws IOException {
        String filePath = "data/mosMap.json";
        JSONObject fullMap = new JSONObject();
        JSONArray linesAr = new JSONArray();
        JSONArray stationAr = new JSONArray();
        JSONArray connecAr = new JSONArray();
        ArrayList<Stations> stations = new ArrayList<>();
        ArrayList<Lines> lines = new ArrayList<>();
        ArrayList<Connections> connections = new ArrayList<>();
        HashSet<Connections> connectionsSet = new HashSet<>();
        WEBParser webParser = new WEBParser();
        JSONFileParser jsonParser = new JSONFileParser();

        webParser.linesParsing(lines);
        webParser.stationsParsing(stations);
        webParser.connectionParsingtoSet(connectionsSet);


        JSONWriter jsonWriter = new JSONWriter();
        jsonWriter.putToJsonlines(linesAr, lines);
        jsonWriter.putToJsonStations(stationAr, stations);
        jsonWriter.putToJsonConnections(connecAr, connectionsSet);


        fullMap.put("lines", linesAr);
        fullMap.put("stations", stationAr);
        fullMap.put("connections", connecAr);

        FileWriter file2 = new FileWriter(filePath);
        file2.write(fullMap.toJSONString());
        file2.flush();

        jsonParser.linesWithStationscount(filePath);
        jsonParser.connectionsCount(filePath);
    }


}

