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
    if(!Arrays.equals(ds.difference(a,b),answer))
    {
      System.out.println("dif "+testnum);
      System.exit(0);
    }

    a = new int[]{0,1,2};
    b = new int[]{2,3,4};
    testnum++;
    answer = new int[]{3,4};
    if(!Arrays.equals(ds.difference(a,b),answer))
    {
      System.out.println("dif "+testnum);
      System.exit(0);
    }

    a = new int[]{0,1,2};
    b = new int[]{3,4,5};
    testnum++;
    answer = new int[]{0,1,2};
    if(!Arrays.equals(ds.difference(a,b),answer))
    {
      System.out.println("dif "+testnum);
      System.exit(0);
    }

    a = new int[]{3,4,5};
    b = new int[]{0,1,2};
    testnum++;
    answer = new int[]{3,4,5};
    if(!Arrays.equals(ds.difference(a,b),answer))
    {
      System.out.println("dif "+testnum);
      System.exit(0);
    }

    a = new int[]{1,2,3};
    b = new int[]{0,4,5};
    testnum++;
    answer = new int[]{1,2,3};
    if(!Arrays.equals(ds.difference(a,b),answer))
    {
      System.out.println("dif "+testnum);
      System.exit(0);
    }

    a = new int[]{0,4,5};
    b = new int[]{1,2,3};
    testnum++;
    answer = new int[]{0,4,5};
    if(!Arrays.equals(ds.difference(a,b),answer))
    {
      System.out.println("dif "+testnum);
      System.exit(0);
    }
  }
}
