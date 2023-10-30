public class SizeManager {
    private int xy;
    public SizeManager(int xy){
        this.xy = xy;
    }
    public void setSize(int xy) {
        this.xy = xy;
    }
    public int getXY(){
        return xy;
    }
    public int getSize(){
        return xy * xy;
    }
    public int getX(int i){
        return i % getXY();
    }
    public int getY(int i){
        return i / getXY();
    }
}
