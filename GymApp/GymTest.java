import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

/**
 * The test class GymTest.
 *
 * @author  (Siobhan Drohan)
 * @version (02 Feb 2017)
 */
public class GymTest
{
    //instance fields for testing throughout the test class
    private Member normalMember1;
    private Member normalMember2;
    private Member invalidMemberDetailsLower;
    private Member invalidMemberDetailsUpper;

    //instance fields for testing the BMI category method.
    private Member under15;
    private Member equal15;
    private Member equal16;
    private Member below18point5;
    private Member equal18point5;
    private Member below25;
    private Member equal25;
    private Member below30;
    private Member equal30;
    private Member below35;
    private Member equal35;
    private Member below40;
    private Member equal40;

    //instance fields for testing the ideal weight method; these are ideal body weights:
    private Member under5footM1;
    private Member under5footF1;
    private Member justover5footM;
    private Member justover5footF;
    private Member wellover5footM;
    private Member wellover5footF;

    //instance fields for testing the ideal weight method; these are NOT ideal body weights:
    private Member under5footM1NotIdeal;
    private Member under5footF1NotIdeal;
    private Member justover5footMNotIdeal;
    private Member justover5footFNotIdeal;
    private Member wellover5footMNotIdeal;
    private Member wellover5footFNotIdeal;

    private Gym idealWeightsGym;
    private Gym bmiCategoriesGym;
    private Gym allMembersGym;    
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        normalMember1 = new Member(100001, "012345678901234567890123456789", "Waterford", 1, 35, "f");
        normalMember2 = new Member(999999, "012345678901234567890123456789", "Waterford", 3, 250, "m");
        invalidMemberDetailsLower = new Member(100000,  "01234567890123456789012345678901", 
            "No validation on this field", 0.8, 34.8, "t");
        invalidMemberDetailsUpper = new Member(1000000, "01234567890123456789012345678901", 
            "No validation on this field", 3.2, 250.2, "ff");

        //instance fields for testing the BMI category method.
        under15       = new Member(123456, "v sev u-weight", "Waterford", 2, 59.96,  "m");
        equal15       = new Member(123457, "sev u-weight",   "Waterford", 2, 60,     "f");
        equal16       = new Member(123458, "u-weight",       "Waterford", 2, 64,     "Unspecified");
        below18point5 = new Member(123459, "u-weight",       "Waterford", 2, 73.88,  "f");
        equal18point5 = new Member(123460, "normal",         "Waterford", 2, 74,     "m");
        below25       = new Member(123461, "normal",         "Waterford", 2, 99.2,   "f");
        equal25       = new Member(123462, "overweight",     "Waterford", 2, 100,    "m");
        below30       = new Member(123463, "overweight",     "Waterford", 2, 119.92, "f");
        equal30       = new Member(123464, "mod obese",      "Waterford", 2, 120,    "m");
        below35       = new Member(123465, "mod obese",      "Waterford", 2, 139.96, "Unspecified");
        equal35       = new Member(123466, "sev obese",      "Waterford", 2, 140,    "m");
        below40       = new Member(123467, "sev obese",      "Waterford", 2, 159.92, "f");
        equal40       = new Member(123468, "v sev obese",    "Waterford", 2, 160,    "m");
        
  
        idealWeightsGym  = new Gym("Ideal and non-ideal Weight Gym", "The Rock", "0563232532");
        idealWeightsGym.add(  new Member(123456, "under5foot M ideal", "Waterford", 1.50, 50.0, "m"));
        idealWeightsGym.add(  new Member(123457, "under5foot F ideal", "Waterford", 1.50, 45.5, "f"));
        idealWeightsGym.add(  new Member(123458, "just over 5ft M", "Waterford", 1.53, 52, "m"));
        idealWeightsGym.add(  new Member(123459, "just over 5ft F", "Waterford", 1.53, 47.5, "f"));
        idealWeightsGym.add(  new Member(123458, "well over 5ft M", "Waterford", 1.78, 73, "m"));
        idealWeightsGym.add(  new Member(123459, "well over 5ft F", "Waterford", 1.78, 68.5, "f"));
        idealWeightsGym.add(  new Member(123456, "under5foot M NOT ideal",    "Waterford", 1.50, 47.98, "m"));
        idealWeightsGym.add(  new Member(123457, "under5foot F NOT ideal",    "Waterford", 1.50, 43.45, "f"));
        idealWeightsGym.add(  new Member(123458, "just over 5ft M NOT ideal", "Waterford", 1.53, 52.6, "m"));
        idealWeightsGym.add(  new Member(123459, "just over 5ft F NOT ideal", "Waterford", 1.53, 48.1, "f"));        
        idealWeightsGym.add(  new Member(123458, "well over 5ft M NOT ideal", "Waterford", 1.78, 75.2, "m"));
        idealWeightsGym.add(  new Member(123459, "well over 5ft F NOT ideal", "Waterford", 1.78, 70.7, "f"));    
   
        bmiCategoriesGym = new Gym("BMI Categories Allsorts Gym!!!!", "Eddie the Eagle", "051-23222");
        allMembersGym    = new Gym("WIT Arena", "John O'Shea", "+353 51 343322");
    }

    //===========================================
    //  TESTING CONSTRUCTORS AND GETTERS TOGETHER
    //===========================================
    @Test
    public void testGettersWithValidConstructorEntries()
    {
        assertThat(idealWeightsGym.getGymName().equals("Ideal and non-ideal Weight Gym"), is(true));
        assertThat(idealWeightsGym.getGymName().length(), is(30));
        assertThat(idealWeightsGym.getManagerName().equals("The Rock"), is(true));
        assertThat(idealWeightsGym.getPhoneNumber().equals("0563232532"), is(true));
    }

    @Test
    public void testGettersWithInValidConstructorEntries()
    {
        assertThat(bmiCategoriesGym.getGymName().equals("BMI Categories Allsorts Gym!!!"), is(true));
        assertThat(bmiCategoriesGym.getGymName().length(), is(30));
        assertThat(bmiCategoriesGym.getManagerName().equals("Eddie the Eagle"), is(true));
        assertThat(bmiCategoriesGym.getPhoneNumber().equals("unknown"), is(true));
    }
    
    //======================================
    //  TESTING SETTERS AND GETTERS TOGETHER
    //======================================
    @Test
    public void testGettersWithValidSetterEntries()
    {
        assertThat(idealWeightsGym.getGymName().equals("Ideal and non-ideal Weight Gym"), is(true));
        idealWeightsGym.setGymName("Ideal and NON-ideal Weight Gym");
        assertThat(idealWeightsGym.getGymName().equals("Ideal and NON-ideal Weight Gym"), is(true));
        
        assertThat(idealWeightsGym.getManagerName().equals("The Rock"), is(true));
        idealWeightsGym.setManagerName("Dwayne Johnson");
        assertThat(idealWeightsGym.getManagerName().equals("Dwayne Johnson"), is(true));
                
        assertThat(idealWeightsGym.getPhoneNumber().equals("0563232532"), is(true));
        idealWeightsGym.setPhoneNumber("0513232333");
        assertThat(idealWeightsGym.getPhoneNumber().equals("0513232333"), is(true));
    }
    
    @Test
    public void testGettersWithInValidSetterEntries()
    {
        assertThat(bmiCategoriesGym.getGymName().equals("BMI Categories Allsorts Gym!!!"), is(true));
        bmiCategoriesGym.setGymName("BMI Categories Allsorts Gymnasium");
        assertThat(bmiCategoriesGym.getGymName().equals("BMI Categories Allsorts Gymnas"), is(true));
        
        assertThat(bmiCategoriesGym.getManagerName().equals("Eddie the Eagle"), is(true));
        bmiCategoriesGym.setManagerName("Michael Edwards");
        assertThat(bmiCategoriesGym.getManagerName().equals("Michael Edwards"), is(true));
                
        assertThat(bmiCategoriesGym.getPhoneNumber().equals("unknown"), is(true));//"051-23222"
        bmiCategoriesGym.setPhoneNumber("0513232333");
        assertThat(bmiCategoriesGym.getPhoneNumber().equals("0513232333"), is(true));
        bmiCategoriesGym.setPhoneNumber("Ireland 051-3232333");
        assertThat(bmiCategoriesGym.getPhoneNumber().equals("0513232333"), is(true));
    }
    
    //==============================================================================
    //  TESTING EXISTENCE OF toString METHOD AND THE CONTENTS OF THE RETURNED STRING
    //==============================================================================
    @Test
    public void testToString()
    {
        assertThat(idealWeightsGym.toString().contains("Ideal and non-ideal Weight Gym"), is(true));
        assertThat(idealWeightsGym.toString().contains("The Rock"), is(true));
        assertThat(idealWeightsGym.toString().contains("0563232532"), is(true));
        
        //?sdr?  add in something to test for inclusion of arraylist members
    }    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}