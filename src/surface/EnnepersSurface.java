package surface;

import processing.core.PApplet;
import processing.core.PGraphics;
import surface.calculation.IdentityTable;
import surface.calculation.LookUpTable;
import surface.calculation.PowerTable;

/**
 * @author andreaskoeberle
 */
public class EnnepersSurface extends Surface {

    private LookUpTable identityThetaTable;

    private LookUpTable identityPhiTable;

    private LookUpTable sqThetaTable;

    private LookUpTable sqPhiTable;

    private LookUpTable pow3ThetaTable;

    private LookUpTable pow3PhiTable;

    /**
     * @param i_g          A PApplet object where the surface should be drawn in.
     *                     Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps   The horizontal resolution of the surface.
     * @param i_thetaSteps The vertical resolution of the surface.
     * @param i_colors     An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                     You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public EnnepersSurface(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors) {

        super(
                i_g, i_phiSteps,
                i_thetaSteps,
                -2, 2, -2, 2,
                null,
                i_colors);
        phiSub = 2;
    }

    public EnnepersSurface(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps) {

        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                null);
    }

    protected void initValues() {
        identityThetaTable = new IdentityTable(thetaSteps, minTheta, maxTheta);
        identityPhiTable = new IdentityTable(phiSteps, minPhi, maxPhi);

        sqThetaTable = new PowerTable(thetaSteps, minTheta, maxTheta, 2);
        sqPhiTable = new PowerTable(phiSteps, minPhi, maxPhi, 2);

        pow3ThetaTable = new PowerTable(thetaSteps, minTheta, maxTheta, 3);
        pow3PhiTable = new PowerTable(phiSteps, minPhi, maxPhi, 3);
    }

    protected float calculateX(final int i_phiStep, final int i_thetaStep) {
        return identityPhiTable.getValue(i_phiStep)
                * (1 + sqThetaTable.getValue(i_thetaStep))
                - pow3PhiTable.getValue(i_phiStep) / 3;
    }

    protected float calculateY(final int i_phiStep, final int i_thetaStep) {
        return identityThetaTable.getValue(i_thetaStep)
                * (1 + sqPhiTable.getValue(i_phiStep))
                - pow3ThetaTable.getValue(i_thetaStep) / 3;
    }

    protected float calculateZ(final int i_phiStep, final int i_thetaStep) {
        return sqPhiTable.getValue(i_phiStep)
                * sqThetaTable.getValue(i_thetaStep);
    }

}
