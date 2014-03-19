package surface;

import processing.core.PGraphics;
import surface.calculation.CosinusMultiplyTable;
import surface.calculation.CosinusTable;
import surface.calculation.IdentityTable;
import surface.calculation.LookUpTable;
import surface.calculation.SinusMultiplyTable;
import surface.calculation.SinusTable;

/**
 * @author andreaskoeberle
 */
public class Shell extends Surface {

    private final static int NUMBER_OF_SPRIALS = 0;

    private final static int FINAL_RADIUS = 1;

    private final static int HEIGHT = 2;

    private final static int INNER_RADIUS = 3;

    private LookUpTable identityThetaTable;

    private LookUpTable sinThetaMultiplyTable;

    private LookUpTable cosThetaMultiplyTable;

    private LookUpTable cosPhiTable;

    private LookUpTable sinPhiTable;

    /**
     * @param i_g               A PGraphics object where the surface should be drawn in.
     *                          Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps        The horizontal resolution of the surface.
     * @param i_thetaSteps      The vertical resolution of the surfa
     * @param i_numberOfSpirals The number of spirals
     * @param i_finalRadius     The width of the surface
     * @param i_height          The height of the surface
     * @param i_innerRadius     The inner radius of the surface
     * @param i_colors          An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                          You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public Shell(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int i_numberOfSpirals,
            final float i_finalRadius,
            final float i_height,
            final float i_innerRadius,
            final int[][] i_colors) {

        super(
                i_g, i_phiSteps,
                i_thetaSteps,
                0, TWO_PI, 0, TWO_PI,
                new float[]{i_numberOfSpirals, i_finalRadius, i_height, i_innerRadius},
                i_colors
        );
    }

    public Shell(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int i_numberOfSpirals,
            final float i_finalRadius,
            final float i_height,
            final float i_innerRadius) {

        this(
                i_g, i_phiSteps,
                i_thetaSteps,
                i_numberOfSpirals,
                i_finalRadius,
                i_height,
                i_innerRadius,
                null
        );
    }


    public Shell(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps) {

        this(
                i_g, i_phiSteps,
                i_thetaSteps,
                3,
                1,
                10,
                0.1f
        );
    }

    public Shell(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors) {

        this(
                i_g, i_phiSteps,
                i_thetaSteps,
                3,
                1,
                10,
                0.1f,
                i_colors
        );
    }

    protected void initValues() {
        identityThetaTable = new IdentityTable(thetaSteps, minTheta, maxTheta);
        sinThetaMultiplyTable = new SinusMultiplyTable(thetaSteps, minTheta,
                maxTheta, parameter[NUMBER_OF_SPRIALS]);
        cosThetaMultiplyTable = new CosinusMultiplyTable(thetaSteps, minTheta,
                maxTheta, parameter[NUMBER_OF_SPRIALS]);
        cosPhiTable = new CosinusTable(phiSteps, minPhi, maxPhi);
        sinPhiTable = new SinusTable(phiSteps, minPhi, maxPhi);
    }

    protected float calculateX(final int i_phiStep, final int i_thetaStep) {
        return parameter[FINAL_RADIUS]
                * (1 - identityThetaTable.getValue(i_thetaStep) / TWO_PI)
                * cosThetaMultiplyTable.getValue(i_thetaStep)
                * ((1 + cosPhiTable.getValue(i_phiStep)) + parameter[INNER_RADIUS]);
    }

    protected float calculateY(final int i_phiStep, final int i_thetaStep) {
        return parameter[FINAL_RADIUS]
                * (1 - identityThetaTable.getValue(i_thetaStep) / TWO_PI)
                * sinThetaMultiplyTable.getValue(i_thetaStep)
                * ((1 + cosPhiTable.getValue(i_phiStep)) + parameter[INNER_RADIUS]);
    }

    protected float calculateZ(final int i_phiStep, final int i_thetaStep) {
        return parameter[HEIGHT]
                * (identityThetaTable.getValue(i_thetaStep) / TWO_PI)
                + parameter[FINAL_RADIUS]
                * (1 - identityThetaTable.getValue(i_thetaStep) / TWO_PI)
                * sinPhiTable.getValue(i_phiStep);
    }

    /**
     * @return Returns the finalRadius.
     */
    public float finalRadius() {
        return parameter[FINAL_RADIUS];
    }

    /**
     * @param i_finalRadius The finalRadius to set.
     */
    public void setFinalRadius(final float i_finalRadius) {
        parameter[FINAL_RADIUS] = i_finalRadius;
    }

    /**
     * @return Returns the height.
     */
    public float height() {
        return parameter[HEIGHT];
    }

    /**
     * @param i_height The height to set.
     */
    public void setHeight(final float i_height) {
        parameter[HEIGHT] = i_height;
    }

    /**
     * @return Returns the innerRadius.
     */
    public float innerRadius() {
        return parameter[INNER_RADIUS];
    }

    /**
     * @param i_innerRadius The innerRadius to set.
     */
    public void setInnerRadius(final float i_innerRadius) {
        parameter[INNER_RADIUS] = i_innerRadius;
    }

    /**
     * @return Returns the numberOfSpirals.
     */
    public int numberOfSpirals() {
        return (int) parameter[NUMBER_OF_SPRIALS];
    }

    /**
     * @param i_numberOfSpirals The numberOfSpirals to set.
     */
    public void setNumberOfSpirals(final int i_numberOfSpirals) {
        parameter[NUMBER_OF_SPRIALS] = i_numberOfSpirals;
    }

}
