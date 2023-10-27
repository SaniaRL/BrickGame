import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BrickGame extends JFrame implements MouseListener{

    //regions are to hide the code in the region - so it's easier to navigate

    //region<JFrame>
    JPanel gamePanel = new JPanel(); //Background JPanel to hold JLabels
    List<JLabel> labelList = new ArrayList<>(); //List to hold JLabels
    List<String> numberList = new ArrayList<>(); //NumberList in the right order
    List<String> randomNumberList = numberList; //NumberList to randomize
    //Strings instead of int for two reason.
    // 1. The values are not used as values.
    // 2. We can use the method JLabel.addText() directly without typecasting.

    //Updated code to avoid repetition, and keep code short and easy to manage.
    public void addToNumberList(){
        for(int i = 0; i < 16; i++){
            if(i == 0){
                numberList.add(" ");
            }
            else{
                numberList.add(String.valueOf(i));
            }
        }
        Collections.shuffle(randomNumberList);
    }

    //Updated code to avoid repetition, and keep code short and easy to manage.
    public void addToLabelList(){
        for(int i = 0; i < 16; i++){
            JLabel label = new JLabel();
            label.setText(numberList.get(i));
            labelList.add(label);
            gamePanel.add(label);

            label.setHorizontalAlignment(JLabel.CENTER); //Align Center on the X-axis.
            label.setVerticalAlignment(JLabel.CENTER); //Align Center on the Y-axis.
            label.setBackground(Color.LIGHT_GRAY); //Color Choice. Not important.
            label.setFont(new Font("Arial", Font.BOLD, 25)); //Font Choice. Not Important.
            label.setOpaque(true); //Make Opaque so Background will show.
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //Create Border that matches BackgroundPane.
            if(label.getText().equals(" ")){
                label.setBackground(Color.BLACK); //Make the "empty" JLabel black.
            }
        }
    }

    public void addComponents(){
        setLayout(new BorderLayout());  //Set Layout
        setSize(400,400);   //Set Size - Should not be Square when we add a Button.
        setVisible(true);   //Make JFrame Visible.
        add(gamePanel, BorderLayout.CENTER);  //Background Panel is positioned Center. Button can be NORTH or SOUTH?
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Make the Close Button work.
        setLocationRelativeTo(null);    //Make the Frame appear in the middle of the screen.

        gamePanel.setLayout(new GridLayout(4, 4));    //4 * 4 Layout for the 16 Labels.
        gamePanel.setBackground(Color.BLACK); //Set Color. Color not important. Can be whatever.
        gamePanel.setOpaque(true);    //Make Opaque so Background can be seen.
    }
    //endregion

    //Method to Start Program instead of having the code in constructor.
    //To avoid warning that the variable in main is unused.
    public void run(){
        addToNumberList(); //Add Numbers To List
        addToLabelList(); //Add JLabels To List.
        addComponents(); //Add All Components
        addMouseListener(); //Add MouseListener
    }

    public static void main(String[] args) {
        BrickGame brickGame = new BrickGame(); //Create new instance of BrickGame
        brickGame.run(); //Run the program
    }

    //region<Code I Couldn't make this code work well in another class but might move if I can make it work>

    //Method to find the JLabel that is currently empty (containing " ")
    public JLabel getEmptyLabel(){
        JLabel emptyLabel = null; //This method returns a JLabel, so we need to initiate it. It starts as null.
        for (JLabel label : labelList){ //For-loop for each JLabel in labelList.
            if(label.getText().equals(" ")){ //Only performs code-block if JLabel is the "empty" one.
                emptyLabel = label; //Gives the reference to the "empty" JLabel to the locally declared empty JLabel.
            }
        }
        return emptyLabel; //Returns the actual emptyLabel - because the references points to the same address.
    }
    //This code can be done in the Mouse Listener
    public void addMouseListener(){
        int empty = labelList.indexOf(getEmptyLabel());
        int x = empty / 4;
        int y = empty % 4;
        if(y < 3){
            JLabel label = labelList.get(empty+1);
            label.addMouseListener(this);
            revalidate();
            repaint();
        }
        if(y != 0){
            JLabel label = labelList.get(empty-1);
            label.addMouseListener(this);
            revalidate();
            repaint();
        }
        if(x < 3){
            JLabel label = labelList.get(empty + 4);
            label.addMouseListener(this);
            label.revalidate();
            label.repaint();
        }
        if(x > 0){
            JLabel label = labelList.get(empty - 4);
            label.addMouseListener(this);
            label.revalidate();
            label.repaint();
        }
    }
    //Method to change Position of the text and color of the JLabel with the text and color of the "empty" JLabel
    public void changePosition(JLabel label){
        JLabel temp = getEmptyLabel(); //Declare JLabel variable temp; Temporary reference that points to the "empty" JLabel
        String tempText = label.getText();
        temp.setText(tempText);
        temp.setBackground(Color.LIGHT_GRAY);
        label.setBackground(Color.BLACK);
        label.setText(" ");
        repaint();
        revalidate();
    }

    //endregion

    //region<MouseAction>

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JLabel labelClicked){
            for(JLabel label : labelList){
                label.removeMouseListener(this);
            }
            changePosition(labelClicked);
            addMouseListener();
            repaint();
            revalidate();

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof JLabel label){
            label.setBackground(new Color(141,249,137));
            label.revalidate();
            label.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Kod för att se om musen blivit klickad
        if(e.getSource() instanceof JLabel label){
            label.setBackground(Color.LIGHT_GRAY);
            label.revalidate();
            label.repaint();
        }
    }

    //endregion

}