import java.util.*;

public class SnakeGame {
    private int width, height;
    private int[][] food;
    private int foodindex;
    private Deque<int[]> snake;
    private Set<String> occupied;
    private Map<String, int[]> direction;

    public SnakeGame(int width, int height, int[][] food){
        this.width = width;
        this.height = height;
        this.food = food;
        this.foodindex = 0;
        this.snake = new LinkedList<>();
    }
    public int move(String direction){

    }
}
