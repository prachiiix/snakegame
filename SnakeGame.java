import java.util.*;

// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Main {
    static class SnakeGame {
        private int width, height;
        private int[][] food;
        private int foodIndex;
        private Deque<int[]> snake; // snake body, front is head
        private Set<String> occupied; // set of positions occupied by the snake
        private Map<String, int[]> directions;

        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            this.foodIndex = 0;
            this.snake = new LinkedList<>();
            this.occupied = new HashSet<>();

            // Initialize snake at top-left corner
            snake.offerFirst(new int[]{0, 0});
            occupied.add("0,0");

            // Define movement directions
            this.directions = new HashMap<>();
            directions.put("U", new int[]{-1, 0});
            directions.put("D", new int[]{1, 0});
            directions.put("L", new int[]{0, -1});
            directions.put("R", new int[]{0, 1});
        }

        public int move(String dir) {
            int[] move = directions.get(dir);
            int[] head = snake.peekFirst();
            int newRow = head[0] + move[0];
            int newCol = head[1] + move[1];
            String newPos = newRow + "," + newCol;

            // Check wall collision
            if (newRow < 0 || newRow >= height || newCol < 0 || newCol >= width) {
                return -1;
            }

            // Check self collision (except tail, which may move forward)
            int[] tail = snake.peekLast();
            occupied.remove(tail[0] + "," + tail[1]); // temporarily remove tail
            if (occupied.contains(newPos)) {
                return -1;
            }

            // Add new head
            snake.offerFirst(new int[]{newRow, newCol});
            occupied.add(newPos);

            // Check if food is eaten
            if (foodIndex < food.length && newRow == food[foodIndex][0] && newCol == food[foodIndex][1]) {
                foodIndex++; // Eat food, don't remove tail
                occupied.add(tail[0] + "," + tail[1]); // re-add tail (snake grows)
            } else {
                // Normal move: remove tail
                snake.pollLast();
            }

            return snake.size() - 1; // Score is length minus 1
        }
    }

    public static void main(String[] args) {
        int[][] food = {{1, 2}, {0, 1}};
        SnakeGame game = new SnakeGame(3, 2, food);

        System.out.println(game.move("R")); // Output: 0
        System.out.println(game.move("D")); // Output: 0
        System.out.println(game.move("R")); // Output: 1 (eats food)
        System.out.println(game.move("U")); // Output: 1
        System.out.println(game.move("L")); // Output: 2 (eats food)
        System.out.println(game.move("U")); // Output: -1 (hits wall)
    }
}
