public class Book <T, U, V>{

    public T title;
    public U author;
    public V publishingYear;

    public T getTitle() {
        return title;
    }
    public void setTitle(T title) {
        this.title = title;
    }
    public U getAuthor() {
        return author;
    }
    public void setAuthor(U author) {
        this.author = author;
    }
    public V getPublishingYear() {
        return publishingYear;
    }
    public void setPublishingYear(V publishingYear) {
        this.publishingYear = publishingYear;
    }

    public Book(){}

    public Book(T title){
        this.title = title;
    }

    public Book(T title, V publishingYear){
        this.title = title;
        this.publishingYear = publishingYear;
    }

    public Book(T title, U author, V publishingYear){
        this.title = title;
        this.author = author;
        this.publishingYear = publishingYear;
    }
}
