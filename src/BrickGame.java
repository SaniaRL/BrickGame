import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrickGame extends JFrame{

    //region<JFrame>
    JPanel backgroundPanel = new JPanel();          //Background JPanel to hold JLabels
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

    public void addComponents(){
        setLayout(new BorderLayout());
        setSize(400,400);
        setVisible(true);
        add(backgroundPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        backgroundPanel.setLayout(new GridLayout(4, 4));
        backgroundPanel.setBackground(Color.BLACK);
        backgroundPanel.setOpaque(true);

        backgroundPanel.add(label1);
        backgroundPanel.add(label2);
        backgroundPanel.add(label3);
        backgroundPanel.add(label4);
        backgroundPanel.add(label5);
        backgroundPanel.add(label6);
        backgroundPanel.add(label7);
        backgroundPanel.add(label8);
        backgroundPanel.add(label9);
        backgroundPanel.add(label10);
        backgroundPanel.add(label11);
        backgroundPanel.add(label12);
        backgroundPanel.add(label13);
        backgroundPanel.add(label14);
        backgroundPanel.add(label15);
        backgroundPanel.add(label16);
    }

    public void addNumbersToLabels(List<String> numberList){
        for(int i = 0; i < labelList.size() && labelList.size() == numberList.size(); i++){
            JLabel label = labelList.get(i);
            String number = numberList.get(i);
            label.setText(number);
            label.setAlignmentX(CENTER_ALIGNMENT);
            label.setAlignmentY(CENTER_ALIGNMENT);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setBackground(Color.LIGHT_GRAY);
            label.setFont(new Font("Arial", Font.BOLD, 25));
            label.setOpaque(true);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            if(number.equals(" ")){
                label.setBackground(Color.BLACK);

            }
        }
    }

    //endregion

    public void run(){
        addToLabelList();
        addToNumberList();
        addComponents();
        Collections.shuffle(randomNumberList);      //Randomize order/shuffle Strings to add on JLabels
        addNumbersToLabels(randomNumberList);       //Add numbers to JLabels
    }

    public static void main(String[] args) {
        BrickGame brickGame = new BrickGame();
        brickGame.run();
    }
}