package surface.calculation;


public class CosinusHTable extends LookUpTable {

    public CosinusHTable(
            final int i_steps,
            final float i_minimalValue,
            final float i_maximalValue
    ) {
        super(i_steps, i_minimalValue, i_maximalValue);
    }

    protected float calculateValue(final float i_angle, final float[] i_constants) {
        return (float) (Math.exp(i_angle) + Math.exp(-i_angle)) / 2.0f;
    }

}
