import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private int maxScore;
    private int currentScore;
    private List<Integer> currentDeck;
    private List<Integer> cardsDrawn;
    private List<Integer> originalDeck;
    private boolean rememberDeck;

    private Random random = new Random();

    public Game(){
        maxScore = 21;
        currentScore = 0;
        currentDeck = createStandardDeck(1);
        cardsDrawn = new ArrayList<>();
        originalDeck = currentDeck;
        rememberDeck = true;
    }

    public Game(int _maxScore, int numDecks, boolean _rememberDeck){
        maxScore = _maxScore;
        currentScore = 0;
        currentDeck = createStandardDeck(numDecks);
        cardsDrawn = new ArrayList<>();
        originalDeck = currentDeck;
        rememberDeck = _rememberDeck;
    }

    public Game(boolean _rememberDeck){
        maxScore = 21;
        currentScore = 0;
        currentDeck = createStandardDeck(1);
        cardsDrawn = new ArrayList<>();
        originalDeck = currentDeck;
        rememberDeck = _rememberDeck;
    }

    public Game(Game game){
        maxScore = game.getMaxScore();
        currentScore = game.getCurrentScore();
        currentDeck = new ArrayList<>(game.getCurrentDeck());
        cardsDrawn = new ArrayList<>(game.getCardsDrawn());
        originalDeck = new ArrayList<>(game.getOriginalDeck());
        rememberDeck = game.isRememberDeck();
    }
    public Game(int _maxScore, int _currentScore, List<Integer> _currentDeck, List<Integer> _cardsDrawn, List<Integer> _originalDeck, boolean _rememberDeck){
        maxScore = _maxScore;
        currentScore = _currentScore;
        currentDeck = _currentDeck;
        cardsDrawn = _cardsDrawn;
        originalDeck = _originalDeck;
        rememberDeck = _rememberDeck;
    }

    private List<Integer> createStandardDeck(int numDecks) {
        List<Integer> deck = new ArrayList<>();
        for(int i=1;i<=numDecks;i++){
            for(int j=2;j<=11;j++){
                for(int k=1;k<=4;k++){
                    deck.add(j);
                    if(j==10){
                        deck.add(j);
                        deck.add(j);
                        deck.add(j);
                    }
                }
            }
        }
        return deck;
    }

    public int drawRandomCard(boolean isSelf){
        int id = random.nextInt(currentDeck.size());
        int card = currentDeck.get(id);
        if(card==11 && isSelf && currentScore+card>maxScore){
            card = 1;
        }
        currentDeck.remove(id);
        if(isSelf){
            cardsDrawn.add(card);
            currentScore += card;
        }
        return card;
    }

    public int drawCard(int card, boolean isSelf){
        if(!currentDeck.contains(card)){return -1;}
        int id = currentDeck.indexOf(card);
        if(card==11 && isSelf && currentScore+card>maxScore){
            card = 1;
        }
        currentDeck.remove(id);
        if(isSelf){
            cardsDrawn.add(card);
            currentScore += card;
        }
        return card;
    }

    public double calculateSafeChance(){
        double chance = 0;
        if(rememberDeck){
            for(int card : currentDeck){if(currentScore+card<=maxScore){chance++;}}
            chance /= currentDeck.size();
        }else{
            for(int card : originalDeck){if(currentScore+card<=maxScore){chance++;}}
            chance /= originalDeck.size();
        }
        return chance;
    }

    public boolean isValidScore(){
        return currentScore <= maxScore;
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

    public List<Integer> getOriginalDeck() {
        return originalDeck;
    }

    @Override
    public String toString(){
        return "Game{score=["+currentScore+"/"+maxScore+"], drawn="+cardsDrawn+", rememberDeck=["+rememberDeck+"]}";
    }
}
