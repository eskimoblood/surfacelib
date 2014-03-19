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
public class MoebiusStrip extends Surface {

    private LookUpTable sinThetaTable;
    private LookUpTable cosThetaTable;
    private LookUpTable sinThetaMultiplyTable;
    private LookUpTable cosThetaMultiplyTable;

    private LookUpTable identityPhiTable;


    private final static int INNER_RADIUS = 0;

    /**
     * @param i_g           A PGraphics object where the surface should be drawn in.
     *                      Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps    The horizontal resolution of the surface.
     * @param i_thetaSteps  The vertical resolution of the surface.
     * @param i_innerRadius The inner radius of the surface.
     * @param i_colors      An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                      You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */

    public MoebiusStrip(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float i_innerRadius,
            final int[][] i_colors
    ) {
        super(
                i_g, i_phiSteps, i_thetaSteps,
                0, TWO_PI, -3, 3,
                new float[]{i_innerRadius},
                i_colors
        );
        phiSub = 2;
    }

    /**
     * This default constructor, creates a moebiusstrip with an inner radius of 1.
     *
     * @param i_g          A PGraphics object where the surface should be drawn in.
     *                     Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps   The horizontal resolution of the surface.
     * @param i_thetaSteps The vertical resolution of the surface.
     */
    public MoebiusStrip(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps
    ) {
        this(
                i_g, i_phiSteps, i_thetaSteps, 1f
        );
    }

    public MoebiusStrip(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors
    ) {
        this(
                i_g, i_phiSteps, i_thetaSteps, 1f, i_colors
        );
    }

    public MoebiusStrip(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final float i_innerRadius
    ) {
        this(
                i_g, i_phiSteps, i_thetaSteps, i_innerRadius,
                null
        );
    }

    protected void initValues() {
        sinThetaTable = new SinusTable(thetaSteps, minTheta, maxTheta);
        cosThetaTable = new CosinusTable(thetaSteps, minTheta, maxTheta);
        sinThetaMultiplyTable = new SinusMultiplyTable(thetaSteps, minTheta, maxTheta, 0.5f);
        cosThetaMultiplyTable = new CosinusMultiplyTable(thetaSteps, minTheta, maxTheta, 0.5f);
        identityPhiTable = new IdentityTable(phiSteps, minPhi, maxPhi);
    }

    protected float calculateX(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return cosThetaTable.getValue(i_thetaStep) *
                (parameter[INNER_RADIUS] + identityPhiTable.getValue(i_phiStep) * cosThetaMultiplyTable.getValue(i_thetaStep));
    }

    protected float calculateY(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return sinThetaTable.getValue(i_thetaStep) *
                (parameter[INNER_RADIUS] + identityPhiTable.getValue(i_phiStep) * cosThetaMultiplyTable.getValue(i_thetaStep));
    }

    protected float calculateZ(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return identityPhiTable.getValue(i_phiStep) * sinThetaMultiplyTable.getValue(i_thetaStep);
    }

}
