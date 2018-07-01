import java.util.Arrays;
import java.util.*;
import java.lang.*;
import java.io.*;
public class Tester{
  public static void main(String[] args)
  {
    diffTests();
    combTests();

    System.out.println("testing hand generator.");
    long endTime = 0;
    long startTime = System.currentTimeMillis();

    startTime = System.currentTimeMillis();
    WinningHands wh = new WinningHands();
    int[][] handsNoAtama = new int[928796][12];
    endTime = System.currentTimeMillis();
    System.out.println(endTime-startTime+"ms");
    System.out.println("initial memory allocation finished.");

    startTime = System.currentTimeMillis();
    handsNoAtama = wh.generateAllHands();
    endTime = System.currentTimeMillis();
    System.out.println("hands generated successfully.");
    System.out.println(endTime-startTime+"ms");


    System.gc();
    System.out.println("garbage collected");

    //starting discad test

    System.out.println("testing DiscardSelector.");
    int[] mountain = new int[34];
    Arrays.fill(mountain,4);
    DiscardSelector ds2 = new DiscardSelector(18,12);
    int[] worths = new int[handsNoAtama.length];
    Arrays.fill(worths,1);
    int[] hand = new int[]{1,2,3,5,6,7,10,13,24,25,27,30,31,34};
    ds2.update(hand,mountain,136,handsNoAtama,worths);
    int tile = ds2.select();
    endTime = System.currentTimeMillis();
    System.out.println(endTime-startTime+"ms");
    System.out.println("tile selected is: "+tile);





  }//main end

  public static void diffTests()
  {
    DiscardSelector ds = new DiscardSelector(0,0);
    //test difference
    int[] a;
    int[] b;
    int[] answer;
    int testnum=0;

    a = new int[]{2,3,4};
    b = new int[]{0,1,2};
    answer = new int[]{3,4};


    a = new int[]{0,1,2};
    b = new int[]{2,3,4};
    testnum=1;
    answer = new int[]{0,1};
    diffTest(ds.difference(a,b),answer,testnum);

    a = new int[]{0,1,2};
    b = new int[]{3,4,5};
    testnum=2;
    answer = new int[]{0,1,2};
    diffTest(ds.difference(a,b),answer,testnum);

    a = new int[]{3,4,5};
    b = new int[]{0,1,2};
    testnum=3;
    answer = new int[]{3,4,5};
    diffTest(ds.difference(a,b),answer,testnum);

    a = new int[]{1,2,3};
    b = new int[]{0,4,5};
    testnum=4;
    answer = new int[]{1,2,3};
    diffTest(ds.difference(a,b),answer,testnum);

    a = new int[]{0,4,5};
    b = new int[]{1,2,3};
    testnum=5;
    answer = new int[]{0,4,5};
    diffTest(ds.difference(a,b),answer,testnum);

    a = new int[]{0,2,3,4,5,6,7,8};
    b = new int[]{1,2,3,8,9,10,15};
    testnum=6;
    answer = new int[]{0,4,5,6,7};
    diffTest(ds.difference(a,b),answer,testnum);

    a = new int[]{0,0,0,4,5,6,7,7,7};
    b = new int[]{0,2,4,5,7,10,15};
    testnum=7;
    answer = new int[]{0,0,6,7,7};
    diffTest(ds.difference(a,b),answer,testnum);
  }
  public static void combTests()
  {
    DiscardSelector ds = new DiscardSelector(0,0);
    combTest(ds.combination(5,2),10,0);
    combTest(ds.combination(11,4),330,1);
    combTest(ds.combination(15,7),6435,2);
    combTest(ds.combination(136,18),1.2200028e+22,3);
  }
  public static void diffTest(int[] test, int[] answer, int testnum)
  {
    if(!Arrays.equals(test,answer))
    {
      System.out.println("dif "+testnum+" failed.");
      System.out.println("tested "+Arrays.toString(test)+" against "+Arrays.toString(answer));
      System.exit(0);
    }
    System.out.println("dif "+testnum+" passed.");
  }
  public static void combTest(double test, double answer, int testnum)
  {
    if(test>answer+0.00001 && test<answer-0.00001)
    {
      System.out.println("combination "+testnum+" failed.");
      System.out.println("tested "+test+" against "+answer);
      System.exit(0);
    }
    System.out.println("combination "+testnum+" passed.");
  }
}
