import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @program DynamicArray
 * @description:
 * @author: Zong Shi
 * @create 2019-08-13 12:58
 */
public class Cell extends Rectangle{
  int x;
  int y;
  boolean currentStatus;
  boolean nextStatus;
  Rectangle r;

  public Cell(int y, int x, boolean currentStatus, boolean nextStatus) {
    this.x = x;
    this.y = y;
    this.currentStatus = currentStatus;
    this.nextStatus = nextStatus;
    r = new Rectangle();
    r.setHeight(10);
    r.setWidth(10);
   
    
  }

  public boolean getCurrentStatus() {
    return currentStatus;
  }

  public void setCurrentStatus(boolean currentStatus) {
    this.currentStatus = currentStatus;
  }

  public boolean getNextStatus() {
    return nextStatus;
  }

  public void setNextStatus(boolean nextStatus) {
    this.nextStatus = nextStatus;
  }

  public int getXx() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getYy() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
  
  public Rectangle getRect() {
	  if (currentStatus == false) {
		  r.setFill(Color.BURLYWOOD);
		  return r;
	  } else {
		  r.setFill(Color.LIGHTBLUE);
		  return r;
	  }
  }
}
