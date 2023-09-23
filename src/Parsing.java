import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parsing {
    private final Map<Character, Integer> mapEncrypted = new HashMap<>();
    private final Map<Character, Integer> mapStatistic = new HashMap<>();
    private final Map<Character, Character> mapDecrypted = new HashMap<>();

    public void parse() {
        Util.writeMessage("Введите путь к файлу для его расшифровки");
        String pathEncrypted = Util.readString();

        Util.writeMessage("Введите путь к файлу для набора статистики");
        String pathStatistic = Util.readString();

        Path parsing = Util.buildFileName(pathEncrypted, "parsing");

        fillMapValues(mapEncrypted, pathEncrypted);
        fillMapValues(mapStatistic, pathStatistic);
        List<Map.Entry<Character, Integer>> listEncrypted = mapToList(mapEncrypted);
        List<Map.Entry<Character, Integer>> listStatistic = mapToList(mapStatistic);

        if (listEncrypted.size() <= listStatistic.size()) {
            for (int i = 0; i < listEncrypted.size(); i++) {
                mapDecrypted.put(listEncrypted.get(i).getKey(), listStatistic.get(i).getKey());
            }
        } else {
            Util.writeMessage("Размер файла статистики должен быть больше , чем зашифрованный");
        }
        try(BufferedReader reader  = Files.newBufferedReader(Path.of(pathEncrypted));
            BufferedWriter writer = Files.newBufferedWriter(parsing)) {
            while (reader.ready()){
                String string = reader.readLine();
                StringBuilder builder = new StringBuilder();
                char[] charArray = string.toCharArray();
                for (char aChar : charArray) {
                    Character character = mapDecrypted.get(aChar);
                    builder.append(character);
                }
                writer.write(builder + System.lineSeparator());
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        Util.writeMessage("Содержимое файла расшифровано");
    }

    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Comparator<Map.Entry<Character, Integer>> comparator = (o1, o2) -> o2.getValue() - o1.getValue();
        list.sort(comparator);
        return list;
    }

    private void fillMapValues(Map<Character, Integer> map, String path) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
            String string = reader.readLine();
            char[] charArray = string.toCharArray();
            for (char aChar : charArray) {
                if (!map.containsKey(aChar)) {
                    map.put(aChar, 1);
                } else {
                    map.put(aChar, map.get(aChar) + 1);
                }
            }
        } catch (IOException e) {
        }
    }
}
