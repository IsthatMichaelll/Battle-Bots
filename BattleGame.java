import java.util.*;
class Character {
String name;
String weapon;
String ability;
int health;
// Character class is created
// Instance variables are created. This will store the name, weapon and ability of the character.
// Variable health is created. This will store the health points of the character.


public Character(String name, String weapon, String ability, int health) {
this.name = name;
this.weapon = weapon;
this.ability = ability;
this.health = health;
}
// A constructor for the character class is created. The constructors is defined with parameters: String name, weapon, ability, and health.
// parameter name, weapon, ability, and health are being assigned values to the instance variables name, weapon, ability, and health.
// Used this; to mark the difference between the parameter and the instance.


public void displayInfo() {
System.out.println("Character:" + name);
System.out.println("Weapon:" + weapon);
System.out.println("Ability:" + ability);
System.out.println("Health:" + health);
System.out.println();
}
}
// Declaring a method named displayInfo. This will be used to print the character info
// Characters info is printed.


public class BattleGame {
   static Character[] characters = {
           new Character("Brute", "Ax", "Grenade", 15),
           new Character("Attacker", "Sword", "Rocket Launcher", 15),
           new Character("Archer", "Spear", "Poison Arrow", 15),
           new Character("Tactician", "Bow and Arrow", "Stun Grenade", 15),
           new Character("Camper", "Knife", "Turret", 15)
   };

// A class named BattleGame is declared. This will be the main class.
// An static array name characters is created with 5 character objects. Each object has its own attributes.
// The static array will hold instances of the character class throughout the Battle game class.
// The static array is with the class itself.
// Created a new object of the character class that will be passed to the constructor (create an instance of a class object) of the character class.

static Scanner scanner = new Scanner(System.in);
static Random random = new Random();
// Created static objects used for user input and random number generation for the entire class.
// These static objects will be shared across all the methods in the class without creating an instance of the main class.
static Character player1Character;
static Character player2Character;
static String player1Name;
static String player2Name;
// Created static variables to store the characters for player 1 and player 2.
// Static variables will need to be accessed across different method calls.
static Queue<Integer> turnOrder = new LinkedList<>();
   static Set<Integer> usedNumbers = new HashSet<>();



// Chosen data structures Queue, set, linked list, hash set.
// Declared a variable call usedNumbers to hold a set of numbers (set) and created an instance of a hash set to not allow duplicates.
// Declared a variable called turnOrder to keep track of the order of the players taking turns (queue - fist in, first out)
// and created an instance of linked list to allow adding and removing of taking turns. This should determine the turns by removing
// the player from the front of the queue.
// usedNumbers should help to prevent players from selecting the same number.
// Both static variables should be allowed to access all instances of the class.
static int rounds = 0;
// Declared a static variable named rounds that is set to 0. This will keep track of the number of rounds played.
// This will be share among all instances of the class.
public static void main(String[] args) {
    System.out.println("Welcome to the Battle Game!");
    System.out.print("Press X to Enter! ");
    String entry = scanner.next();
    if (entry.equalsIgnoreCase("X")) {
        displayPlayerMenu(1);
    } else {
        System.out.println("Invalid entry. Exiting.");
    }
}

// This is the main method. The program will start with this method first.
// Created two statements to tell the user to press to start.
// Created a string scanner.next method to allow the user to press a string (x)to start
// Created a if statement with a condition that checks if the user input is a x.
// if it is true then the displayPlayerMenu method will be called, if not then it will print error message.
private static void displayPlayerMenu(int player) {
    if (player == 1 && player1Name == null) {
        System.out.print("Enter name for Player 1: ");
        player1Name = scanner.next();
    } else if (player == 2 && player2Name == null) {
        System.out.print("Enter name for Player 2: ");
        player2Name = scanner.next();

    }System.out.println(" Player " + player + ", select your character:");
    for (int i = 0; i < characters.length; i++) {
        characters[i].displayInfo();
    }
    System.out.println("6. Quit");
    System.out.println("7. Change Character");
    int choice = getUserChoice();
    if (choice == 6) {
        System.out.println("Quitting. Goodbye!");
        System.exit(0);
    } else if (choice == 7) {
        displayPlayerMenu(player);
    } else {
        if (player == 1) {
            player1Character = characters[choice - 1];
            turnOrder.offer(1);
            displayPlayerMenu(2);
        } else {
            player2Character = characters[choice - 1];
            turnOrder.offer(2);
            displayBattleMenu();
        }
    }
    }

    // Created a method called displayPlayerMenu, which should display all 5 characters, option to quit or to change character.
    // created a parameter in the method to represent the player number.
    // created a statement for the user to select a character
    // Created a for loop that will go through the characters array and display all the info on the characters by calling the displayInfo method.
    // Created a if statement for the option to quit or to change character. if true the program will either print a quitting message or
    // displays the displayPlayerMenu method, depending on choice.
    // The displaysPlayerMenu method should be called recursively.
    // Created a else if statement with a condition that checks if the current player is player 1. If the player is player 1 then
    
    // it will assign the selected character to player1character.
    // added the offer method to add player 1 to the turn order queue to keep track of turns for each player.
    // added a operation choice - 1, to correct the array&#39;s start position back one, so the user can select the right character.
    
    private static void displayBattleMenu() {
        System.out.println("1. Start Battle");
        System.out.println("2. Quit");
        int choice = getUserChoice();
        if (choice == 1) {
            startBattle();
        } else if (choice == 2) {
            displayPlayerMenu(1);
        } else {
            System.out.println("Invalid choice. Please try again.");
            displayBattleMenu();
        }
    }
 
    // Created a method called displayBattleMenu that will display the option to start the game or quit.
    // Printed a statement for the user to star game or to quit.
    // Set choice equal to the method getUserChoice
    // Created a if statement with a condition that if the player chooses option 1, the method startBattle will be called
    // or if option 2 is selected then the displayPlayerMenu method will be called, and if neither is selected then the displayBattleMenu will be called.
    
    
    private static void startBattle() {
        System.out.println("Let the battle begin!");
        while (player1Character.health > 0 && player2Character.health > 0) {
            rounds++;
            performRound();
        }
        String winnerName = player1Character.health > player2Character.health ? player1Name : player2Name;
        System.out.println("Game over! " + winnerName + " wins!");
    }
// Created a startBattle method that continue to execute rounds depending on the players health
// Created a while loop with a condition that continues as long as both player 1 and player 2&#39;s health is greater than 1.
// As the rounds increase the loop will call the performRound method.
// Created a ternary operator ? : as a shortcut for a if-else statement. Basically checks if what player has the higher health whoever does wins.
static Character winnerOfRound3 = null;


private static void performRound() {
    System.out.println("Round " + rounds);
    int player1Choice = selectNumber(1);
    int player2Choice = selectNumber(2);

    // Determine the attacker based on who chose the higher number
    int attacker;
    if (player1Choice == player2Choice) {
        // In case of a tie, randomly decide the attacker
        attacker = random.nextBoolean() ? 1 : 2;
        System.out.println("Both players chose the same number. Randomly selecting the attacker for this round.");
    } else {
        attacker = player1Choice > player2Choice ? 1 : 2;
    }

    if (attacker == 1) {
        performAttack(player1Character, player2Character, player1Choice, rounds);
    } else {
        performAttack(player2Character, player1Character, player2Choice, rounds);
    }

    System.out.println(player1Name + " Health: " + player1Character.health);
    System.out.println(player2Name + " Health: " + player2Character.health);

    if (rounds == 3) {
        winnerOfRound3 = attacker == 1 ? player1Character : player2Character;
        System.out.println("Round 3 completed! " + (attacker == 1 ? player1Name : player2Name) + " can use their ability in the next round!");
    }
}

// Created a variable that is equal to the selectNumber method. This will make both players pick a number 1 - 10.
// used (Math.abs) function to make sure the result is not negative.
// (player1Choice - random.nextInt(10)+ 1): whatever number player 1 picks will be subtracted by the random number generated and then 1
// will be added to that. Calculating the difference between both number.
// random.nextInt(10) + 1 , will generate a random number.

// The same will be done for player 2.
// Created a ternary operator to compare both numbers by both players. Whoever is closer wins.
// Created a if-else statement with a condition that calls the method performAttack if attacker is equal to player 1 if not the player 2.

private static void performAttack(Character attacker, Character defender, int selectedNumber, int round) {
    System.out.println("Player " + (attacker == player1Character ? player1Name : player2Name) + "'s Turn:");

    int damage;
    if (round == 4 && attacker == winnerOfRound3) {
        damage = 8; // Ability damage for winner of Round 3
        System.out.println(attacker.name + " uses their ability '" + attacker.ability + "' for " + damage + " damage!");
    } else {
        // Default attack logic based on the round
        damage = calculateDefaultDamage(round);
        System.out.println(attacker.name + " attacks with their weapon '" + attacker.weapon + "' for " + damage + " damage!");
    }

    defender.health -= damage;
}

private static int calculateDefaultDamage(int round) {
    if (round <= 3) {
        return 2; // For rounds 1, 2, and 3, the damage is 2
    } else {
        return 5; // For rounds above 3, the damage is 5
    }
}
// Created a method called performAttack. This method will handle the amount of damage being dealt with weapons and ability&#39;s for each round.
// This method will be private to the class, with parameters that the method will take. ( objects of the character class )
// Used the ternary operator to print whose turn it is.
// Used a if statement with a condition that if the round is less than or equal to 3, set the damage to 2.
// else-if the round is equal to 4 set the damage equal to 5.
// The defenders health will be subtracted equal to the damage in each round.


private static int selectNumber(int player) {
    int selectedNumber = 0;
    boolean validNumber = false;

    String playerName = player == 1 ? player1Name : player2Name;
    while (!validNumber) {
        System.out.print(playerName + ", select a number between 1-10 (or enter -1 to quit): ");

        // Check if the input is an integer
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume the invalid input
            continue;
        }

        selectedNumber = scanner.nextInt();

        // Check for quit condition
        if (selectedNumber == -1) {
            System.out.println("Quitting. Goodbye!");
            System.exit(0);
        }

        // Validate the number is within the correct range
        if (selectedNumber < 1 || selectedNumber > 10) {
            System.out.println("Please only input a number between 1 and 10.");
        }
    }

    usedNumbers.add(selectedNumber);
    return selectedNumber;
}



// Created a private method called selectNumber. This method is used toget the input from the players.
// This method will have parameter named player that will indicate thecurrent player.
// Created a statement that will prompt the user to select a number.
// Created an if statement that will check if the user entered -1, which willquit the game.
// it will print a goodbye message and exits the program.
// Created a method that will add the selected number to the hash set.
// Created a return statement to return the value to variables player1choice and player2choice.


 private static int getUserChoice() {
       System.out.print("Enter your choice: ");
       return scanner.nextInt();
   }
}



// Created a private method within the battleGame class.
// This method is used to obtain the choices from the user from the game menu.
// Created a return statement to return the value entered by the user.
