import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Logic {

    CustomMouseListener customMouseListener = new CustomMouseListener();

    public JLabel getEmptyLabel(List<JLabel> labelList){
        JLabel emptyLabel = null;
        for (JLabel label : labelList){
            if(label.getText().equals(" ")){
                emptyLabel = label;
            }
        }
        return emptyLabel;
    }

    /*public void changePosition(BrickGame brickGame, List<JLabel> labelList, JLabel label){
        JLabel temp = getEmptyLabel(labelList);
        String tempText = label.getText();
        temp.setText(tempText);
        temp.setBackground(Color.LIGHT_GRAY);
        label.setBackground(Color.BLACK);
        label.setText(" ");
        brickGame.repaint();
        brickGame.revalidate();
    }

     */

    public void changePosition(List<JLabel> labelList, JLabel label){
        JLabel temp = getEmptyLabel(labelList);
        String tempText = label.getText();
        temp.setText(tempText);
        temp.setBackground(Color.LIGHT_GRAY);
        label.setBackground(Color.BLACK);
        label.setText(" ");
        label.repaint();
        label.revalidate();
    }


    public void addMouseListener(){
        BrickGame brickGame = new BrickGame();
        int empty = brickGame.labelList.indexOf(getEmptyLabel(brickGame.labelList));
        int x = empty % 4;
        int y = empty % 4;
        if(y < 3){
            JLabel label = brickGame.labelList.get(empty+1);
            label.addMouseListener(customMouseListener);
            brickGame.revalidate();
            brickGame.repaint();
        }
        if(y != 0){
            JLabel label = brickGame.labelList.get(empty-1);
            label.addMouseListener(customMouseListener);
            brickGame.revalidate();
            brickGame.repaint();
        }
        if(x < 3){
            JLabel label = brickGame.labelList.get(empty + 4);
            label.addMouseListener(customMouseListener);
            label.revalidate();
            label.repaint();
        }
        if(x != 0){
            JLabel label = brickGame.labelList.get(empty - 4);
            label.addMouseListener(customMouseListener);
            label.revalidate();
            label.repaint();
        }
    }

    public void changePosition(JPanel backgroundPanel, List<JLabel> labelList) {
        int empty = labelList.indexOf(getEmptyLabel(labelList));
        int x = empty / 4;
        int y = empty % 4;
        if (y < 3) {
            JLabel label = labelList.get(empty + 1);
            label.addMouseListener(new CustomMouseListener());
            label.revalidate();
            label.repaint();
        }
        if (y < 3) {
            JLabel label = labelList.get(empty - 1);
            label.revalidate();
            label.repaint();
        }
    }

}
