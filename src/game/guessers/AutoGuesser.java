package game.guessers;

public class AutoGuesser extends Guesser {
    private static final String LETTERS_ORDERED_BY_FREQUENCY = "etaoinshrldcumfpgwybvkxjqz";
    private int myIndex = 0;

    @Override
    public String makeGuess() {
        return Character.toString(LETTERS_ORDERED_BY_FREQUENCY.charAt(myIndex++));
    }
}
