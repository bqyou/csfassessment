package ibf2022.batch1.csf.assessment.server.models;

import org.bson.Document;

public class Comment {

    private String title;
    private String name;
    private String comment;
    private int rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Document toDocument() {
        Document d = new Document();
        d.put("title", getTitle());
        d.put("name", getName());
        d.put("rating", getRating());
        d.put("comment", getComment());
        return d;
    }
}
