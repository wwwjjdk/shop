import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
          for(int i = 0; i< 9; i++){
              System.out.printf("тест номер %s \n",i+1);
              Game.getGame().createMatrix().getPrint().getSearch();
          }



    }
}
