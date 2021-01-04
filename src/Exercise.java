
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
@SuppressWarnings("unused")
public class Exercise implements Serializable{
    private static final long serialVersionUID = -1622536020144679558L;




        class Answers implements Serializable{
        private static final long serialVersionUID = -7833709422448085208L;
        String content;
        boolean correct;
        public Answers(){content = ""; correct = false;}
        public Answers(String ans, boolean cr){
            content = ans;
            correct = cr;
        }
    }
    private ArrayList<Formula> operationList = new ArrayList<Formula>();
    //	private List <String> answers = new ArrayList<>(); //第7章增加：用户填写的所有题目的答案
    private List <Answers> answers = new ArrayList<>(); //第7章增加：用户填写的所有题目的答案
    private int current=0; // only used for iterator
    private ExerciseType currentType; //第7章新添加题目类型，为保存用
    public ExerciseType getCurrentType() {
        return currentType;
    }
    public void setAnswer(int index, String ans){
        Formula op;
        op = operationList.get(index);
        String result = String.valueOf(op.getResult());
        String tans = ans.trim();
        answers.set(index, new Answers(tans,result.equals(tans)));
    }
    public String getAnswer(int index){
        return answers.get(index).content;
    }
    public void clearAnswers(){
        for(int i=0; i<answers.size(); i++)
            answers.set(i,new Answers("",false));
    }
    public int Correct(){
        int count=0;
        for(int i=0; i<answers.size(); i++){
            if(answers.get(i).correct) count++;
        }
        return count;
    }
    private void setCurrentType(ExerciseType type) {
        this.currentType = type;
    }
    public boolean getJudgement(int index){
        return answers.get(index).correct;
    }

    private Formula generateOperation(){
        Random random = new Random();
        int opValue = random.nextInt(2);
        if (opValue == 1){
            return new AddFormula();
        }
        return new SubstractFormula();
    }
    public void saveObject(String filename) throws ExerciseIOException{ //串行化存储对象
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExerciseIOException("存储对象失败");
        }
    }
    public static Exercise loadObject(String filename) throws ExerciseIOException{  //串行化载入对象
        Exercise exercise = null;
        try{
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            exercise = (Exercise)ois.readObject();
            ois.close();
            fis.close();
        }catch(Exception e){
            throw new ExerciseIOException("载入对象失败");
        }
        return exercise;
    }

    public void generateWithFormerType(int operationCount){
        switch(currentType){
            case ADD_AND_SUB:
                this.generateBinaryExercise(operationCount);
                break;
            case ADD_ONLY:
                this.generateAdditionExercise(operationCount);
                break;
            case SUB_ONLY:
                this.generateSubstractExercise(operationCount);
                break;
        }
    }
    public void generateAdditionExercise( int operationCount){
        Formula anOperation;
        setCurrentType(ExerciseType.ADD_ONLY); //设置题目类型
        operationList.clear();    //先清空再生成
        answers.clear();
        while (operationCount > 0 ){
            do {anOperation = new AddFormula();
            }while (operationList.contains(anOperation));
            operationList.add(anOperation);
            answers.add(new Answers("",false));
            //System.out.println("count="+ operationList.size());
            operationCount--;
        }
    }
    public void generateBinaryExercise(int operationCount){
        Formula anOperation;
        setCurrentType(ExerciseType.ADD_AND_SUB); //设置题目类型
        operationList.clear();    //先清空再生成
        answers.clear();
        while (operationCount > 0 ){
            do{anOperation = generateOperation();
            }while (operationList.contains(anOperation));
            operationList.add(anOperation);
            answers.add(new Answers("",false));
            operationCount--;
        }
    }
    public void generateSubstractExercise(int operationCount){
        Formula anOperation;
        /*
         * 根据第7章内容添加部分代码
         */
        setCurrentType(ExerciseType.SUB_ONLY); //设置题目类型
        operationList.clear();    //先清空再生成
        answers.clear();
        while (operationCount > 0 ){
            do{anOperation = new SubstractFormula();
            }while (operationList.contains(anOperation));
            operationList.add(anOperation);
            answers.add(new Answers("",false));
            operationCount--;
        }
    }
    // --- 2015-8-4: begin
    public void add(Formula anOperation){
        operationList.add(anOperation);
    }
    public boolean contains(Formula anOperation){
        return operationList.contains(anOperation);
    }
    public int length(){
        return operationList.size();
    }
    // write Exercise in a file, each Operation as "3+5"
    public void writeExercise(){
        File wfile = new File("eq2.txt");
        try{
            Writer out = new FileWriter(wfile, true);
            for (Formula op: operationList){
                out.write(op.toString()+",");
            }
            out.flush();
            out.close();
        }
        catch(IOException e){
            System.out.println("ERROR: "+e);
        }
    }
    public void writeCSVExercise(File aFile){
        try{
            Writer out = new FileWriter(aFile, true);
            for (Formula op: operationList){
                out.write(op.toString()+",");
            }
            out.flush();
            out.close();
        }
        catch(IOException e){
            System.out.println("ERROR: "+e);
        }
    }
    // read in a file each  as "3+5", and convert to Operation， add in Exercise
    public Exercise readCSVExercise(){
        Exercise exercise = new Exercise();
        String eqString;
        Formula op;
        Scanner sc = null;
        File rfile = new File("eq2.txt");
        try{
            sc = new Scanner(rfile);
            sc.useDelimiter(",\\n");

            while(sc.hasNext()){
                eqString = sc.next();
                op = new AddFormula();
                op.unsafeConstructor(eqString);
                exercise.add(op);
            }
        }
        catch(IOException e){
            System.out.println("ERROR: "+e);
        }

        return exercise;
    }
    public Exercise readCSVExercise(File aFile){
        Exercise exercise = new Exercise();
        String eqString;
        Formula op;
        try{
            Scanner sc = new Scanner(aFile);
            sc.useDelimiter(",");

            while(sc.hasNext()){
                // 处理任意的\t，\f， \n等
                eqString = sc.next().replaceAll("\\s", "");
                op = new AddFormula();
                op.unsafeConstructor(eqString);
                exercise.add(op);
            }
        }
        catch(IOException e){
            System.out.println("ERROR: "+e);
        }

        return exercise;
    }

    // 下面两个方法用于实现遍历数据
    public boolean hasNext(){ 		// 若有元素返回true，否则返回false，
        return current <= operationList.size()-1;
    }
    public Formula next(){		// 若有元素返回当前元素，移动到一个
        return operationList.get(current++);
    }
    public void printCurrent(){
        System.out.println("current="+current);
    }
    //根据第7章的需求新添加的一种获取元素的方法
    public Formula getOperation(int index){
        if(index < operationList.size()) return operationList.get(index);
        else return null;
    }
    // for test
    public void all(){
        for (Formula op:operationList){
            System.out.println(op.asString());
        }
    }
    // for test
    public void writeResults(File aFile){
        try{
            Writer out = new FileWriter(aFile, true);
            for (Formula op: operationList){
                out.write(op.getResult()+",");
            }
            out.flush();
            out.close();
        }
        catch(IOException e){
            System.out.println("ERROR: "+e);
        }
    }
}


