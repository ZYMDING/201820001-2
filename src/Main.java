import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Exercise text = new Exercise() ;
        System.out.println("输入1输出习题，输入2输出答案，其他命令为无效指令");
        Scanner number = new Scanner(System.in);
        int order = number.nextInt();
        text.produceExercises();
        while(order == 1 || order == 2){
            if(order == 1){
                text.outputExercises();
            }
            else if(order == 2){
                text.produceResult();
            }
            else {
                System.out.println("输入指令无效");
            }
            order = number.nextInt();
        }
    }
}
