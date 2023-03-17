package aula05;

import java.util.ArrayList;
import java.util.Scanner;

public class ex03 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Circulo> circulos = new ArrayList<>();
		ArrayList<Retangulo> retangulos = new ArrayList<>();
		ArrayList<Triangulo> triangulos = new ArrayList<>();
		int op;
		do {
			printMenu();
			op = input.nextInt();
			switch (op) {
				case 1:
					createCircle(circulos, input);
					break;
				case 2:
					createTriangle(triangulos, input);
					break;
				case 3:
					createRectangle(retangulos, input);
					break;
				case 4:
					listAllFigures(circulos, retangulos, triangulos);
					break;
				case 5:
					compareAllFigures(circulos, retangulos, triangulos);
					break;
			}
		} while (op != 0);
	}

	public static void printMenu() {
		System.out.println("\n\nOperations");
		System.out.println("1 - create new circle");
		System.out.println("2 - create new triangle");
		System.out.println("3 - create new rectangle");
		System.out.println("4 - list all figures");
		System.out.println("5 - compare all figures");
		System.out.println("0 - exit");
	}

	public static void createCircle(ArrayList<Circulo> lst, Scanner input) {
		System.out.print("Radius of circle: ");
		double radius = input.nextDouble();
		lst.add(new Circulo(radius));
	}

	public static void createRectangle(ArrayList<Retangulo> lst, Scanner input) {
		System.out.print("Length of rectangle: ");
		double l = input.nextDouble();
		System.out.print("Width of rectangle: ");
		double w = input.nextDouble();
		lst.add(new Retangulo(l, w));
	}

	public static void createTriangle(ArrayList<Triangulo> lst, Scanner input) {
		System.out.print("Size of side1: ");
		double side1 = input.nextDouble();
		System.out.print("Size of side2: ");
		double side2 = input.nextDouble();
		System.out.print("Size of side3: ");
		double side3 = input.nextDouble();
		lst.add(new Triangulo(side1, side2, side3));
	}

	public static void listAllFigures(ArrayList<Circulo> circulos, ArrayList<Retangulo> retangulos,
			ArrayList<Triangulo> triangulos) {
		for (Circulo c : circulos) {
			System.out.println(c.toString());
		}
		for (Retangulo r : retangulos) {
			System.out.println(r.toString());
		}
		for (Triangulo t : triangulos) {
			System.out.println(t.toString());
		}
	}

	public static void compareAllFigures(ArrayList<Circulo> circulos, ArrayList<Retangulo> retangulos,
			ArrayList<Triangulo> triangulos) {
		if (circulos.size() < 2 || retangulos.size() < 2 || triangulos.size() < 2) {
			System.out.println("Not enough pairs in order to compare all figures.");
			return;
		}
		for (Circulo c : circulos) {
			for (Circulo c2 : circulos) {
				if (c == c2) {
					continue;
				}
				System.out.println(c.toString() + " equals:" + c.equals(c2) + " " + c2.toString());
			}
		}

		for (Retangulo c : retangulos) {
			for (Retangulo c2 : retangulos) {
				if (c == c2) {
					continue;
				}
				System.out.println(c.toString() + " equals:" + c.equals(c2) + " " + c2.toString());
			}
		}

		for (Triangulo c : triangulos) {
			for (Triangulo c2 : triangulos) {
				if (c == c2) {
					continue;
				}
				System.out.println(c.toString() + " equals:" + c.equals(c2) + " " + c2.toString());
			}
		}

	}
}
