import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

public class BrickGame extends JFrame{
    ChangeColorScheme changeColorScheme;
    SizeManager sizeManager;

    //regions are to hide the code in the region - so it's easier to navigate

    //region<JFrame>
    JPanel gamePanel = new JPanel(); //Background JPanel to hold JLabels
    List<JLabel> labelList = new ArrayList<>(); //List to hold JLabels
    List<String> randomNumberList = new ArrayList<>(); // nummerlista som är slumpad
    JMenu mainMenu; // skapar variabel för huvudmeny i Swing
    JMenu sizeMenu;
    JMenu colorMenu; // skapar variabel för färgmeny i Swing

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
        while (!isSolvable(stringToInteger(randomNumberList))){
            Collections.shuffle(randomNumberList);
        }
    }

    public List<Integer> stringToInteger(List<String> stringList){
        List<Integer> integerList = new ArrayList<>();
        for (String string : stringList){
            if(string.equals(" ")){
                integerList.add(0);
            }
            else{
                integerList.add(Integer.parseInt(string));
            }
        }
        return integerList;
    }

    // metod som skapar enkelt spel
    public void addToNumberListEasy(){
        // nummer 1 till 14 kommer i ordning
        for(int i = 1; i < sizeManager.getSize() - 1; i++){
            randomNumberList.add(String.valueOf(i));
        }
        // lägger till tom bricka" "
        randomNumberList.add(" ");

        // lägger till nummer 15
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

    public void updateColor(){
        gamePanel.setBackground(changeColorScheme.getBackgroundColor()); // anger bakgrundsfärgen för panelen i Swing
        for(JLabel label : labelList){ // loopar igenom alla labels som vi har
            label.setBorder(BorderFactory.createLineBorder(changeColorScheme.getBackgroundColor())); // Border får bakgrundsfärg
            if(label == getEmptyLabel()){
                label.setBackground(changeColorScheme.getBackgroundColor()); // tom bricka får bakgrundsfärgen
            }
            else{
                label.setBackground(changeColorScheme.getBrickColor()); // vanliga brickor (1 till 15) får brick färgen
            }
        }
    }
    public void addMenu() {
        // skapar klickbar menu-item(meny-val) för Pink/rosa
        JMenuItem pink =new JMenuItem("Pink");
        pink.addActionListener( // en ActionListener metod som tar en lambdas
                actionEvent -> {  // lambdas metoden börjar här och tar en actionEvent parameter
                    // här är lambdas metodens implementation
                    changeColorScheme.setColorScheme("Pink"); // anger färgschemat Pink/rosa
                    updateColor();
                } // här slutar lambdas metoden
        ); // här är slutparentes för actionListener metoden som tog en lambdas

        // skapar klickbar menu-item(meny-val) för yellow/gul
        JMenuItem yellow =new JMenuItem("Yellow");
        yellow.addActionListener( // en ActionListener metod som tar en lambdas
                actionEvent -> {  // lambdas metoden börjar här och tar en actionEvent parameter
                    // här är lambdas metodens implementation
                    changeColorScheme.setColorScheme("Yellow"); // anger färgschemat yellow/gul
                    updateColor();
                } // här slutar lambdas metoden
        ); // här är slutparentes för actionListener metoden som tog en lambdas

        // skapar klickbar menu-item(meny-val) för blue/blå
        JMenuItem blue =new JMenuItem("Blue");
        blue.addActionListener( // en ActionListener metod som tar en lambdas
                actionEvent -> {  // lambdas metoden börjar här och tar en actionEvent parameter
                    // här är lambdas metodens implementation
                    changeColorScheme.setColorScheme("Blue"); // anger färgschemat blue/blå
                    updateColor();
                } // här slutar lambdas metoden
        ); // här är slutparentes för actionListener metoden som tog en lambdas

        // skapar klickbar menu-item(meny-val) för green/grön
        JMenuItem green =new JMenuItem("Green");
        green.addActionListener( // en ActionListener metod som tar en lambdas
                actionEvent -> {  // lambdas metoden börjar här och tar en actionEvent parameter
                    // här är lambdas metodens implementation
                    changeColorScheme.setColorScheme("Green"); // anger färgschemat green/grön
                    updateColor();
                } // här slutar lambdas metoden
        ); // här är slutparentes för actionListener metoden som tog en lambdas


        // skapar klickbar menu-item(meny-val) för NewGame
        JMenuItem newGame =new JMenuItem("New game");
        newGame.addActionListener( // en ActionListener metod som tar en lambdas
                actionEvent -> {  // lambdas metoden börjar här och tar en actionEvent parameter
                    // här är lambdas metodens implementation
                    reset(); // reset(raderar värdet) alla variabler
                    run(); // anropar run metoden för att starta nytt brick spel igen
            } // här slutar lambdas metoden
        ); // här är slutparentes för actionListener metoden som tog en lambdas

        // skapar klickbar menu-item(meny-val) för NewGame Easy
        JMenuItem newGameEasy =new JMenuItem("New game Easy");
        newGameEasy.addActionListener( // en ActionListener metod som tar en lambdas
                actionEvent -> {  // lambdas metoden börjar här och tar en actionEvent parameter
                    // här är lambdas metodens implementation
                    reset(); // reset(raderar värdet) alla variabler
                    runEasy(); // anropar run-easy metoden för att starta nytt brick spel igen
                } // här slutar lambdas metoden
        ); // här är slutparentes för actionListener metoden som tog en lambdas

        // skapar klickbar menu-item(meny-val) för Exit
        JMenuItem closeGame =new JMenuItem("Exit");
        closeGame.addActionListener( // en ActionListener metod som tar en lambdas
                actionEvent -> // lambdas metoden börjar här och tar en actionEvent parameter
                        System.exit(0) // här är lambdas metodens implementation, stänger ner Java programmet
        ); // här är slutparentes för actionListener metoden som tog en lambdas

        // skapar meny variabel i Swing
        colorMenu = new JMenu("Color"); // sätter label-texten till Color för menyn
        colorMenu.add(pink); // lägger till Pink-menyItem i menyn
        colorMenu.add(yellow); // lägger till Yellow-menyItem i menyn
        colorMenu.add(blue); // lägger till Blue-menyItem i menyn
        colorMenu.add(green); // lägger till Green-menyItem i menyn

        // skapar meny variabel i Swing
        mainMenu = new JMenu("Menu"); // sätter label-texten till Menu för menyn
        sizeMenu = new JMenu("Size");
        mainMenu.add(newGame); // lägger till NewGame-menyItem i menyn
        mainMenu.add(newGameEasy); // lägger till NewGameEasy-menyItem i menyn
        mainMenu.add(sizeMenu);
        mainMenu.add(colorMenu);
        mainMenu.add(closeGame); // lägger till Exit-menyItem i menyn

        //skapar olika storlekar för sizeMenu
        JMenuItem threeX3 = new JMenuItem("3 x 3");
        JMenuItem fourX4 = new JMenuItem("4 x 4");
        JMenuItem fiveX5 = new JMenuItem("5 x 5");
        JMenuItem eightX8  = new JMenuItem("8 x 8");
        JMenuItem tenX10 = new JMenuItem("10 x 10");
        sizeMenu.add(threeX3);
        sizeMenu.add(fourX4);
        sizeMenu.add(fiveX5);
        sizeMenu.add(eightX8);
        sizeMenu.add(tenX10);

        //add actionListener to sizeMenu MenuItems
        threeX3.addActionListener(ActiveEvent -> {reset(); run(3);});
        fourX4.addActionListener(ActiveEvent -> {reset(); run(4);});
        fiveX5.addActionListener(ActiveEvent -> {reset(); run(5);});
        eightX8.addActionListener(ActiveEvent -> {reset(); run(8);});
        tenX10.addActionListener(ActiveEvent -> {reset(); run(10);});

        // Skapar en meny-bar/rad
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(mainMenu); // lägger till huvud-menyn i meny-bar/rad
       // menuBar.add(colorMenu); // lägger till color-menyn i meny-bar/rad

        // lägger till menu-bar/rad i JFrame
        setJMenuBar(menuBar);
    }

    // reset metod som nollställer/raderar värdet i alla variabler
    private void reset() {
        labelList = new ArrayList<>(); // skapar ny lista med text-etikett som är tom
        randomNumberList = new ArrayList<>(); // skapar ny slump nummer lita som är tom
        gamePanel.removeAll(); // ta bort gamla panelen
    }

    // kontrollerar ifall spelet är avklarad
    private boolean isGameCompleted() {
        boolean completedGame = true; // skapar boolean variabeln 'completedGame' som ska ange ifall spelaren klarat av spelet
        // itererar/loopar igen alla labels (hoppar över sista brickan)
        for (int i = 1; i < labelList.size(); i++) {
            String siffraString = i + ""; // skapar string variabel som håller reda på vilket loop nummer vi är på (startar med nummer 1)
            if (!siffraString.equals(labelList.get(i-1).getText())) { // get index startar från 0 (för vi tar minus 1)
                completedGame = false; // nu vet vi att spelet inte är avklarat, vi sätter 'completedGame' till false
            }
        }
        return completedGame;
    }

    //CAN GAME BE SOLVED?
    //region<AI-Generated Code>
    public boolean isSolvable(List<Integer> puzzle) {
        int inversions = 0;
        int gridSize = (int) abs(Math.sqrt(puzzle.size()));

        // Calculate the number of inversions
        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = i + 1; j < puzzle.size(); j++) {
                int tileA = puzzle.get(i);
                int tileB = puzzle.get(j);
                if (tileA > tileB) {
                    inversions++;
                }
            }
        }
        // Check if solvable based on grid size and inversions
        if (gridSize % 2 == 1) {
            return inversions % 2 == 0;
        } else {
            int emptyPosition = puzzle.indexOf(0);
            int emptyRow = emptyPosition / gridSize + 1;
            return (inversions % 2 == 0 && (gridSize - emptyRow) % 2 == 1) ||
                    (inversions % 2 == 1 && (gridSize - emptyRow) % 2 == 0);
        }
    }
    //endregion

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
        addMenu(); // Add Menu with listener
        addToNumberList(); //Add Numbers To List
        addToLabelList(); //Add JLabels To List.
        addComponents(); //Add All Components
        addMouseListener(); //Add MouseListener
    }
    public void run(int size){
        sizeManager = new SizeManager(size);
        addToNumberList(); //Add Numbers To List
        addToLabelList(); //Add JLabels To List.
        addComponents(); //Add All Components
        addMouseListener(); //Add MouseListener
    }

    // Startar ett enkelt spel
    public void runEasy(){
        addMenu(); // lägger till meny
        addToNumberListEasy(); // lägger till enkel nummer lista
        addToLabelList(); // lägger till labels
        addComponents(); // lägger till swing komponenterna
        addMouseListener(); // lägger till mus-lyssnare
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
            if (isGameCompleted()) { // kontrollerar ifall spelaren klarat av spelet
                int result = JOptionPane.showConfirmDialog(null, "You have completed the game, want to play a new game?",
                        "Congratulation", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    reset(); // reset/nollställer alla variabler
                    run(); // startar ett nytt spel
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
                        if(eventLabel != getEmptyLabel()){
                            eventLabel.setBackground(changeColorScheme.getMouseListenerColor());
                        }
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