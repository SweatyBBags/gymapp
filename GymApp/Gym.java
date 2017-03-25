import java.lang.Object;
import java.util.ArrayList;
/**
 * Gym class developed to utilise Member as an Array list
 * 
 * @author John Madden (w20077700)
 * @version 2017-02-20
 */
public class Gym
{
    // instance variables
    private String gymName;
    private String managerName;
    private String phoneNumber;
    private ArrayList<Member> members;

    /**
     * Constructor for objects of class Gym
     * 
     * @param gymName   The name of the gym
     * @param managerName   The name of the gym's manager
     */
    public Gym(String gymName, String managerName)
    {
        // initialise instance variables
        if (gymName.length() > 30) 
        {   //limit string length to 30 characters
            this.gymName = gymName.substring(0, 30);
        }
        else 
        {
            this.gymName = gymName;
        }  

        this.managerName = managerName; //no validation required

        members = new ArrayList<Member>();
    }

    /**
     * Constructor for objects of class Gym
     * 
     * @param gymName       The name of the gym
     * @param managerName   The name of the gym's manager
     * @param phoneNumber   The gym's phone number as a string
     */
    public Gym(String gymName, String managerName, String phoneNumber)
    {
        // initialise instance variables
        if (gymName.length() > 30) 
        {   //limit string length to 30 characters
            this.gymName = gymName.substring(0, 30);
        }
        else 
        {
            this.gymName = gymName;
        }  

        this.managerName = managerName; //no validation

        this.phoneNumber = phoneNumber; 
        //initialise phone number to value entered
        for (int i = 0; i < phoneNumber.length(); i++)
        {   //then check phone number is digits only   
            if (!Character.isDigit(phoneNumber.charAt(i)))
            {   //searches string for a non-digit   
                this.phoneNumber = "unknown";
            }   //updates if a digit is found
        } 

        members = new ArrayList<Member>();
    }

    //getters

    /**
     * Returns the gym's name.* 
     * @return  Name if the gym
     */
    public String getGymName()
    {
        return gymName;
    }

    /**
     * Returns the gym manager's name.* 
     * @return  Name of the gym manager
     */
    public String getManagerName()
    {
        return managerName;
    }

    /**
     * Returns the gym's phone number.* 
     * @return  The phone number of the gym as a string
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    //setters

    /**
     * Updates the gym field
     * @param gymName The new name for the gym (limited to 30 characters)
     */
    public void setGymName(String gymName)
    {
        if (gymName.length() > 30) 
        {   //limit string length to 30 characters
            this.gymName = gymName.substring(0, 30);
        }
        else 
        {
            this.gymName = gymName;
        }  
    }

    /**
     * Updates the gym manager field
     * @param managerName The new name for the manager
     */
    public void setManagerName(String managerName)
    {   //no validation required
        this.managerName = managerName; 
    }  

    /**
     * Updates the phone number field
     * @param phoneNumber The new phone number
     */
    public void setPhoneNumber(String phoneNumber)
    {  
        for (int i = 0; i < phoneNumber.length(); i++) 
        {   //check phone number is digits only
            if (!Character.isDigit(phoneNumber.charAt(i)))
            {   //searches string for a non-digit
                phoneNumber = this.phoneNumber;
            }   //return back to original number if a non-digit is found
        }
        this.phoneNumber = phoneNumber;
    }

    //methods

    /**
     * Adds a member to the gym array list
     * @param member Object created in Member class
     */
    public void add(Member member)
    {
        members.add(member);
    }

    /**
     * Lists the gym member's names
     * @return  An indexed list of gym members
     */
    public String listMembers()
    {
        String list = "";
        for (int index = 0; index < members.size(); index++)
        {
            list = list + index + " - " + members.get(index).toString() + "\n";
        }
        if (list.equals(""))
        {
            return "No members";
        }
        else
        {
            return list;
        }        
    }

    /**
     * Lists the gym's members belonging to the BMI category specified 
     * @param   category The category to be displayed
     * @return  The names of the members of the BMI category specified 
     */
    public String listBySpecificBMICategory(String category)
    {
        String list = "";
        category = category.toUpperCase();
        for (int index = 0; index < members.size(); index++)
        {
            if (members.get(index).determineBMICategory().contains(category))
            {
                list = list + members.get(index).getMemberName() + "\n";
            }
        }
        if (list.equals(""))
        {
            return "There are no members in this category";
        }
        else
        {
            return list;
        }
    }              

    /**
     * Lists the gym member's along with their heights and starting weights 
     * @return  The names of the members of the BMI category specified 
     */
    public String listMemberDetailsImperialAndMetric()
    {
        String list = "";
        if (members.size() > 0)
        {
            for (Member member : members)
            {
                list = list + member.getMemberName() + ": " + member.getStartingWeight() + 
                    "\tkg (" + member.convertWeightKGtoPounds() + " lbs)\t" + member.getHeight() + 
                    " metres (" + member.convertHeightMetresToInches() + " inches)\n";
            }
            return list;
        }
        else
        {
            return "There are no members in the gym";
        }
    }

    /**
     * Lists the gym members of ideal body weight
     * @return  The names of the members of with idea body weight
     */
    public String listMembersWithIdealBodyWeight()
    {
        if (members.size() > 0)
        {
            String list = "";
            for (int index = 0; index < members.size(); index++)
            {
                if (members.get(index).isIdealBodyWeight() == true)
                {
                    list = list + members.get(index).getMemberName() + "\n";
                }
            }
            if (list.equals(""))
            {
                return "There are no members in the gym with an ideal body weight";
            }
            else
            {
                return list;
            }
        }
        else
        {
            return "There are no members in the gym";
        }

    }

    /**
     * Gives the number of members in the gym
     * @return  The number of members 
     */
    public int numberOfMembers()
    {
        return members.size();
    }

    /**
     * Removes the member of the gym corresponding to the index specified
     * @param index Choose the index of the member to be removed
     */
    public void remove(int index)
    {
        if ( (index >= 0) && (index < members.size() ) )
        {
            members.remove(index);
        }
    }  

    /**
     * Returns a user-friendly string representation of the Gym object
     * 
     * @return User-friendly String representing the current Gym
     */
    public String toString()    
    {
        String listMembers = listMembers();
        return "Gym Name: " + gymName + ". Manager's name: " + managerName +
            ". Phone number: " + phoneNumber + 
            " \nList of the members in the gym: " + listMembers + "";
    }
}