package aula07;

import java.util.ArrayList;
import java.util.List;
import Helper.*;

public class ex01 {
	public static void main(String[] args) {
		List<Forma> circulos = new ArrayList<Forma>();
		List<Forma> retangulos = new ArrayList<Forma>();
		List<Forma> triangulos = new ArrayList<Forma>();
		int op;

		do {
			printMenu();
			op = Utils.nextInt("");
			switch (op) {
				case 1:
					createCircle(circulos);
					break;
				case 2:
					createTriangle(triangulos);
					break;
				case 3:
					createRectangle(retangulos);
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

	public static void createCircle(List<Forma> lst) {
		double radius = Utils.nextDouble("Radius of circle: ");
		Utils.nextLine("");
		String cor = Utils.nextLine("Color: ");
		lst.add(new Circulo(cor, radius));
	}

	public static void createRectangle(List<Forma> lst) {
		double l = Utils.nextDouble("Length of rectangle: ");
		double w = Utils.nextDouble("Width of rectangle: ");
		Utils.nextLine("");
		String cor = Utils.nextLine("Color: ");
		lst.add(new Retangulo(cor, l, w));
	}

	public static void createTriangle(List<Forma> lst) {
		double side1 = Utils.nextDouble("Size of side1: ");
		double side2 = Utils.nextDouble("Size of side2: ");
		double side3 = Utils.nextDouble("Size of side3: ");
		Utils.nextLine("");
		String cor = Utils.nextLine("Color: ");
		lst.add(new Triangulo(cor, side1, side2, side3));
	}

	public static void listAllFigures(List<Forma> circulos, List<Forma> retangulos,
			List<Forma> triangulos) {
		for (Forma c : circulos) {
			System.out.println(c.toString());
		}
		for (Forma r : retangulos) {
			System.out.println(r.toString());
		}
		for (Forma t : triangulos) {
			System.out.println(t.toString());
		}
	}

	public static void compareAllFigures(List<Forma> circulos, List<Forma> retangulos,
			List<Forma> triangulos) {
		if (circulos.size() < 2 && retangulos.size() < 2 && triangulos.size() < 2) {
			System.out.println("Not enough pairs in order to compare all figures.");
			return;
		}
		for (Forma c : circulos) {
			for (Forma c2 : circulos) {
				if (c == c2) {
					continue;
				}
				System.out.println(c.toString() + " equals:" + c.equals(c2) + " " + c2.toString());
			}
		}

		for (Forma c : retangulos) {
			for (Forma c2 : retangulos) {
				if (c == c2) {
					continue;
				}
				System.out.println(c.toString() + " equals:" + c.equals(c2) + " " + c2.toString());
			}
		}

		for (Forma c : triangulos) {
			for (Forma c2 : triangulos) {
				if (c == c2) {
					continue;
				}
				System.out.println(c.toString() + " equals:" + c.equals(c2) + " " + c2.toString());
			}
		}
	}
}
