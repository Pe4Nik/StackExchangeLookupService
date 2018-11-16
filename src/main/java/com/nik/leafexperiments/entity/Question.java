package com.nik.leafexperiments.entity;


import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "link")
    private String link;
    @Column(name = "creator")
    private String creator;
    @Column(name = "isanswered")
    private boolean isAnswered;
    @Column(name = "date")
    private String creationDate;
    @Column(name = "answers")
    private int answersCount;

    public Question() {
    }

    public Question(int id, String title, String link, String creator, boolean isAnswered,
                    String creationDate, int answersCount) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.creator = creator;
        this.isAnswered = isAnswered;
        this.creationDate = creationDate;
        this.answersCount = answersCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getAnswersCount() {
        return answersCount;
    }

    public void setAnswersCount(int answersCount) {
        this.answersCount = answersCount;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
