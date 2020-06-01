package encryptdecrypt;

// Strategy Interface for our types of Algorithms
public interface CryptographyAlgorithmStrategy {
    String cryptographyAlgorithm(String message);
}
