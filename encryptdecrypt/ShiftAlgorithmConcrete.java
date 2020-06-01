package encryptdecrypt;

// Shift Algorithm if the argument was provided for shift
public class ShiftAlgorithmConcrete implements CryptographyAlgorithmStrategy{
    private final int key;
    final String UPPERCASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public ShiftAlgorithmConcrete(int key) {
        this.key = key;
    }

    // Essentially a Caesar Cipher, move the letters along an alphabet then loop them back to the front
    @Override
    public String cryptographyAlgorithm(String message) {
        char[] messageArr = message.toCharArray();
        StringBuilder newMessage = new StringBuilder();
        for (char character: messageArr) {
            if (Character.isUpperCase(character)) {
                newMessage.append(shiftingAlgorithm(UPPERCASE_ALPHABET, character));
            }
            else if (Character.isLowerCase(character)) {
                newMessage.append(shiftingAlgorithm(LOWERCASE_ALPHABET, character));
            }
            else {
                newMessage.append(character);
            }
        }
        return newMessage.toString();
    }

    // added a helper method to smooth, and remove repetition on our cryptographyAlgorithm
    private char shiftingAlgorithm(String alphabet, char character) {
        int index = alphabet.indexOf(character);
        index += key;
        while (index > 25) {
            index -= 26;
        }
        while (index < 0) {
            index  += 26;
        }
        return alphabet.charAt(index);
    }
}