import java.util.Arrays;
public class Tester{
  public static void main(String[] args)
  {
    DiscardSelector ds = new DiscardSelector(0,0,null,null);
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

    //combination tests
    combTest(ds.combination(5,2),10,0);
    combTest(ds.combination(11,4),330,1);
    combTest(ds.combination(15,7),6435,2);
    combTest(ds.combination(136,18),1.2200028e+22,3);
  }//main end

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
