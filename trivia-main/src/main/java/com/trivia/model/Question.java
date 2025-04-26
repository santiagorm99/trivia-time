package com.trivia.model;
import java.util.Objects;

/**
 * @author santiago
 * @version 1.0
 */
public class Question {
 
    private int id;
    private String questionText;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correctAnswer;

    /**
     * Constructor por defecto
     */
    public Question() {
    }

    /**
     * Constructor con todos los parametros
     * @param id 
     * @param questionText 
     * @param answer1 
     * @param answer2 
     * @param answer3 
     * @param answer4 
     * @param correctAnswer 
     */
    public Question(int id, String questionText, String answer1, String answer2, String answer3, String answer4, int correctAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }

    //Getters y Setters
    public int getId() {
        return this.id;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public String getAnswer1() {
        return this.answer1;
    }

    public String getAnswer2() {
        return this.answer2;
    }

    public String getAnswer3() {
        return this.answer3;
    }

    public String getAnswer4() {
        return this.answer4;
    }

    public int getCorrectAnswer() {
        return this.correctAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;
        return id == question.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", question='" + getQuestionText() + "'" +
            ", answer1='" + getAnswer1() + "'" +
            ", answer2='" + getAnswer2() + "'" +
            ", answer3='" + getAnswer3() + "'" +
            ", answer4='" + getAnswer4() + "'" +
            ", correctAnswer='" + getCorrectAnswer() + "'" +
            "}";
    }
    
}