package com.david.flashcardas;

/*
@author Dave Elliott
updated 16 Nov 2016, additional encapsulation and comments
 */

class Flashcard implements Comparable<Flashcard>{

    private long m_cardNumber = 1L;
    private String m_subject;
    private String m_sideA;
    private String m_sideB;

    Flashcard(long cardNumber, String subject, String sideA, String sideB){
        setCardNumber(cardNumber);
        setSubject(subject);
        setSideA(sideA);
        setSideB(sideB);
    }
    Flashcard(String subject, String sideA, String sideB){
        setSubject(subject);
        setSideA(sideA);
        setSideB(sideB);
    }

    private long getCardNumber(){
        return m_cardNumber;
    }

    private void setCardNumber(long cardNumber) {
        m_cardNumber = cardNumber;
    }

    private String getSubject(){
        return m_subject;
    }
    private void setSubject(String subject) {
        m_subject = subject;
    }

    private String getSideA() {
        return m_sideA;
    }

    private void setSideA(String sideA) {
        m_sideA = sideA;
    }

    private String getSideB() {
        return m_sideB;
    }

    private void setSideB(String sideB) {
        m_sideB = sideB;
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "cardNumber=" + getCardNumber() +
                ", subject='" + getSubject() + '\'' +
                ", sideA='" + getSideA() + '\'' +
                ", sideB='" + getSideB() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        Flashcard that = (Flashcard)obj;
        return this.getSubject().equals( that.getSubject() );
    }

    @Override
    public int compareTo(Flashcard that) {
        int difference;

        difference = this.getSubject().compareTo(that.getSubject());

        return difference;
    }

    //TODO BUG compareTo uses getSubject only!
}
