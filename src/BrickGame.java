import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

public class BrickGame extends JFrame{
    JMenu mainMenu; // Create menu for our swing game
    JMenu colorMenu; // Create menu for our swing game
    ChangeColorScheme changeColorScheme;
    SizeManager sizeManager;

    //regions are to hide the code in the region - so it's easier to navigate

    //region<JFrame>
    JPanel gamePanel = new JPanel(); //Background JPanel to hold JLabels
    List<JLabel> labelList = new ArrayList<>(); //List to hold JLabels
    List<String> randomNumberList = new ArrayList<>(); //NumberList which is randomized

    //Strings instead of int for two reason.
    // 1. The values are not used as number-values.
    // 2. We can use the method JLabel.addText() directly without typecasting.

    //Updated code to avoid repetition, and keep code short and easy to manage.
    public void addToNumberList(){
        for(int i = 1; i < sizeManager.getSize() + 1; i++){
            if(i == sizeManager.getSize()){
                randomNumberList.add(" ");
            }
            else{
                randomNumberList.add(String.valueOf(i));
            }
        }
        Collections.shuffle(randomNumberList);
    }

    // creates easy game
    //TODO remove changes
    public void addToNumberListEasy(){
        // number 1 to 14 is placed in order
        for(int i = 1; i < sizeManager.getSize() - 1; i++){
            randomNumberList.add(String.valueOf(i));
        }
        // add empty
        randomNumberList.add(" ");
        // add number 15 in wrong order
        //TODO ta in värdet så det kan vara olika
        randomNumberList.add(String.valueOf(sizeManager.getSize() - 1));
    }

    //Updated code to avoid repetition, and keep code short and easy to manage.
    public void addToLabelList(){
        for(int i = 0; i < sizeManager.getSize(); i++){
            JLabel label = new JLabel();
            label.setText(randomNumberList.get(i));
            labelList.add(label);
            gamePanel.add(label);

            label.setHorizontalAlignment(JLabel.CENTER); //Align Center on the X-axis.
            label.setVerticalAlignment(JLabel.CENTER); //Align Center on the Y-axis.
            label.setBackground(changeColorScheme.getBrickColor()); //Color Choice. Not important.

            label.setFont(new Font("Arial", Font.BOLD, 25)); //Font Choice. Not Important.
            label.setOpaque(true); //Make Opaque so Background will show.
            label.setBorder(BorderFactory.createLineBorder(changeColorScheme.getBackgroundColor())); //Create Border that matches BackgroundPane.
            if(label.getText().equals(" ")){
                label.setBackground(changeColorScheme.getBackgroundColor()); //Make the "empty" JLabel black.
            }
        }
    }

    public void addMenue() {
        // create clickable menu item for change color Pink
        JMenuItem pink =new JMenuItem("Pink");
        pink.addActionListener( // create a lambda-method
                actionEvent -> {  // actionEvent is method parameter
                    // here starts method implementation
                    changeColorScheme.setColorScheme("Pink");
                    gamePanel.setBackground(changeColorScheme.getBackgroundColor()); //Set Color background color
                    for(JLabel label : labelList){
                        if(label == getEmptyLabel()){
                            label.setBackground(changeColorScheme.getBackgroundColor());
                        }
                        else{
                            label.setBackground(changeColorScheme.getBrickColor());
                        }
                    }
                } // end lambda-method
        );
        // create clickable menu item for change color yellow
        JMenuItem yellow =new JMenuItem("Yellow");
        yellow.addActionListener( // create a lambda-method
                actionEvent -> {  // actionEvent is method parameter
                    // here starts method implementation
                    changeColorScheme.setColorScheme("Yellow");
                    gamePanel.setBackground(changeColorScheme.getBackgroundColor()); //Set Color background color
                    for(JLabel label : labelList){
                        if(label == getEmptyLabel()){
                            label.setBackground(changeColorScheme.getBackgroundColor());
                        }
                        else{
                            label.setBackground(changeColorScheme.getBrickColor());
                        }
                    }
                } // end lambda-method
        );
        // create clickable menu item for change color Blue
        JMenuItem blue =new JMenuItem("Blue");
        blue.addActionListener( // create a lambda-method
                actionEvent -> {  // actionEvent is method parameter
                    // here starts method implementation
                    changeColorScheme.setColorScheme("Blue");
                    gamePanel.setBackground(changeColorScheme.getBackgroundColor()); //Set Color background color
                    for(JLabel label : labelList){
                        if(label == getEmptyLabel()){
                            label.setBackground(changeColorScheme.getBackgroundColor());
                        }
                        else{
                            label.setBackground(changeColorScheme.getBrickColor());
                        }
                    }
                } // end lambda-method
        );
        // create clickable menu item for change color Green
        JMenuItem green =new JMenuItem("Green");
        green.addActionListener( // create a lambda-method
                actionEvent -> {  // actionEvent is method parameter
                    // here starts method implementation
                    changeColorScheme.setColorScheme("Green");
                    gamePanel.setBackground(changeColorScheme.getBackgroundColor()); //Set Color background color
                    for(JLabel label : labelList){
                        if(label == getEmptyLabel()){
                            label.setBackground(changeColorScheme.getBackgroundColor());
                        }
                        else{
                            label.setBackground(changeColorScheme.getBrickColor());
                        }
                    }
                } // end lambda-method
        );


        // create clickable menu item for NewGame
        JMenuItem newGame =new JMenuItem("New game");
        newGame.addActionListener( // create a lambda-method
            actionEvent -> {  // actionEvent is method parameter
                // here starts method implementation
                reset(); // reset variables
                run(); // call run method to start game again
            } // end lambda-method
        );
        // create clickable menu item for NewGameEasy
        JMenuItem newGameEasy =new JMenuItem("New game Easy");
        newGameEasy.addActionListener(actionEvent -> {
            reset(); // reset variables
            runEasy(); // call run method to start game again
        });
        // create clickable menu item for Exit
        JMenuItem closeGame =new JMenuItem("Exit");
        closeGame.addActionListener(actionEvent -> System.exit(0));

        // create menu
        mainMenu = new JMenu("Menu"); // Sets label for our Menu
        mainMenu.add(newGame); // adds clickable items to our menu
        mainMenu.add(newGameEasy); // adds clickable items to our menu
        mainMenu.add(closeGame); // adds clickable items to our menu

        colorMenu = new JMenu("Color"); // Sets label for our Men
        colorMenu.add(pink);
        colorMenu.add(yellow);
        colorMenu.add(blue);
        colorMenu.add(green);

        // create menu-bar are
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(mainMenu); // adds main-menu to menuBar
        menuBar.add(colorMenu); // adds color-menu to menuBar

        // adds menu-bar area to JFrame
        setJMenuBar(menuBar);
    }

    private void reset() {
        labelList = new ArrayList<>(); // all labels are empty now
        randomNumberList = new ArrayList<>(); // number list is empty now
        gamePanel.removeAll(); // removes gamePanel
    }

    // check if game is completed
    private boolean isGameCompleted() {
        boolean completedGame = true; // creates boolean variable 'completedGame' that says game is completed
        // loop through all bricks (skips last empty brick)
        for (int i = 1; i < labelList.size(); i++) {
            String siffraString = i + ""; // creates string which hold value of the loop (starts with number 1)
            if (!siffraString.equals(labelList.get(i-1).getText())) { // get index starts with 0 (because we subtract 1)
                completedGame = false; // now we know one brick is not placed right, we set 'completedGame' to false
            }
        }
        return completedGame;
    }

    public void addComponents(){
        setLayout(new BorderLayout());  //Set Layout
        setSize(600,600);   //Set Size - Should not be Square when we add a Button.
        setVisible(true);   //Make JFrame Visible.
        add(gamePanel, BorderLayout.CENTER);  //Background Panel is positioned Center. Button can be NORTH or SOUTH?
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Make the Close Button work.
        setLocationRelativeTo(null);    //Make the Frame appear in the middle of the screen.

        gamePanel.setLayout(new GridLayout(sizeManager.getXY(), sizeManager.getXY()));    //4 * 4 Layout for the 16 Labels.
        gamePanel.setBackground(changeColorScheme.getBackgroundColor()); //Set Color. Color not important. Can be whatever.
        gamePanel.setOpaque(true);    //Make Opaque so Background can be seen.
    }
    //endregion

    //Method to start Program instead of having the code in constructor.
    //To avoid warning that the variable in main is unused.
    public void run(){
        sizeManager = new SizeManager(4);
        changeColorScheme = new ChangeColorScheme();
        changeColorScheme.setColorScheme(""); //Change color to default
        addMenue(); // Add Menu with listener
        addToNumberList(); //Add Numbers To List
        addToLabelList(); //Add JLabels To List.
        addComponents(); //Add All Components
        addMouseListener(); //Add MouseListener
    }

    // Start an easy game
    public void runEasy(){
        addMenue(); // Add Menu with listener
        addToNumberListEasy(); //Add Easy Numbers To List
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
        int x = empty % sizeManager.getXY(); //x = 0, 1, 2, 3
        int y = empty / sizeManager.getXY(); //y = 0, 1, 2, 3
        for(JLabel label : labelList){
            if (sizeManager.getX(labelList.indexOf(label)) == x || sizeManager.getY(labelList.indexOf(label)) == y ){
                label.addMouseListener(brickMouseAdapter);
                label.revalidate();
                label.repaint();
            }
        }
    }

    //Method to change Position of the text and color of the JLabel with the text and color of the "empty" JLabel
    public void changePosition(JLabel label){
        JLabel emptyLabel = getEmptyLabel(); //Declare JLabel variable temp; Temporary reference that points to the "empty" JLabel
        String tempText = label.getText();
        emptyLabel.setText(tempText);
        emptyLabel.setBackground(changeColorScheme.getBrickColor());
        label.setBackground(changeColorScheme.getBackgroundColor());
        label.setText(" ");
        repaint();
        revalidate();
    }

    //Method to reset Mouse Listeners
    public void resetMouseListeners(){
        for(JLabel label : labelList){
            label.removeMouseListener(brickMouseAdapter);
        }
    }

    //endregion
    //region<MouseListener>

    MouseAdapter brickMouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(e.getSource() instanceof JLabel labelClicked){
                resetMouseListeners();
                int iLabelClicked = labelList.indexOf(labelClicked);
                int iEmptyLabel = labelList.indexOf(getEmptyLabel());
                int xLabel = sizeManager.getX(iLabelClicked);
                int yLabel = sizeManager.getY(iLabelClicked);
                int xEmptyLabel = sizeManager.getX(iEmptyLabel);
                int yEmptyLabel = sizeManager.getY(iEmptyLabel);

                int xLoop = abs(((labelList.indexOf(getEmptyLabel()) - iLabelClicked)) / sizeManager.getXY());
                int yLoop = abs(iEmptyLabel - iLabelClicked);
                //  3 7 11 11 7 3
                //THIS CODE SEEMS TO WORK  -  SOON
                //Up - works the best of them
                if(xLabel == xEmptyLabel && iEmptyLabel > iLabelClicked){
                    for(int i = 0; i < xLoop; i++){
                        changePosition(labelList.get(labelList.indexOf(getEmptyLabel()) - sizeManager.getXY()));
                    }
                }
                //Down - is messing with me
                if(xLabel == xEmptyLabel && iEmptyLabel < iLabelClicked){
                    for(int i = 0; i < xLoop; i++){
                        changePosition(labelList.get(labelList.indexOf(getEmptyLabel()) + sizeManager.getXY()));
                    }
                }
                //Right - THIS ONE WORKS BUT WHY?
                if(yLabel == yEmptyLabel && iEmptyLabel > iLabelClicked){
                    for(int i = 0; i < yLoop; i++){
                        changePosition(labelList.get(labelList.indexOf(getEmptyLabel()) - 1));
                    }
                }
                //Left - works a bit
                if(yLabel == yEmptyLabel && iEmptyLabel < iLabelClicked){
                    for(int i = 0; i < yLoop; i++){
                        changePosition(labelList.get(labelList.indexOf(getEmptyLabel()) + 1));
                    }
                }

                addMouseListener();
                repaint();
                revalidate();
            }
            if (isGameCompleted()) {
                int result = JOptionPane.showConfirmDialog(null, "You have completed the game, want to play a new game?",
                        "Congratulation", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    reset(); // reset variables
                    run(); // start new game
                }
            }
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            if(e.getSource() instanceof JLabel eventLabel){
                int emptyLabelPosition = labelList.indexOf(getEmptyLabel());
                int eventLabelPosition = labelList.indexOf(eventLabel);
                int eventLabelX = sizeManager.getX(eventLabelPosition);
                int eventLabelY = sizeManager.getY(eventLabelPosition);
                int emptyLabelX = sizeManager.getX(emptyLabelPosition);
                int emptyLabelY = sizeManager.getY(emptyLabelPosition);
                int allowedGap = abs(emptyLabelPosition - eventLabelPosition);
                for (JLabel label : labelList){
                    int labelGap = abs(emptyLabelPosition - labelList.indexOf(label));
                    int labelPosition = labelList.indexOf(label);
                    int labelX = sizeManager.getX(labelPosition);
                    int labelY = sizeManager.getY(labelPosition);
                        if((((eventLabelX == emptyLabelX && labelX == eventLabelX) ||
                                (eventLabelY == emptyLabelY && labelY == eventLabelY)) && (labelGap <= allowedGap))
                                && (((emptyLabelX > eventLabelX) == (emptyLabelX > labelX)) &&
                                ((labelPosition < emptyLabelPosition) == (eventLabelPosition < emptyLabelPosition)))){
                            if(label != getEmptyLabel()){
                                label.setBackground(changeColorScheme.getInBetweenColor());
                            }
                        }
                    eventLabel.setBackground(changeColorScheme.getMouseListenerColor());
                }
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            //It seems that mouseExited is excavated before mouseEntered, so this is a simple solution.
            if(e.getSource() instanceof JLabel){
                for(JLabel label : labelList){
                    if(label == getEmptyLabel()){
                        label.setBackground(changeColorScheme.getBackgroundColor());
                    }
                    else{
                        label.setBackground(changeColorScheme.getBrickColor());
                    }
                }
            }
        }
    };
    //endregion
}