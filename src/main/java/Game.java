import java.util.Arrays;
import java.util.Random;

public class Game {
    private Random random = new Random();
    private int size = 7;
    private char field = '-';
    private char cactus = '*';
    private char dog = 'Щ';
    private char human = 'Ч';
    private char walk = 'X';
    private static Game game;
    char [][] matrix = new char[size][size];
    private int [] humanMatrixXY = {random.nextInt(1,size),random.nextInt(size)};

    public static Game getGame(){
        if(game == null){
            return  new Game();
        }
        return game;
    }

    public Game createMatrix(){
        for(int i = 0; i < size; i++){
            for (int j = 0; j<size; j++){
                matrix[i][j] = field;
            }
        }
        for(int k =0; k<10;k++){
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            matrix[x][y] = cactus;
        }
        matrix[0][0] = dog;
        matrix[humanMatrixXY[0]][humanMatrixXY[1]] = human;
       // System.out.println(Arrays.deepToString(matrix));
        return this;
    }

    public Game getPrint(){
        for(int i=0;i <size; i++){
            for (int j = 0;j < size; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        return this;
    }

    public Game getSearch(){
        matrix =findPath(matrix,humanMatrixXY[0],humanMatrixXY[1]);
        getPrint();
        return this;
    }

    private char[][] findPath(char[][] field, int x, int y) {
        final  int humanX = x;
        final int humanY = y;
        char[][] memory = new char[field.length][field.length];
        boolean[][] path = new boolean[field.length][field.length];
        char[][] result = new char[field.length][field.length];
        while (x != 0 || y != 0) {
            char direction = whereFrom(field, x, y, memory);
            if (direction == 'N') {
                throw new RuntimeException("что-то пошло не так...");
            } else if (direction == 'U') {
                path[x][y] = true;
                y--;
            } else if (direction == 'L') {
                path[x][y] = true;
                x--;
            }
        }
        for (int x1 = 0; x1 < field.length; x1++) {
            for (int y1 = 0; y1 < field.length; y1++ ) {
                if (x1 == humanX && y1 == humanY) {
                    result[x1][y1] = 'Ч';
                } else if (path[x1][y1]) {
                    result[x1][y1] = 'x';
                } else {
                    result[x1][y1] = field[x1][y1];
                }
            }


        }
        return result;
    }


    private char whereFrom(char[][] field, int x, int y, char[][] memory) {
        if (memory[x][y] != 0) return memory[x][y];
        if (x > 0) {
            int leftX = x - 1;
            int leftY = y;
            if (leftX == 0 && leftY == 0) {
                memory[x][y] = 'L';
                return 'L';
            }
            if (field[leftX][leftY] != '*') {
                if (whereFrom(field, leftX, leftY, memory) != 'N') {
                    memory[x][y] = 'L';
                    return 'L';
                }
            }
        }
        if (y > 0) {
            int upX = x;
            int upY = y - 1;
            if (upX == 0 && upY == 0) {
                memory[x][y] = 'U';
                return 'U';
            }
            if (field[upX][upY] != '*') {
                if (whereFrom(field, upX, upY, memory) != 'N') {
                    memory[x][y] = 'U';
                    return 'U';
                }
            }
        }
        memory[x][y] = 'N';
        return 'N';
    }


}
