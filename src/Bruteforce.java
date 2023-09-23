import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Bruteforce {
    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void bruteforce() throws IOException {
        Util.writeMessage("Введите адрес файл для его расшифровки");
        String path = Util.readString();
        Path bruteforce = Util.buildFileName(path, "_bruteforce");

        StringBuilder builder = new StringBuilder();
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(bruteforce)) {
            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string).append("\n");
                list.add(string);
            }
            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String decrypt = caesarCipher.decrypt(builder.toString(), i);
                if (isValidateText(decrypt)) {
                    for (String str : list) {
                        String encrypt = caesarCipher.decrypt(str, i);
                        writer.write(encrypt);
                        writer.newLine();
                    }
                    Util.writeMessage("Содержимое расшифровано ,ключ расшифровки = " + i);
                    break;
                }
            }
        }
    }

    private boolean isValidateText(String text) {
        boolean isValidate = false;
        String[] strings = text.split(" ");
        for (String string : strings) {
            if (string.length() > 28) {
                return false;
            }

        }
        if (text.contains(". ") || text.contains(", ") || text.contains("! ") || text.contains("? ")) {
            isValidate = true;
        }
        while (isValidate) {
            Util.writeMessage(text);
            Util.writeMessage("Понятен ли этот текст? Y/N");
            String answer = Util.readString();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                isValidate = false;
            } else {
                Util.writeMessage("Повторите попытку , Выбор между Y/N");
            }
        }

        return false;
    }
}
