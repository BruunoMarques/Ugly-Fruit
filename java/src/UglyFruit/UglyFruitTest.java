package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UglyFruitTest {
	  public static ArrayList<Delegation> delegations = new ArrayList<Delegation>();
	  public static ArrayList<User> users = new ArrayList<User>();
	  
	  public static void main(String[] args) {
		  Scanner scanner=new Scanner(System.in);
		  boolean exit = false;
		  
		  System.out.println("UGLY FRUIT\n");
		  
		  User u1 = new User("Claudia");
		  User u2 = new User("Margarida");
		  User u3 = new User("Bruno");
		  User u4 = new User("Rita");
		  users.add(u1);
		  users.add(u2);
		  users.add(u3);
		  users.add(u4);
		  
		  Delegation d1 = new Delegation("Delegation1", "Porto", 2L);
		  Delegation d2 = new Delegation("Delegation2", "Lisboa", 20L);
		  VDMMap productsSet =
		      MapUtil.map(
		          new Maplet(new Product("Morango"), 2L),
		          new Maplet(new Product("Batata doce"), 2L),
		          new Maplet(new Product("Tomate"), 15.0),
		          new Maplet(new Product("Pepino"), 20L),
		          new Maplet(new Product("Couve"), 20L),
		          new Maplet(new Product("Rabanete"), 20L),
		          new Maplet(new Product("Ameixa"), 20L),
		          new Maplet(new Product("Ananas"), 20L));
		  Producer p1 = new Producer("Diana", Utils.copy(productsSet));
		  Producer p2 = new Producer("Manuel", Utils.copy(productsSet));
		  Producer p3 = new Producer("Carlos", MapUtil.map(new Maplet(new Product("Kiwi"), 40L)));
		  Producer p4 = new Producer("Ricardo", Utils.copy(productsSet));
		  Object basketSizeSmall = UglyFruit.quotes.smallQuote.getInstance();
		  Object basketSizeLarge = UglyFruit.quotes.largeQuote.getInstance();
		  
		  d1.registerProducer(p1);
		  d1.registerUser(u1, basketSizeSmall);
		  d1.registerUser(u2, basketSizeSmall);
		  d1.registerUser(u3, basketSizeSmall);
		  delegations.add(d1);
		  delegations.add(d2);
		  System.out.println("");
		  
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
					  System.out.println("Invalid Input! Try again.\n");	
			  }	  
		}
		System.out.println("UglyFruit has ended");
	  }
  
  public static void createDelegation() {
	  Scanner scanner=new Scanner(System.in);
	  boolean exit = false;
	  System.out.println("");
	  
	  while (!exit) {
		  System.out.print("Name: ");
		  String name = scanner.nextLine();
		  boolean alreadyExists = false;
		  
		  for(int i = 0; i < delegations.size(); i++) {
			  if (delegations.get(i).getName().equals(name))
				  alreadyExists = true;
		  }
		  
		  if(alreadyExists) {		  
			  System.out.println("A delegation with that name already exists. Try again.");
		  } else {
			  System.out.print("Location: ");
			  String location = scanner.nextLine();
			  
			  System.out.print("User capacity (integer): ");
			  String uc = scanner.nextLine();
			  int userCapacity = -1;
			  boolean invalid = false;
			  
			  try {
				    userCapacity = Integer.parseInt(uc);
			  } catch (NumberFormatException e) {
			    System.out.println("Invalid input! Try again.\n");
			    invalid = true;
			  }
			  
			  if(userCapacity < 1 && !invalid) {
				  System.out.println("User capacity must bigger than zero. Try again.\n");
			  } else if (!invalid){
					  Delegation del = new Delegation(name, location, userCapacity);
					  delegations.add(del);
					  
					  System.out.println("Delegation " + name + " created.\n");
					  
					  exit = true;
			  }
		  }
	}
  }
  
  public static void manageDelegations() {
	  Scanner scanner=new Scanner(System.in);
	  System.out.println("");
	  
	  if (delegations.size() == 0) {
		  System.out.println("No delegations have been added...\n");
		  return;
	  }else{ 
		  for(int i = 0; i < delegations.size(); i++) {
			  int displayedChoice = i+1;
			  System.out.println(displayedChoice + " : name -> " + delegations.get(i).getName());
		  }
		  System.out.println("");
		  
		  boolean exit = false;
		  while (!exit) {
			  System.out.print("Choose Delegation: ");
			  String ch = scanner.nextLine();
			  int choice = -1;
			  boolean invalid = false;
			  
			  try {
				  choice = Integer.parseInt(ch);
			  } catch (NumberFormatException e) {
				  invalid = true;
			  }
			  
			  if(invalid || choice < 1 || choice > delegations.size()) {
				  System.out.println("Invalid input! Try again.\n");
				  invalid = true;
			  }
			  
			  if(!invalid) {
				  delegationOptions(delegations.get(choice-1));
				  exit = true;
			  }
		  }
		  
	  }
  }
  
  public static void delegationOptions(Delegation del) {
	  Scanner scanner=new Scanner(System.in);
	  boolean exit = false;
	  
	  System.out.println("");
	  System.out.println("Name: " + del.getName());
	  System.out.println("Location: " + del.getLocation());
	  System.out.println("User capacity: " + del.getUserCapacity());
	  
	  for (Iterator iterator_5 = del.getUsers().iterator(); iterator_5.hasNext(); ) {
		  User u = (User) iterator_5.next();
		  System.out.println("Name: " + u.getName() + " | Paid value: " + u.getPaidValue());
	  }
	  
	  while (!exit) {
		  System.out.println("0 : Exit");
		  System.out.println("1 : Register User");
		  System.out.println("2 : Register Producer");
		  System.out.println("3 : Create Baskets");
		  System.out.print("Choice: ");
		  String choice = scanner.nextLine();
		  
		  switch(choice) {
			  case "0":
				  exit = true;
				  break;
			  case "1":
				  registerUser(del);
				  break;
			  case "2":
				  registerProducer(del);
				  break;
			  case "3":
				  createBaskets(del);
				  break;
			  default:
				  System.out.println("Invalid Input! Try again.\n");	
		  }	  
	}	  
	System.out.println("");	  
  }
  
  public static void registerUser(Delegation del) {
	  Scanner scanner=new Scanner(System.in);
	  boolean exit = false;
	  System.out.print("");
	  
	  while (!exit) {
		  System.out.print("Name: ");
		  String name = scanner.nextLine();
		  
		  exit = true;
		  
		  /*if(alreadyExists) {		  
			  System.out.println("A delegation with that name already exists. Try again.");
		  } else {
			  System.out.print("Location: ");
			  String location = scanner.nextLine();
			  
			  System.out.print("User capacity (integer): ");
			  String uc = scanner.nextLine();
			  int userCapacity = -1;
			  boolean invalid = false;
			  
			  try {
				    userCapacity = Integer.parseInt(uc);
			  } catch (NumberFormatException e) {
			    System.out.println("Invalid input! Try again.\n");
			    invalid = true;
			  }
			  
			  if(userCapacity < 1 && !invalid) {
				  System.out.println("User capacity must bigger than zero. Try again.\n");
			  } else if (!invalid){
					  Delegation del = new Delegation(name, location, userCapacity);
					  delegations.add(del);
					  
					  System.out.println("Delegation " + name + " created.\n");
					  
					  exit = true;
			  }
		  }*/
	  }	  
  }
  
  public static void registerProducer(Delegation del) {
	  
  }
  
  public static void createBaskets(Delegation del) {
	  
  }

  public UglyFruitTest() {}

  public String toString() {

    return "UglyFruitTest{}";
  }
}
