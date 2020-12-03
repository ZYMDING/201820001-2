public class ExerciseSheet {

    private static final short COLUMN_NUM = 5;
    //格式化输出
    public void formattedDisplay(Exercise ex, int columns) {
        int column  =1;
        int count = 1;
        while(ex.hasNext()) {
            if(column > columns) {
                System.out.println();
                column = 1;
            }
            System.out.printf("%2d.  ", count);
            System.out.print((ex.next()).asString() + "\t"+"\t");
            column++;
            count++;
        }
        System.out.println();
    }

    public void formattedDisplay(Exercise ex) {
        formattedDisplay(ex, COLUMN_NUM);
    }

    public static void main(String[] args) {
        ExerciseSheet sheet = new ExerciseSheet();
        Exercise exerciseAdd = new Exercise();
        Exercise exerciseSub = new Exercise();
        Exercise exerciseMix = new Exercise();

        exerciseAdd.generateAdditionExercise(50);
        System.out.println("加法算式习题");
        sheet.formattedDisplay(exerciseAdd, 5);

        System.out.println("减法算式习题");
        exerciseSub.generateSubstractExercise(50);
        sheet.formattedDisplay(exerciseSub, 5);

        System.out.println("加减法混合算式习题");
        exerciseMix.generateExercise(50);
        sheet.formattedDisplay(exerciseMix, 5);
    }
}



