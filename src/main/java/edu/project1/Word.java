package edu.project1;

public abstract class Word {

    private String word;

    public Word() {
        word = "";
    }

    public Word(String receivedWord) {
        word = receivedWord;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String newWord) {
        word = newWord;
    }
}
