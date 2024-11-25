import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

public final class PersonDataGenerator {

    private static final String[] NAMES = new String[] {
            "Per", "Mik", "Djok", "Raj", "Gaj", "Vlaj", "Zlaj", "Ars", "Bor",
            "Ac", "Jov", "Kost", "Nikol", "Vlad", "Vukot", "Život", "Siniš", "Puniš",
            "Nad", "Ned", "An", "Tanj", "Jelen", "Marij", "Tamar", "Nin", "Mil", "Mim", "Petr", "Ver",
            "Oliver", "Ivan", "Bojan", "Goran", "Zoran", "Dragan"};

    private static final String[] LOCATIONS = new String[] {
            "Novi Sad", "Beograd", "Zrenjanin", "Sombor", "Subotica", "Kikinda", "Smederevo", "Kovin", "Kragujevac", "Ruma"
    };

    public static final Stream<Person> streamOfPersons(int n) {
        Random r = new Random(0);
        return Stream.generate(() -> person(r)).limit(n);
    }

    public static final Stream<String> stringStream(int n) {
        return streamOfPersons(n).map(o -> Person.toString(o));
    }

    private static final LocalDate START = LocalDate.of(1940, 1, 1);
    private static final Person person(Random r) {
        int index = r.nextInt(NAMES.length);
        String name = NAMES[index] + "a";
        String surname = NAMES[r.nextInt(NAMES.length)] + ((r.nextDouble() > 0.4) ? "ić" : r.nextDouble() > 0.5 ? "ović" : "ovski");
        Gender gender = index < NAMES.length / 2 ? Gender.MALE : Gender.FEMALE;
        if (gender == Gender.FEMALE && r.nextDouble() < 0.1) {
            surname = surname + "-" + NAMES[r.nextInt(NAMES.length)] + "ić";
        }
        String location1 = LOCATIONS[r.nextInt(LOCATIONS.length)];
        String location2 = LOCATIONS[r.nextInt(LOCATIONS.length)];
        LocalDate date = START.plusDays(r.nextInt(20000));
        int income = 10 * (50_00 + (int)(50_00 * r.nextGaussian()));
        if (income < 5000) {
            income = 0;
        }
        Person[] children = new Person[r.nextInt(6)];
        for (int i = 0; i < children.length; i++) {
            int index2 = (index + i + 1) % NAMES.length;
            String name2 = NAMES[index2] + "a";
            Gender gender2 = index2 < NAMES.length / 2 ? Gender.MALE : Gender.FEMALE;
            LocalDate date2 = date.plusDays(7500 + r.nextInt(7500));
            if (date2.getYear() > 2018) {
                date2 = date2.withYear(2018);
            }
            children[i] = new Person(name2, surname, gender2, date2, location2, location2, 0);
        }
        return new Person(name, surname, gender, date, location1, location2, income, children);
    }
}
