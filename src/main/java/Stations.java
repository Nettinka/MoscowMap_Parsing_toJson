public class Stations {
    public String stationName;
    public String lineNameStationOn;


    public String getLineNameStationOn() {
        return lineNameStationOn;
    }


    public Stations(String stationName, String lineNameStationOn) {
        this.stationName = stationName;
        this.lineNameStationOn = lineNameStationOn;
    }

    public String getStationName() {
        return stationName;
    }


    public String toString() {
        return "Stations [name=" + stationName + ", line-number=" + lineNameStationOn + "]";
    }


}
