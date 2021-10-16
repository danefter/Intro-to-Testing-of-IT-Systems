package inte.project;

import org.junit.jupiter.api.Assertions;
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
    @Test
    void addPointToExistingMembershipPoint(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        membershipPoints.addPoints(100);
        assertEquals(200, membershipPoints.getPoints());
    }
    @Test
    void addPointIsNegative(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            membershipPoints.addPoints(-100);
        });
        assertEquals(100, membershipPoints.getPoints());
    }

    @Test
    void subtractPoints(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        membershipPoints.subtractPoints(100);
        assertEquals(0, membershipPoints.getPoints());
    }
    @Test
    void subtractPointsBeyondZero(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            membershipPoints.subtractPoints(200);
        });
    }

}