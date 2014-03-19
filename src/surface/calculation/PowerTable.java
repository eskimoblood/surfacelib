package surface.calculation;


public class PowerTable extends LookUpTable {

    public PowerTable(
            final int i_steps,
            final float i_minimalValue,
            final float i_maximalValue,
            final float i_exponent
    ) {
        super(i_steps, i_minimalValue, i_maximalValue, new float[]{i_exponent});
    }

    protected float calculateValue(final float i_base, final float[] i_constants) {
        return (float) Math.pow(i_base, i_constants[0]);
    }

}
