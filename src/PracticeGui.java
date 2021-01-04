
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PracticeGui extends JFrame {
    private int winWidth = 800;              //宽度
    private int winHeight = 400;             //高度
    private int formulaNum = 25;             //算式数量
    private int row = 5;                     //列数
    private int formulaWidth = 100;          //算式长度
    private int answerWidth = 30;            //答案长度
    private int formulaAnswerHeight = 20;    //算式、答案的高度
    private JTextField[] displayFormula;     //算式数组
    private JTextField[] displayAnswer;      //答案数组
    private JPanel jp;                       //面板容器
    private Exercise exercise;               //习题集
    private JTextArea textArea;              //文本域
    private int rightNum;                    //答案正确数量
    private int errorNum;                    //答案错误数量
    private float rightRate;                 //答案正确率
    private float errorRate;                 //答案错误率

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PracticeGui frame = new PracticeGui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PracticeGui() {

        //设置窗体标题
        setTitle("加减法运算");

        //设置窗体关闭方式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 150, winWidth, winHeight);

        //定制新面板替代旧面板
        jp = new JPanel();
        this.setContentPane(jp);

        //取消窗体布局管理器
        jp.setLayout(null);

        //上左下右逆时针方法依次指定四个方向距离边框的空白像素
        jp.setBorder(new EmptyBorder(10, 10, 10, 10));

        //设置颜色
        jp.setBackground(Color.gray);

        //创建“生成习题”按钮，设置大小，位置
        JButton generateBtn = new JButton("重新生成习题");
        generateBtn.setBounds(10, 10, 150, 30);
        jp.add(generateBtn);

        //利用匿名内部类实为“生成习题”按钮增加监听事件
        generateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {//arg0不可随意改变
                exercise.generateAdditionExercise(formulaNum);
                updateExercise();//重新生成后在界面上显示
            }
        });

        //创建“提交习题”按钮，设置大小，位置
        JButton btnSubmit = new JButton("提交习题");
        btnSubmit.setBounds(630, 10, 150, 30);
        jp.add(btnSubmit);

        //利用匿名内部类实为“提交习题”按钮增加监听事件
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//e不可随意改变
                judgeValue();//提交习题，判断正确性
            }
        });

        //下面为做题信息，描述总题述，正确率等
        textArea = new JTextArea();

        //设置文本信息
        textArea.setText("做题情况：\n" + "总题数："+ formulaNum);

        //内容不可修改
        textArea.setEditable(false);

        //定义位置，大小
        textArea.setBounds(50, 250, 690, 100);

        //将控件加入面板
        jp.add(textArea);

//    JSeparator separator = new JSeparator();
//    separator.setBounds(0, 80, 564, 10);
//    jp.add(separator);

        initializeExercise();

        updateExercise();

    }

    //初始化函数
    private void initializeExercise() {

        //初始化界面，没有数据
        exercise = new Exercise();
        exercise.generateAdditionExercise(formulaNum);

        //算式文本框控件数组
        displayFormula = new JTextField[formulaNum];

        //答案文本框控件数组
        displayAnswer = new JTextField[formulaNum];

        for (int i = 0; i < formulaNum; i++) {

            //单行文本框
            displayFormula[i] = new JTextField();

            //每行输出5个算式，总共输出10行
            //x,y为开始坐标，后两项为宽度，高度
            displayFormula[i].setBounds(50 + (i % row) * (formulaWidth + answerWidth + 10),//10为算式间隔
                    100 + (i / row) * (formulaAnswerHeight + 10),
                    formulaWidth,
                    formulaAnswerHeight);

            //设置文字对齐方式，左对齐
            displayFormula[i].setHorizontalAlignment(JTextField.RIGHT);

            //此控件不能被编辑
            displayFormula[i].setEditable(false);

            //将显示答案数组控件加入到面板容器内
            jp.add(displayFormula[i]);

            //进行类似于之前设置显示算式的过程
            displayAnswer[i] = new JTextField();

            displayAnswer[i].setBounds(50 + formulaWidth + (i % row) * (formulaWidth + answerWidth+ 10),
                    100 + (i / row) * (formulaAnswerHeight + 10),
                    answerWidth,
                    formulaAnswerHeight);
            //将控件加入面板
            jp.add(displayAnswer[i]);
        }

    }

    //将随机生成的题目画到面板上
    private void updateExercise(){
        Formula op;
        for(int i=0; i<formulaNum; i++){

            //根据索引值获取算式
            op = exercise.getOperation(i);

            //设置文本，asString为“ 1 + 1 = ”形式
            displayFormula[i].setText(op.asString());

            //未提交答案之前将答案文本框背景颜色设置为白色
            displayAnswer[i].setBackground(Color.WHITE);
            //将答案内容设置为空
            displayAnswer[i].setText("");
        }
        //设置文本信息
        textArea.setText("做题情况");
    }

    //判断结果
    private void judgeValue(){
        Formula op;
        //将正确答案数量和错误答案数量都置为0
        rightNum = errorNum = 0;

        for(int i=0; i<formulaNum; i++){
            //根据索引值获取算式
            op = exercise.getOperation(i);
            String result = String.valueOf(op.getResult());
            //获得的文本去除空格
            String answer = displayAnswer[i].getText().trim();
            //判断输入的答案answer与系统给出的答案op.getValue的值是否相等
            if(result.equals(answer)){//结果正确
                //该答案文本框内背景颜色变为绿色
                displayAnswer[i].setBackground(Color.GREEN);
                //正确答案数量+1
                rightNum++;
            }else{
                //该答案文本框内背景颜色变为红色色
                displayAnswer[i].setBackground(Color.RED);
                //错误答案数量+1
                errorNum++;
            }
        }

        rightRate = 100 * rightNum / formulaNum;            //计算答案正确率

        errorRate = 100 * errorNum / formulaNum;            //计算答案错误率

        //设置文本信息
        textArea.setText("做题情况：\n" +"总共" + formulaNum + "道题：" + "总共做对了" + rightNum + "道题，做错了" + errorNum + "道题\n"
                         +"答题正确率为" + rightRate + "%,错误率为" + errorRate + "%") ;
    }

}
