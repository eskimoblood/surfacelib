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
public class Horn extends Surface {

    private LookUpTable identityThetaTable;

    private LookUpTable sinThetaMultiplyTable;

    private LookUpTable cosThetaMultiplyTable;

    private LookUpTable sinPhiTable;

    private LookUpTable cosPhiTable;

    /**
     * @param i_g          A PGraphics object where the surface should be drawn in.
     *                     Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps   The horizontal resolution of the surface.
     * @param i_thetaSteps The vertical resolution of the surface.
     * @param i_colors     An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                     You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public Horn(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors) {

        super(
                i_g, i_phiSteps,
                i_thetaSteps,
                0, 1, 0, TWO_PI,
                null,
                i_colors);
    }

    public Horn(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps) {

        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                null
        );
    }


    protected void initValues() {
        identityThetaTable = new IdentityTable(thetaSteps, minTheta, maxTheta);
        sinPhiTable = new SinusTable(phiSteps, minPhi, maxPhi);
        cosPhiTable = new CosinusTable(phiSteps, minPhi, maxPhi);
        sinThetaMultiplyTable = new SinusMultiplyTable(thetaSteps, minTheta,
                maxTheta, TWO_PI);
        cosThetaMultiplyTable = new CosinusMultiplyTable(thetaSteps, minTheta,
                maxTheta, TWO_PI);
    }

    protected float calculateX(final int i_phiStep, final int i_thetaStep) {
        return (2 + identityThetaTable.getValue(i_thetaStep)
                * cosPhiTable.getValue(i_phiStep))
                * sinThetaMultiplyTable.getValue(i_thetaStep);
    }

    protected float calculateY(final int i_phiStep, final int i_thetaStep) {
        return (2 + identityThetaTable.getValue(i_thetaStep)
                * cosPhiTable.getValue(i_phiStep))
                * cosThetaMultiplyTable.getValue(i_thetaStep)
                + 2
                * identityThetaTable.getValue(i_thetaStep);
    }

    protected float calculateZ(final int i_phiStep, final int i_thetaStep) {
        return identityThetaTable.getValue(i_thetaStep)
                * sinPhiTable.getValue(i_phiStep);
    }

}
