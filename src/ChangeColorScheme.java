import java.awt.*;

public class ChangeColorScheme {
    private Color brickColor;
    private Color mouseListenerColor;
    private Color inBetweenColor;

    public void setColorScheme(String color){
        switch (color){
            case "Pink" -> pinkColor();
            case "Yellow" -> yellowColor();
            default -> defaultColor();
        }
    }

    public void defaultColor(){
        brickColor = Color.lightGray;
        inBetweenColor = new Color(168, 252, 165);
        mouseListenerColor = new Color(141,249,137);
    }
    public void pinkColor(){
        brickColor = new Color(255, 195, 193);
        inBetweenColor = new Color(255,142,173);
        mouseListenerColor = new Color(255,102,165);
    }
    public void yellowColor() {
        brickColor = new Color(241, 255, 163);
        inBetweenColor = new Color(253, 255, 94);
        mouseListenerColor = new Color(255, 247, 32);
    }

    //region<Getters>
    public Color getBrickColor() {
        return brickColor;
    }

    public Color getMouseListenerColor() {
        return mouseListenerColor;
    }

    public Color getInBetweenColor() {
        return inBetweenColor;
    }
    //endregion

}
