
public class AddFormula extends Formula{

    public AddFormula() {
         generateFormula('+');
    }
    //检查结果约束
    public boolean checkingFormula(int anInteger) {
        return anInteger <= UPPER;
    }

    //加法计算的实现
    public int calculate(int left, int right) {
        return left + right;
    }
}

