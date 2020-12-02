import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public class FormulaTest{

    @org.junit.jupiter.api.Test
    void produceAddFormula() {
        int[] Add = new int[3];
        Add=Formula.produceAddFormula();
        if(!(0 <= Add[0] && Add[0] <= 100)){
            throw new RuntimeException("左运算数不在0-100的范围内");
        }
        if(!(0 <= Add[1] && Add[1] <= 100)){
            throw new RuntimeException("右运算数不在0-100的范围内");
        }
    }

    @org.junit.jupiter.api.Test
    void produceSubstactFormula() {
        int[] Sub = new int[3];
        Sub =Formula.produceSubstactFormula();
        if(!(0 <= Sub[0] && Sub[0] <= 100)){
            throw new RuntimeException("左运算数不在0-100的范围内");
        }
        if(!(0 <= Sub[1] && Sub[1] <= 100)){
            throw new RuntimeException("右运算数不在0-100的范围内");
        }
    }

    @org.junit.jupiter.api.Test
    void produceRandomFormula() {
        produceAddFormula();
        produceSubstactFormula();
    }
}
