import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.HashSet;

public class JSONWriter {


    public static void putToJsonlines(JSONArray ar, ArrayList<Lines> lines) {
        //создание множества объектов пары номер линии-название линии
        for (Lines li : lines) {
            JSONObject linespec = new JSONObject();
            linespec.put("number", li.getLineNumber());
            linespec.put("name", li.getLineNames());
            ar.add(linespec);
        }

    }

    public static void putToJsonStations(JSONArray ar, ArrayList<Stations> stations) {
        //создание множества объектов номер линии - названия всех станций на линии
        for (Stations st : stations) {
            JSONArray arstations = new JSONArray();
            JSONObject stationOb = new JSONObject();

            String[] nameSt = st.getStationName().split(",");
            for (int i = 0; i < nameSt.length; i++) {
                arstations.add(nameSt[i].trim());
            }

            stationOb.put(st.lineNameStationOn, arstations);
            ar.add(stationOb);
        }

    }


    public static void putToJsonConnections(JSONArray array, HashSet<Connections> connections) {

        for (Connections con : connections) {
            JSONArray oneConnection = new JSONArray();
            JSONObject connectionFrom = new JSONObject();
            JSONObject connectionTo = new JSONObject();
            connectionFrom.put("line", con.getFirstLineNumber());
            connectionFrom.put("station", con.getFirstStationName());
            connectionTo.put("line", con.getSecondLineNumber());
            connectionTo.put("station", con.getSecondStationName());
            oneConnection.add(connectionFrom);
            oneConnection.add(connectionTo);
            array.add(oneConnection);
        }


    }
}
