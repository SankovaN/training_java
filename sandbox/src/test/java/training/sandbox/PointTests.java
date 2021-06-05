package training.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance1() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);

        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void testDistance2() {
        Point p1 = new Point(3,-4);
        Point p2 = new Point(-6,5);

        Assert.assertEquals(p1.distance(p2), 12.727922061357855);
    }

    @Test
    public void testDistance3() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,5);

        Assert.assertEquals(p1.distance(p2), 5.0);
    }
}
