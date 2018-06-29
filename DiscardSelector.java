import java.util.*;
import java.lang.*;
public class DiscardSelector{
  int[] mountain;
  int J=18;
  static int TOTAL_WINNING_HANDS=0;
  static int MAX_SWAP=8;
  int[] hand;
  int[][] winningHands;
  int[] worths;

  public DiscardSelector(int J, int max_swap, int[][] winningHands, int[] worths)
  {
    this.J=J;
    MAX_SWAP=max_swap;
    TOTAL_WINNING_HANDS=worths.length;
    this.winningHands=winningHands;
    this.worths=worths;
  }
  void update(int[] hand, int[] mountain, int[][] winningHands, int[] worths)
  {
    this.hand=hand;
    this.mountain=mountain;
    if(winningHands!=null)
      this.winningHands=winningHands;
    if(worths!=null)
      this.worths=worths;
  }
  int select() //hand and discard both size 34
  {
    //initialization
    int mult=0;
    double universe = combination(mountain.length,J);
    int[] counter;
    int[] targetTiles;
    double[] Es=new double[hand.length];

    //for each potential discard
    for(int d=0; d<hand.length; d++)
    {
      //for each winningHand find E and find total
      double E=0;
      for(int w=0; w<TOTAL_WINNING_HANDS; w++)
      {
        targetTiles=difference(winningHands[w],hand);
        counter = new int[MAX_SWAP];
        mult=union(targetTiles, counter, 0, 0);
        double probability = (double)mult/universe;
        E += worths[w]*probability;
      }
      Es[d]=E;
    }
    //return discard with highest total E
    int maxIndex=0;
    for(int e=0; e< Es.length; e++)
    {
      if(Es[e]>Es[maxIndex])
        maxIndex=e;
    }
    return hand[maxIndex];
  }
  int union(int[] tiles, int[] counter, int level, int result) //level starts from zero, counter size=depth
  {
    int mult=0;
    int tempMult=0;
    for(int x=0; x<level; x++)
    {
      mult+=mul(tiles[counter[x]]);
    }
    int init=0;
    if(level>0) init=counter[level-1]+1;
    for (int i = init; i < counter.length; i++) {
      counter[level]=i;
      tempMult=mult;
      tempMult+=mul(tiles[i]);
      result+=Math.pow(-1, level%2) * combination(mountain.length-tempMult,J);
      union(tiles, counter, level+1, result);
    }
    return result;
  }
  int combination(int n, int k)
  {
    return fac(n)/(fac(k)*fac(n-k));
  }
  int fac(int n)
  {
    int result=1;
    for(int i=1; i<=n; i++)
    {
      result*=i;
    }
    return result;
  }
  int mul(int tile)
  {
    return 0;
  }
  int[] difference(int[] a, int[] b) //assumes sorted and same size
  {
    if(a.length != b.length)
    {
      System.out.println("error in difference.");
      System.exit(0);
    }
    int count=0;
    LinkedList<Integer> al = new LinkedList<Integer>();
    for(int i=0; i<a.length; i++)
    {
      while(a[i]>b[count] && count<a.length)
      {
        al.add(b[count]);
        count++;
      }
    }
    return null;
  }



}
