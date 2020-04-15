// https://www.cs.nmsu.edu/~rth/cs/cs187/f97/battleshipdesign.html

package object_oriented_design;

/**
 *
 * @author venkateshwaran shanmugham
 */

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 


class Ship{
  static final int HORIZONTAL = 0;
  static final int VERTICAL = 1;
  private int number;
  private int length;
  private int health;
  private int direction = HORIZONTAL;
  Ship(int length, int number){
    this.length = length;
    this.number = number;
    this.health = length;
  }
  
  public int getHealth(){
    return this.health;
  }
  
  public void reduceHealth(){
    this.health--;
  }
  
  public int getLength(){
    return this.length;
  }
  
  public int getNumber(){
    return this.number;
  }
}

class Util{
  public static boolean outOfBoundXY(int x, int y, int n){
    return x<0 || x>=n || y<0 || y>=n ;
  }
  
  public static int getindex(String pos, int i){
    return pos.charAt(i)-'A';
  }
  
  public static void welcomeString(){
    System.out.println("Welcome to BattleShip");
  }
  
  public static void firingGuidelines(){
    System.out.println("Battle starts: Firing guidelines");
    System.out.println("Please enter the input as Row Letter, column Letter");
    System.out.println("Row and column Letters start from A");
    System.out.println("Example -> AB means row A, column B, hit the position AB"); 
  }
  
  public static void shipPlacementGuidelines(){
    System.out.println("Ship placement Guidelines: ");
    System.out.println("Please enter the input as Row Letter, column Letter and Direction: ");
    System.out.println("Row and column Letters start from A and Direction can be H or V (Horizontal or vertical)");
    System.out.println("Example -> ABH means row A, column B and Place horizontally starting from position AB");
  }
  
  public static void errorMessage(String s){
    System.out.println("INVALID INPUT: " + s);
  }
  
  public static void columnHeader(int n){
    System.out.print("  ");
    for(int i=0;i<n;i++)
      System.out.print((char)('A'+i)+" ");
    System.out.println();
  }
  
  public static void showShipPlacement(Player owner, Player other){
    System.out.println(owner.getName()+"'s Ship Placement Board");
    owner.getShipBoard().showShipBoard(other.getFireBoard());
  }
  
  public static void showFireHistory(Player owner, Player other){
    System.out.println(owner.getName()+"'s Fire History Board");
    owner.getFireBoard().showFireBoard(other.getShipBoard(), other.getShips());
  }
}

class Board{
  protected int arr[][];
  protected int n;
  Board(int n){
    this.n = n;
    this.arr = new int[n][n];
  }
  
  public void showBoard(){
    Util.columnHeader(n);
    for(int i=0;i<n;i++){
      System.out.print((char)('A'+i)+" ");
      for(int j=0;j<n;j++){
        System.out.print(arr[i][j]+" ");
      }
      System.out.println();
    }
  }
  
  public int getValueAtPos(int x, int y){
    return arr[x][y];
  }
}

class FireBoard extends Board{
  FireBoard(int n){
    super(n);
  }
  
  public void showFireBoard(ShipBoard sb, Ship[] ships){
    Util.columnHeader(n);
    for(int i=0;i<n;i++){
      System.out.print((char)('A'+i)+" ");
      for(int j=0;j<n;j++){
        if(arr[i][j]==0)
          System.out.print(arr[i][j]+" ");
        else if(arr[i][j]==1 && sb.arr[i][j]==0){
          System.out.print("- ");
        }else if(arr[i][j]==1 && sb.arr[i][j]>0){
          if(ships[sb.arr[i][j]-1].getHealth()==0){
            System.out.print(sb.arr[i][j]+" ");
          }else{
            System.out.print("X ");
          }
        }
      }
      System.out.println();
    }
  }
  
  public boolean updateFireBoard(String pos){
    if(!validFirePos(pos)){
      return false;
    }
    int x = Util.getindex(pos, 0), y = Util.getindex(pos, 1);
    if(arr[x][y]!=0){
      System.out.println("Already fired the spot: "+pos);
      System.out.println("Fire somewhere else: ");
      return false;
    }
    arr[x][y] = 1;
    return true;
  }
  
  public boolean validFirePos(String pos){
    if(pos.length()!=2){
      Util.errorMessage("Length is not "+ 2);
      return false;
    }
    int x = Util.getindex(pos, 0), y = Util.getindex(pos, 1);
    if(Util.outOfBoundXY(x,y, n)){
      Util.errorMessage(pos.substring(0,2)+" not in the matrix range");
      return false;
    }
    return true;
  }
}
  
class ShipBoard extends Board{
  ShipBoard(int n){
    super(n);
  }
  
  public void showShipBoard(FireBoard fb){
    Util.columnHeader(n);
    for(int i=0;i<n;i++){
      System.out.print((char)('A'+i)+" ");
      for(int j=0;j<n;j++){
        if(arr[i][j]>0 && fb.arr[i][j]==1)
          System.out.print("X ");
        else if(arr[i][j]>0 && fb.arr[i][j]==0){
          System.out.print(arr[i][j]+" ");
        }else if(arr[i][j]==0 && fb.arr[i][j]==1){
          System.out.print("- ");
        }else if(arr[i][j]==0 && fb.arr[i][j]==0)
          System.out.print(arr[i][j]+" ");
      }
      System.out.println();
    }
  }
  
  // invalid_input
  // ship out of bound
  // ship collision with another
  // successful placement
  public boolean placeShip(Ship s, String pos){
    if(!validPlacementInput(pos)){
      return false;
    }
    int x = Util.getindex(pos, 0), y = Util.getindex(pos, 1);
    char k = pos.charAt(2);
    int d = k=='H'? Ship.HORIZONTAL : Ship.VERTICAL;
    int l = s.getLength();

    if(!shipBound(x,y,l,d)){
      return false;
    }
    if(!checkCollision(x,y,l,d)){
      return false;
    }
    // place ship
    for(int i=0;i<l;i++){
      if(d==Ship.HORIZONTAL){
        this.arr[x][y+i] = s.getNumber();
      }else{
        this.arr[x+i][y] = s.getNumber();
      }
    }
    return true;
  }
  
  public boolean validPlacementInput(String pos){
    if(pos.length()!=3){
      Util.errorMessage("Length is not "+ 3);
      return false;
    }
    int x = Util.getindex(pos, 0), y = Util.getindex(pos, 1);
    char k = pos.charAt(2);
    if(Util.outOfBoundXY(x, y, n)){
      Util.errorMessage(pos.substring(0,2)+" not in the matrix range");
      return false;
    } 
    if(k!='H' && k!='V'){
      Util.errorMessage("Direction can only be 'H' or 'V'");
      return false;
    } 
    return  true;
  }
  
  public boolean shipBound(int x,int y, int l, int d){
    if(d==Ship.HORIZONTAL && y+l>n){
      System.out.println("Ship extending Too much to right: Please placing a little left");
      return false;
    }
    else if(d==Ship.VERTICAL && x+l>n){
      System.out.println("Ship extending Too much to bottom: Please placing a little upwards");
      return false;
    }
    return true;
  }
  
  public boolean checkCollision(int x, int y, int l, int d){
    for(int i=0;i<l;i++){
      if(d==Ship.HORIZONTAL && this.arr[x][y+i]!=0){
        System.out.println(String.format("Ship Overlapping at: %c%c",(char)(x+'A'),(char)(y+i+'A') ));
        return false;
      }else if(d==Ship.VERTICAL && this.arr[x+i][y]!=0){
        System.out.println(String.format("Ship Overlapping at: %c%c",(char)(x+i+'A'),(char)(y+'A') ));
        return false;
      }
    }
    return true;
  } 
}

class Player{
  //stubs
  final String p1Inp[] = new String[]{"AAH","BAH","CAH","DAH","EAH"};
  final String p2Inp[] = new String[]{"AAH","BAH","CAH"};
  int c = 0;
  private ShipBoard shipBoard;
  private FireBoard fireBoard;
  private String name;
  private Ship ships[];
  private int number =0;
  private int NumberOfFires = 0;
  private int health = 0;
  
  Player(String name, int shipArr[], int number, int NumberOfFires, int matrixSize){
    this.name = name;
    this.number = number;
    this.NumberOfFires = NumberOfFires;
    this.ships = new Ship[shipArr.length];
    shipBoard = new ShipBoard(matrixSize);
    fireBoard = new FireBoard(matrixSize);
    for(int i=0;i<shipArr.length;i++)
      this.ships[i] = new Ship(shipArr[i], i+1);
  }
  
  public String getName(){
    return name;
  }
  
  public ShipBoard getShipBoard(){
    return shipBoard;
  }
    
  public FireBoard getFireBoard(){
    return fireBoard;
  }
  
  public Ship[] getShips(){
    return ships;
  }
  
  public Ship getShip(int i){
    return ships[i];
  }
  
  public void reduceHealth(){
    this.health--;
  }
  
  public void play(Player other)throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    Util.showShipPlacement(this,other);
    Util.showFireHistory(this,other);
    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    
    for(int i=0;i<NumberOfFires;i++){
      System.out.println(name+"'s turn");
      System.out.println("Enter the Location To hit: ");
      String pos = reader.readLine();
      
      if(!fireBoard.updateFireBoard(pos)){
        i--;
      }else{
        int x = Util.getindex(pos, 0), y = Util.getindex(pos, 1);
        int hit = other.getShipBoard().getValueAtPos(x, y);
        if(hit>0){
          other.getShip(hit-1).reduceHealth();
          other.reduceHealth();
        }
      }
      Util.showFireHistory(this,other);
      // check win condition for every fire
      if(other.lost()){
        return;
      }
    }
  }
  
  public boolean lost(){
    return health==0;
  }
  
  public void placeAllShip()throws IOException{
    System.out.println(name+"'s turn");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Util.shipPlacementGuidelines();
    for(int i=0;i<ships.length;i++){
      System.out.println("Place ship of length: "+ getShip(i).getLength());
      shipBoard.showBoard();
     
      String pos = reader.readLine(); // comment this for stubbing
      
//      // ***** stub for placing ships witout manual efforts
//      String pos;
//      if(number==1){
//        pos = p1Inp[c++];
//      }else{
//        pos = p2Inp[c++];
//      }
//      //***** comment lines between ***** and comment readline to stub
//      //
      
      if(!shipBoard.placeShip(ships[i],pos))
        i--;
      else{
        health+=ships[i].getHealth();
      }
    }
    shipBoard.showBoard();
  }
}

class Game{
  Player players[];
  private int matrixSize;
  private int playerCount = 2;
  private int playercannonsEachTurn = 1;
  private int ships[];
  Game(int matrixSize, int ships[], int playercannonsEachTurn){
    this.matrixSize = matrixSize;
    this.ships = ships;
    this.playercannonsEachTurn = playercannonsEachTurn;
    players = new Player[2];
  }
  public void createPlayers()throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
    for(int i=0;i<playerCount;i++){
      System.out.println("Enter player 1's name: ");
      String name = reader.readLine();
      System.out.println("Player "+(i+1)+": " + name);
      players[i] = new Player(name, ships, i+1,playercannonsEachTurn, matrixSize);
    }
  }
  
  public void placeship()throws IOException{
    for(Player p: players)
      p.placeAllShip();
  }
          
  public void battle() throws IOException{
    // if more than 2 players are involved i need to switch chances through a list
    // and ak for input from user on whom to strike
    Util.firingGuidelines();
    int i=0;
    // toggle between 1 and 0
    while(true) {
      Player cur = players[i], other = players[1-i];
      cur.play(other);
      if(other.lost()){
        System.out.println(cur.getName() + " wins");
        break;
      }
      i = 1-i;
    }
  }
}

public class BattleShip {
  public static void main(String args[])throws IOException{
    // change this to configure: like modify maze size, reduce ships length and count.
    Game game = new Game(5, new int[]{3,3,2},3);
    Util.welcomeString();
    game.createPlayers();
    game.placeship();
    game.battle();
  }
  
}
