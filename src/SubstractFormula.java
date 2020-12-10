public class SubstractFormula extends Formula {
    //减法子类构造函数
    public SubstractFormula() {
        this.generateFormula('-');
    }
    //检查结果是否大于0
    public boolean checkingFormula(int anInteger) {
        return anInteger >= 0;
    }
    //实现减法计算
    public int calculate(int left, int right) {
        return left - right;
    }
}

