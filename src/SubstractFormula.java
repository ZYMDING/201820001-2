
public class SubstractFormula extends Formula{

    public SubstractFormula() {
        generateFormula('-');
    }
    //检查结果约束
    public boolean checkingFormula(int anInteger) {
        return anInteger >= LOWER;
    }
    //减法计算实现
    public int calculate(int left, int right) {
        return left - right;
    }
}




