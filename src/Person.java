import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public final class Person {

    private final String name;
    private final String surname;
    private final Gender gender;
    private final LocalDate birthDate;
    private final String placeOfBirth;
    private final String placeOfResidence;
    private final int income;
    private final List<Person> children;

    public Person(String name, String surname, Gender gender, LocalDate birthDate, String placeOfBirth, String placeOfResidence, int income, Person... children) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        if (surname == null) {
            throw new IllegalArgumentException();
        }
        this.surname = surname;
        if (gender == null) {
            throw new IllegalArgumentException();
        }
        this.gender = gender;
        if (birthDate == null) {
            throw new IllegalArgumentException();
        }
        this.birthDate = birthDate;
        if (placeOfBirth == null) {
            throw new IllegalArgumentException();
        }
        this.placeOfBirth = placeOfBirth;
        if (placeOfResidence == null) {
            throw new IllegalArgumentException();
        }
        this.placeOfResidence = placeOfResidence;
        this.income = income;
        if (children == null) {
            throw new IllegalArgumentException();
        }
        List<Person> d = new ArrayList<>();
        for (Person child : children) {
            d.add(child);
        }
        this.children = Collections.unmodifiableList(d);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public int getIncome() {
        return income;
    }

    public List<Person> getChildren() {
        return children;
    }

    public int getChildrenCount() {
        return getChildrenCount(null);
    }

    public int getChildrenCount(Gender gender) {
        if (gender == null) {
            return children.size();
        }
        return (int) children.stream()
                .filter(o -> o.getGender() == gender)
                .count();
    }

    @Override
    public String toString() {
        return String.format("%08X ", System.identityHashCode(this)) + name + " " + surname;
    }

    public static String toString(Person person) {
        StringJoiner joiner = new StringJoiner(" | ");
        joiner.add(person.name);
        joiner.add(person.surname);
        //joiner.add(person.pol.toString());
        //joiner.add(person.birthDate.toString());
        //joiner.add(person.mestoRodjenja);
        //joiner.add(person.mestoStanovanja);
        joiner.add(String.valueOf(person.income));
        //joiner.add(String.valueOf(person.deca.size()));
//        for (Person dete : person.deca) {
//            joiner.add(dete.toString());
//        }

        return joiner.toString();
    }

    public static Person fromString(String string) {
        String[] data = string.split("\\|");
        if (data.length % 8 != 0) {
            throw new IllegalArgumentException();
        }
        Person person = fromString(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
        try {
            int childrenCount = Integer.parseInt(data[7]);
            Person[] children = new Person[childrenCount];
            for (int i = 0; i < childrenCount; i++) {
                children[i] = fromString(data[8 * i + 0], data[8 * i + 1], data[8 * i + 2], data[8 * i + 3], data[8 * i + 4], data[8 * i + 5], data[8 * i + 6]);
            }
            return new Person(person.name, person.surname, person.gender, person.birthDate, person.placeOfBirth, person.placeOfResidence, person.income, children);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Person fromString(String... data) {
        try {
            return new Person(data[0], data[1], Gender.valueOf(data[2]), LocalDate.parse(data[3]), data[4], data[5], Integer.parseInt(data[6]));
        } catch (NullPointerException | DateTimeParseException | NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
