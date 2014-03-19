package surface;

import processing.core.PGraphics;

import surface.calculation.CosinusTable;
import surface.calculation.LookUpTable;
import surface.calculation.SinusTable;

public class SphereHarmonics extends Surface {

    static public class RTable extends LookUpTable {

        public RTable(
                final int i_steps,
                final float i_minimalValue,
                final float i_maximalValue,
                final float[] i_preset
        ) {
            super(
                    i_steps,
                    i_minimalValue,
                    i_maximalValue,
                    i_preset
            );
        }

        protected float calculateValue(float i_angle, float[] i_constants) {
            return (float) (Math.pow(Math.sin(i_constants[0] * i_angle), i_constants[1]) + Math.pow(Math.cos(i_constants[2] * i_angle), i_constants[3]));
        }

    }

    private LookUpTable r1;
    private LookUpTable r2;

    private LookUpTable sinThetaTable;
    private LookUpTable cosThetaTable;

    private LookUpTable sinPhiTable;
    private LookUpTable cosPhiTable;

    /**
     * @param i_g          A PGraphics object where the surface should be drawn in.
     *                     Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps   The horizontal resolution of the surface.
     * @param i_thetaSteps The vertical resolution of the surface.
     * @param i_colors     An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                     You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     * @param i_preset,    An array with 8 floats s
     */
    public SphereHarmonics(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float[] i_preset,
            final int[][] i_colors
    ) {
        super(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                0, TWO_PI, 0, PI,
                i_preset,
                i_colors
        );
    }

    public SphereHarmonics(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float[] i_preset

    ) {
        super(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                0, TWO_PI, 0, PI,
                i_preset,
                null
        );
    }

    public SphereHarmonics(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps
    ) {
        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                new float[]{0, 0, 0, 0, 100, 100, 100, 100},
                null
        );
    }

    public SphereHarmonics(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors
    ) {
        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                new float[]{0, 0, 0, 0, 100, 100, 100, 100},
                i_colors
        );
    }

    protected void initValues() {


        setParameterR1(parameter[0], parameter[1], parameter[2], parameter[3]);
        setParameterR2(parameter[4], parameter[5], parameter[6], parameter[7]);

        sinThetaTable = new SinusTable(thetaSteps, minTheta, maxTheta);
        cosThetaTable = new CosinusTable(thetaSteps, minTheta, maxTheta);

        sinPhiTable = new SinusTable(phiSteps, minPhi, maxPhi);
        cosPhiTable = new CosinusTable(phiSteps, minPhi, maxPhi);
    }


    public void setParameterR1(float m0, float m1, float m2, float m3) {
        r1 = new RTable(phiSteps, minPhi, maxPhi, new float[]{m0, m1, m2, m3});

    }

    public void setParameterR2(float m0, float m1, float m2, float m3) {
        r2 = new RTable(thetaSteps, minTheta, maxTheta, new float[]{m0, m1, m2, m3});
    }

    protected float calculateX(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return
                (r1.getValue(i_phiStep) + r2.getValue(i_thetaStep))
                        * sinPhiTable.getValue(i_phiStep)
                        * cosThetaTable.getValue(i_thetaStep);

    }

    protected float calculateY(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return
                (r1.getValue(i_phiStep) + r2.getValue(i_thetaStep))
                        * cosPhiTable.getValue(i_phiStep);
    }

    protected float calculateZ(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return
                (r1.getValue(i_phiStep) + r2.getValue(i_thetaStep))
                        * sinPhiTable.getValue(i_phiStep)
                        * sinThetaTable.getValue(i_thetaStep);
    }

}
