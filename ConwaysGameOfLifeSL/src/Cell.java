import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author  Hasegawa, Natsuki; Shi, Zong; Lock, Samuel; Bollineni, Dharani
 * @version: 1.0
 */
public class Cell extends Rectangle{
  int x;
  int y;
  boolean currentStatus;
  boolean nextStatus;
  Rectangle r;

  /**
   * Constructs a new cell object. 
   * @param y the y coordinate. 
   * @param x the y coordinate. 
   * @param currentStatus currently alive (true) or dead (false).
   * @param nextStatus next status is alive (true) or dead (false).
   */
  public Cell(int y, int x, boolean currentStatus, boolean nextStatus) {
    this.x = x;
    this.y = y;
    this.currentStatus = currentStatus;
    this.nextStatus = nextStatus;
    r = new Rectangle();
    r.setHeight(10);
    r.setWidth(10);
   
  }

  /**
   * Returns if cell is alive or dead.
   * @return currentStatus alive (true) or dead (false).
   */
  public boolean getCurrentStatus() {
    return currentStatus;
  }

  /**
   * Sets if cell is alive or dead. Often used in event handling of setting the initial state or adding to array.
   * @param currentStatus alive (true) or dead (false).
   */
  public void setCurrentStatus(boolean currentStatus) {
    this.currentStatus = currentStatus;
  }

  /**
   * Used to create the following swapping temporary array to current array for visualisation
   * @return nextStatus alive (true) or dead (false). 
   */  
  public boolean getNextStatus() {
    return nextStatus;
  }
  
  /**
   * Allows changing of next perceived state.
   * @param nextStatus alive (true) or dead (false).
   */
  public void setNextStatus(boolean nextStatus) {
    this.nextStatus = nextStatus;
  }
  
/**
 *  Returns the x location status to calculate what is around the cell.
 * @return x location.
 */
  public int getXx() {
    return x;
  }

  /**
   * Allows to change x position in array.
   * @param x location.
   */
  public void setX(int x) {
    this.x = x;
  }

  
/**
 *  Returns the y status to calculate what is around the cell.
 * @return  y location.
 */
  public int getYy() {
    return y;
  }
  
  /**
   * Sets y location for array. 
   * @param y location.
   */

  public void setY(int y) {
    this.y = y;
  }
  
  /**
   * Returns rectangle of cell in Array. If current status is true; returns one color; if false returns a different to create grid. 
   * @return r rectangle of cell in Array.
   */
  
  public Rectangle getRect() {
	  if (currentStatus == false) {
		  r.setFill(Color.BLACK);
		  return r;
	  } else {
		  r.setFill(Color.DEEPPINK);
		  return r;
	  }
  }
}
