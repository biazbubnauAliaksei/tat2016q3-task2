package com.epam.gomel.homework;
import org.testng.annotations.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

/**
 * Created by Al on 21.09.2016.
 */
public class BoyTest {

    private Boy poorBoy;
    private Boy richBoy;
    private Girl girl;

    @BeforeMethod
    public void setUp() throws Exception {
        poorBoy = new Boy(Month.DECEMBER, 1d);
        richBoy = new Boy(Month.AUGUST, 9999999d);
        girl = new Girl(true);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        poorBoy = null;
        richBoy = null;
        girl = null;
    }

    @DataProvider(name = "summer-born")
    public Object[][] makeSummerBoy(){
        return new Object[][]{
                {new Boy(Month.AUGUST)},
                {new Boy(Month.JULY)},
                {new Boy(Month.JUNE)}
        };
    }

    @Test(description = "month is summer, expected true as method result", dataProvider = "summer-born", groups = {"boys-common", "boys-total"})
    public void monthIsSummer(Boy boy) {
       boolean target = boy.isSummerMonth();
        assertTrue(target, "Month of born is summer, method should return true");
    }

    @Test(description = "month isn't summer, expected false as method result", groups = {"boys-common", "boys-total"})
    public void monthIsNotSummer(){
        boolean target = poorBoy.isSummerMonth();
        assertFalse(target, "Month of born isn't summer, method should return false");
    }

    @Test(description = "checks girlfriend for beauty conformity", groups = {"boys-common", "boys-total"})
    public void girlFriendIsPretty(){
        poorBoy.setGirlFriend(girl);
        Boolean target = poorBoy.isPrettyGirlFriend();
        assertTrue(target, "girlfriend is pretty, target method should returns true");
    }

    @Test(description = "checks girlfriend for isn't beauty conformity", groups = {"boys-common", "boys-total"})
    public void girlFriendIsNotPretty(){
        Girl girl = new Girl();
        poorBoy.setGirlFriend(girl);
        Boolean target = poorBoy.isPrettyGirlFriend();
        assertFalse(target, "girlfriend isn't pretty, target method should returns false");
    }

    @Test(description = "checks for wealth with sufficient amount in wealth")
    public void boyIsWealthy() {
        boolean target = richBoy.isRich();
        assertTrue(target, "boy is rich, target method should returns true");
    }

    @Test(description = "checks for isn't wealthy when wealth is not enough", groups = {"boys-common", "boys-total"})
    public void boyIsNotWealthy(){
        boolean target = poorBoy.isRich();
        assertFalse(target, "boy isn't rich, target method should returns false");
    }

    @Test(description = "wealth should be decreased by appropriate amount", groups = {"boys-common", "boys-total"})
    public void wealthIsDecreaseByRequiredAmount(){
        double target = 111d;
        double expected = richBoy.getWealth() - target;
        richBoy.spendSomeMoney(target);
        assertEquals(richBoy.getWealth(), expected, "wealth should be decreased by target amount");
    }

    @Test(expectedExceptions = RuntimeException.class, description = "method throws exception when not sufficient wealth for spending",
            groups = {"boys-common", "boys-total"})
    public void notEnoughWealthForSpend(){
         poorBoy.spendSomeMoney(111d);
    }

    @Test(description = "checks for branch returns EXCELLENT by suitable conditions",
            groups = "boys-total", dependsOnGroups = "boys-common")
    public void moodIsExcellent(){
        richBoy.setGirlFriend(girl);
        assertThat("mood should be EXCELLENT", Mood.EXCELLENT, is(richBoy.getMood()));
    }

    @Test(description = "checks for branch returns GOOD by suitable conditions",
            groups = "boys-total", dependsOnGroups = "boys-common")
    public void moodIsGood(){
        Boy boy = new Boy(Month.JANUARY, 9999999d, girl);
        assertThat("mood should be GOOD", Mood.GOOD, is(boy.getMood()));
    }

    @Test(description = "checks for branch returns HORRIBLE by suitable conditions",
            groups = "boys-total", dependsOnGroups = "boys-common")
    public void moodIsNeutral(){
        assertThat("mood should be NEUTRAL", Mood.NEUTRAL, is(richBoy.getMood()));
    }

    @Test(description = "checks for branch returns BAD by suitable conditions", groups = "boys-total", dependsOnGroups = "boys-common")
    public void moodIsBad(){
        Boy boy = new Boy(Month.AUGUST);
        assertThat("mood should be BAD", Mood.BAD, is(boy.getMood()));
    }

    @Test(description = "checks for branch returns HORRIBLE by suitable conditions",
            groups = "boys-total", dependsOnGroups = "boys-common")
    public void moodIsHorrible(){
        Boy boy = new Boy(Month.DECEMBER);
        assertThat("mood should be HORRIBLE", Mood.HORRIBLE, is(boy.getMood()));
    }
}