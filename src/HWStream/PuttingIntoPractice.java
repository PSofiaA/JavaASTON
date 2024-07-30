package HWStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PuttingIntoPractice {
    public static void streamTasks(List <Transaction> transactions){

        /** 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей
         к большей). */
        // Либо можно было сразу сделать .forEach(System.out::print) (захотела попробовать лист)
        List year2011 = transactions.stream()
                .filter((Transaction trsn) -> trsn.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .toList();

        System.out.println("Задача 1. Транзакции за 2011 год: " + year2011);

        /**  2. Вывести список неповторяющихся городов, в которых работают трейдеры. */
        List towns = transactions.stream()
                .map((Transaction transaction) -> transaction.getTrader().getCity())
                .distinct()
                .toList();

        System.out.println("Задача 2. Список городов: " + towns);

        /**  3. Найти всех трейдеров из Кембриджа и отсортировать их по именам. */
        System.out.println("Задача 3.Отсортированные трейдеры из Кембрижда: ");
        //Здесь для удобства вывожу только имена трейдеров
        transactions.stream()
                .map(Transaction::getTrader)
                .filter((Trader trader) -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(trader -> System.out.println(trader.getName()));

        /**  4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке. */
        String tradersAlphabet = transactions.stream()
                .map((Transaction transaction) -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining (", "));
        System.out.println("Задача 4.Трейдеры по алфавиту: " + tradersAlphabet + ". Ожидается: Alan, Brian, Mario, Raoul");

        /**  5. Выяснить, существует ли хоть один трейдер из Милана. */
        boolean isFromMilan = transactions.stream()
                .anyMatch((Transaction transaction) -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("Задача 5. Есть ли хотя бы один трейдер из Милана? " + isFromMilan);

        /**  6. Вывести суммы всех транзакций трейдеров из Кембриджа.*/
        System.out.println("Задача 6. Cуммы всех транзакций трейдеров из Кембриджа:");
        transactions.stream()
                .filter((Transaction transaction) -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        /**  7. Какова максимальная сумма среди всех транзакций?*/
        Integer maxTransaction = transactions.stream()
                .map(Transaction::getValue)
                .mapToInt(value -> value)
                .max()
                .getAsInt();
        System.out.println("Задача 7. Максимальная сумма среди всех транзакций равна " + maxTransaction);

        /**  8. Найти транзакцию с минимальной суммой.*/
        Transaction minTransaction = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .get();
        System.out.println("Задача 8. Транзакция с минимальной суммой: " + minTransaction);
    }


    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        streamTasks(transactions);
    }
}
