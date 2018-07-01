import java.util.*;
import java.lang.*;
public class DiscardSelector{
  int[] mountain;
  int mountainSize;
  int J=18;
  int TOTAL_WINNING_HANDS=0;
  int MAX_SWAP=8;
  int[] hand; //tile id 0-33
  int[][] winningHands;
  int[] worths;

  public DiscardSelector(int J, int max_swap)
  {
    this.J=J;
    MAX_SWAP=max_swap;
  }
  void update(int[] hand, int[] mountain, int mountainSize, int[][] winningHands, int[] worths)
  {
    this.hand=hand;
    this.mountain=mountain;
    this.mountainSize=mountainSize;
    TOTAL_WINNING_HANDS=worths.length;
    if(winningHands!=null)
      this.winningHands=winningHands;
    if(worths!=null)
      this.worths=worths;
  }
  int select()
  {
    //initialization
    double mult=0;
    double universe = combination(mountainSize,J);
    int[] targetTiles;
    double[] Es=new double[hand.length];
    double probability;

    //for each potential discard
    for(int d=0; d<hand.length; d++)
    {
      //for each winningHand find E and find total
      double E=0;
      mult=0;
      for(int w=0; w<TOTAL_WINNING_HANDS; w++)
      {

        targetTiles=difference(winningHands[w],difference(hand,new int[]{hand[d]}));
        mult=union(targetTiles);
        probability = 1-mult/universe;
        System.out.println(probability);
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
  //mult=0, level=0, result=0, dir=1
  double res=0;
  double union(int[] tiles) //level starts from zero
  {
    res=0;
    unionHelper( tiles,  0,  0,  1);
    return res;
  }
  void unionHelper(int[] tiles, int mult, int level, int dir)
  {
    int m=0;
    if(level<MAX_SWAP)
    {

      for (int i = level; i < tiles.length; i++) {
        m=mult+mul(tiles[i]);
        res+=dir*combination(mountainSize-m,J); //O(N)
        unionHelper(tiles, m, level+1, dir*-1);
      }

    }
  }
  double combination(int n, int k)
  {
    return fac(n)/(fac(k)*fac(n-k));
  }
  double fac(int n)
  {
    double factor = 1;
    for (int i=1; i<=n; i++) {
      factor = factor*i;
    }
    return factor;
  }
  int mul(int tile)
  {
    return mountain[tile];
  }
  int[] difference(int[] a, int[] b) //assumes sorted. time is O(x) x=(|a|<|b|)?|a|:|b|
  {
    int acount=0;
    int bcount=0;
    int[] sameindex = new int[a.length];
    int samecount = 0;
    Arrays.fill(sameindex, -1);
    while(acount < a.length && bcount < b.length)
    {
      if(a[acount]==b[bcount])
      {
        sameindex[samecount] = acount;
        acount++;
        bcount++;
        samecount++;
      }
      else if(a[acount]>b[bcount])
      {
        bcount++;
      }
      else if(a[acount]<b[bcount])
      {
        acount++;
      }
    }
    int[] diff = new int[a.length-samecount];
    int count=0;
    int difcount=0;
    for(int i=0; i<a.length; i++)
    {
      if(i!=sameindex[count])
      {
        diff[difcount] = a[i];
        difcount++;
      }
      else
        count++;
    }
    return diff;
  }
}
