package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MembershipPointsTest {
    @Test
    void constructorObjectExists(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        assertNotNull(membershipPoints);
    }
    @Test
    void constructorNegativePointsSetToZero(){
        MembershipPoints membershipPoints = new MembershipPoints(-100);
        assertEquals(0, membershipPoints.getAllPoints());
    }
    @Test
    void constructorPointsDecimalRounded(){
        MembershipPoints membershipPoints = new MembershipPoints(100.6);
        assertEquals(101, membershipPoints.getAllPoints());
    }
    @Test
    void constructorNegativeDoubleSetToZero(){
        MembershipPoints membershipPoints = new MembershipPoints(-100.6);
        assertEquals(0, membershipPoints.getAllPoints());
    }
    @Test
    void getPointsOneArgConstructor(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        assertEquals(100, membershipPoints.getAllPoints());
    }
    @Test
    void addPointToExistingMembershipPoint(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        membershipPoints.addPoints(100);
        assertEquals(200, membershipPoints.getAllPoints());
    }
    @Test
    void addPointIsNegative(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        Assertions.assertThrows(IllegalArgumentException.class, () -> membershipPoints.addPoints(-100));
        assertEquals(100, membershipPoints.getAllPoints());
    }

    @Test
    void subtractPoints(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        membershipPoints.subtractPoints(100);
        assertEquals(0, membershipPoints.getAllPoints());
    }
    @Test
    void subtractPointsBeyondZero(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        Assertions.assertThrows(IllegalArgumentException.class, () -> membershipPoints.subtractPoints(200));
    }

    @Test
    void collecting100PointsReducesTotalPoints(){
        MembershipPoints membershipPoints = new MembershipPoints(400);
        membershipPoints.getCertainAmountOfPoints(100);
        assertEquals(300, membershipPoints.getAllPoints());
    }

    @Test
    void collecting200PointsWhenOnly100ExistsThrowsException(){
        MembershipPoints membershipPoints = new MembershipPoints(100);
        assertThrows(IllegalArgumentException.class, () -> membershipPoints.getCertainAmountOfPoints(200));
    }
}