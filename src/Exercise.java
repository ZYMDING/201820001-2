public class Exercise  {
    public int[][] exercises = new int[50][3];
    public int[][] produceExercises () {              //生成习题
        int[] array = new int[3];
        for (int i = 0; i < 50; i++) {
            if (i == 0) {
                exercises[i] =Formula.produceRandomFormula();
            } else {
                for (int j = 0; j < i; j++) {
                    array =Formula.produceRandomFormula();
                    while (check(array, exercises[j])) {
                        array =Formula.produceRandomFormula();
                    }
                    exercises[j] = array;
                }
            }
        }
        return exercises;
    }

    //程序生成没有重复算式的习题
    public boolean check(int[] a,int[] b) {

        if (((a[0] == b[0]) && a[1] == b[1]) && (a[2] == b[2])) {
            return true;
        } else if ((a[0] == b[1]) && (a[1] == b[0]) && (a[2] == 0) && (b[2] == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public void outputExercises(){                       //输出习题
        for(int i = 0;i < 50; i++){
            System.out.print((i+1) + ":\t");
            if (exercises[i][2] == 0){
                System.out.print(String.format("%2d + %2d = ", exercises[i][0], exercises[i][1])+'\t'+'\t');
            }else {
                System.out.print(String.format("%2d - %2d = ", exercises[i][0], exercises[i][1])+'\t'+'\t');
            }
            if((i+1) % 5 == 0)
                System.out.println();
        }
    }
    public void produceResult(){                         //输出答案
        int []result = new int[50];
        for (int i = 0; i < 50; i++){
            if (exercises[i][2] == 0){
                result[i] = exercises[i][0]+exercises[i][1];
            }else {
                result[i] = exercises[i][0]-exercises[i][1];
            }
        }
        for (int i = 0; i < 50; i ++){
            System.out.print(String.format("%2d : %3d ", (i + 1), result[i])+'\t'+'\t');
            if((i+1) % 5 == 0)
                System.out.println();
        }
    }
}

