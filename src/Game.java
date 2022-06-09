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
        currentDeck = createStandardDeck(1);
        cardsDrawn = new ArrayList<>();
        rememberDeck = true;
    }

    private List<Integer> createStandardDeck(int numDecks) {
        List<Integer> deck = new ArrayList<>();
        for(int i=1;i<=numDecks;i++){
            for(int j=1;j<=4;j++){
                for(int k=2;k<=11;k++){
                    deck.add(k);
                    if(k==10){
                        deck.add(k);
                        deck.add(k);
                        deck.add(k);
                    }
                }
            }
        }
        return deck;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public List<Integer> getCurrentDeck() {
        return currentDeck;
    }

    public List<Integer> getCardsDrawn() {
        return cardsDrawn;
    }

    public boolean isRememberDeck() {
        return rememberDeck;
    }

    @Override
    public String toString(){
        return "Game{score=["+currentScore+"/"+maxScore+"], drawn="+cardsDrawn+", rememberDeck=["+rememberDeck+"]}";
    }
}
