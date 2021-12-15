import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class NBody {
    public static double readRadius(String filename) {
        File file = new File(filename);
        Scanner scanner;

        try {
            if (file.exists()) {
                scanner = new Scanner(file, StandardCharsets.UTF_8);

                scanner.nextInt();
                double res = scanner.nextDouble();
                scanner.close();

                return res;
            }
        }
        catch (IOException ioe) {
            System.err.println("Could not open " + filename);
        }
        return 0.0;
    }

    public static Planet[] readPlanets(String filename) {
        File file = new File(filename);
        Scanner scanner;

        try {
            if (file.exists()) {
                scanner = new Scanner(file, StandardCharsets.UTF_8);

                int counts = scanner.nextInt();
                scanner.nextDouble();

                Planet[] res = new Planet[counts];

                for (int i = 0; i < counts; i++) {
                    double xPos = scanner.nextDouble();
                    double yPos = scanner.nextDouble();
                    double xV = scanner.nextDouble();
                    double yV = scanner.nextDouble();
                    double mass = scanner.nextDouble();
                    String gif = scanner.next();

                    res[i] = new Planet(xPos, yPos, xV, yV, mass, gif);
                }
                scanner.close();

                return res;
            }
        }
        catch (IOException ioe) {
            System.err.println("Could not open " + filename);
        }

        return null;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];

        Planet[] planets = readPlanets(filename);

        double radius = readRadius(filename);

        double time = 0;

        double scale = 0;
        assert planets != null;
        for (Planet item : planets) {
            scale = Math.max(Math.abs(item.xxPos), Math.abs(item.yyPos));
        }

        StdDraw.setScale(-scale, scale);
        while (time < T) {
            StdDraw.enableDoubleBuffering();
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet item : planets) {
                item.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }
    }
}
