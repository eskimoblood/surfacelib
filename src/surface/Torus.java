package surface;

import processing.core.PApplet;
import processing.core.PGraphics;
import surface.calculation.CosinusTable;
import surface.calculation.LookUpTable;
import surface.calculation.SinusTable;

/**
 * @author andreaskoeberleoberle
 */

public class Torus extends Surface {
    private LookUpTable PhiCosTable;
    private LookUpTable ThetaCosTable;
    private LookUpTable PhiSinTable;
    private LookUpTable ThetaSinTable;

    private final static int INNERRADIUS = 0;
    private final static int OUTERRADIUS = 1;

    /**
     * @param i_g          A PGraphics object where the surface should be drawn in.
     *                     Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps   The horizontal resolution of the surface.
     * @param i_thetaSteps The vertical resolution of the surface.
     */
    public Torus(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps) {

        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                3f,
                1f
        );
    }

    /**
     * @param i_colors An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                 You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public Torus(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors) {

        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                3f,
                1f,
                i_colors
        );
    }

    /**
     * @param i_innerRadius The length between the middlepoint and the middle of the torus.
     *                      Note this value should be greater than the outerRadius to have a hole in the torrus.
     *                      The default value is 3.
     * @param i_outerRadius The length between middle and the end of the torus.
     *                      Note this value should be smaller than the innerRadius to have a hole in the torrus.
     *                      The default value is 1.
     */
    public Torus(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float i_innerRadius,
            final float i_outerRadius) {
        super(
                i_g, i_phiSteps,
                i_thetaSteps,
                0, TWO_PI, 0, TWO_PI,
                new float[]{i_innerRadius, i_outerRadius},
                null
        );
    }

    public Torus(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float i_innerRadius,
            final float i_outerRadius,
            final int[][] i_colors) {
        super(
                i_g, i_phiSteps,
                i_thetaSteps,
                0, TWO_PI, 0, TWO_PI,
                new float[]{i_innerRadius, i_outerRadius},
                i_colors
        );
    }

    protected void initValues() {
        PhiCosTable = new CosinusTable(phiSteps, minPhi, maxPhi);
        PhiSinTable = new SinusTable(phiSteps, minPhi, maxPhi);

        ThetaCosTable = new CosinusTable(thetaSteps, minTheta, maxTheta);
        ThetaSinTable = new SinusTable(thetaSteps, minTheta, maxTheta);
    }


    protected float calculateX(final int i_phiStep, final int i_thetaStep) {
        return (parameter[INNERRADIUS] + parameter[OUTERRADIUS] * ThetaCosTable.getValue(i_thetaStep))
                * PhiCosTable.getValue(i_phiStep);
    }

    protected float calculateY(final int i_phiStep, final int i_thetaStep) {
        return (parameter[INNERRADIUS] + parameter[OUTERRADIUS] * ThetaCosTable.getValue(i_thetaStep))
                * PhiSinTable.getValue(i_phiStep);
    }

    protected float calculateZ(final int i_phiStep, final int i_thetaStep) {
        return parameter[OUTERRADIUS] * ThetaSinTable.getValue(i_thetaStep);
    }

    /**
     * @return the inner Radius of the torus
     */
    public float innerRadius() {
        return parameter[INNERRADIUS];
    }

    /**
     * @return the outerRadius of the Torus
     */
    public float outerRadius() {
        return parameter[OUTERRADIUS];
    }

    /**
     * Use this to set the innerRadius of the torus.
     *
     * @param i_innerRadius The length between the center and the middle of the torus.
     *                      Note this value should be greater than the outerRadius to have a hole in the torus.
     */
    public void setInnerRadius(final float i_innerRadius) {
        parameter[INNERRADIUS] = i_innerRadius;
        setSurface();
    }

    /**
     * Use this to set the outerRadius of the torus.
     *
     * @param i_outerRadius The length between center and the end of the torus.
     *                      Note this value should be smaller than the innerRadius to have a hole in the torus.
     */
    public void setOuterRadius(final float i_outerRadius) {
        parameter[OUTERRADIUS] = i_outerRadius;
        setSurface();
    }

    /**
     * Use this to set the inner and outer radius of the torus.
     *
     * @param i_innerRadius The length between the center and the middle of the torus.
     *                      Note this value should be greater than the outerRadius to have a hole in the torus.
     * @param i_outerRadius The length between middle and the end of the torus.
     *                      Note this value should be smaller than the innerRadius to have a hole in the torus.
     */
    public void setRadius(float i_innerRadius, float i_outerRadius) {
        parameter[INNERRADIUS] = i_innerRadius;
        parameter[OUTERRADIUS] = i_outerRadius;
        setSurface();
    }

} 
