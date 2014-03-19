package surface.calculation;


public class SinusTable extends LookUpTable {

    public SinusTable(
            final int i_steps,
            final float i_minimalValue,
            final float i_maximalValue
    ) {
        super(i_steps, i_minimalValue, i_maximalValue);
    }

    protected float calculateValue(final float i_angle, final float[] i_constants) {
        return (float) Math.sin(i_angle);
    }

}
