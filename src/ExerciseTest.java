import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {
    Exercise bo = new Exercise();
    int[] a = {1, 2, 0};
    int[] b = {2, 4, 0};
    boolean x;
    @Test
    void produceExercises() {
        x = bo.check(a, b);
        if(x){
            throw new RuntimeException("算法会有重复");
        }
    }

    @Test
    void produceResult() {
        int result;
        int[] Random = new int[3];
        Random = Formula.produceRandomFormula();
        if(Random[2] == 0){
            result = Random[0] + Random[1];
            if(!(0 <= result && result <= 100)){
                throw new RuntimeException("加法结果不在0-100的范围内");
            }
        }else {
            result = Random[0] - Random[1];
            if(!(0 <= result && result <= 100)){
                throw new RuntimeException("减法结果不在0-100的范围内");
            }
        }
    }
}