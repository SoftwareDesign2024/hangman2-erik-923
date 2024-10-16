import game.executioners.CheatingExecutioner;
import game.executioners.Executioner;
import game.guessers.AutoGuesser;
import game.guessers.Guesser;
import util.HangmanDictionary;
import game.HangmanGame;


/**
 * This class launches the Hangman game and plays once.
 * 
 * @author Michael Hewner
 * @author Mac Mason
 * @author Robert C. Duvall
 * @author Shannon Duvall
 */
public class Main {
    public static final String DICTIONARY = "data/lowerwords.txt";
    public static final int NUM_LETTERS = 6;
    public static final int NUM_MISSES = 8;


    public static void main (String[] args) {
        Executioner executioner = new CheatingExecutioner(new HangmanDictionary(DICTIONARY), NUM_LETTERS);
        Guesser guesser = new AutoGuesser();
        new HangmanGame(NUM_MISSES, executioner, guesser).play();
    }
}
