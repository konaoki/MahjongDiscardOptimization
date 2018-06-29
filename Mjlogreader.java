import java.io.*;


/** Hais array
 0 ~ 8 Manzu 1,2,3.....9
 9 ~ 17 Pinzu
 18 ~ 26 Souzu
 27 ~ 30 Directions E, S, W, N
 31 ~ 33 Dragons W, G, R
*/

public class Mjlogreader{
  int[] hand;
  int[] discardPile;
  int discarded;
  final static int PLAYERID = 2;
  String data;
  BufferedReader br;
  int index;
  final static String TSUMO = "<V";
  final static String DAHAI = "<F";
  final static String ENDSIGN = "/>";
  String[] temp;
  int[] startGame;
  int junme;
  int curr_game;
  int curr_ptr;

  public Mjlogreader(String filename){
    hand = new int[34];
    discardPile = new int[34];
    startGame = new int[20];
    try{
      br = new BufferedReader(new FileReader(filename));
      data = br.readLine();
      temp = data.substring(data.indexOf("hai2=") + 6, data.indexOf("hai3=") - 2).split(",");
      for(int i = 0; i < temp.length; i++){
        hand[tileConverter(Integer.parseInt(temp[i]))]++;
      }
      int idx = 0;
      startGame[idx] = data.indexOf("<INIT");
      while(data.indexOf("<INIT", startGame[idx] + 1) != -1){
        idx++;
        startGame[idx] = data.indexOf("<INIT", startGame[idx - 1] + 1);
      }
      junme = 0;
    }catch(IOException e){
      System.out.println("error");
    }
  }


  public int tileConverter(int mjlognumber){
    return mjlognumber >> 2;
  }


  public int[] getHand(){
    return hand;
  }

  public int[] getDiscardPile(){
    return discardPile;
  }

  public int getDiscardedTile(){
    return discarded;
  }

  /**
   * Goes around all the players
   * For naki, just come back.
   * When junme == 0, it will only TSUMO
   * When junme > 0, it will discard and Tsumo.
   */
  public void next(){
    int tsumo;
    if(junme == 0){
      junme++;
      curr_ptr = startGame[curr_game];
      tsumo = getNextTile(true);
      System.out.println("Tsumo: " + tsumo);
      hand[tsumo]++;
    }else if(curr_ptr > startGame[curr_game + 1] || curr_ptr == -1){
      System.out.println("--------");
      System.out.println("End of Kyoku");
      System.out.println("--------");
      curr_game++;
      if(startGame[curr_game] == 0){
        System.out.println("--------");
        System.out.println("--------");
        System.out.println("END OF THE GAME");
      }
    }else{
      junme++;
      discarded = getNextTile(false);
      System.out.println("Junme: " + junme);
      System.out.println("Discard: " + discarded);
      hand[discarded]--;
      discardPile[discarded]++;
      tsumo = getNextTile(true);
      System.out.println("Tsumo: " + tsumo);
      hand[tsumo]++;
    }
  }

  private void printHandArray(){
    System.out.println("---------");

    for(int i = 0; i < 34; i++) {
    			if(i == 0) System.out.print("M: ");
    			if(i == 9) System.out.print("\nP: ");
    			if(i == 18) System.out.print("\nS: ");
    			if(i == 27) System.out.print("\nW: ");
    			if(i == 31) System.out.print("\nD: ");
    			System.out.print(hand[i] + ", ");
    		}
    	System.out.println("\n---------");
  }
  private int getNextTile(boolean isTsumo){
    String indicator;
    indicator = (isTsumo)? TSUMO : DAHAI;
    curr_ptr = data.indexOf(indicator, curr_ptr);
    int returnVal = tileConverter(Integer.parseInt(data.substring(data.indexOf(indicator, curr_ptr) + 2, data.indexOf(ENDSIGN, curr_ptr))));

    curr_ptr = data.indexOf(indicator, curr_ptr + 1);
    return returnVal;
  }

  public static void main(String[] args){
    Mjlogreader mr = new Mjlogreader("sample.mjlog");
    for(int i = 0; i < 10; i++){
      mr.printHandArray();
      mr.next();
    }
  }

}
