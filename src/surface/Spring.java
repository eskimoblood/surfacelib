package surface;

import processing.core.PApplet;
import processing.core.PGraphics;
import surface.calculation.CosinusTable;
import surface.calculation.IdentityTable;
import surface.calculation.LookUpTable;
import surface.calculation.SinusTable;

/**
 * @author andreaskoeberle
 */
public class Spring extends Surface {

    private LookUpTable identityThetaTable;

    private LookUpTable sinThetaTable;

    private LookUpTable cosThetaTable;

    private LookUpTable sinPhiTable;

    private LookUpTable cosPhiTable;

    private final static int RADIUS_1 = 0;

    private final static int RADIUS_2 = 1;

    private final static int PERIOD_LENGTH = 2;

    /**
     * @param i_g            A PGraphics object where the surface should be drawn in.
     *                       Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps     The horizontal resolution of the surface.
     * @param i_thetaSteps   The vertical resolution of the surface.
     * @param i_radius1
     * @param i_radius2
     * @param i_periodLength
     * @param i_colors       An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                       You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public Spring(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float i_radius1,
            final float i_radius2,
            final float i_periodLength,
            final int[][] i_colors) {

        super(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                0, TWO_PI, 0, TWO_PI,
                new float[]{i_radius1, i_radius2, i_periodLength},
                i_colors
        );
    }

    public Spring(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float i_radius1,
            final float i_radius2,
            final float i_periodLength) {
        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                i_radius1,
                i_radius2,
                i_periodLength,
                null
        );
    }

    public Spring(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors) {

        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                0.35f,
                0.35f,
                4,
                i_colors
        );
    }

    public Spring(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps) {

        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                0.35f,
                0.35f,
                4
        );
    }

    protected void initValues() {

        identityThetaTable = new IdentityTable(thetaSteps, minTheta, maxTheta);

        sinThetaTable = new SinusTable(thetaSteps, minTheta, maxTheta);
        cosThetaTable = new CosinusTable(thetaSteps, minTheta, maxTheta);

        sinPhiTable = new SinusTable(phiSteps, minPhi, maxPhi);
        cosPhiTable = new CosinusTable(phiSteps, minPhi, maxPhi);

    }

    protected float calculateX(final int i_phiStep, final int i_thetaStep) {
        return (1 - parameter[RADIUS_1] * cosPhiTable.getValue(i_phiStep))
                * cosThetaTable.getValue(i_thetaStep);
    }

    protected float calculateY(final int i_phiStep, final int i_thetaStep) {
        return (1 - parameter[RADIUS_1] * cosPhiTable.getValue(i_phiStep))
                * sinThetaTable.getValue(i_thetaStep);
    }

    protected float calculateZ(final int i_phiStep, final int i_thetaStep) {
        return parameter[RADIUS_2]
                * (sinPhiTable.getValue(i_phiStep) + parameter[PERIOD_LENGTH]
                * identityThetaTable.getValue(i_thetaStep) / PI);
    }

    /**
     * @return Returns the periodLength.
     */
    public float periodLength() {
        return parameter[PERIOD_LENGTH];
    }

    /**
     * @param i_periodLength The periodLength to set.
     */
    public void setPeriodLength(final float i_periodLength) {
        parameter[PERIOD_LENGTH] = i_periodLength;
        setSurface();
    }

    /**
     * @return Returns the radius1.
     */
    public float radius1() {
        return parameter[RADIUS_1];
    }

    /**
     * @param i_radius1 The radius1 to set.
     */
    public void setRadius1(final float i_radius1) {
        parameter[RADIUS_1] = i_radius1;
        setSurface();
    }

    /**
     * @return Returns the radius2.
     */
    public float radius2() {
        return parameter[RADIUS_2];
    }

    /**
     * @param i_radius2 The radius2 to set.
     */
    public void setRadius2(final float i_radius2) {
        parameter[RADIUS_2] = i_radius2;
        setSurface();
    }

    public void setParameter(final float i_radius1, final float i_radius2,
                             final float i_periodLength) {
        parameter[RADIUS_1] = i_radius1;
        parameter[RADIUS_2] = i_radius2;
        parameter[PERIOD_LENGTH] = i_periodLength;
        setSurface();
    }
}
