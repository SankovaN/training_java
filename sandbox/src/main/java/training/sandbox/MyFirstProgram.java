package training.sandbox;

public class MyFirstProgram {

	public static void main(String[] args)
	{
		Point p1 = new Point();
		Point p2 = new Point();

		p1.x = 1;
		p1.y = 1;
		p2.x = 7;
		p2.y = 7;

		System.out.println("Расстояние между двумя точками = " + distance(p1, p2));
	}

	public static double distance(Point p1, Point p2) {
		double l = Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
		return l;
	}

}