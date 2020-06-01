package encryptdecrypt;

// Unicode Algorithm if the argument was provided for unicode
public class UnicodeAlgorithmConcrete implements CryptographyAlgorithmStrategy{
    private final int key;

    public UnicodeAlgorithmConcrete(int key) {
        this.key = key;
    }

    // shifts characters based on their unicode value and key
    @Override
    public String cryptographyAlgorithm(String message) {
        char[] messageArr = message.toCharArray(); // array used for encryption
        StringBuilder newMessage = new StringBuilder();
        for (char character: messageArr) {
            newMessage.append((char)(character + key));
        }
        return newMessage.toString();
    }
}
