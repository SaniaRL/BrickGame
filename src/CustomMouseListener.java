import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomMouseListener //implements MouseListener
{
    //Code does not work in separate class. Will try to fix.
    /*
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JLabel labelClicked){
            BrickGame brickGame = new BrickGame();
            Logic logic = new Logic();
            for(JLabel label : brickGame.labelList){
                label.removeMouseListener(this);
            }
            logic.changePosition(labelClicked);
            logic.addMouseListener();
            brickGame.repaint();
            brickGame.revalidate();

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
        if(e.getSource() instanceof JLabel label){
            label.setBackground(Color.LIGHT_GRAY);
            label.revalidate();
            label.repaint();
        }
    }

     */
}
