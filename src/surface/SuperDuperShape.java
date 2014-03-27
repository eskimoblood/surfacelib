package surface;

import processing.core.PApplet;
import processing.core.PGraphics;
import surface.calculation.CosinusTable;
import surface.calculation.LookUpTable;
import surface.calculation.SinusTable;

/**
 * SuperSuperShapes are generalized version of the the 3d SuperShape formular by Paul Bourkes. The implemtation based on the
 * <a href="http://www.k2g2.org/blog:bit.craft:superdupershape_explorer">SuperDuperShape Explorer</a>. To understand the meaning of
 * all the variables take look at the example.
 *
 * @author andreaskoeberle
 */
public class SuperDuperShape extends Surface {

    static public class SuperShapePreset {
        public float n1;
        public float n2;
        public float n3;
        public float m;

        public SuperShapePreset(
                final float i_n1,
                final float i_n2,
                final float i_n3,
                final float i_m
        ) {
            n1 = i_n1;
            n2 = i_n2;
            n3 = i_n3;
            m = i_m;
        }
    }

    static public class RTable extends LookUpTable {

        public RTable(
                final int i_steps,
                final float i_minimalValue,
                final float i_maximalValue,
                final SuperShapePreset i_preset
        ) {
            super(
                    i_steps,
                    i_minimalValue,
                    i_maximalValue,
                    new float[]{i_preset.n1, i_preset.n2, i_preset.n3, i_preset.m}
            );
        }

        protected float calculateValue(final float i_angle, final float[] i_constants) {
            final float s1 = (float) Math.pow(Math.abs(Math.cos(i_constants[3] * i_angle / 4)), i_constants[1]);
            final float s2 = (float) Math.pow(Math.abs(Math.sin(i_constants[3] * i_angle / 4)), i_constants[2]);
            return (float) Math.pow(s1 + s2, -1 / i_constants[0]);
        }

    }

    static public class LerpTable extends LookUpTable {

        public LerpTable(
                final int i_steps,
                final float i_minimalValue,
                final float i_maximalValue
        ) {
            super(
                    i_steps,
                    i_minimalValue,
                    i_maximalValue
            );
        }

        protected float calculateValue(final float i_angle, final float[] i_constants) {
            return i_angle;
        }

    }

    static public class PowerMultiplyTable extends LookUpTable {

        public PowerMultiplyTable(
                final int i_steps,
                final float i_minimalValue,
                final float i_maximalValue,
                final float[] i_constants
        ) {
            super(
                    i_steps,
                    i_minimalValue,
                    i_maximalValue,
                    i_constants
            );
        }

        protected float calculateValue(final float i_angle, final float[] i_constants) {
            return (float) Math.pow(i_angle * i_constants[0], i_constants[1]);
        }

    }

    static public class MultiplyTable extends LookUpTable {

        public MultiplyTable(
                final int i_steps,
                final float i_minimalValue,
                final float i_maximalValue,
                final float[] i_constants
        ) {
            super(
                    i_steps,
                    i_minimalValue,
                    i_maximalValue,
                    i_constants
            );
        }

        protected float calculateValue(float i_angle, final float[] i_constants) {
            for (int i = 0; i < i_constants.length; i++) {
                i_angle *= i_constants[i];
            }
            return i_angle;
        }
    }

    private SuperShapePreset preset1;
    private SuperShapePreset preset2;

    private LookUpTable r1;
    private LookUpTable r2;

    private LookUpTable sinThetaTable;
    private LookUpTable cosThetaTable;

    private LerpTable phiTable;
    private LerpTable thetaTable;
    private PowerMultiplyTable d1;
    private MultiplyTable t2;
    private PowerMultiplyTable d2;
    private float t2c;

    private final static int N_11 = 0;
    private final static int N_12 = 1;
    private final static int N_13 = 2;
    private final static int M_1 = 3;

    private final static int N_21 = 4;
    private final static int N_22 = 5;
    private final static int N_23 = 6;
    private final static int M_2 = 7;

    private final static int C_1 = 8;
    private final static int C_2 = 9;
    private final static int C_3 = 10;

    private final static int T_1 = 11;
    private final static int T_2 = 12;
    private final static int D_1 = 13;
    private final static int D_2 = 14;

    /**
     * @param i_g          A PGraphics object where the surface should be drawn in.
     *                     Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps   The horizontal resolution of the surface.
     * @param i_thetaSteps The vertical resolution of the surface.
     * @param i_colors     An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                     You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public SuperDuperShape(PApplet i_g, int i_phiSteps, int i_thetaSteps,
                           float n1_1, float n1_2, float n1_3, float m1_1,
                           float n2_1, float n2_2, float n2_3, float m2_1,
                           float c1, float c2, float c3,
                           float t1, float t2, float d1, float d2,
                           int[][] i_colors) {
        super(
                i_g, i_phiSteps, i_thetaSteps,
                -PI * c1, PI * c1, -HALF_PI * c2, HALF_PI * c2,
                new float[]{
                        n1_1, n1_2, n1_3, m1_1,
                        n2_1, n2_2, n2_3, m2_1,
                        c1, c2, c3,
                        t1, t2, d1, d2
                },
                i_colors
        );
    }

    /**
     * @param n1_1
     * @param n1_2
     * @param n1_3
     * @param m1_1
     * @param n2_1
     * @param n2_2
     * @param n2_3
     * @param m2_1
     * @param c1
     * @param c2
     * @param c3
     * @param t1
     * @param t2
     * @param d1
     * @param d2
     */
    public SuperDuperShape(PApplet i_g, int i_phiSteps, int i_thetaSteps,
                           float n1_1, float n1_2, float n1_3, float m1_1,
                           float n2_1, float n2_2, float n2_3, float m2_1,
                           float c1, float c2, float c3,
                           float t1, float t2, float d1, float d2) {
        this(
                i_g, i_phiSteps, i_thetaSteps, n1_1, n1_2, n1_3, m1_1,
                n2_1, n2_2, n2_3, m2_1,
                c1, c2, c3,
                t1, t2, d1, d2,
                null
        );
    }


    public SuperDuperShape(PApplet i_g, int i_phiSteps, int i_thetaSteps,
                           SuperShapePreset r1, SuperShapePreset r2,
                           float c1, float c2, float c3,
                           float t1, float t2, float d1, float d2) {
        this(
                i_g, i_phiSteps, i_thetaSteps,
                r1.n1, r1.n2, r1.n3, r1.m,
                r2.n1, r2.n2, r2.n3, r2.m,
                c1, c2, c3,
                t1, t2, d1, d2,
                null);
    }


    protected void initValues() {
        if (preset1 == null) {
            preset1 = new SuperShapePreset(parameter[N_11], parameter[N_12], parameter[N_13], parameter[M_1]);
        }
        if (preset2 == null) {
            preset2 = new SuperShapePreset(parameter[N_21], parameter[N_22], parameter[N_23], parameter[M_2]);
        }

        setD1();
        setD2();
        setT2();
        setT2c();

        r1 = new RTable(thetaSteps, minTheta, maxTheta, preset1);
        r2 = new RTable(phiSteps, minPhi, maxPhi, preset2);


        sinThetaTable = new SinusTable(thetaSteps, minTheta, maxTheta);
        cosThetaTable = new CosinusTable(thetaSteps, minTheta, maxTheta);
        thetaTable = new LerpTable(thetaSteps, minTheta, maxTheta);

        phiTable = new LerpTable(phiSteps, minPhi, maxPhi);

    }


    protected float calculateX(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        float v2 = phiTable.getValue(i_phiStep) + parameter[C_3] * thetaTable.getValue(i_thetaStep);

        return (float) (r1.getValue(i_thetaStep) * (parameter[T_1] + d1.getValue(i_thetaStep) * r2.getValue(i_phiStep) * Math.cos(v2)) * sinThetaTable.getValue(i_thetaStep));
    }

    protected float calculateY(
            final int i_phiStep,
            final int i_thetaStep
    ) {

        float v2 = phiTable.getValue(i_phiStep) + parameter[C_3] * thetaTable.getValue(i_thetaStep);

        return (float) (r1.getValue(i_thetaStep) * (parameter[T_1] + d1.getValue(i_thetaStep) * r2.getValue(i_phiStep) * Math.cos(v2)) * cosThetaTable.getValue(i_thetaStep));
    }

    protected float calculateZ(
            final int i_phiStep,
            final int i_thetaStep
    ) {


        float v2 = phiTable.getValue(i_phiStep) + parameter[C_3] * thetaTable.getValue(i_thetaStep);

        return (float) (d2.getValue(i_thetaStep) * (r2.getValue(i_phiStep) * Math.sin(v2) - t2.getValue(i_thetaStep)) + t2c);
    }

    /**
     * @return Returns the preset1.
     */
    public SuperShapePreset parameterR1() {
        return preset1;
    }


    /**
     * @param i_preset The preset1 to set.
     */
    public void setParameterR1(final SuperShapePreset i_preset) {
        preset1 = i_preset;
        r1 = new RTable(thetaSteps, minTheta, maxTheta, preset1);
        setSurface();
    }

    public void setParameterR1(
            final float i_n1,
            final float i_n2,
            final float i_n3,
            final float i_m
    ) {
        setParameterR1(new SuperShapePreset(i_n1, i_n2, i_n3, i_m));
    }


    /**
     * @return Returns the preset2.
     */
    public SuperShapePreset parameterR2() {
        return preset2;
    }


    /**
     * @param i_preset The preset2 to set.
     */
    public void setParameterR2(final SuperShapePreset i_preset) {
        preset2 = i_preset;
        r2 = new RTable(phiSteps, minPhi, maxPhi, preset2);
        setSurface();
    }

    public void setParameterR2(
            final float i_n1,
            final float i_n2,
            final float i_n3,
            final float i_m
    ) {
        setParameterR2(new SuperShapePreset(i_n1, i_n2, i_n3, i_m));
    }

    public void setParameter(
            final SuperShapePreset i_preset1,
            final SuperShapePreset i_preset2
    ) {
        preset1 = i_preset1;
        preset2 = i_preset2;
        r1 = new RTable(thetaSteps, minTheta, maxTheta, preset1);
        r2 = new RTable(phiSteps, minPhi, maxPhi, preset2);
        setSurface();
    }

    public void setParameter(
            final float i_n11,
            final float i_n21,
            final float i_n31,
            final float i_m1,
            final float i_n12,
            final float i_n22,
            final float i_n32,
            final float i_m2
    ) {
        setParameter(
                new SuperShapePreset(i_n11, i_n21, i_n31, i_m1),
                new SuperShapePreset(i_n12, i_n22, i_n32, i_m2)
        );
    }

    public void setParameterT1(float t1) {
        parameter[T_1] = t1;
        setSurface();
    }

    public void setParameterT2(float t2) {
        parameter[T_2] = t2;
        setT2();
        setT2c();
        setSurface();
    }

    public void setParameterD1(float d1) {
        parameter[D_1] = d1;
        setD1();
        setSurface();
    }

    public void setParameterD2(float d2) {
        parameter[D_2] = d2;
        setD2();
        setT2c();
        setSurface();
    }

    public void setParameterC1(float c1) {
        minTheta = -PI * c1;
        maxTheta = PI * c1;
        parameter[C_1] = c1;
        initValues();
        setSurface();
    }

    public void setParameterC2(float c2) {
        minPhi = -HALF_PI * c2;
        maxPhi = HALF_PI * c2;
        parameter[C_2] = c2;
        initValues();
        setSurface();
    }

    public void setParameterC3(float c3) {
        parameter[C_3] = c3;
        setSurface();
    }

    private void setD1() {
        d1 = new PowerMultiplyTable(thetaSteps, minTheta, maxTheta, new float[]{parameter[C_1], parameter[D_1]});
    }

    private void setD2() {
        d2 = new PowerMultiplyTable(thetaSteps, minTheta, maxTheta, new float[]{parameter[C_2], parameter[D_2]});
    }

    private void setT2() {
        t2 = new MultiplyTable(thetaSteps, minTheta, maxTheta, new float[]{parameter[T_2] * parameter[C_2]});
    }

    private void setT2c() {
        t2c = (float) (Math.pow(parameter[C_2], parameter[D_2]) * parameter[T_2] * parameter[C_2] / 2);
    }
}
