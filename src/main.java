import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args){
        Scanner S = new Scanner(System.in);
        System.out.println("Welcome to the Blackjack AI");
        System.out.println("What is the maximum score allowed?");
        int maxScore = S.nextInt();
        System.out.println("How many decks of cards will be used?");
        int numDecks = S.nextInt();
        System.out.println("Start of the game!");
        Game game = new Game(maxScore,numDecks,true);
        while(game.isValidScore()){
            System.out.println("When you draw another card you have a "+game.calculateSafeChance()*100+"% chance of not going over "+game.getMaxScore()+".");
            System.out.println("1) Draw own card");
            System.out.println("2) Draw other card");
            int person = S.nextInt();
            System.out.println("Card value? Enter 1 for a random card.");
            int card = S.nextInt();
            if(card==1){
                System.out.println("A "+game.drawRandomCard(person==1)+" was drawn.");
            }else {
                game.drawCard(card, person==1);
            }
            if(person==1){System.out.println("Your score is now " + game.getCurrentScore() + "/" + game.getMaxScore() + ".");}
        }
    }

    public static void doTest(){
        List<List<Double>> rememberTrueData = new ArrayList<>();
        List<List<Double>> rememberFalseData = new ArrayList<>();

        // Run simulations
        Game game = new Game(false);
        for(double t=1;t>=0;t=round(t-0.1,1)) {
            List<Double> runData = new ArrayList<>();
            runData.add(t);
            runData.addAll(runGames(game, 1000, false, t));
            rememberFalseData.add(runData);
            System.out.println(runData);
        }
        System.out.println();
        game = new Game(true);
        for(double t=1;t>=0;t=round(t-0.1,1)) {
            List<Double> runData = new ArrayList<>();
            runData.add(t);
            runData.addAll(runGames(game, 1000, false, t));
            rememberTrueData.add(runData);
            System.out.println(runData);
        }
    }

    public static List<Double> runGames(Game baseGame, int amount, boolean doPrint, double threshold){
        double losses = 0;
        double wins = 0;
        List<Double> finalScores = new ArrayList<>();
        for(int n=1;n<=amount;n++){
            Game game = new Game(baseGame);
            if(doPrint){System.out.println(game.toString());};
            while(game.isValidScore() && game.calculateSafeChance()>threshold){
                if(doPrint) {
                    System.out.println(game.calculateSafeChance() * 100 + "% chance to not lose");
                    System.out.println("You draw a " + game.drawRandomCard(true));
                    System.out.println(game.toString());
                }else{
                    game.drawRandomCard(true);
                }
            }
            if(game.isValidScore()){
                wins++;
                if(doPrint){System.out.println("You did not lose!");}
            }else{
                losses++;
                if(doPrint){System.out.println("You lost!");}
            }
            finalScores.add((double) game.getCurrentScore());
        }
        // System.out.println("wins="+wins/amount*100+"%, losses="+losses/amount*100+"%");
        List<Double> R = new ArrayList<>();
        R.add(wins/amount);
        R.add(losses/amount);
        R.addAll(finalScores);
        return R;
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
