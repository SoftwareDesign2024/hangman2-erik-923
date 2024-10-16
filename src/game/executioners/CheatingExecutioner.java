package game.executioners;

import game.HangmanGame;
import game.executioners.Executioner;
import util.DisplayWord;
import util.HangmanDictionary;

import java.util.*;

public class CheatingExecutioner extends Executioner {
    private List<String> myRemainingWords;

    public CheatingExecutioner(HangmanDictionary dictionary, int wordLength) {
        super(dictionary, wordLength);
        myRemainingWords = dictionary.getWords(wordLength);
    }

    public void cheat(char guess, DisplayWord displayWord) {
        // create template of guesses and find one with most matching remaining words
        HashMap<DisplayWord, List<String>> templatedWords = new HashMap<DisplayWord, List<String>>();
        for (String w : myRemainingWords) {
            DisplayWord template = new DisplayWord(displayWord);
            template.update(guess, w);
            if (!templatedWords.containsKey(template)) {
                templatedWords.put(template, new ArrayList<>());
            }
            templatedWords.get(template).add(w);
        }
        int max = 0;
        DisplayWord maxKey = new DisplayWord("");
        for (Map.Entry<DisplayWord, List<String>> entry : templatedWords.entrySet()) {
            //System.out.println(entry.getValue());
            if (entry.getValue().size() > max) {
                max = entry.getValue().size();
                maxKey = entry.getKey();
            }
        }

        // update secret word to match template of guesses
        myRemainingWords = templatedWords.get(maxKey);
        Collections.shuffle(myRemainingWords);
        mySecretWord = myRemainingWords.get(0);
        displayWord = maxKey;
    }

    @Override
    public boolean checkGuessInSecret (char guess, DisplayWord displayWord) {
        cheat(guess, displayWord);
        //Debugging purposes
        System.out.println("*** " + mySecretWord + " ***");
        if (mySecretWord.indexOf(guess) >= 0) {
            displayWord.update(guess, mySecretWord);
            return true;
        }
        return false;
    }
}
