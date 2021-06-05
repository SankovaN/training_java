package training.sandbox;

public class MyFirstProgram {

	public static void main(String[] args)
	{
		Point p1 = new Point(1, 1);
		Point p2 = new Point(7, 7);

		System.out.println("Расстояние между двумя точками = " + p1.distance(p2));
	}

}