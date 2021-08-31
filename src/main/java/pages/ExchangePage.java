package pages;

public interface ExchangePage extends Page {
    void preAction();

    Double getCourseDouble(String course);
}