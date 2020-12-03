import org.junit.jupiter.api.Test;

class FormulaTest {
    AddFormula addtest = new AddFormula();
    @Test
    void checkEquals() {
        addtest.equals(addtest);

    }

    @Test
    String testToString() {
        String add;
        add = String.format("%3d %c %3d ", addtest.getLeftRandom(), addtest.getOperator(), addtest.getRightRandom());
        return add;
    }

    @Test
    String asString() {
        return addtest.toString() + " = ";


    }

    @Test
    String fullString() {
        return addtest.toString() + " = " + addtest.getResult();
    }
}