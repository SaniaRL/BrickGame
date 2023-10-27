import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BrickGame {

    //region<Labels and labelList>
    List<JLabel> labelList = new ArrayList<>();     //List to hold JLabels
    //JLabels:
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    JLabel label4 = new JLabel();
    JLabel label5 = new JLabel();
    JLabel label6 = new JLabel();
    JLabel label7 = new JLabel();
    JLabel label8 = new JLabel();
    JLabel label9 = new JLabel();
    JLabel label10 = new JLabel();
    JLabel label11 = new JLabel();
    JLabel label12 = new JLabel();
    JLabel label13 = new JLabel();
    JLabel label14 = new JLabel();
    JLabel label15 = new JLabel();
    JLabel label16 = new JLabel();

    public void addToLabelList(){
        labelList.add(label1);
        labelList.add(label2);
        labelList.add(label3);
        labelList.add(label4);
        labelList.add(label5);
        labelList.add(label6);
        labelList.add(label7);
        labelList.add(label8);
        labelList.add(label9);
        labelList.add(label10);
        labelList.add(label11);
        labelList.add(label12);
        labelList.add(label13);
        labelList.add(label14);
        labelList.add(label15);
        labelList.add(label16);
    }
    //endregion

    //region<Numbers & numberList>
    List<String> numberList = new ArrayList<>();    //NumberList in the right order
    List<String> randomNumberList = numberList;     //NumberList to randomize
    String one = "1";
    String two = "2";
    String three = "3";
    String four = "4";
    String five = "5";
    String six = "6";
    String seven = "7";
    String eight = "8";
    String nine = "9";
    String ten = "10";
    String eleven = "11";
    String twelve = "12";
    String thirteen = "13";
    String fourteen = "14";
    String fifteen = "15";
    String empty = " ";

    public void addToNumberList(){
        numberList.add(one);
        numberList.add(two);
        numberList.add(three);
        numberList.add(four);
        numberList.add(five);
        numberList.add(six);
        numberList.add(seven);
        numberList.add(eight);
        numberList.add(nine);
        numberList.add(ten);
        numberList.add(eleven);
        numberList.add(twelve);
        numberList.add(thirteen);
        numberList.add(fourteen);
        numberList.add(fifteen);
        numberList.add(empty);
    }
    //endregion

    public void run(){
        addToLabelList();
        addToNumberList();
    }

    public static void main(String[] args) {
        BrickGame brickGame = new BrickGame();
        brickGame.run();
    }
}