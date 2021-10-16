package inte.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipTest {
    @Test
    void constructorTestId() {
        Membership membership = new Membership();
        assertNotNull(membership);
    }
    @Test
    void constructorTestPointsAddedExists() {
        Membership membership = new Membership(100);
        assertEquals(100, membership.getMembershipPoints().getPoints());
    }

}