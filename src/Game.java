import java.util.ArrayList;
import java.util.List;

public class Game {
    private int maxScore;
    private int currentScore;
    private List<Integer> currentDeck;
    private List<Integer> cardsDrawn;
    private boolean rememberDeck;

    public Game(){
        maxScore = 21;
        currentScore = 0;
        currentDeck = createStandardDeck();
        cardsDrawn = new ArrayList<>();
        rememberDeck = true;
    }

    private List<Integer> createStandardDeck() {
        return new ArrayList<>();
    }
}
