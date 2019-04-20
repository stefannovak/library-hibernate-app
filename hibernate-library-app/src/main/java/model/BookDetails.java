package model;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class BookDetails {
    @Id @GeneratedValue //(strategy = GenerationType.IDENTITY) this gives me a crazy bug which I am working on fixing.
    private int id;

    private String title;
    private String author;
    private String genre;
    private String location;

    public BookDetails() {}

    @Override
    public String toString() {
        return "Book{" +
                "id: " + id +
                ", title: '" + title + '\'' +
                ", author: '" + author + '\'' +
                ", genre: '" + genre + '\'' +
                ", location: " + location +
                '}' + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int userId) {
        this.id = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}