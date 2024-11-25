import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * A stream of persons is provided in the form of the method PersonDataGenerator::personsStream.
 * Using the given method, lambda expressions, and stream operations, implement the following methods
 * and call them from the main program:
 *
 * 1) List<Person> sortByBirthDateDesc()
 * 2) Set<Person> getPersonsWithIncomeGreaterThan(int)
 * 3) double getAverageIncomeByLocation(String)
 * 4) Map<String, Double> getAverageIncomeForEveryLocation()
 * 5) Map<String, Person> getPersonWithLargestIncomePerLocation()
 * 6) Map<Integer, List<Person>> groupByNumberOfChildren()
 * Map<String, Map<Integer, List<Person>>> razvrstaniPoMestuIBrojuDece()
 * String gradSaNajviseDoseljenika()
 * String gradSaNajviseStarosedelaca()
 * String najbogatijiGrad()
 * String najpopularnijeMuskoIme()
 * String najpopularnijeZenskoIme()
 *
 * U glavnom programu ispisati sledece podatke koriscenjem redukcija tokova
 * podataka (terminalna operacija .reduce()).
 *
 * Najbogatija osoba
 * -----------------
 * 01234567 Pera Peric
 *
 * Muska imena
 * -----------
 * Aca, Arsa, Bora, Vlada, ...
 *
 * Godina kada je rodjena najstarija osoba
 * ---------------------------------------
 * 1940
 *
 * U glavnom programu ispisati sledece podatke na zadati nacin. Racunanje podataka
 * realizovati pomocu operacije .collect(). Prvo podatke skupiti u mapu a potom
 * iz mape pustiti novi tok podataka i formatirati ispis pomocu metoda String::format.
 * Obratiti paznju na format ispisa, velika i mala slova i broj decimala.
 *
 * Grad       | Broj ljudi | Prosecna primanja
 * -----------+------------+------------------
 * NOVI SAD   |        234 |          49867.56
 * BEOGRAD    |        322 |          50072.33
 * KRAGUJEVAC |        225 |          49215.45
 * ...
 *
 * Ime        | Br roditelja | Broj muske dece | Broj zenske dece
 * -----------+--------------+-----------------+-----------------
 * Pera       |          234 |             356 |              297
 * Mika       |          322 |             442 |              443
 * Jelena     |          225 |             295 |              312
 * ...
 *
 * Godine | Primanja
 * zivota | Najnize   | Najvise   | Ukupno     | Prosek    | Devijacija
 * -------+-----------+-----------+------------+-----------+-----------
 * ...
 * 22     |  12600.00 | 102400.00 | 7652300.00 | 503476.12 |     132.66
 * 23     |  29600.00 |  99700.00 | 6843500.00 | 496456.26 |      98.32
 * 24     |  23400.00 | 123400.00 | 8134800.00 | 512388.43 |     253.01
 * ...
 */
public class Main {

    public static void main(String[] args) {
        int n = 200;
        // printResult(Task.sortByBirthDateDesc(n));
//        printResult(Task.getPersonsWithIncomeGreaterThan(n, 100_000));
//        System.out.println(Task.getAverageIncomeByLocation(100, "BEOGRAD"));
//        printResult(Task.getAverageIncomeForEveryLocation(1000));
//        printResultOsoba(Task.getPersonWithLargestIncomePerLocation(5000));
//        printResult(Task.groupByNumberOfChildren(50));
        int[] ls = {1, 2, 3, 4, 5, 6};
        int[] result = new int[ls.length + 1];
        result[result.length - 1] = 0;
        for (int i = result.length - 2; i >= 0; i --) {
             result[i] = result[i + 1] + ls[i];
        }
        for (int num: result) {
            System.out.println(num);
        }
    }

    private static void printResult(Map<Integer, List<Person>> result) {
        result.forEach((key, personList) -> {
            System.out.println("Num of kids:" + key);
            System.out.println("***************************");
            personList.forEach(person -> {
                String childrenNames = person.getChildren().stream()
                        .map(Person::getName)
                        .collect(Collectors.joining(", "));
                System.out.println(person + " : [" + childrenNames + "]");
            });
        });
    }

    /*
    private static void printAll() {
        PersonDataGenerator.streamOfPersons(5000)
                .forEach(System.out::println);
    }

    private static void printResult(List<Person> result) {
        result.stream().forEach(person -> System.out.println(Person.toString(person)));
    }

    private static void printResult(Set<Person> result) {
        result.stream().forEach(person -> System.out.println(Person.toString(person)));
    }

    private static void printResult(Map<String, Double> result) {
        result.forEach((key, value) -> {
            System.out.printf("%-15s | %.2f\n", key, value);
        });
    }

    private static void printResultOsoba(Map<String, Person> result) {
        result.forEach((key, value) ->
                        System.out.printf("%-15s | %s\n", key, Person.toString(value))
                );
    }
     */
}
