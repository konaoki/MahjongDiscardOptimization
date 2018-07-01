import java.util.*;
import java.lang.*;

public class WinningHands{
  int[][] kootsus;
  int[][] shuntsus;
  int[][] atamas;
  int[][] handsNoAtama;
  int handIndex;
  public WinningHands()
  {

  }
  private void init(){
    kootsus = new int[34][3];
    shuntsus = new int[21][3];
    atamas = new int[34][2];
    handsNoAtama = new int[928796][12];

    for(int i=0; i<34; i++)
    {
      Arrays.fill(kootsus[i],i);
      Arrays.fill(atamas[i],i);
    }
    for(int i=0; i<7; i++)
    {
      shuntsus[i][0]=i;
      int a = i;
      a++;
      shuntsus[i][1]=a;
      a++;
      shuntsus[i][2]=a;
    }
    for(int i=7; i<14; i++)
    {
      int a = i;
      a++;
      a++;
      shuntsus[i][0]=a;
      a++;
      shuntsus[i][1]=a;
      a++;
      shuntsus[i][2]=a;
    }
    for(int i=14; i<21; i++)
    {
      int a = i;
      a++;
      a++;
      a++;
      a++;
      shuntsus[i][0]=a;
      a++;
      shuntsus[i][1]=a;
      a++;
      shuntsus[i][2]=a;
    }
    handIndex=0;
  }

  //level=0
  int c=0;
  private void shuntsuSkootsuKHand(int level, int[] currhand, int S, int kCount)
  {
    if(level<S)
    {
      //add selected shuntsu to currhand[level*3]
      for(int i=0; i<shuntsus.length; i++)
      {
        for(int k=0; k<3; k++)
        {
          currhand[level*3+k]=shuntsus[i][k];
        }

        shuntsuSkootsuKHand(level+1, currhand, S,kCount);
      }
    }
    else if(level<4)
    {
      for(int i=kCount; i<kootsus.length; i++)
      {
        for(int k=0; k<3; k++)
        {
          currhand[level*3+k]=kootsus[i][k];
        }

        shuntsuSkootsuKHand(level+1, currhand, S,i+1);
      }
    }
    if(level==4)
    {
      Arrays.sort(currhand);
      handsNoAtama[handIndex] = currhand.clone();
      handIndex++;

    }
    c++;
  }
  public int[][] generateAllHands()
  {
    init();
    int[] currhand;

    currhand = new int[12];
    shuntsuSkootsuKHand(0, currhand, 0,0);
    System.out.println(handIndex);
    for(int i=0; i<1; i++)
    {
      System.out.println(Arrays.toString(handsNoAtama[i]));
    }

    currhand = new int[12];
    shuntsuSkootsuKHand(0, currhand, 1,0);
    System.out.println(handIndex);

    currhand = new int[12];
    shuntsuSkootsuKHand(0, currhand, 2,0);
    System.out.println(handIndex);

    currhand = new int[12];
    shuntsuSkootsuKHand(0, currhand, 3,0);
    System.out.println(handIndex);

    currhand = new int[12];
    shuntsuSkootsuKHand(0, currhand, 4,0);
    System.out.println(handIndex);

    return handsNoAtama;
  }

}
