package HWio;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String BASE_DIR = "tests/";

    public static Path pathBuilder(String fileName) throws InvalidPathException {
        return Paths.get(BASE_DIR + fileName);
    }

    public static List<String> getFileWords(Path path) throws IOException {
        List<String> words = new ArrayList<>();

        try (Scanner scanner = new Scanner(path)){
            while(scanner.hasNext()){
                String word = scanner.next();
                word = word.replaceAll("(?ui)[^a-z0-9а-яё]", "");
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        }
        return words;
    }

    public static void main(String[] args) throws IOException,InvalidPathException {
        Path textPath = pathBuilder("TaskText.txt");
        Path numbersPath = pathBuilder("TaskNumbers.txt");
        Path javaPath = pathBuilder("TaskJava.java");
        List<String> textWords = getFileWords(textPath);
        List<String> stringsNumbers = Files.readAllLines(numbersPath);

        //Задан файл с текстом, найти и вывести в консоль все слова, начинающиеся с гласной буквы.
        System.out.println("Все слова, начинающиеся с гласной буквы: ");
        for (String word : textWords) {
            if (word.matches("(?ui)^(?=[ёуеыаоэяию]).*"))
                System.out.print(word + " ");
        }


        //Задан файл с текстом, найти и вывести в консоль все слова,
        //для которых последняя буква одного слова совпадает с первой буквой следующего слова
        System.out.println("\nВсе слова,для которых последняя буква одного слова совпадает с первой буквой следующего слова: ");
        for (int i = 0; i < textWords.size() - 1; i++) {
            String currentWord = textWords.get(i);
            String nextWord = textWords.get(i + 1);

            char firstWord1 = nextWord.charAt(0);
            char lastWord2 = currentWord.charAt(currentWord.length() - 1);

            if (firstWord1 == lastWord2)
                System.out.println(textWords.get(i) + " " + textWords.get(i + 1));
        }

        //Задан файл с текстом. В каждой строке найти и вывести наибольшее число цифр, идущих подряд.
        System.out.println("Подсчет и вывод для каждой строки наибольшего числа цифр, идущих подряд.");
        for (String str : stringsNumbers) {
            int counter = 0, maxCounter = 0;
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i))) {
                    counter++;
                    if (counter > maxCounter)
                        maxCounter = counter;
                } else counter = 0;
            }
            System.out.println("В строке \"" + str + "\" наибольшее число цифр подряд " + maxCounter);
        }

        //Задан файл с java-кодом. Прочитать текст программы из файла и все слова public в объявлении
        //атрибутов и методов класса заменить на слово private. Результат сохранить в другой заранее созданный файл.
        List<String> javaToReplace = Files.readAllLines(javaPath);
        List<String> javaReplaced = new ArrayList<>();
        Path targetPath1 = Paths.get(BASE_DIR + "/ReplacedCode.java");
        for (String str : javaToReplace) {
            if (str.contains("class") || !str.contains("public"))
                javaReplaced.add(str);
            else javaReplaced.add(str.replace("public", "private"));
        }

        try (FileWriter writer = new FileWriter(BASE_DIR + "/ReplacedCode.java")) {
            for (String str : javaReplaced) {
                writer.write(str + System.lineSeparator());
            }
        }
   


        //Задан файл с java-кодом.
        //Прочитать текст программы из файла и записать в другой файл в обратном порядке символы каждой строки.
        List<String> javaToReverse = Files.readAllLines(javaPath);
        List<String> javaReversed = new ArrayList<String>();
        for (String str : javaToReverse) {
            javaReversed.add(new StringBuilder(str).reverse().toString());
        }
        try (FileWriter writer = new FileWriter(BASE_DIR + "/ReversedCode.java", true)) {
            for (String str : javaReversed) {
                writer.write(str + System.lineSeparator());
            }
        }
    }
}
