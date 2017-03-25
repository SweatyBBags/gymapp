import java.util.Scanner;
import java.util.ArrayList;
/**
 * Create a gym from the terminal and perform the listed functions
 * 
 * @author John Madden (w20077700)
 * @version 2017-02-20
 */
public class MenuController
{
    // instance variables
    private Scanner input;
    private Gym gym;

    /**
     * Constructor for objects of class MenuController
     */
    public MenuController()
    {
        // initialise instance variables
        input = new Scanner(System.in);

        //read in the details....
        System.out.print("Please enter the gym name: ");
        String gymName = input.nextLine();

        System.out.print("Please enter the manager's name: ");
        String managerName = input.nextLine();

        System.out.print("Please enter the phone number: ");
        String phoneNumber = input.nextLine();

        gym = new Gym(gymName, managerName, phoneNumber);

        runMenu();
    }

    /**
     * mainMenu() - This method displays the menu for the application, 
     * reads the menu option that the user entered and returns it.
     * 
     * @return     the users menu choice
     */
    private int mainMenu()
    { 
        System.out.println("\fGym Menu");
        System.out.println("---------");     
        System.out.println("  1) Add a Member");    
        System.out.println("  2) List all Members");        
        System.out.println("  3) Remove Member (by index)");    
        System.out.println("  4) Number of members in gym");        
        System.out.println("---------");     
        System.out.println("  5) List gym details"); 
        System.out.println("  6) List members with ideal starting weight"); 
        System.out.println("  7) List members with a specific BMI category"); 
        System.out.println("  8) List all member stats imperically and metrically"); 
        System.out.println("  0) Exit");
        System.out.print("==>> ");
        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the loop.
     */
    private void runMenu()
    {
        int option = mainMenu();
        while (option != 0)
        {  
            switch (option)
            {
                case 1:
                    addMember();
                    break;                
                case 2:
                    System.out.println(gym.listMembers());
                    break;
                case 3:    
                    System.out.println(gym.listMembers());
                    if (gym.numberOfMembers() > 0)
                    {
                        System.out.print("Please enter the index for the member you wish to delete: ");
                        int index = input.nextInt();
                        gym.remove(index);
                    }
                    break;
                case 4:    
                    System.out.println(gym.numberOfMembers());
                    break;
                case 5:    
                    System.out.println(gym.toString());
                    break;
                case 6:    
                    System.out.println(gym.listMembersWithIdealBodyWeight());
                    break;
                case 7:    
                    if (gym.numberOfMembers() == 0)
                    {
                        System.out.print("There are no members in the gym");
                    }
                    else
                    {
                        bmiCategory();
                    }
                    break;
                case 8:    
                    System.out.println(gym.listMemberDetailsImperialAndMetric());
                    break;
                default:   
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress enter to continue...");
            input.nextLine();
            input.nextLine(); //2nd read for bug in Scanner; String read is ignored after reading int.

            option = mainMenu(); //display the main menu again
        }
        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }

    /**
     * gather the member data from the user and create a new member.
     */
    private void addMember()
    {  
        //dummy read of String to clear the buffer - bug in Scanner class.
        input.nextLine();
        System.out.println("Enter the Member details...");

        System.out.print("\tChoose an ID (between 100000 and 999999):  ");
        int memberId = input.nextInt();
        input.nextLine();

        System.out.print("\tEnter Name (max 30 chars):  ");
        String memberName = input.nextLine();

        System.out.print("\tEnter Address:  ");
        String memberAddress = input.nextLine();

        System.out.print("\tEnter Height (between 1m and 3m):  ");
        double height = input.nextDouble();
        input.nextLine();

        System.out.print("\tEnter Starting Weight (between 35kg and 250kg):");
        double startingWeight = input.nextDouble();
        input.nextLine();

        System.out.print("\tEnter Gender (M/F): ");
        String gender = input.nextLine();

        gym.add(new Member(memberId, memberName, memberAddress, height, startingWeight, gender));
    }

    /**
     * Helper method to perform menu option 6
     */
    private void bmiCategory()
    {  
        //dummy read of String to clear the buffer
        //bug in Scanner class.
        input.nextLine();

        System.out.println("Enter a category or a keyword:\nVERY SEVERELY UNDERWEIGHT\nSEVERELY UNDERWEIGHT\nUNDERWEIGHT");
        System.out.println("NORMAL\nOVERWEIGHT\nMODERATELY OBESE\nSEVERELY OBESE\nVERY SEVERELY OBESE");
        String category = input.nextLine();

        String list = gym.listBySpecificBMICategory(category);        
        System.out.println("\n" + list + "\n");
    }
}

