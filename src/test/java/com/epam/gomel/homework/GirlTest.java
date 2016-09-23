package com.epam.gomel.homework;

import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

/**
 * Created by Al on 21.09.2016.
 */
public class GirlTest {
    Girl girl;
    Boy richBoy;
    Boy poorBoy;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        girl = new Girl(true);
        richBoy = new Boy(Month.AUGUST, 9999999d);
        poorBoy = new Boy(Month.DECEMBER, 1d);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        girl = null;
        richBoy = null;
        poorBoy = null;
    }

    @Test(description = "checks for conformity when boyFriend is rich", groups = {"girls-common", "girls-total"})
    public void boyFriendIsRich() {
        girl.setBoyFriend(richBoy);
        assertTrue(girl.isBoyfriendRich(), "BoyFriend is rich, should returns true");
    }

    @Test(description = "checks for conformity when boyfriend is not rich", groups = {"girls-common", "girls-total"})
    public void boyFriendIsNotRich() {
        girl.setBoyFriend(poorBoy);
        assertFalse(girl.isBoyfriendRich(), "BoyFriend isn't rich, should returns false");
    }

    @Test(description = "checks method returns correct value in suitable condition", groups = {"girls-common", "girls-total"})
    public void boyFriendWillBuyShoes() {
        girl.setBoyFriend(richBoy);
        assertTrue(girl.isBoyFriendWillBuyNewShoes(), "BoyFriend is rich, he'll buy new shoes, returns false");
    }

    @Test(description = "checks method returns correct value in suitable condition", groups = {"girls-common", "girls-total"})
    public void boyFriendWillNotBuyShoes() {
        girl.setBoyFriend(poorBoy);
        assertFalse(girl.isBoyFriendWillBuyNewShoes(), "BoyFriend is not rich and will not buy new shoes, returns false");
    }

    @Test(description = "checks method returns correct value in suitable condition", groups = {"girls-common", "girls-total"})
    public void slimFriendBecomeFat() {
        Girl girl = new Girl(false, true);
        assertTrue(girl.isSlimFriendBecameFat(), "Slim friend become fat, should returns true");
    }

    @Test(description = "checks method returns correct value in suitable condition", groups = {"girls-common", "girls-total"})
    public void slimFriendGotNoFewKillos() {
        Girl girl = new Girl(true, false);
        assertFalse(girl.isSlimFriendBecameFat(), "Slim friend not become fat, should returns false");
    }

    @Test(description = "invoke boys method when boyfriend is rich and check boys wealth for decrease by appropriate amount",
            groups = {"girls-common", "girls-total"})
    public void boyFriendRichSpendBoyFriendMoneyExactAmount(){
        girl.setBoyFriend(richBoy);
        double expected = 1000d;
        double initWealth = richBoy.getWealth();
        girl.spendBoyFriendMoney(expected);
        double currentWealth = richBoy.getWealth();

        assertThat("Boys wealth should be decreased by given amount", expected, is(initWealth - currentWealth));
    }

    @Test(description = "checks branch that returns EXCELLENT when conditions is suitable",
            groups = "girls-total", dependsOnGroups = "girls-common")
    public void moodIsExcellent() {
        Girl girl = new Girl(true, false, richBoy);
        assertEquals(girl.getMood(), Mood.EXCELLENT, "Is pretty, mood should be EXCELLENT");
    }

    @Test(description = "checks branch that returns GOOD when conditions is suitable",
            groups = "girls-total", dependsOnGroups = "girls-common")
    public void moodIsGood() {
        Girl girl = new Girl(false, false, richBoy);
        assertThat("BoyFriend is rich, mood should be GOOD", Mood.GOOD, is(girl.getMood()));
    }

    @Test(description = "checks branch that returns NEUTRAL when conditions is suitable",
            groups = "girls-total", dependsOnGroups = "girls-common")
    public void moodIsNeutral() {
        Girl girl = new Girl(false, true);
        assertEquals(girl.getMood(), Mood.NEUTRAL, "Isn't beauty, boyFriend isn't rich, only friend becomes fat. Mood should be NEUTRAL");
    }

    @Test(description = "checks branch that returns I_HATE_THEM_ALL when conditions is suitable",
            groups = "girls-total", dependsOnGroups = "girls-common")
    public void moodIsHateThemAll() {
        Girl girl = new Girl(false, false);
        System.out.println(girl.getMood());
        assertEquals(girl.getMood(), Mood.I_HATE_THEM_ALL, "Mood should be I_HATE_THEM_ALL");
    }
}