import javax.swing.*;
import java.util.List;

public class Logic {
    public JLabel getEmptyLabel(List<JLabel> labelList){
        JLabel emptyLabel = null;
        for (JLabel label : labelList){
            if(label.getText().equals(" ")){
                emptyLabel = label;
            }
        }
        return emptyLabel;
    }


    public void changePosition(JPanel backgroundPanel, List<JLabel> labelList) {
        int empty = labelList.indexOf(getEmptyLabel());
        int x = empty / 4;
        int y = empty % 4;
        if (y < 3) {
            JLabel label = labelList.get(empty + 1);
            //label.addMouseListener();
            label.revalidate();
            label.repaint();
        }
        if (y < 3) {
            JLabel label = labelList.get(empty - 1);
            //label.addMouseListener();
            label.revalidate();
            label.repaint();
        }
    }
}
