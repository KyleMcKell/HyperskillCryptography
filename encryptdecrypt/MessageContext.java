package encryptdecrypt;

// Context for message, which sends over to an algorithm
public class MessageContext {
    private final CryptographyAlgorithmStrategy algorithm;

    public MessageContext(CryptographyAlgorithmStrategy algorithm) {
        this.algorithm = algorithm;
    }

    public String cryptography(String message) {
        return this.algorithm.cryptographyAlgorithm(message);
    }
}