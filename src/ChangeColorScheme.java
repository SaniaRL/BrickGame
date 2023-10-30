import java.awt.*;

public class ChangeColorScheme {
    private Color brickColor;
    private Color mouseListenerColor;
    private Color inBetweenColor;

    public void setColorScheme(String color){
        switch (color){
            case "Pink" -> pinkColor();
            case "Yellow" -> yellowColor();
            case "Blue" -> blueColor();
            case "Green" -> greenColor();
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
    public void blueColor() {
        brickColor = new Color(161, 233, 255);
        inBetweenColor = new Color(109, 207, 255);
        mouseListenerColor = new Color(47, 181, 255);
    }
    public void greenColor() {
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
