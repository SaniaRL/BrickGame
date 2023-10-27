import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrickGame extends JFrame{

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

    //Method to start Program instead of having the code in constructor.
    //To avoid warning that the variable in main is unused.
    public void run(){
        addToNumberList(); //Add Numbers To List
        addToLabelList(); //Add JLabels To List.
        addComponents(); //Add All Components
        addMouseListener(); //Add MouseListener
    }

    public static void main(String[] args) {
        //If we use this we can put the code in run() in constructor and still avoid warning
        @SuppressWarnings("unused")
        BrickGame brickGame = new BrickGame(); //Create new instance of BrickGame
        brickGame.run(); //Method to run the program
    }

    //region<I Couldn't make this code work well in another class but might move if I can make it work>

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
        int x = empty % 4; //x = 0, 1, 2, 3
        int y = empty / 4; //y = 0, 1, 2, 3
        List<JLabel> nextDoorNeighbors = new ArrayList<>();
        List<JLabel> closeNeighbors = new ArrayList<>();
        List<JLabel> neighbors = new ArrayList<>();

        if(x < 1){
            neighbors.add(labelList.get(empty + 3));
        }
        if(x < 2){
            closeNeighbors.add(labelList.get(empty + 2));
        }
        if(x < 3){
            nextDoorNeighbors.add(labelList.get(empty + 1));
        }

        if(x != 0){
            nextDoorNeighbors.add(labelList.get(empty - 1));
        }
        if(x > 1){
            closeNeighbors.add(labelList.get(empty - 2));
        }
        if(x > 2){
            neighbors.add(labelList.get(empty - 3));
        }



        if(y < 3){
            nextDoorNeighbors.add(labelList.get(empty + 4));
        }
        if(y < 2){
            closeNeighbors.add(labelList.get(empty + 8));
        }
        if(y < 1){
            neighbors.add(labelList.get(empty + 12));
        }


        if(y > 0){
            nextDoorNeighbors.add(labelList.get(empty - 4));
        }
        if(y > 1){
            closeNeighbors.add(labelList.get(empty - 8));
        }
        if(y > 2){
            neighbors.add(labelList.get(empty - 12));
        }

        for(JLabel label : nextDoorNeighbors){
            label.addMouseListener(nextDoorNeighbourMouseAdapter);
            label.revalidate();
            label.repaint();
        }

        for(JLabel label : closeNeighbors){
            label.addMouseListener(closeNeighbourMouseAdapter);
            label.revalidate();
            label.repaint();
        }

        for(JLabel label : neighbors){
            label.addMouseListener(neighbourMouseAdapter);
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

    //region<MouseListener>

    MouseAdapter nextDoorNeighbourMouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
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
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            if(e.getSource() instanceof JLabel label){
                label.setBackground(new Color(141,249,137));
                label.revalidate();
                label.repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            if(e.getSource() instanceof JLabel label){
                label.setBackground(Color.LIGHT_GRAY);
                label.revalidate();
                label.repaint();
            }

        }
    };

    MouseAdapter closeNeighbourMouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(e.getSource() instanceof JLabel labelClicked){
                for(JLabel label : labelList){
                    label.removeMouseListener(this);
                }
                int iLabelClicked = labelList.indexOf(labelClicked);
                int iEmptyLabel = labelList.indexOf(getEmptyLabel());
                int i = iLabelClicked-labelList.indexOf(getEmptyLabel());
                if(i == - 8){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel - 4);
                    changePosition(inBetweenLabel);
                    changePosition(labelClicked);
                }
                if(i == 8){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel + 4);
                    changePosition(inBetweenLabel);
                    changePosition(labelClicked);
                }
                if(i == - 2){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel - 1);
                    changePosition(inBetweenLabel);
                    changePosition(labelClicked);
                }
                if(i == 2){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel + 2);
                    changePosition(inBetweenLabel);
                    changePosition(labelClicked);
                }
                addMouseListener();
                repaint();
                revalidate();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            if(e.getSource() instanceof JLabel label){
                label.setBackground(new Color(141,249,137));
                label.revalidate();
                label.repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            if(e.getSource() instanceof JLabel label){
                label.setBackground(Color.LIGHT_GRAY);
                label.revalidate();
                label.repaint();
            }

        }
    };

    MouseAdapter neighbourMouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(e.getSource() instanceof JLabel labelClicked){
                for(JLabel label : labelList){
                    label.removeMouseListener(this);
                }
                int iLabelClicked = labelList.indexOf(labelClicked);
                int iEmptyLabel = labelList.indexOf(getEmptyLabel());
                int i = iLabelClicked-labelList.indexOf(getEmptyLabel());
                if(i == - 12){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel - 4);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel - 8);
                    changePosition(inBetweenLabel);
                    changePosition(inBetweenLabel2);
                    changePosition(labelClicked);
                }
                if(i == 12){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel + 4);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel + 8);
                    changePosition(inBetweenLabel);
                    changePosition(inBetweenLabel2);
                    changePosition(labelClicked);
                }
                if(i == - 3){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel - 1);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel - 2);
                    changePosition(inBetweenLabel);
                    changePosition(inBetweenLabel2);
                    changePosition(labelClicked);
                }
                if(i == 3){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel + 1);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel + 2);
                    changePosition(inBetweenLabel);
                    changePosition(inBetweenLabel2);
                    changePosition(labelClicked);
                }

                addMouseListener();
                repaint();
                revalidate();
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            if(e.getSource() instanceof JLabel label){
                label.setBackground(new Color(141,249,137));
                label.revalidate();
                label.repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            if(e.getSource() instanceof JLabel label){
                label.setBackground(Color.LIGHT_GRAY);
                label.revalidate();
                label.repaint();
            }

        }
    };


    //endregion

}