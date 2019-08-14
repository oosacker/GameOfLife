/**
 * @program DynamicArray
 * @description:
 * @author: Zong Shi, Natsuki Hasegawa
 * @create 2019-08-13 12:58
 */

public class Cell {
  int x;
  int y;
  boolean currentStatus;
  boolean nextStatus;

  /**
   * Creates a new cell object.
   * @param y y position.
   * @param x x position.
   * @param currentStatus Whether if cell is currently alive or not.
   * @param nextStatus Whether if cell will  alive or not in the next state.
   */
  public Cell(int y, int x, boolean currentStatus, boolean nextStatus) {
    this.x = x;
    this.y = y;
    this.currentStatus = currentStatus;
    this.nextStatus = nextStatus;
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

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
    