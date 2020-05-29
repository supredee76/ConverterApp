package convert;

import static convert.ConversionUtil.*;

import java.util.*;

class Program {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    printHeader();
    printReady();
    printOptions();

    while(true){
      String[] input = sc.nextLine().split("\\s");

      if(input.length == 1){        if(input[0].equals("e")){
          break; 
        } else if(input[0].equals("h")){
          printOptions();
        } else { 
          printDisclaimer();
        }
      } else {
        if(input.length == 3){
          double number = Double.parseDouble(input[0]); 
          String from = input[1];
          String to = input[2];
          printResult(number, from, to);
        } else {
          printDisclaimer();
        }
      }
      printReady();
    }
    
    sc.close();
  }
  
  private static void printResult(double n, String from, String to){
    from = from.toUpperCase();
    to = to.toUpperCase();
    double ans = convert(n, from, to);
    if(ans != -1.0){
      System.out.println(String.format("%.2f %s", ans, to));
    } else {
      printDisclaimer();
    }
    
  }
  
  private static void printHeader(){
    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    System.out.println("XXXXXXXXXXXXXXXXX Welcome to Unit Of Measurment (UOM) Converter XXXXXXXXXXXXXXXXXXX");
    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
  }
  
  private static void printOptions(){    
    System.out.println("Length: Centimeter[CM] Inch[IN] Foot[FT] Yard[YD]");
    System.out.println("Weight: Kilogram[KG] Pound[LB]");
    System.out.println("Liquid: Liter[L] Gallon[G] Pound[LB]\n");  
  }

  private static void printDisclaimer(){
    System.out.println("Invalid query. Check your format and/or unit code and try again.");
  }

  private static void printReady(){
    System.out.println("You can enter conversion query following this format. (or enter Help[h], Exit[e])");
    System.out.println("     [quantity] [source units type] [desire unit type]\n");
  }
}
