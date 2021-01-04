//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Random;

public abstract class Formula {
    static final int UPPER = 100;
    static final int LOWER = 0;
    private int leftRandom = 0;
    private int rightRandom = 0;
    private char operator;
    private int value;

    public Formula() {
    }

    abstract int calculate(int var1, int var2);     //算式计算

    abstract boolean checkingFormula(int var1);     //检验算式结果

    protected void generateFormula(char symbol) {
        int[] Formula = new int[2];
        Random random = new Random();

        int result;
        do {
            Formula[0] = random.nextInt(101);
            Formula[1] = random.nextInt(101);
            result = this.calculate(Formula[0], Formula[1]);
        } while(!this.checkingFormula(result));

        this.leftRandom = Formula[0];
        this.rightRandom = Formula[1];
        this.operator = symbol;
        this.value = result;
    }

    public int getLeftRandom() {
        return this.leftRandom;
    }        //获取左操作数

    public int getRightRandom() {
        return this.rightRandom;
    }      //获取右操作数

    public char getOperator() {
        return this.operator;
    }           //获取操作符

    public int getResult() {
        return this.value;
    }                 //获取计算结果

    public boolean checkEquals(Formula anOperation) {             //判断算式是否相等
        return this.leftRandom == anOperation.getLeftRandom() && this.rightRandom == anOperation.getRightRandom() && this.operator == anOperation.getOperator();
    }

    public String toString() {
        String str = String.format("%3d %c %3d ", this.leftRandom, this.getOperator(), this.rightRandom);
        return str;
    }

    public String asString() {
        return this.toString() + " = ";
    }

    public String fullString() {
        return this.toString() + " = " + this.getResult();
    }

    private void unsafeConstructor(int left,int right, char anOperator){
        leftRandom = left;
        rightRandom = right;
        operator = anOperator;
        value = anOperator == '+'?left+right:left-right;
    }
    public void unsafeConstructor(int left,int right, int result, char anOperator){
        left = left;
        rightRandom = right;
        operator = anOperator;
        value = result;
    }
    public void unsafeConstructor(String eqString){
        int opPos=0;
        int length=eqString.length();
        // try to locate the position of the operator either '+' or '-'
        opPos=eqString.indexOf("+");
        if (opPos <= 0){
            opPos=eqString.indexOf("-");
        }
        unsafeConstructor(Integer.parseInt(eqString.substring(0,opPos)),
                Integer.parseInt(eqString.substring(opPos+1,length)),
                eqString.charAt(opPos));
    }
}


