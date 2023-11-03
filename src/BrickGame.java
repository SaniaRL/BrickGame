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

    //region<JFrame>
    JPanel gamePanel = new JPanel();
    List<JLabel> labelList = new ArrayList<>();
    List<String> randomNumberList = new ArrayList<>();
    JMenu mainMenu;
    JMenu sizeMenu;
    JMenu colorMenu;

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
        for(int i = 1; i < sizeManager.getSize() - 1; i++){
            randomNumberList.add(String.valueOf(i));
        }
        randomNumberList.add(" ");

        randomNumberList.add(String.valueOf(sizeManager.getSize() - 1));
    }

    public void addToLabelList(){
        for(int i = 0; i < sizeManager.getSize(); i++){
            JLabel label = new JLabel();
            label.setText(randomNumberList.get(i));
            labelList.add(label);
            gamePanel.add(label);

            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setBackground(changeColorScheme.getBrickColor());

            label.setFont(new Font("Arial", Font.BOLD, 25));
            label.setOpaque(true);
            label.setBorder(BorderFactory.createLineBorder(changeColorScheme.getBackgroundColor()));
            if(label.getText().equals(" ")){
                label.setBackground(changeColorScheme.getBackgroundColor());
            }
        }
    }

    public void updateColor(){
        gamePanel.setBackground(changeColorScheme.getBackgroundColor());
        for(JLabel label : labelList){
            label.setBorder(BorderFactory.createLineBorder(changeColorScheme.getBackgroundColor()));
            if(label == getEmptyLabel()){
                label.setBackground(changeColorScheme.getBackgroundColor());
            }
            else{
                label.setBackground(changeColorScheme.getBrickColor());
            }
        }
    }
    public void addMenu() {
        JMenuItem pink =new JMenuItem("Pink");
        pink.addActionListener(
                actionEvent -> {
                    changeColorScheme.setColorScheme("Pink");
                    updateColor();
                }
        );

        JMenuItem yellow =new JMenuItem("Yellow");
        yellow.addActionListener(
                actionEvent -> {
                    changeColorScheme.setColorScheme("Yellow");
                    updateColor();
                }
        );

        JMenuItem blue =new JMenuItem("Blue");
        blue.addActionListener(
                actionEvent -> {
                    changeColorScheme.setColorScheme("Blue");
                    updateColor();
                }
        );

        JMenuItem green =new JMenuItem("Green");
        green.addActionListener(
                actionEvent -> {
                    changeColorScheme.setColorScheme("Green");
                    updateColor();
                }
        );


        JMenuItem newGame =new JMenuItem("New game");
        newGame.addActionListener(
                actionEvent -> {
                    reset();
                    run(sizeManager.getXY());
            }
        );

        JMenuItem newGameEasy =new JMenuItem("New game Easy");
        newGameEasy.addActionListener(
                actionEvent -> {
                    reset();
                    runEasy();
                }
        );

        JMenuItem closeGame =new JMenuItem("Exit");
        closeGame.addActionListener(
                actionEvent ->
                        System.exit(0)
        );

        colorMenu = new JMenu("Color");
        colorMenu.add(pink);
        colorMenu.add(yellow);
        colorMenu.add(blue);
        colorMenu.add(green);

        mainMenu = new JMenu("Menu");
        sizeMenu = new JMenu("Size");
        mainMenu.add(newGame);
        mainMenu.add(newGameEasy);
        mainMenu.add(sizeMenu);
        mainMenu.add(colorMenu);
        mainMenu.add(closeGame);

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

        threeX3.addActionListener(ActiveEvent -> {reset(); run(3);});
        fourX4.addActionListener(ActiveEvent -> {reset(); run(4);});
        fiveX5.addActionListener(ActiveEvent -> {reset(); run(5);});
        eightX8.addActionListener(ActiveEvent -> {reset(); run(8);});
        tenX10.addActionListener(ActiveEvent -> {reset(); run(10);});

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(mainMenu); // lägger till huvud-menyn i meny-bar/rad

        setJMenuBar(menuBar);
    }

    private void reset() {
        labelList = new ArrayList<>();
        randomNumberList = new ArrayList<>();
        gamePanel.removeAll();
    }

    // kontrollerar ifall spelet är avklarad
    private boolean isGameCompleted() {
        boolean completedGame = true;
        for (int i = 1; i < labelList.size(); i++) {
            String siffraString = i + "";
            if (!siffraString.equals(labelList.get(i-1).getText())) {
                completedGame = false;
            }
        }
        return completedGame;
    }

    public void runEasy(){
        addMenu();
        addToNumberListEasy();
        addToLabelList();
        addComponents();
        addMouseListener();
    }


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
        setLayout(new BorderLayout());
        setSize(600,600);
        setTitle("Brick Game");
        ImageIcon image = new ImageIcon("square.png");
        setIconImage(image.getImage());
        setVisible(true);
        add(gamePanel, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gamePanel.setLayout(new GridLayout(sizeManager.getXY(), sizeManager.getXY()));
        gamePanel.setBackground(changeColorScheme.getBackgroundColor());
        gamePanel.setOpaque(true);
    }
    //endregion
    public BrickGame(){
        sizeManager = new SizeManager(4);
        changeColorScheme = new ChangeColorScheme();
        addMenu();
        addComponents();
    }

    public void run(int size){
        sizeManager.setSize(size);
        addToNumberList();
        addToLabelList();
        addComponents();
        addMouseListener();
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        BrickGame brickGame = new BrickGame();
        brickGame.run(4);
    }


    public JLabel getEmptyLabel(){
        JLabel emptyLabel = null;
        for (JLabel label : labelList){
            if(label.getText().equals(" ")){
                emptyLabel = label;
            }
        }
        return emptyLabel;
    }
    public void addMouseListener(){
        int empty = labelList.indexOf(getEmptyLabel());
        int x = empty % sizeManager.getXY();
        int y = empty / sizeManager.getXY();
        for(JLabel label : labelList){
            if (sizeManager.getX(labelList.indexOf(label)) == x || sizeManager.getY(labelList.indexOf(label)) == y ){
                label.addMouseListener(brickMouseAdapter);
                label.revalidate();
                label.repaint();
            }
        }
    }

    public void changePosition(JLabel label){
        JLabel emptyLabel = getEmptyLabel();
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

               if(xLabel == xEmptyLabel && iEmptyLabel > iLabelClicked){
                    for(int i = 0; i < xLoop; i++){
                        changePosition(labelList.get(labelList.indexOf(getEmptyLabel()) - sizeManager.getXY()));
                    }
                }
                if(xLabel == xEmptyLabel && iEmptyLabel < iLabelClicked){
                    for(int i = 0; i < xLoop; i++){
                        changePosition(labelList.get(labelList.indexOf(getEmptyLabel()) + sizeManager.getXY()));
                    }
                }
                if(yLabel == yEmptyLabel && iEmptyLabel > iLabelClicked){
                    for(int i = 0; i < yLoop; i++){
                        changePosition(labelList.get(labelList.indexOf(getEmptyLabel()) - 1));
                    }
                }
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
                    reset();
                    run(sizeManager.getXY());
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
}