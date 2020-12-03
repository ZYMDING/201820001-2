import java.util.Random;
public abstract class Formula{

    static final int UPPER = 100;
    static final int LOWER = 0;
    private int leftRandom = 0;     // 左操作数
    private int rightRandom = 0;    // 右操作数
    private char operator;          // 操作符
    private int  value;             // 算式的结果

    abstract int calculate(int left, int right);  //抽象方法：算式的计算，由子类实现
    abstract boolean checkingFormula(int anInteger); // 抽象方法，检验计算结果，子类负责实现

    protected void generateFormula(char symbol) {
        int[] Formula = new int[2];
        int result;
        Random random = new Random();
        do {
            Formula[0] = random.nextInt(UPPER+1);   //产生左操作数
            Formula[1] = random.nextInt(UPPER+1);   //产生右操作数
            result = calculate(Formula[0], Formula[1]);
        }while(!checkingFormula(result));
        leftRandom = Formula[0];
        rightRandom = Formula[1];
        operator = symbol;
        value = result;
    }

    public int getLeftRandom() {
        return leftRandom;
    }

    public int getRightRandom() {
        return rightRandom;
    }

    public char getOperator() {
        return operator;
    }

    public int getResult() {
        return value;
    }
    //比较两个算式是否相等
    public boolean checkEquals(Formula anOperation) {
        return leftRandom == anOperation.getLeftRandom() &&
                rightRandom == anOperation.getRightRandom() &&
                operator == anOperation.getOperator();
    }

    public String toString() {
        String str;
        str = String.format("%3d %c %3d ", leftRandom, getOperator(), rightRandom);

        return str;
    }

    public String asString() {
        return toString() + " = ";
    }

    public String fullString() {
        return toString() + " = " + getResult();
    }

}


