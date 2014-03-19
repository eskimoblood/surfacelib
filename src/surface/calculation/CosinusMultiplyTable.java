package surface.calculation;


public class CosinusMultiplyTable extends LookUpTable {

    public CosinusMultiplyTable(
            final int i_steps,
            final float i_minimalValue,
            final float i_maximalValue,
            final float i_toMultiply
    ) {
        super(i_steps, i_minimalValue, i_maximalValue, new float[]{i_toMultiply});
    }

    protected float calculateValue(final float i_angle, final float[] i_constants) {
        return (float) Math.cos(i_angle * i_constants[0]);
    }

}
