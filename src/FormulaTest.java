
import org.junit.jupiter.api.Test;

class FormulaTest {
    AddFormula addtest = new AddFormula();

    FormulaTest() {
    }

    @Test
    void checkEquals() {
        this.addtest.equals(this.addtest);
    }

    @Test
    String testToString() {
        String add = String.format("%3d %c %3d ", this.addtest.getLeftRandom(), this.addtest.getOperator(), this.addtest.getRightRandom());
        return add;
    }

    @Test
    String asString() {
        return this.addtest.toString() + " = ";
    }

    @Test
    String fullString() {
        return this.addtest.toString() + " = " + this.addtest.getResult();
    }
}