package surface;

import processing.core.PGraphics;
import surface.calculation.CosinusMultiplyTable;
import surface.calculation.LookUpTable;
import surface.calculation.SinusMultiplyTable;

/**
 * @author andreaskoeberle
 */

public class PQTorus extends Surface {
    private LookUpTable PhiPCosMultiTable;
    private LookUpTable PhiPSinMultiTable;
    private LookUpTable ThetaPCosMultiTable;
    private LookUpTable ThetaPSinMultiTable;
    private LookUpTable ThetaQSinMultiTable;


    private final static int P = 0;
    private final static int Q = 1;
    private final static int RADIUS = 2;

    /**
     * @param i_g          A PGraphics object where the surface should be drawn in.
     *                     Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps   The horizontal resolution of the surface.
     * @param i_thetaSteps The vertical resolution of the surface.
     */
    public PQTorus(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps) {

        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                0.1f,
                0.8f,
                0.3f
        );
    }

    /**
     * @param i_colors An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                 You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public PQTorus(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors) {

        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                3f,
                1f,
                1f,
                i_colors
        );
    }

    /**
     * @param i_innerRadius The length between the middlepoint and the middle of the torus.
     *                      Note this value should be greater than the outerRadius to have a hole in the torrus.
     *                      The default value is 3.
     */
    public PQTorus(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float i_P,
            final float i_Q,
            final float i_innerRadius) {
        super(
                i_g, i_phiSteps,
                i_thetaSteps,
                0, TWO_PI, 0, TWO_PI,
                new float[]{i_P, i_Q, i_innerRadius},
                null
        );
    }

    public PQTorus(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float i_P,
            final float i_Q,
            final float i_radius,
            final int[][] i_colors) {
        super(
                i_g, i_phiSteps,
                i_thetaSteps,
                0, TWO_PI, 0, TWO_PI,
                new float[]{i_P, i_Q, i_radius},
                i_colors
        );
    }

    protected void initValues() {
        PhiPCosMultiTable = new CosinusMultiplyTable(phiSteps, minPhi, maxPhi, parameter[P]);
        PhiPSinMultiTable = new SinusMultiplyTable(phiSteps, minPhi, maxPhi, parameter[P]);

        ThetaPCosMultiTable = new CosinusMultiplyTable(thetaSteps, minTheta, maxTheta, parameter[P]);
        ThetaPSinMultiTable = new SinusMultiplyTable(thetaSteps, minTheta, maxTheta, parameter[P]);
        ThetaQSinMultiTable = new SinusMultiplyTable(thetaSteps, minTheta, maxTheta, parameter[Q]);
    }


    protected float calculateX(final int i_phiStep, final int i_thetaStep) {
        float rr = (parameter[RADIUS] + ThetaQSinMultiTable.getValue(i_thetaStep)) * 0.5f;
        return rr * ThetaPCosMultiTable.getValue(i_thetaStep) * PhiPCosMultiTable.getValue(i_phiStep);

    }

    protected float calculateY(final int i_phiStep, final int i_thetaStep) {
        float rr = (parameter[RADIUS] + ThetaQSinMultiTable.getValue(i_thetaStep)) * 0.5f;
        return rr * ThetaPCosMultiTable.getValue(i_thetaStep) * PhiPSinMultiTable.getValue(i_phiStep);
    }

    protected float calculateZ(final int i_phiStep, final int i_thetaStep) {
        float rr = (parameter[RADIUS] + ThetaQSinMultiTable.getValue(i_thetaStep)) * 0.5f;
        return rr * ThetaPSinMultiTable.getValue(i_thetaStep);
    }

    /**
     * @return the inner Radius of the torus
     */
    public float q() {
        return parameter[Q];
    }

    /**
     * @return the outerRadius of the Torus
     */
    public float p() {
        return parameter[P];
    }

    /**
     * Use this to set the innerRadius of the torus.

     */
    public void setQ(final float i_q) {
        parameter[Q] = i_q;
        initValues();
        setSurface();
    }

    /**
     * Use this to set the outerRadius of the torus.
     *
     */
    public void setP(final float i_p) {
        parameter[P] = i_p;
        initValues();
        setSurface();
    }

    /**
     * Use this to set the inner and outer radius of the torus.
     *
     * @param i_innerRadius The length between the middlepoint and the middle of the torus.
     *                      Note this value should be greater than the outerRadius to have a hole in the torrus.
     */
    public void setRadius(final float i_innerRadius) {
        parameter[RADIUS] = i_innerRadius;
        initValues();
        setSurface();
    }

    public void setPQ(final float i_p, final float i_q) {
        parameter[P] = i_p;
        parameter[Q] = i_q;
        initValues();
        setSurface();
    }

} 
