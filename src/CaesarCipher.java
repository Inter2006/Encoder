public class CaesarCipher {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            ".,\":!? +-*/\\@#$%^&(){}[];'|`~=_©«»—…" + "0123456789" + "\u00A0";

    public String encrypt(String message, int key) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char charAt = message.charAt(i);
            int index = ALPHABET.indexOf(charAt);
            if (index >= 0) {
                int newIndex = (index + key) % ALPHABET.length();
                char newChar = 0;
                if (newIndex >= 0) {
                    newChar = ALPHABET.charAt(newIndex);
                } else {
                    newChar = ALPHABET.charAt(newIndex + ALPHABET.length());
                }
                builder.append(newChar);
            }
        }
        return builder.toString();
    }

    public String decrypt(String message, int key){
    return encrypt(message, key * -1);
    }

}
