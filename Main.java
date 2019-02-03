import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    private static char playerOne;
    private static char playerTwo;
    private static char playerThree;
    private static int boardSize;

    public static void main(String[] args) {
        Main main = new Main();
        Random random = new Random();
        main.loadConfig();
        PlayField field = new PlayField(boardSize);

        while(!field.isGameOver()) {
            field.printField();
            int move;
            do {
                System.out.println("Make your move, player one!");
                Scanner in = new Scanner(System.in);
                move = in.nextInt();
            } while (!field.makeMove(move / boardSize, move % boardSize, playerOne));
            if(field.isGameOver()) {
                System.out.println("Player one WINS");
                break; // out of main loop
            }
            field.printField();
            do {
                System.out.println("Make your move, player two!");
                Scanner in = new Scanner(System.in);
                move = in.nextInt();
            } while (!field.makeMove(move / boardSize, move % boardSize, playerTwo));
            if(field.isGameOver()) {
                System.out.println("Player two WINS");
                break; // out of main loop
            }
            System.out.println("Computer is thinking...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            do {
                move = random.nextInt(boardSize*boardSize);
            } while (!field.makeMove(move / boardSize, move % boardSize, playerThree));
            if(field.isGameOver()) {
                System.out.println("Computer WINS");
                break; // out of main loop
            }
        }
        System.out.println("Game Over, Yeah!");
    }

    private void loadConfig() {
        Properties prop = new Properties();
        try {
            prop.load(Main.class.getClassLoader().getResourceAsStream("config.properties"));

            playerOne = prop.getProperty("playerOne").charAt(0);
            playerTwo = prop.getProperty("playerTwo").charAt(0);
            playerThree = prop.getProperty("playerThree").charAt(0);
            boardSize = Integer.parseInt(prop.getProperty("boardSize"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
