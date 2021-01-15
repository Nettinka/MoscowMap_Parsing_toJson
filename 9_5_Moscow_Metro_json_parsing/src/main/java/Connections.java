public class Connections {
    public String firstLineNumber;
    public String firstStationName;
    public String secondLineNumber;
    public String secondStationName;

    public Connections() {

    }

    public Connections(String firstLineNumber, String firstStationName, String secondLineNumber, String secondStationName) {
        this.firstLineNumber = firstLineNumber;
        this.firstStationName = firstStationName;
        this.secondLineNumber = secondLineNumber;
        this.secondStationName = secondStationName;
    }

    public void setFirstLineNumber(String firstLineNumber) {
        this.firstLineNumber = firstLineNumber;
    }

    public void setFirstStationName(String firstStationName) {
        this.firstStationName = firstStationName;
    }

    public void setSecondLineNumber(String secondLineNumber) {
        this.secondLineNumber = secondLineNumber;
    }

    public void setSecondStationName(String secondStationName) {
        this.secondStationName = secondStationName;
    }


    public String getFirstLineNumber() {
        return firstLineNumber;
    }

    public String getFirstStationName() {
        return firstStationName;
    }

    public String getSecondLineNumber() {
        return secondLineNumber;
    }

    public String getSecondStationName() {
        return secondStationName;
    }

    public String toString() {
        return "Connections [{line=" + firstLineNumber + ", station=" + firstStationName + "}" + "{line=" + secondLineNumber + ", station=" + secondStationName + "}]";
    }
}

