package surface.calculation;


public abstract class LookUpTable {

    private final int steps;
    private final float minimalValue;
    private final float maximalValue;
    private final float[] constants;
    private final float[] values;

    public LookUpTable(
            final int i_steps,
            final float i_minimalValue,
            final float i_maximalValue,
            final float[] i_constants
    ) {
        steps = i_steps;
        minimalValue = i_minimalValue;
        maximalValue = i_maximalValue;
        constants = i_constants;
        values = new float[steps];

        calculateTable();
    }

    public LookUpTable(
            final int i_steps,
            final float i_minimalValue,
            final float i_maximalValue
    ) {
        this(i_steps, i_minimalValue, i_maximalValue, null);
    }

    private void calculateTable() {
        for (int i = 0; i < steps; i++) {
            final float value = minimalValue + (maximalValue - minimalValue) / (steps - 1) * i;
            values[i] = calculateValue(value, constants);
        }
    }

    protected abstract float calculateValue(final float i_angle, final float[] i_constants);

    public float getValue(final int i_step) {
        return values[i_step];
    }
}
