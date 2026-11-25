package ua.opnu;

public class BookData implements Comparable<BookData> {
    private String title;
    private String author;
    private int reviews;
    private double total;

    public BookData(String title, String author, int reviews, double total) {
        this.title = title;
        this.author = author;
        this.reviews = reviews;
        this.total = total;
    }

    public double getRating() {
        if (reviews == 0) {
            return 0.0;
        }
        return total / reviews;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(BookData other) {
        // Книга з вищим рейтингом має бути "меншою" (стояти першою при сортуванні)
        int ratingCompare = Double.compare(other.getRating(), this.getRating());
        if (ratingCompare != 0) {
            return ratingCompare;
        }
        // Якщо рейтинги рівні — порівнюємо за назвою у звичайному порядку
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return String.format("\"%s\" (%s) — рейтинг: %.2f", title, author, getRating());
    }
}
