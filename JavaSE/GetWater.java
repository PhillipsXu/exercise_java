public class GetWater {
    public static void main(String[] args) {
        System.out.println(getTotal(20));
    }

    static int total = 0;
    public static int getTotal(int money) {
        int bottle = money;
        int cap = money;
        total = money;
        while (bottle >= 2 || cap >= 3) {
            int _Bottle = bottle / 2;
            int _Cap = cap / 3;
            int backTotal = _Bottle + _Cap;

            int leftBottle = bottle % 2;
            int leftCap = cap % 3;

            bottle = leftBottle + backTotal;
            cap = leftCap + backTotal;
            total += backTotal;
        }
        return total;
    }
}
