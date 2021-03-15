public class Main {
    public static void main(String[] args) {
        String firstString = "3[one]2[go]10[Lo]";
        String secondString = "1[3[to]]roll";
        String wrongStr = "коткомпот";


        Unpacker unpacker = new Unpacker();
        System.out.println(unpacker.unpack(wrongStr));

    }
}
