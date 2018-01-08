package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UglyFruitTest {
	  public static ArrayList<Delegation> delegations = new ArrayList<Delegation>();
	  
	  public static void main(String[] args) {
		  Scanner scanner=new Scanner(System.in);
		  boolean exit = false;
		  System.out.println("UGLY FRUIT\n");
		  
		  while (!exit) {
			  System.out.println("0 : Exit");
			  System.out.println("1 : Create Delegation");
			  System.out.println("2 : Manage Delegations");
			  System.out.print("Choice: ");
			  String choice = scanner.nextLine();
			  
			  switch(choice) {
				  case "0":
					  exit = true;
					  break;
				  case "1":
					  createDelegation();
					  break;
				  case "2":
					  manageDelegations();
					  break;
				  default:
					  System.out.println("Invalid Input! Try again.");	
			  }	  
		}
		System.out.println("UglyFruit has ended");
	  }
  
  public static void createDelegation() {
	  Scanner scanner=new Scanner(System.in);
	  boolean exit = false;
	  
	  while (!exit) {
		  System.out.println("");
		  System.out.print("Name: ");
		  String name = scanner.nextLine();
		  
		  System.out.print("Location: ");
		  String location = scanner.nextLine();
		  
		  System.out.print("User capacity (integer): ");
		  int userCapacity = scanner.nextInt();
		  
		  Delegation del = new Delegation(name, location, userCapacity);
		  delegations.add(del);
		  
		  System.out.println("Delegation " + name + " created.");
		  System.out.println("");
		  
		  exit = true;
	}
  }
  
  public static void manageDelegations() {
	  
  }

  public UglyFruitTest() {}

  public String toString() {

    return "UglyFruitTest{}";
  }
}
