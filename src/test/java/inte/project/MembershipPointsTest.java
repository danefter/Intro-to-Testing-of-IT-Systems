package inte.project;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MembershipPointsTestTest {
    @Test
    void constructorObjectExists(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        assertNotNull(membershipPoints);
    }
    @Test
    void constructorNegativePointsSetToZero(){
        MembershipPoints membershipPoints = new MembershipPoints(-100);
        assertEquals(0, membershipPoints.getPoints());
    }
    @Test
    void constructorPointsDecimalRounded(){
        MembershipPoints membershipPoints = new MembershipPoints(100.6);
        assertEquals(101, membershipPoints.getPoints());
    }
    @Test
    void constructorNegativeDoubleSetToZero(){
        MembershipPoints membershipPoints = new MembershipPoints(-100.6);
        assertEquals(0, membershipPoints.getPoints());
    }
    @Test
    void getPointsOneArgConstructor(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        assertEquals(100, membershipPoints.getPoints());
    }

}