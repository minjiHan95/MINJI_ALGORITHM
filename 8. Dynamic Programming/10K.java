import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public int findMaximumCost(int index, int weight, Knapsack[] myKnapsack) {

        //해당 냅색이 남은 용량보다 크다면 선택할 수 없다
        //index가 0보다 작다면 (범위 벗어나면) 초기값은 0으로 셋
        if (index < 0) {
            return 0;
        } else if (weight < myKnapsack[index].weight) {
            return 0;
        }
        //해당하는 index의 냅색을 선택할 경우
        int includeX = findMaximumCost(index - 1, weight - myKnapsack[index].weight, myKnapsack) + myKnapsack[index].price;
        //선택하지 않을 경우
        int excludeX = findMaximumCost(index - 1, weight, myKnapsack);

        int answer = Math.max(includeX, excludeX);
        return answer;
    }

    public static void main(String[] args) {
        Main app = new Main();
        int x = scanner.nextInt();
        int totalWeight = scanner.nextInt();

        Knapsack[] myKnapsack = new Knapsack[x];

        for (int i = 0; i < x; i++) {
            int weight = scanner.nextInt();
            int price = scanner.nextInt();
            myKnapsack[i] = new Knapsack(weight, price);
        }
        int answer = app.findMaximumCost(x - 1, totalWeight, myKnapsack);
        System.out.println(answer);

    }
}

class Knapsack {
    int weight;
    int price;

    public Knapsack(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
}