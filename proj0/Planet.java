public class Planet {

    private static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;

    public double xxVel;
    public double yyVel;

    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    // helper
    private double distance(Planet other) {
        return (this.xxPos - other.xxPos) * (this.xxPos - other.xxPos) +
                (this.yyPos - other.yyPos) * (this.yyPos - other.yyPos);
    }
    
    public double calcDistance(Planet other) {
        double d = distance(other);
        return Math.sqrt(d);
    }

    public double calcForceExertedBy(Planet other) {
        return G * this.mass * other.mass / distance(other);
    }

    public double calcForceExertedByX(Planet other) {
        double f = calcForceExertedBy(other);
        double dx = other.xxPos - this.xxPos;
        double r = calcDistance(other);

        return f * dx / r;
    }

    public double calcForceExertedByY(Planet other) {
        double f = calcForceExertedBy(other);
        double dy = other.yyPos - this.yyPos;
        double r = calcDistance(other);

        return f * dy / r;
    }

    // helper
    private double calcOneNetForceExertedByX(Planet other) {
        if (equals(other)) {
            return 0;
        }

        return G * (this.mass * other.mass) / distance(other) * (other.xxPos - this.xxPos) / calcDistance(other);
    }

    public double calcNetForceExertedByX(Planet[] others) {
        double res = 0;
        for (Planet item : others) {
            res += calcOneNetForceExertedByX(item);
        }
        return res;
    }

    // helper
    private double calcOneNetForceExertedByY(Planet other) {
        if (equals(other)) {
            return 0;
        }

        return G * (this.mass * other.mass) / distance(other) * (other.yyPos - this.yyPos) / calcDistance(other);
    }

    public double calcNetForceExertedByY(Planet[] others) {
        double res = 0;
        for (Planet item : others) {
            res += calcOneNetForceExertedByY(item);
        }
        return res;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        xxVel = this.xxVel + aX * dt;
        yyVel = this.yyVel + aY * dt;

        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
