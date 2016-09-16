/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import javax.persistence.*;

/**
 *
 * @author vmvini
 */

@Entity
public class Question {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Discipline discipline;

    @Column(columnDefinition = "text")
    private String question;

    @Column(columnDefinition = "text")
    private String answer;

    @ManyToOne
    private Teacher answerer;

    public Question(){}



    public Question(Discipline discipline, String question, String answer, Teacher answerer) {
        this.discipline = discipline;
        this.question = question;
        this.answer = answer;
        this.answerer = answerer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", discipline=" + discipline +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", answerer=" + answerer +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Teacher getAnswerer() {
        return answerer;
    }

    public void setAnswerer(Teacher answerer) {
        this.answerer = answerer;
    }
}
