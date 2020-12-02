import java.util.Random;

public class Formula {

    public static int[] produceAddFormula(){                   //生成加法算式
        Random random = new Random();
        int []AddFormula =new int[3];
        AddFormula[2] = 0;
        do {
            AddFormula[0] = (int)random.nextInt(101);
            AddFormula[1] = (int)random.nextInt(101);
        } while (AddFormula[0] + AddFormula[1] > 100);
        return AddFormula;
    }

    public static int[] produceSubstactFormula(){               //生成减法算式
        Random random = new Random();
        int []SubstactFormula = new int[3];
        SubstactFormula[2] = 1;
        do {
            SubstactFormula[0] = (int)random.nextInt(101);
            SubstactFormula[1] = (int)random.nextInt(101);
        } while (SubstactFormula[0] - SubstactFormula[1] < 0);
        return SubstactFormula;
    }

    public static int[] produceRandomFormula(){                //随机生成算式
        Random random = new Random();
        int symbol = (int)random.nextInt(2);
        if(symbol == 0){
            return produceAddFormula();
        }else {
            return  produceSubstactFormula();
        }
    }
}
