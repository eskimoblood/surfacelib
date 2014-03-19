package surface;

import processing.core.PGraphics;
import surface.calculation.IdentityTable;
import surface.calculation.LookUpTable;
import surface.calculation.PowerTable;


/**
 * @author andreaskoeberle
 */
public class WhitneyUmbrella extends Surface {

    private LookUpTable identityThetaTable;
    private LookUpTable identityPhiTable;

    private LookUpTable pow2PhiTable;

    /**
     * @param i_g          A PGraphics object where the surface should be drawn in.
     *                     Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps   The horizontal resolution of the surface.
     * @param i_thetaSteps The vertical resolution of the surface.
     * @param i_colors     An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                     You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public WhitneyUmbrella(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors
    ) {
        super(
                i_g, i_phiSteps, i_thetaSteps,
                -1.5f, 1.5f, -1.5f, 1.5f,
                null,
                i_colors
        );
        phiSub = 2;
    }

    public WhitneyUmbrella(
            final PGraphics i_g,
            final int i_phiSteps,
            final int i_thetaSteps
    ) {
        this(
                i_g, i_phiSteps,
                i_thetaSteps,
                null
        );
    }

    protected void initValues() {
        identityThetaTable = new IdentityTable(thetaSteps, minTheta, maxTheta);
        identityPhiTable = new IdentityTable(phiSteps, minPhi, maxPhi);
        pow2PhiTable = new PowerTable(phiSteps, minPhi, maxPhi, 2);
    }

    protected float calculateX(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return identityThetaTable.getValue(i_thetaStep) * identityPhiTable.getValue(i_phiStep);
    }

    protected float calculateY(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return identityThetaTable.getValue(i_thetaStep);
    }

    protected float calculateZ(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return pow2PhiTable.getValue(i_phiStep);
    }

}
