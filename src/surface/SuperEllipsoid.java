package surface;

import processing.core.PGraphics;
import surface.calculation.CosinusTable;
import surface.calculation.LookUpTable;
import surface.calculation.SinusTable;

/**
 * @author andreaskoeberle
 */
public class SuperEllipsoid extends Surface {

    private LookUpTable sinThetaTable;

    private LookUpTable cosThetaTable;

    private LookUpTable sinPhiTable;

    private LookUpTable cosPhiTable;

    private final static int N1 = 0;
    private final static int N2 = 1;

    /**
     * @param i_g          A PGraphics object where the surface should be drawn in.
     *                     Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps   The horizontal resolution of the surface.
     * @param i_thetaSteps The vertical resolution of the surface.
     * @param i_colors     An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                     You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public SuperEllipsoid(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float i_N1,
            final float i_N2,
            final int[][] i_colors) {

        super(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                -HALF_PI, HALF_PI, -PI, PI,
                new float[]{i_N1, i_N2},
                i_colors);
    }

    public SuperEllipsoid(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float i_N1,
            final float i_N2) {

        super(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                -HALF_PI, HALF_PI, -PI, PI,
                new float[]{i_N1, i_N2},
                null);
    }

    public SuperEllipsoid(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors) {

        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                0.2f,
                1f,
                i_colors);
    }

    public SuperEllipsoid(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps) {

        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                0.2f,
                1f
        );
    }

    protected void initValues() {
        sinThetaTable = new SinusTable(thetaSteps, minTheta, maxTheta);
        cosThetaTable = new CosinusTable(thetaSteps, minTheta, maxTheta);
        sinPhiTable = new SinusTable(phiSteps, minPhi, maxPhi);
        cosPhiTable = new CosinusTable(phiSteps, minPhi, maxPhi);
    }

    protected float calculateX(final int i_phiStep, final int i_thetaStep) {
        return (float) (sign(cosPhiTable.getValue(i_phiStep)) * Math.pow(Math.abs(cosPhiTable.getValue(i_phiStep)), parameter[N1]) * sign(cosThetaTable.getValue(i_thetaStep)) * Math.pow(Math.abs(cosThetaTable.getValue(i_thetaStep)), parameter[N2]));
    }


    protected float calculateY(final int i_phiStep, final int i_thetaStep) {
        return (float) (sign(cosPhiTable.getValue(i_phiStep)) * Math.pow(Math.abs(cosPhiTable.getValue(i_phiStep)), parameter[N1]) * sign(sinThetaTable.getValue(i_thetaStep)) * Math.pow(Math.abs(sinThetaTable.getValue(i_thetaStep)), parameter[N2]));
    }

    protected float calculateZ(final int i_phiStep, final int i_thetaStep) {
        return (float) (sign(sinPhiTable.getValue(i_phiStep)) * Math.pow(Math.abs(sinPhiTable.getValue(i_phiStep)), parameter[N1]));
    }

}