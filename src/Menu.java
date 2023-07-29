
public class Menu {
    public static void main(String[] args) {

        while (true) {
            Util.writeMessage("Выберите действие, введя его номер: \n" +
                    "1.Зашифровать \n" +
                    "2.Расшифровать \n" +
                    "3.Подобрать ключ \n" +
                    "4.Синтаксический анализ \n" +
                    "5.Выход из программы \n");
            String answer = Util.readString();
            switch (answer) {
                case "1" -> new EncryptedDecrypted().encryptedDecrypted(true);
                case "2" -> new EncryptedDecrypted().encryptedDecrypted(false);
                case "3" -> System.out.println("Подбираем ключ");
                case "4" -> System.out.println("Синтаксический анализ");
                case "5" -> {
                    return;
                }
            }
        }
    }
}