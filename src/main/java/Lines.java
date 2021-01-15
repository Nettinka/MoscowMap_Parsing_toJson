public class Lines {
    public String lineNames;
    public String lineNumber;

    public Lines(String lineNames, String lineNumber) {
        this.lineNames = lineNames;
        this.lineNumber = lineNumber;
    }

    public String getLineNumber() {
        return lineNumber;
    }


    public String getLineNames() {
        return lineNames;
    }


    public String toString() {
        return "Lines [number=" + lineNumber + ", name=" + lineNames + "]";
    }
}
