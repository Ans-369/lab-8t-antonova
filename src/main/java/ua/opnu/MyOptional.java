package ua.opnu;

public class MyOptional<T> {
    private T value;
    private boolean present;

    // Конструктор без параметрів (порожній)
    public MyOptional() {
        this.value = null;
        this.present = false;
    }

    // Конструктор зі значенням
    public MyOptional(T value) {
        if (value == null) {
            throw new IllegalArgumentException("MyOptional не приймає null");
        }
        this.value = value;
        this.present = true;
    }

    public boolean isPresent() {
        return present;
    }

    public boolean isEmpty() {
        return !isPresent();
    }

    public T get() {
        if (!present) {
            throw new IllegalStateException("Значення відсутнє (MyOptional порожній)");
        }
        return value;
    }

    public T orElse(T defaultValue) {
        return present ? value : defaultValue;
    }

    @Override
    public String toString() {
        if (present) {
            return "MyOptional[value=" + value + "]";
        }
        return "MyOptional[empty]";
    }
}
