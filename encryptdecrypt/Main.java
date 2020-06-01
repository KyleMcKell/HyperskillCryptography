package encryptdecrypt;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        // Args gathered from Program Arguments, set each to their default value
        CryptographyModes mode = CryptographyModes.ENC;
        CryptographyAlgorithms algType = CryptographyAlgorithms.SHIFT;
        int key =  0;
        String data = "";
        String out = "";
        String in = "";

        // set the values for any program arguments that were supplied
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                    mode = CryptographyModes.valueOf(args[i + 1].toUpperCase());
                    break;
                case "-alg":
                    algType = CryptographyAlgorithms.valueOf(args[i+1].toUpperCase());
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-out":
                    out = args[i + 1];
                    break;
                case "-in":
                    in = args[i+1];
                    break;
                default:
                    break;
            }
        }

        // key needs to be adjusted to fit the mode (encrypt/decrypt)
        key = adjustKeyToMode(mode, key);
        // data needs to be adjusted in case there is an "in" argument and a "data" argument
        if ("".equals(data)) {
            data = adjustData(in);
        }

        // find which algorithm to use via our Strategy Pattern
        final CryptographyAlgorithmStrategy algorithm = create(algType, key);

        // change message with said algorithm
        final String changedMessage = algorithm.cryptographyAlgorithm(data);

        // if file is given, print message on file, if not, print message to System
        if (out.equals("")) {
            System.out.println(changedMessage);
        }
        else {
            try (FileWriter writer = new FileWriter(out)) {
                writer.write(changedMessage);
            } catch (Exception e){
                System.out.printf("An exception occurs %s", e.getMessage());
            }
        }
    }

    // Change data to the data that is inside in
    public static String adjustData(String in)  {
        try {
            // this seems messy, but just understand the reassignment as to save creating a new String
            in = new String(Files.readAllBytes(Paths.get(in)));
        } catch (Exception e) {
            System.out.println("File not Found");
        }
        // return the data that was inside in (not the filepath which previously was stored
        return in;
    }

    /*
    The difference between encrypting and decrypting, with our current algorithms, is simply reversing the key
    Obviously if a more complicated algorithm came up, we would want to do encryption and decryption
    inside of each respective Concrete Strategy
    */
    public static int adjustKeyToMode(CryptographyModes mode, int key) {
        switch (mode) {
            case ENC:
                return key;
            case DEC:
                return -key;
            default:
                throw new IllegalArgumentException("Unknown cryptography mode " + mode);
        }
    }

    // getting our algorithm to use based on the requested algorithm type
    public static CryptographyAlgorithmStrategy create(CryptographyAlgorithms algType, int key) {
        switch (algType) {
            case SHIFT:
                return new ShiftAlgorithmConcrete(key);
            case UNICODE:
                return new UnicodeAlgorithmConcrete(key);
            default:
                throw new IllegalArgumentException("Unknown cryptography algorithm type " + algType);
        }
    }
}
