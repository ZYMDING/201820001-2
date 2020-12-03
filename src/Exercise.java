 import java.util.ArrayList;
import java.util.Random;

public class Exercise {
    // 存放算式的动态数组
    private ArrayList<Formula> operationList = new ArrayList<Formula>();
    private int current = 0; // 动态数组的游标

    //产生加法算式习题
    public void generateAdditionExercise(int operationCount) {
        Formula anOperation;
        while(operationCount > 0) {
            do {
                anOperation = new AddFormula();
            }while(operationList.contains(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
    }

    // 产生减法算式习题
    public void generateSubstractExercise(int operationCount) {
        Formula anOperation;
        while(operationCount > 0) {
            do {
                anOperation = new SubstractFormula();
            }while(operationList.contains(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
    }

    //产生混合习题
    public void generateExercise(int operationCount) {
        Formula anOperation;
        Random random = new Random();
        while(operationCount > 0) {
            do {
                int opValue = random.nextInt(2);
                if(opValue == 0)
                    anOperation = new AddFormula();
                else
                    anOperation = new SubstractFormula();
            }while(operationList.contains(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
    }

    public boolean hasNext() {
        return current <= operationList.size()-1;
    }

    public Formula next() {
        return operationList.get(current++);
    }

}



