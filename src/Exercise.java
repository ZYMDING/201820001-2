//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Random;

public class Exercise {
    private ArrayList<Formula> operationList = new ArrayList();
    private int current = 0;

    public Exercise() {
    }
    //产生加法算式
    public void generateAdditionExercise(int operationCount) {
        while(operationCount > 0) {
            AddFormula anOperation;
            do {
                anOperation = new AddFormula();
            } while(this.operationList.contains(anOperation));

            this.operationList.add(anOperation);
            --operationCount;
        }

    }

    //产生减法算式
    public void generateSubstractExercise(int operationCount) {
        while(operationCount > 0) {
            SubstractFormula anOperation;
            do {
                anOperation = new SubstractFormula();
            } while(this.operationList.contains(anOperation));

            this.operationList.add(anOperation);
            --operationCount;
        }

    }
    //产生加减法随机算式
    public void generateExercise(int operationCount) {
        for(Random random = new Random(); operationCount > 0; --operationCount) {
            Object anOperation;
            do {
                int opValue = random.nextInt(2);
                if (opValue == 0) {
                    anOperation = new AddFormula();
                } else {
                    anOperation = new SubstractFormula();
                }
            } while(this.operationList.contains(anOperation));

            this.operationList.add((Formula) anOperation);
        }

    }
    //判断是否有下一个算式
    public boolean hasNext() {
        return this.current <= this.operationList.size() - 1;
    }

    public Formula next() {
        return (Formula)this.operationList.get(this.current++);
    }
}


