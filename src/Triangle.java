public class Triangle {

    private double[] lengths;


    private boolean isEquilateral;
    private boolean isIsosceles;

    public Triangle(double[] lengths) throws Exception{
        this.lengths = lengths;
        if(lengths.length != 3) throw new Exception("This is not a triangle");
        this.isEquilateral = lengths[0] == lengths[1] && lengths[0] == lengths[2];
        this.isIsosceles = lengths[0] == lengths[1] || lengths[0] == lengths[2] || lengths[1] == lengths[2];
    }

    public TriangleType getType() {
        if(isEquilateral) return TriangleType.EQUILATERAL;
        else if(isIsosceles){
            if(isoscelesExists()) return TriangleType.ISOSCELES;
            else return TriangleType.INEXISTANT;
        }
        else{
            if(irregularExists()) return TriangleType.IRREGULAR;
            else return TriangleType.INEXISTANT;
        }
    }

    private boolean isoscelesExists(){
        if(lengths[0] == lengths[1]) return lengths[2] < lengths[0] + lengths[1];
        else if(lengths[0] == lengths[2]) return lengths[1] < lengths[0] + lengths[2];
        else return lengths[0] < lengths[1] + lengths[2];
    }

    private boolean irregularExists(){
        double h = getMaxFromDoubleArray(lengths);
        double sum = 0;
        for(double d : lengths) if(d != h) sum += d;
        return sum > h;
    }

    private static double getMaxFromDoubleArray(double... doubles){
        double max = Double.MIN_VALUE;
        for(double d : doubles) if(d > max) max = d;
        return max;
    }
}
