import java.lang.Object;
import java.util.ArrayList;
/**
 * Represents a member of the gym.
 * Stores ID, name, address, height, starting weight, and gender.
 * 
 * @author John Madden (w20077700)
 * @version 2017-02-20
 */
public class Member
{
    // instance variables
    private int memberId;
    private String memberName;
    private String memberAddress;
    private double height;
    private double startingWeight;
    private String gender;

    /**
     * Constructor for objects of class Member
     * 
     * *@param memberId Member's ID number between 100000 and 999999
     * *    Defaults to 10000
     * *@param memberName Member's name 30 characters max.
     * *    Records first 30 characters only if additional are entered.
     * *@param memberAddress Member's address                  
     * *@param height Member's height in metres
     * *    1m (min), 3m (max). Defaults to 0. 
     * *@param startingWeight Member's starting weight in kg
     * *    35kg (min), 250kg (max). Defaults to 0.
     * *@param gender Member's member (M or F)
     * *    Defaults to "Unspecified"
     */

    public Member(int memberId, String memberName, String memberAddress, double height, double startingWeight, String gender)
    {
        // initialise instance variables
        if (memberId > 100000 && memberId <= 999999)
        {
            this.memberId = memberId;
        }
        else //default to 100000 if invalid entry made
        {
            this.memberId = 100000;
        }

        if (memberName.length() > 30) 
        {   //limit string length to 30 characters
            this.memberName = memberName.substring(0, 30);
        }
        else 
        {
            this.memberName = memberName;
        }    

        this.memberAddress = memberAddress; //no validation
 
        if (height >= 1 && height <= 3)       
        {   //values between 1 and 3 only
            this.height = height;
        }
        else
        {   //default to 0 if invalid entry made
            this.height = 0;
        }

        if (startingWeight >= 35 && startingWeight <= 250)       
        {   //values between 35 and 250 only
            this.startingWeight = startingWeight;
        }
        else
        {   //default to 0 if invalid entry made
            this.startingWeight = 0;
        }

        if (gender.equals("M") || gender.equals("m") || gender.equals("F") || gender.equals("f"))
        {   //return capital letter only for valid entry
            gender = gender.toUpperCase();
            this.gender = gender;
        }        
        else
        {   //default to unspecified if other entry made
            this.gender = "Unspecified";
        }       
    }

    //methods

    /**
     * Calculate a member's body mass index, weight divided by height squared.
     * 
     * @return  Member's BMI (kg m ^-2) truncated to two decimal places
     */
    public double calculateBMI()
    {
        return toTwoDecimalPlaces(startingWeight / (height * height));
    }

    /**
     * Converts a member's height from metres to inches, 1m = 39.37 inches.
     * 
     * @return  Member's height in inches truncated to two decimanl places
     */
    public double convertHeightMetresToInches()
    {
        return toTwoDecimalPlaces(height * 39.37);
    }

    /**
     * Converts a member's starting weight from kilograms to pounds
     * *    1kg = 2.20 lbs.
     * 
     * @return  Member's starting weight in pounds
     * *    truncated to two decimal places
     */
    public double convertWeightKGtoPounds()
    {
        return toTwoDecimalPlaces(startingWeight * 2.20);
    }

    /**
     * Determines the BMI catergory to which the member belongs.
     * The categories are determined as follows:
     * 
     * <pre>
     * less than 15 "VERY SEVERELY UNDERWEIGHT"
     * between 15 (inclusive) and 16 (exclusive) "SEVERELY UNDERWEIGHT"
     * between 16 (inclusive) and 18.5 (exclusive) "UNDERWEIGHT"
     * between 18.5 (inclusive) and 25 (exclusive) "NORMAL"
     * between 25 (inclusive) and 30 (exclusive) "OVERWEIGHT"
     * between 30 (inclusive) and 35 (exclusive) "MODERATELY OBESE"
     * between 35 (inclusive) and 40 (exclusive) "SEVERELY OBESE"
     * greater than 40 "VERY SEVERELY OBESE"
     * </pre>
     * 
     * @return  Member's BMI category
     */
    public String determineBMICategory()
    {   
        double bmi = calculateBMI();

        if (bmi < 15)
        {
            return "VERY SEVERELY UNDERWEIGHT";
        }

        else if (bmi >= 15 && bmi < 16)
        {
            return "SEVERELY UNDERWEIGHT";
        }

        else if (bmi >= 16 && bmi < 18.5)
        {
            return "UNDERWEIGHT";
        }

        else if (bmi >= 18.5 && bmi < 25)
        {
            return "NORMAL";
        }

        else if (bmi >= 25 && bmi < 30)
        {
            return "OVERWEIGHT";
        }

        else if (bmi >= 30 && bmi < 35)
        {
            return "MODERATELY OBESE";
        }

        else if (bmi >= 35 && bmi < 40)
        {
            return "SEVERELY OBESE";
        }

        else
        {
            return "VERY SEVERELY OBESE";
        }
    }

    /**
     * Check's if a member's starting weight is ideal
     * *     as defined by the Devine formula.
     * Males IBW = 50kg + 2.3kg per inch over 5ft
     * Females IBW = 45.5kg + 2.3kg per inch over 5ft
     * Members under 5ft are assigned the IBW of a 5ft member
     * 
     * @return  True if starting weight = ideal body weight. False otherwise.
     */
    public boolean isIdealBodyWeight()
    {  
        double inchHeight = convertHeightMetresToInches();

        if (inchHeight > 60)
        {
            inchHeight = inchHeight - 60;
        }
        else
        {
            inchHeight = 0;
        }

        double idealBodyWeight = 2.3 * inchHeight;

        if (gender.equals("M"))
        {
            idealBodyWeight = idealBodyWeight + 50;
        }
        else
        {
            idealBodyWeight = idealBodyWeight + 45.5;
        }

        return idealBodyWeight <= startingWeight + 2 && idealBodyWeight >= startingWeight - 2;
    }

    /**
     * Returns a user-friendly string representation of the Member object
     * 
     * @return User-friendly String representing the current Member
     */
    public String toString()    
    {
        String bmi = Double.toString(calculateBMI());
        String bmiCategory = determineBMICategory();
        return "Name: " + memberName + ". ID: " + memberId + 
                ". Address: " + memberAddress + ".\n\tHeight: " + height + 
                "m. Starting Weight: " + startingWeight + 
                "kg. BMI:" + bmi + " (" + bmiCategory + ")";          
    }

    //getters 

    /**
     * Returns a member's height.
     * @return  Member's height in metres
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * Returns a member's address.
     * 
     * @return  Member's address
     */
    public String getMemberAddress()
    {
        return memberAddress;
    }

    /**
     * Returns a member's gender.
     * 
     * @return  Member's gender, "M" or "F"
     */
    public String getMemberGender()
    {
        return gender;
    }

    /**
     * Returns a member's ID number.
     * 
     * @return  Member's ID as a 6 digit integer.
     */
    public int getMemberId()
    {
        return memberId;
    }

    /**
     * Returns a member's name.
     * 
     * @return  Member's name truncated to 30 characters.
     */
    public String getMemberName()
    {
        return memberName;
    }

    /**
     * Returns a member's starting weight.
     * 
     * @return  Member's starting weight in kg
     */
    public double getStartingWeight()
    {
        return startingWeight;
    }

    //setters

    /**
     * Updates the gender to the value passed as a parameter 
     * 
     * @param gender    The new gender
     */
    public void setGender(String gender)
    {
        if (gender.equals("M") || gender.equals("m") || gender.equals("F") || gender.equals("f"))
        {   //return capital letter only for valid entry
            gender = gender.toUpperCase();
            this.gender = gender;
        } //no else, leave unchanged if invalid entry is made  
    }

    /**
     * Updates the height to the value passed as a parameter 
     * 
     * @param height    The new height
     */
    public void setHeight(double height)
    {
        if (height >= 1 && height <= 3)       
        {
            this.height = height;
        } //no else, leave unchanged if invalid entry is made  
    }    

    /**
     * Updates the address to the value passed as a parameter 
     * 
     * @param memberAddress    The new address
     */
    public void setMemberAddress(String memberAddress)
    {
        this.memberAddress = memberAddress;           
    }    

    /**
     * Updates the ID to the value passed as a parameter 
     * 
     * @param memberId    The new identification number
     */
    public void setMemberId(int memberId)
    {
        if (memberId > 100000 && memberId <= 999999)
        {
            this.memberId = memberId;
        } //no else, leave unchanged if invalid entry is made
    }

    /**
     * Updates the name to the value passed as a parameter 
     * 
     * @param memberName    The new name
     */
    public void setMemberName(String memberName)
    {
        if (memberName.length() > 30) 
        {
            this.memberName = memberName.substring(0, 30);
        }
        else
        {
            this.memberName = memberName;
        }    
    }

    /**
     * Updates the name to the value passed as a parameter 
     * 
     * @param startingWeight    The new starting weight
     */
    public void setStartingWeight(double startingWeight)
    {
        if (startingWeight >= 35 && startingWeight <= 250)       
        {
            this.startingWeight = startingWeight;
        } //no else, leave unchanged if invalid entry is made
    }   

    //helper

    /**
     * helper method to truncate numbers to two decimal places
     * @return number to two decimal places
     */
    private double toTwoDecimalPlaces(double num)
    {
        return (int) (num * 100 ) / 100.0; 
    }
}