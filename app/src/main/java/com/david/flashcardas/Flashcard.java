package com.david.flashcardas;

public class Flashcard implements Comparable<Flashcard>{


    private long cardNumber = 1;
    private String subject;
    private String sideA;
    private String sideB;

    public Flashcard(long cardNumber, String subject, String sideA, String sideB){
        this.cardNumber = cardNumber;
        this.subject = subject;
        this.sideA = sideA;
        this.sideB = sideB;
    }
    public Flashcard(String subject, String sideA, String sideB){
        this.subject = subject;
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public long getCardNumber(){
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSideA() {
        return sideA;
    }

    public void setSideA(String sideA) {
        this.sideA = sideA;
    }

    public String getSideB() {
        return sideB;
    }

    public void setSideB(String sideB) {
        this.sideB = sideB;
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "cardNumber=" + cardNumber +
                ", subject='" + subject + '\'' +
                ", sideA='" + sideA + '\'' +
                ", sideB='" + sideB + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object that) {

        return this.getSubject().equals(that);
    }

    @Override
    public int compareTo(Flashcard that) {
        int difference;

        difference = this.getSubject().compareTo(that.getSubject());

        return difference;
    }

    //TODO BUG compareTo uses getSubject only!
}
