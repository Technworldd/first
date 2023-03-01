import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CoinTossGame {
    private final int NUM_TOSSES = 10;
    private final List<Boolean> player1Tosses;
    private final List<Boolean> player2Tosses;
    private int player1Wins;
    private int player2Wins;
    private int ties;

    public CoinTossGame() {
        player1Tosses = new ArrayList<>();
        player2Tosses = new ArrayList<>();
        player1Wins = 0;
        player2Wins = 0;
        ties = 0;
    }

    public void playRound(int roundNumber) {
        Random rand = new Random();
        int player1Heads = 0;
        int player2Heads = 0;

        System.out.println("Player 1 tosses:");
        for (int i = 0; i < NUM_TOSSES; i++) {
            boolean player1Toss = rand.nextBoolean();
            player1Tosses.add(player1Toss);
            System.out.print(player1Toss ? "Head " : "Tail ");

            if (player1Toss) {
                player1Heads++;
            }
        }

        System.out.println("\nPlayer 2 tosses:");
        for (int i = 0; i < NUM_TOSSES; i++) {
            boolean player2Toss = rand.nextBoolean();
            player2Tosses.add(player2Toss);
            System.out.print(player2Toss ? "Head " : "Tail ");

            if (player2Toss) {
                player2Heads++;
            }
        }

        String roundResult = String.format("\nPlayer 1 heads are %d\nPlayer 2 heads are %d\n", player1Heads,
                player2Heads);
        String winner;

        if (player1Heads > player2Heads) {
            winner = "Player 1";
            player1Wins++;
        } else if (player2Heads > player1Heads) {
            winner = "Player 2";
            player2Wins++;
        } else {
            winner = "Tie";
            ties++;
        }

        System.out.println(roundResult + "Winner is " + winner + "\n");
        writeRoundResultToFile(roundResult, winner, roundNumber);
    }

    private void writeRoundResultToFile(String roundResult, String winner, int roundNumber) {

        try {

            FileWriter csvWriter = new FileWriter("toss_results.csv", true);
            csvWriter.append("\n");
            csvWriter.write("Round " + roundNumber);
            csvWriter.append("\n");
            csvWriter.write("Player1");
            csvWriter.append(",");
            csvWriter.append(getFormattedTosses(player1Tosses)).append("\n");
            csvWriter.write("Player2");
            csvWriter.append(",");
            csvWriter.append(getFormattedTosses(player2Tosses));
            csvWriter.append(roundResult);
            csvWriter.write("Winner is ");
            csvWriter.append(winner).append("\n").append("\n");
            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            System.out.println("Failed to write to CSV file");
        }
    }

    private String getFormattedTosses(List<Boolean> tosses) {
        StringBuilder sb = new StringBuilder();

        for (Boolean toss : tosses) {
            sb.append(toss ? "Head" : "Tail").append(", ");
        }

        return sb.toString().trim().substring(0, sb.length() - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many rounds do you want to play? ");
        int numRounds = scanner.nextInt();

        CoinTossGame game = new CoinTossGame();

        for (int i = 1; i <= numRounds; i++) {
            System.out.println("Round " + i + ":");
            game.playRound(i);
        }

        System.out.println("Winning Count of Player 1: " + game.player1Wins);
        System.out.println("Winning Count of Player 2: " + game.player2Wins);
        System.out.println("Tie Count: " + game.ties);

        int player1Wins = 0;
        int player2Wins = 0;
        int WINNER1 = 0;
        int WINNER2 = 0;
        int ties = 0;

        for (Boolean toss : game.player1Tosses) {
            if (toss) {
                player1Wins++;
                WINNER1++;
            }
        }

        for (Boolean toss : game.player2Tosses) {
            if (toss) {
                player2Wins++;
                WINNER2++;
            }
        }

        ties = numRounds - player1Wins - player2Wins;

        System.out.println("\nWinning Count of Player 1: " + player1Wins);
        System.out.println("Winning Count of Player 2: " + player2Wins);
        System.out.println("Tie Count: " + ties);

        if (WINNER1 > WINNER2) {
            System.out.println("Champion is Player 1");
        } else if (WINNER2 > WINNER1) {
            System.out.println("Champion is Player 2");
        } else {
            System.out.println("The game ended in a tie");
        }
    }
}