//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class ExerciseSheet {
    private static final short COLUMN_NUM = 5;

    public ExerciseSheet() {
    }

    public void formattedDisplay(Exercise ex, int columns) {
        int column = 1;

        for(int count = 1; ex.hasNext(); ++count) {
            if (column > columns) {
                System.out.println();
                column = 1;
            }

            System.out.printf("%2d.  ", count);
            System.out.print(ex.next().asString() + "\t\t");
            ++column;
        }

        System.out.println();
    }

    public void formattedDisplay(Exercise ex) {
        this.formattedDisplay(ex, 5);
    }

    public static void main(String[] args) {
        ExerciseSheet sheet = new ExerciseSheet();
        Exercise exerciseAdd = new Exercise();
        Exercise exerciseSub = new Exercise();
        Exercise exerciseMix = new Exercise();
        exerciseAdd.generateAdditionExercise(50);       //产生加法算式习题
        System.out.println("加法算式习题");
        sheet.formattedDisplay(exerciseAdd, 5);
        System.out.println("减法算式习题");                             //产生减法算式习题
        exerciseSub.generateSubstractExercise(50);
        sheet.formattedDisplay(exerciseSub, 5);
        System.out.println("加减法混合算式习题");
        exerciseMix.generateWithFormerType(50);                 //产生随机算式习题
        sheet.formattedDisplay(exerciseMix, 5);
    }

}
