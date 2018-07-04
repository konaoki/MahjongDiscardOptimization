import java.util.*;
import java.lang.*;
public class Simulator{
  public static void main(String[] args)
  {
    Mjlogreader mjr = new Mjlogreader("test.mjv.jp.txt");
    DiscardSelector ds = new DiscardSelector(18,8);
    int[] hand = mjr.getHand();
    int[] dicardPile = mjr.getDiscardPile();
    int discard = mjr.getDiscardedTile();

    //update(int[] hand, int[] mountain, int mountainSize, int[][] winningHands, int[] worths);
    mjr.next();
  }
}
