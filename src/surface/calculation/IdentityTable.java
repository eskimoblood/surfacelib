package surface.calculation;


public class IdentityTable extends LookUpTable {

    public IdentityTable(
            final int i_steps,
            final float i_minimalValue,
            final float i_maximalValue
    ) {
        super(i_steps, i_minimalValue, i_maximalValue);
    }

    protected float calculateValue(final float i_angle, final float[] i_constants) {
        return i_angle;
    }

}
