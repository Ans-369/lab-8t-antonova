package ua.opnu;

import java.util.Arrays;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
// ЗАВДАННЯ 1: MyOptional<T>
        System.out.println("=== Завдання 1: MyOptional ===");
        MyOptional<String> middleName = new MyOptional<>();
        System.out.println(middleName); // MyOptional[empty]
        System.out.println("isPresent: " + middleName.isPresent()); // false
        System.out.println("orElse: " + middleName.orElse("немає")); // "немає"

        MyOptional<String> username = new MyOptional<>("admin");
        System.out.println(username); // MyOptional[value=admin]
        System.out.println("isPresent: " + username.isPresent()); // true
        System.out.println("get(): " + username.get()); // "admin"
        System.out.println("orElse: " + username.orElse("guest")); // "admin"

        try {
            String test = middleName.get();
            System.out.println("unexpected: " + test);
        } catch (IllegalStateException ex) {
            System.out.println("Очікуваний виняток: " + ex.getMessage());
        }

        try {
            MyOptional<String> broken = new MyOptional<>(null);
            System.out.println("unexpected: " + broken);
        } catch (IllegalArgumentException ex) {
            System.out.println("Правильно не дозволив null: " + ex.getMessage());
        }

        // ЗАВДАННЯ 2: BookData та Comparable
        System.out.println("\n=== Завдання 2: Сортування BookData ===");
        BookData[] books = {
                new BookData("Кобзар", "Т. Шевченко", 10, 45.0),         // рейтинг 4.5
                new BookData("Енеїда", "І. Котляревський", 10, 48.0),    // рейтинг 4.8
                new BookData("Захар Беркут", "І. Франко", 5, 22.5),      // рейтинг 4.5 (рівний з Кобзарем)
                new BookData("Тигролови", "І. Багряний", 4, 20.0)        // рейтинг 5.0
        };

        Arrays.sort(books);
        for (BookData book : books) {
            System.out.println(book);
        }

        // ЗАВДАННЯ 3: Узагальнений метод printArray()
        System.out.println("\n=== Завдання 3: Printer ===");
        Printer myPrinter = new Printer();
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};
        myPrinter.printArray(intArray);
        myPrinter.printArray(stringArray);

        // ЗАВДАННЯ 4: Узагальнений метод filter()
        System.out.println("\n=== Завдання 4: Узагальнений filter() ===");
        Integer[] numbers = {1, 2, 3, 4, 5, 6};
        String[] words = {"Java", "C++", "Python", "Go", "Kotlin"};

        // Виводимо через Arrays.toString(), щоб уникнути ClassCastException при присвоєнні Object[] -> T[]
        System.out.println("Парні числа: " + Arrays.toString(filter(numbers, n -> n % 2 == 0)));
        System.out.println("Слова довше 3 символів: " + Arrays.toString(filter(words, s -> s.length() > 3)));

        // ЗАВДАННЯ 5: Узагальнений метод contains()
        System.out.println("\n=== Завдання 5: Узагальнений contains() ===");
        System.out.println("Чи є 'Java' у масиві words: " + contains(words, "Java"));
        System.out.println("Чи є 'Ruby' у масиві words: " + contains(words, "Ruby"));
        System.out.println("Чи є число 4 у масиві numbers: " + contains(numbers, 4));

        // ЗАВДАННЯ 6: GenericTwoTuple та GenericThreeTuple
        System.out.println("\n=== Завдання 6: Кортежі (Tuples) ===");
        GenericTwoTuple<String, Integer> productPrice = new GenericTwoTuple<>("Ноутбук", 35000);
        System.out.println("Товар і ціна: " + productPrice);
        System.out.println("Назва: " + productPrice.first + ", Ціна: " + productPrice.second + " грн");

        GenericThreeTuple<String, Integer, Boolean> userStatus = new GenericThreeTuple<>("Олександр", 21, true);
        System.out.println("Дані користувача: " + userStatus);
        System.out.println("Ім'я: " + userStatus.twoTuple.first +
                ", Вік: " + userStatus.twoTuple.second +
                ", Активний: " + userStatus.three);
    }

    // --- Методи для Завдань 4 та 5 ---

    // Завдання 4: узагальнений filter()
    @SuppressWarnings("unchecked")
    public static <T> T[] filter(T[] input, Predicate<T> p) {
        T[] result = (T[]) new Object[input.length];
        int counter = 0;

        for (T element : input) {
            if (p.test(element)) {
                result[counter] = element;
                counter++;
            }
        }

        return Arrays.copyOfRange(result, 0, counter);
    }

    // Завдання 5: узагальнений contains()
    public static <T extends Comparable<T>, V extends T> boolean contains(T[] array, V element) {
        for (T item : array) {
            if (item.equals(element)) {
                return true;
            }
        }
        return false;
    }
}
