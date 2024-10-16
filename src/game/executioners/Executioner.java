package game.executioners;

import game.HangmanGame;
import util.DisplayWord;
import util.HangmanDictionary;

public class Executioner {
    // word that is being guessed
    protected String mySecretWord;

    public Executioner(HangmanDictionary dictionary, int wordLength) {
        mySecretWord = makeSecretWord(dictionary, wordLength);
    }


    public DisplayWord getDisplayWord() {
        return new DisplayWord(mySecretWord);
    }

    public void printEnding() {
        System.out.println("The secret word was " + mySecretWord);
    }
    // Returns a secret word.
    private String makeSecretWord (HangmanDictionary dictionary, int wordLength) {
        return dictionary.getRandomWord(wordLength).toLowerCase();
    }

    // Returns true only if given guess is in the secret word.
    public boolean checkGuessInSecret (char guess, DisplayWord displayWord) {
        if (mySecretWord.indexOf(guess) >= 0) {
            displayWord.update(guess, mySecretWord);
            return true;
        }
        return false;
    }

    // Returns true only if the guesser has guessed all letters in the secret word.
    public boolean isGameWon (DisplayWord displayWord) {
        return displayWord.equals(mySecretWord);
    }
}
