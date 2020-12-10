public class AddFormula extends Formula {
    //加法子类构造函数
    public AddFormula() {
        this.generateFormula('+');
    }
    //检查结果是否小于100
    public boolean checkingFormula(int anInteger) {
        return anInteger <= 100;
    }
    //实现加法计算
    public int calculate(int left, int right) {
        return left + right;
    }
}
