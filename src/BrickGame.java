import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrickGame extends JFrame{
    //Add instances of Classes in this Project
    Logic logic = new Logic();

    //regions are to hide the code in the region - so it's easier to navigate

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

    public void addToLabelList(){                   //Add Labels to ArrayList
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
    //Strings instead of int for two reason.
    // 1. The values are not used as values.
    // 2. We can use the method JLabel.addText() directly without typecasting.
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

    public void addToNumberList(){                  //Add Numbers(Strings) to ArrayList
        //Repeated code. We can ignore warnings. Or try to find a better practice. This works.
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
        setLayout(new BorderLayout());  //Set Layout
        setSize(400,400);   //Set Size - Should not be Square when we add a Button.
        setVisible(true);   //Make JFrame Visible
        add(backgroundPanel, BorderLayout.CENTER);  //Background Panel is positioned Center. Button can be NORTH or SOUTH?
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Make the Close Button work.
        setLocationRelativeTo(null);    //Make the Frame appear in the middle of the screen.

        backgroundPanel.setLayout(new GridLayout(4, 4));    //4 * 4 Layout for the 16 Labels.
        backgroundPanel.setBackground(Color.BLACK); //Set Color. Color not important. Can be whatever.
        backgroundPanel.setOpaque(true);    //Make Opaque so Background can be seen.

        //Add JLabels to Background Panel
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

    //Method to add Numbers to JLabels and att the same time change the appearance of the JLabels.
    //Center Alignment and Border are usable, Font and other design choices are less important.
    //In this way it gets done with less code.
    public void addNumbersToLabels(List<String> numberList){
        //First make sure the Lists have the same amount of content
        //Then loop through them and add each number to the corresponding JLabel
        for(int i = 0; i < labelList.size() && labelList.size() == numberList.size(); i++){
            JLabel label = labelList.get(i); //Get the JLabel at position i.
            String number = numberList.get(i); //Get the String number at position i.
            label.setText(number); //Set the String number to JLabel.
            label.setHorizontalAlignment(JLabel.CENTER); //Align Center on the X-axis.
            label.setVerticalAlignment(JLabel.CENTER); //Align Center on the Y-axis.
            label.setBackground(Color.LIGHT_GRAY); //Color Choice. Not important.
            label.setFont(new Font("Arial", Font.BOLD, 25)); //Font Choice. Not Important.
            label.setOpaque(true); //Make Opaque so Background will show.
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //Create Border that matches BackgroundPane.

            if(number.equals(" ")){
                label.setBackground(Color.BLACK); //Make the "empty" JLabel black.
            }
        }
    }
    //endregion

    public void run(){
        addToLabelList(); //Add JLabels To List.
        addToNumberList(); //Add Numbers To List
        addComponents(); //Add All Components
        Collections.shuffle(randomNumberList); //Randomize order/shuffle Strings to add on JLabels
        addNumbersToLabels(randomNumberList); //Add numbers to JLabels
    }

    public static void main(String[] args) {
        BrickGame brickGame = new BrickGame(); //Create new instance of BrickGame
        brickGame.run(); //Run the program
    }
}