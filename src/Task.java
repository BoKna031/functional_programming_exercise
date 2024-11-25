import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Task {

   public static List<Person> sortByBirthDateDesc(int n) {
       return PersonDataGenerator.streamOfPersons(n)
               .sorted(Comparator.comparing(Person::getBirthDate).reversed())
               .toList();
   }

    public static Set<Person> getPersonsWithIncomeGreaterThan(int n, int kolicina) {
        return PersonDataGenerator.streamOfPersons(n)
                .filter(person -> person.getIncome() > kolicina)
                .collect(Collectors.toSet());
    }

    public static double getAverageIncomeByLocation(int n, String mesto) {
       return PersonDataGenerator.streamOfPersons(n)
               .filter(person -> person.getPlaceOfResidence().equalsIgnoreCase(mesto))
               .mapToDouble(Person::getIncome)
               .average()
               .orElse(0.0);
    }

    public static Map<String, Double> getAverageIncomeForEveryLocation(int n) {
       return PersonDataGenerator.streamOfPersons(n)
               .collect(Collectors.groupingBy(Person::getPlaceOfResidence, Collectors.averagingDouble(Person::getIncome)));
    }

    public static Map<String, Person> getPersonWithLargestIncomePerLocation(int n) {
       return PersonDataGenerator.streamOfPersons(n)
               .collect(Collectors.groupingBy(
                       Person::getPlaceOfResidence
                       , Collectors.maxBy(Comparator.comparingDouble(Person::getIncome))))
               .entrySet()
               .stream().collect(Collectors.toMap(
                       Map.Entry::getKey, person -> person.getValue().orElse(null)));
    }

    public static Map<Integer, List<Person>> groupByNumberOfChildren(int n) {
       return PersonDataGenerator.streamOfPersons(n)
               .collect(Collectors.groupingBy(person -> person.getChildrenCount()));
    }
}
