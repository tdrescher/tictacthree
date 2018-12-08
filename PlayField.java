public class PlayField {
    PlayField(int dimension) {
        this.gameOver = false;
        this.dimension = dimension;
        if (dimension <= 10 && dimension >= 3) {
            tiles = new char[dimension][dimension];
        } else {
            tiles = null;
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++)
                tiles[i][j] = '▢';
        }
    }

    private char[][] tiles;
    private int dimension;

    boolean isGameOver() {
        return gameOver;
    }

    private boolean gameOver;

    void printField() {
        System.out.print("┏");
        for (int i = 0; i < dimension; i++) {
            System.out.print("━");
        }
        System.out.println("┓");
        for (int i = 0; i < dimension; i++) {
            System.out.print("┃");
            for (int j = 0; j < dimension; j++)
                System.out.print(tiles[i][j]);
            System.out.println("┃");
        }
        System.out.print("┗");
        for (int i = 0; i < dimension; i++) {
            System.out.print("━");
        }
        System.out.println("┛");
    }

    boolean makeMove(int x, int y, char playerSymbol) {
        if (tiles[x][y] == '▢') {
            tiles[x][y] = playerSymbol;
            checkWinForPlayer(playerSymbol);
            return true;
        } else {
            return false;
        }
    }

    private void checkWinForPlayer(char playerSymbol) {
        //check col
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tiles[j][i] != playerSymbol)
                    break;
                if (j == dimension - 1) {
                    gameOver = true;
                }
            }

        }
        //check row
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tiles[i][j] != playerSymbol)
                    break;
                if (j == dimension - 1) {
                    gameOver = true;
                }
            }
        }
        //check diag
        for (int i = 0; i < dimension; i++) {
            if (tiles[i][i] != playerSymbol)
                break;
            if (i == dimension - 1) {
                gameOver = true;
            }
        }
        //check anti diag
        for (int i = 0; i < dimension; i++) {
            if (tiles[i][(dimension - 1) - i] != playerSymbol)
                break;
            if (i == dimension - 1) {
                gameOver = true;
            }
        }
    }
}
