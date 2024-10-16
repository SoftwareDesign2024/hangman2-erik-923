package game;

import game.executioners.Executioner;
import game.guessers.Guesser;
import util.DisplayWord;
import util.HangmanDictionary;


/**
 * This class represents the traditional word-guessing game Hangman
 * that plays interactively with the user.
 *
 * @author Robert C. Duvall
 * @author Shannon Duvall
 */
public class HangmanGame {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    // how many guesses are remaining
    private int myNumGuessesLeft;
    // what is shown to the user
    private DisplayWord myDisplayWord;

    private Executioner executioner;
    private Guesser guesser;



    /**
     * Create Hangman game with the given dictionary of words to play a game with words
     * of the given length and giving the user the given number of chances.
     */
    public HangmanGame (int numGuesses, Executioner executioner, Guesser guesser) {
        myNumGuessesLeft = numGuesses;
        this.executioner = executioner;
        myDisplayWord = executioner.getDisplayWord();
        this.guesser = guesser;
    }

    public static String getALPHABET() {
        return ALPHABET;
    }

    public DisplayWord getMyDisplayWord() {
        return myDisplayWord;
    }

    /**
     * Play one complete game.
     */
    public void play () {
        boolean gameOver = false;
        while (!gameOver) {
            printStatus();

            String guess = guesser.makeGuess();
            if (guess.length() == 1 && Character.isAlphabetic(guess.charAt(0))) {
                processGuess(guess.toLowerCase().charAt(0));
                if (isGameLost()) {
                    System.out.println("YOU ARE HUNG!!!");
                    gameOver = true;
                }
                else if (executioner.isGameWon(myDisplayWord)) {
                    System.out.println("YOU WIN!!!");
                    gameOver = true;
                }
            }
            else {
                System.out.println("Please enter a single letter ...");
            }
        }
        executioner.printEnding();
    }


    // Process a guess by updating the necessary internal state.
    private void processGuess(char guess) {
        // do not count repeated guess as a miss
        int index = guesser.getMyLettersLeftToGuess().indexOf("" + guess);
        if (index >= 0) {
            guesser.recordGuess(index);
            if (! executioner.checkGuessInSecret(guess, myDisplayWord)) {
                myNumGuessesLeft -= 1;
            }
        }
    }

    // Returns true only if the guesser has used up all their chances to guess.
    private boolean isGameLost () {
        return myNumGuessesLeft == 0;
    }

    // Print game stats
    private void printStatus () {
        System.out.println(myDisplayWord);
        System.out.println("# misses left = " + myNumGuessesLeft);
        System.out.println("letters not yet guessed = " + guesser.getMyLettersLeftToGuess());
        // NOT PUBLIC, but makes it easier to test
        //System.out.println("*** " + mySecretWord);
        System.out.println();
    }
}
