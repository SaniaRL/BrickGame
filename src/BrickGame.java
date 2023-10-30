import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrickGame extends JFrame{
    JMenu menu; // Create menu for our swing game
    ChangeColorScheme changeColorScheme;

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
        for(int i = 1; i < 17; i++){
            if(i == 16){
                randomNumberList.add(" ");
            }
            else{
                randomNumberList.add(String.valueOf(i));
            }
        }
        Collections.shuffle(randomNumberList);
    }

    // creates easy game
    public void addToNumberListEasy(){
        // number 1 to 14 is placed in order
        for(int i = 1; i < 15; i++){
            randomNumberList.add(String.valueOf(i));
        }
        // add empty
        randomNumberList.add(" ");
        // add number 15 in wrong order
        randomNumberList.add("15");
    }

    //Updated code to avoid repetition, and keep code short and easy to manage.
    public void addToLabelList(){
        for(int i = 0; i < 16; i++){
            JLabel label = new JLabel();
            label.setText(randomNumberList.get(i));
            labelList.add(label);
            gamePanel.add(label);

            label.setHorizontalAlignment(JLabel.CENTER); //Align Center on the X-axis.
            label.setVerticalAlignment(JLabel.CENTER); //Align Center on the Y-axis.
            label.setBackground(changeColorScheme.getBrickColor()); //Color Choice. Not important.

            label.setFont(new Font("Arial", Font.BOLD, 25)); //Font Choice. Not Important.
            label.setOpaque(true); //Make Opaque so Background will show.
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //Create Border that matches BackgroundPane.
            if(label.getText().equals(" ")){
                label.setBackground(Color.BLACK); //Make the "empty" JLabel black.
            }

          //TODO  changeColorScheme.pinkColor(labelList);
        }
    }

    public void addMenue() {
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
        menu = new JMenu("Menu"); // Sets label for our Menu
        menu.add(newGame); // adds clickable items to our menu
        menu.add(newGameEasy); // adds clickable items to our menu
        menu.add(closeGame); // adds clickable items to our menu

        // create menu-bar are
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu); // adds menu to menuBar

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
        JLabel emptyLabel = getEmptyLabel(); //Declare JLabel variable temp; Temporary reference that points to the "empty" JLabel
        String tempText = label.getText();
        emptyLabel.setText(tempText);
        emptyLabel.setBackground(changeColorScheme.getBrickColor());
        label.setBackground(Color.BLACK);
        label.setText(" ");
        repaint();
        revalidate();
    }

    //Method to reset Mouse Listeners
    public void resetMouseListeners(){
        for(JLabel label : labelList){
            label.removeMouseListener(nextDoorNeighbourMouseAdapter);
            label.removeMouseListener(closeNeighbourMouseAdapter);
            label.removeMouseListener(neighbourMouseAdapter);
        }
    }

    //endregion

    //region<MouseListener>
    MouseAdapter nextDoorNeighbourMouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(e.getSource() instanceof JLabel labelClicked){
                resetMouseListeners();
                changePosition(labelClicked);
                addMouseListener();
                repaint();
                revalidate();

                //TODO flytta detta till egen mouse adapter så att det bara står på ett ställe
                if (isGameCompleted()) {
                    // shows ConfirmDialog if game is completet
                    int result = JOptionPane.showConfirmDialog(null, "You have completed the game, want to play a new game?",
                            "Congratulation", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) { // if user has chose to start new game
                        reset(); // reset variables
                        run(); // start new game
                    }
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            if(e.getSource() instanceof JLabel label){
                label.setBackground(changeColorScheme.getMouseListenerColor());
                label.revalidate();
                label.repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            if(e.getSource() instanceof JLabel label){
                label.setBackground(changeColorScheme.getBrickColor());
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
                resetMouseListeners();
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
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel + 1);
                    changePosition(inBetweenLabel);
                    changePosition(labelClicked);
                }
                addMouseListener();
                repaint();
                revalidate();

                //TODO flytta detta till egen mouse adapter så att det bara står på ett ställe
                if (isGameCompleted()) {
                    int result = JOptionPane.showConfirmDialog(null, "You have completed the game, want to play a new game?",
                            "Congratulation", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        reset(); // reset variables
                        run(); // start new game
                    }
                }
            }
        }

        JLabel inBetweenLabel;
        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            if(e.getSource() instanceof JLabel label){
                int iLabelClicked = labelList.indexOf(label);
                int iEmptyLabel = labelList.indexOf(getEmptyLabel());
                int i = iLabelClicked-labelList.indexOf(getEmptyLabel());
                if(i == - 8){
                    inBetweenLabel = labelList.get(iEmptyLabel - 4);
                    inBetweenLabel.setBackground(changeColorScheme.getInBetweenColor());
                }
                if(i == 8){
                    inBetweenLabel = labelList.get(iEmptyLabel + 4);
                    inBetweenLabel.setBackground(changeColorScheme.getInBetweenColor());
                }
                if(i == - 2){
                    inBetweenLabel = labelList.get(iEmptyLabel - 1);
                    inBetweenLabel.setBackground(changeColorScheme.getInBetweenColor());
                }
                if(i == 2){
                    inBetweenLabel = labelList.get(iEmptyLabel + 1);
                    inBetweenLabel.setBackground(changeColorScheme.getInBetweenColor());
                }
                label.setBackground(changeColorScheme.getMouseListenerColor());
                label.revalidate();
                label.repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            if(e.getSource() instanceof JLabel label){
                int iLabelClicked = labelList.indexOf(label);
                int iEmptyLabel = labelList.indexOf(getEmptyLabel());
                int i = iLabelClicked-labelList.indexOf(getEmptyLabel());
                if(i == - 8){
                    inBetweenLabel = labelList.get(iEmptyLabel - 4);
                    inBetweenLabel.setBackground(changeColorScheme.getBrickColor());
                }
                if(i == 8){
                    inBetweenLabel = labelList.get(iEmptyLabel + 4);
                    inBetweenLabel.setBackground(changeColorScheme.getBrickColor());
                }
                if(i == - 2){
                    inBetweenLabel = labelList.get(iEmptyLabel - 1);
                    inBetweenLabel.setBackground(changeColorScheme.getBrickColor());
                }
                if(i == 2){
                    inBetweenLabel = labelList.get(iEmptyLabel + 1);
                    inBetweenLabel.setBackground(changeColorScheme.getBrickColor());
                }
                label.setBackground(changeColorScheme.getBrickColor());
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
                resetMouseListeners();
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

                //TODO flytta detta till egen mouse adapter så att koden bara står på ett ställe
                if (isGameCompleted()) {
                    int result = JOptionPane.showConfirmDialog(null, "You have completed the game, want to play a new game?",
                            "Congratulation", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        reset(); // reset variables
                        run(); // start new game
                    }
                }
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            if(e.getSource() instanceof JLabel label){
                int iLabel = labelList.indexOf(label);
                int iEmptyLabel = labelList.indexOf(getEmptyLabel());
                int i = iLabel-labelList.indexOf(getEmptyLabel());
                if(i == - 12){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel - 4);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel - 8);
                    inBetweenLabel.setBackground(changeColorScheme.getInBetweenColor());
                    inBetweenLabel2.setBackground(changeColorScheme.getInBetweenColor());
                }
                if(i == 12){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel + 4);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel + 8);
                    inBetweenLabel.setBackground(changeColorScheme.getInBetweenColor());
                    inBetweenLabel2.setBackground(changeColorScheme.getInBetweenColor());
                }
                if(i == - 3){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel - 1);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel - 2);
                    inBetweenLabel.setBackground(changeColorScheme.getInBetweenColor());
                    inBetweenLabel2.setBackground(changeColorScheme.getInBetweenColor());
                }
                if(i == 3){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel + 1);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel + 2);
                    inBetweenLabel.setBackground(changeColorScheme.getInBetweenColor());
                    inBetweenLabel2.setBackground(changeColorScheme.getInBetweenColor());
                }

                label.setBackground(changeColorScheme.getMouseListenerColor());
                label.revalidate();
                label.repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            if(e.getSource() instanceof JLabel label){
                int iLabel = labelList.indexOf(label);
                int iEmptyLabel = labelList.indexOf(getEmptyLabel());
                int i = iLabel-labelList.indexOf(getEmptyLabel());
                if(i == - 12){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel - 4);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel - 8);
                    inBetweenLabel.setBackground(changeColorScheme.getBrickColor());
                    inBetweenLabel2.setBackground(changeColorScheme.getBrickColor());
                }
                if(i == 12){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel + 4);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel + 8);
                    inBetweenLabel.setBackground(changeColorScheme.getBrickColor());
                    inBetweenLabel2.setBackground(changeColorScheme.getBrickColor());
                }
                if(i == - 3){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel - 1);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel - 2);
                    inBetweenLabel.setBackground(changeColorScheme.getBrickColor());
                    inBetweenLabel2.setBackground(changeColorScheme.getBrickColor());
                }
                if(i == 3){
                    JLabel inBetweenLabel = labelList.get(iEmptyLabel + 1);
                    JLabel inBetweenLabel2 = labelList.get(iEmptyLabel + 2);
                    inBetweenLabel.setBackground(changeColorScheme.getBrickColor());
                    inBetweenLabel2.setBackground(changeColorScheme.getBrickColor());
                }
                label.setBackground(changeColorScheme.getBrickColor());
                label.revalidate();
                label.repaint();
            }

        }
    };


    //endregion

}