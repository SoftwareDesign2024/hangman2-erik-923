package game.guessers;

import game.HangmanGame;
import util.ConsoleReader;

public class Guesser {
    // tracks letters guessed
    private StringBuilder myLettersLeftToGuess;

    public Guesser() {
        this.myLettersLeftToGuess = new StringBuilder(HangmanGame.ALPHABET);
    }

    public String makeGuess() {
        return ConsoleReader.promptString("Make a guess: ");
    }

    // Record that a specific letter was guessed
    public void recordGuess (int index) {
        myLettersLeftToGuess.deleteCharAt(index);
    }

    public StringBuilder getMyLettersLeftToGuess() {return myLettersLeftToGuess;}
}
