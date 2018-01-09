package UglyFruit;

import java.io.IOException;
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UglyFruitTest {
	  public static ArrayList<Delegation> delegations = new ArrayList<Delegation>();
	  public static ArrayList<User> users = new ArrayList<User>();
	  public static ArrayList<Producer> producers = new ArrayList<Producer>();
	  public static void main(String[] args) {
		  Scanner scanner=new Scanner(System.in);
		  boolean exit = false;
		  		  
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
		          new Maplet(new Product("Morango"), 9L),
		          new Maplet(new Product("Batata doce"), 20L),
		          new Maplet(new Product("Tomate"), 15.0),
		          new Maplet(new Product("Pepino"), 20L),
		          new Maplet(new Product("Couve"), 20L),
		          new Maplet(new Product("Rabanete"), 20L),
		          new Maplet(new Product("Ameixa"), 20L),
		          new Maplet(new Product("Ananas"), 20L));
		  Producer p1 = new Producer("Diana", Utils.copy(productsSet));
		  Producer p2 = new Producer("Ricardo", Utils.copy(productsSet));
		  
		  producers.add(p1);
		  producers.add(p2);
		  
		  Object basketSizeSmall = UglyFruit.quotes.smallQuote.getInstance();
		  Object basketSizeLarge = UglyFruit.quotes.largeQuote.getInstance();
		  
		  d1.registerProducer(p1);
		  d2.registerProducer(p2);
		  d1.registerUser(u1, basketSizeSmall);
		  d1.registerUser(u2, basketSizeLarge);
		  d1.registerUser(u3, basketSizeSmall);
		  
		  delegations.add(d1);
		  delegations.add(d2);
		  System.out.println("");
		  
		  while (!exit) {
			  System.out.flush();  
			  System.out.println("UGLY FRUIT\n");
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
			  System.out.println(displayedChoice + " : name -> " + delegations.get(i).getName() + " ; No. producers: " + delegations.get(i).getProducers().size() + " ; No. of users registered: " + delegations.get(i).getUsers().size());
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
	  System.out.println("");
	  
	  while (!exit) {
		  System.out.println("0 : Exit");
		  System.out.println("1 : Register User");
		  System.out.println("2 : Register Producer");
		  System.out.println("3 : See Users");
		  System.out.println("4 : See Producers");
		  System.out.println("5 : Create Baskets");
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
				  seeUsers(del);
			  case "4":
				  seeProducers(del);
			  case "5":
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
	  System.out.println("");
	  
	  while (!exit) {
		  System.out.print("Name: ");
		  String name = scanner.nextLine();
		  boolean invalid = false;
		  
		  if(name.length() > 15) {
			  System.out.println("An username cannot have more than 15 characters. Try again.");
		  } else {
			  boolean invalidUser = false;
			  
			  for (Iterator iterator_5 = del.getUsers().iterator(); iterator_5.hasNext(); ) {
				  User u = (User) iterator_5.next();
				  if (u.getName().equals(name)) {
					  invalidUser = true;
					  System.out.println("An user of that name already exists on delegation" + del.getName() + ". Try again.");
					  break;
				  }
			  }
			  
			  for (Iterator iterator_5 = del.getPendingUsers().iterator(); iterator_5.hasNext(); ) {
				  User u = (User) iterator_5.next();
				  if (u.getName().equals(name)) {
					  invalidUser = true;
					  System.out.println("An user of that name already exists on delegation" + del.getName() + ". Try again.");
					  break;
				  }
			  }
			  
			  
			  if(!invalidUser) {
				  User user = new User(name);
				  if(!PlatformUtils.validateUser(user)) {
					  System.out.println("That user is already registered on another delegation. Try again.");
				  } else {
					  System.out.print("Basket Size (Small or Large): ");
					  String basketSize = scanner.nextLine();
					  basketSize = basketSize.toUpperCase();
					  Object basketSizeSmall = UglyFruit.quotes.smallQuote.getInstance();
					  Object basketSizeLarge = UglyFruit.quotes.largeQuote.getInstance();
					  
					  switch(basketSize) {
						  case "S":
							  del.registerUser(user, basketSizeSmall);
							  break;
						  case "SMALL":
							  del.registerUser(user, basketSizeSmall);
							  break;
						  case "L":
							  del.registerUser(user, basketSizeLarge);
							  break;
						  case "LARGE":
							  del.registerUser(user, basketSizeLarge);
							  break;
						  default:
							  System.out.println("Invalid Input! Try again.\n");					  	
					  
					  }
					  System.out.println("");
					  exit = true;
				  }
			  }
			  
		  }
	  }	  
  }
  
  public static void seeUsers(Delegation del) {
	  Object basketSizeSmall = UglyFruit.quotes.smallQuote.getInstance();
	  
	  if(!del.getUsers().isEmpty()) {
		  System.out.println("\nList of registered users:");
		  for (Iterator iterator_5 = del.getUsers().iterator(); iterator_5.hasNext(); ) {
			  User u = (User) iterator_5.next();
			  System.out.println("Name: " + u.getName());
			  System.out.println("Delegation: " + u.getDelegation().getName());
			  System.out.println("Paid value: " + u.getPaidValue());
			  
			  if (basketSizeSmall.equals(u.getBasketSize()))
				  System.out.println("Basket Size: Small");
			  else System.out.println("Basket Size: Large");
			  
			  System.out.println("No. of baskets received: " + u.getReceivedBaskets());
			  
			  if (u.getCancelBasket())
				  System.out.println("Cancel Basket: True\n");
			  else System.out.println("Cancel Basket: False\n");
		  }
	  } else {
		  System.out.println("The delegation " + del.getName() + " has no users registered.\n");
	  }
	  
	  if(!del.getPendingUsers().isEmpty()) {
		  System.out.println("\nList of pending users:");
		  for (Iterator iterator_5 = del.getPendingUsers().iterator(); iterator_5.hasNext(); ) {
			  User u = (User) iterator_5.next();
			  System.out.println("Name: " + u.getName());
			  
			  if (basketSizeSmall.equals(u.getBasketSize()))
				  System.out.println("Basket Size: Small");
			  else System.out.println("Basket Size: Large");
		  }
		  System.out.println("");
	  } else {
		  System.out.println("The delegation " + del.getName() + " has no pending users.\n");
	  }
  }
  
  public static void seeProducers(Delegation del) {
	  
  }
  
  public static void registerProducer(Delegation del) {
	  Scanner scanner=new Scanner(System.in);
	  boolean exit = false;
	  System.out.print("");

	  while (!exit) {
		  System.out.print("Name: ");
		  String name = scanner.nextLine();

		  boolean existing = false;

		  for(int i = 0; i < producers.size(); i++) {
			  if (producers.get(i).getName().equals(name))
				  existing = true;
		  }
		  System.out.println("");

		  exit = true;

		  if(existing) {
			  System.out.println("A producer with that name already exists. Try again.");
		  } else {
			  VDMMap EmptyProductsSet = MapUtil.map();
		  	Producer p = new Producer(name, Utils.copy(EmptyProductsSet));


		  	boolean finished = false;
			  System.out.println("Insert the products you wish to prodvide the platform with");

		  	while (!finished){
				System.out.print("Insert Product Name: ");
				String productname = scanner.nextLine();

				System.out.print("Insert Product ammount: ");
				String ammount = scanner.nextLine();
				int	realAmmount;
				boolean invalid = false;
					try {
						realAmmount = Integer.parseInt(ammount);
						p.addProduct(new Product(productname),realAmmount);
					} catch (NumberFormatException e) {
						System.out.println("Invalid input! Try again.\n");
						invalid = true;
					}

					System.out.print("If finished press 0");
					String finish = scanner.nextLine() ;
					if (finish.equals("0")){
						del.registerProducer(p);
						finished = true;
					}
			}
		  }
	  }
  }
  
  public static void createBaskets(Delegation del) {
	  Object basketSizeSmall = UglyFruit.quotes.smallQuote.getInstance();
	  
	  if(del.getUsers().isEmpty()) {
		  System.out.println("There are no registered users on " + del.getName() + "!");
		  return;
	  }
	  
	  if (del.getProducers().isEmpty()) {
		  System.out.println("There are no registered producers on " + del.getName() + "!");
		  return;
	  }
	  
	  if (!(del.getrequiredWeight(Utils.copy(del.getUsers())).doubleValue() < del.getMaxWeight().doubleValue())) {
		  System.out.println("Not enough weight to fill the baskets.");
		  return;
	  }
	  
	  if (del.productsPerBasket(del.getUsers().size()).longValue() < 8L) {
		  System.out.println("Not enough variety to fill the baskets.");
		  return;
	  }
		  del.createBaskets();
		  
		for (Iterator iterator_5 = MapUtil.dom(del.getUserBaskets()).iterator(); iterator_5.hasNext(); ) {
		    User u = (User) iterator_5.next();
		    Basket b = (Basket) Utils.get(del.getUserBaskets(), u);
		    
		    System.out.println("Basket for user " + u.getName() + " in delegation " + del.getName());
		    
		    for (Iterator iterator_1 = MapUtil.dom(b.getProducts()).iterator(); iterator_1.hasNext(); ) {
		        Product p = (Product) iterator_1.next();
		        Number w = (Number) Utils.get(b.getProducts(), p);
		        
		        System.out.println("Product " + p.getName() + " -> Weight: " + w);
		      }
		    System.out.println("");
		  }
		  
  }

  public UglyFruitTest() {}

  public String toString() {

    return "UglyFruitTest{}";
  }
}
