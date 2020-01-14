public enum TriangleType {
    IRREGULAR("This triangle is irregular: It has 3 different sides."),
    ISOSCELES("This triangle is isosceles: It has 2 equals sides."),
    EQUILATERAL("This triangle is Equilateral: All its sides have the same length."),
    INEXISTANT("The lengths specified do not describe a triangle.");

    TriangleType(String description){
        this.description = description;
    }
    String description;

    @Override
    public String toString() {
        return description;
    }
}
