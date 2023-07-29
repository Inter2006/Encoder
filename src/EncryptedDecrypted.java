import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptedDecrypted {
    private final CaesarCipher caesar = new CaesarCipher();

    public void encryptedDecrypted(boolean flag) {
        Util.writeMessage("Введите адрес файла для его " + (flag ? "за" : "рас") + "шифровки");
        String path = Util.readString();
        Util.writeMessage("Введите ключ");
        int key = Util.readInt();
        Path result = Util.buildFileName(path, flag ? "_encrypted" : "_decrypted");
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(result) ) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encryptDecrypt = flag ? caesar.encrypt(string, key) : caesar.decrypt(string, key);
                writer.write(encryptDecrypt + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
