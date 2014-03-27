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
public class TetrahedralEllipse extends Surface {

    private LookUpTable identyThetaTable;
    private LookUpTable cosPhiTable;
    private LookUpTable sinPhiTable;

    /**
     * @param i_g          A PGraphics object where the surface should be drawn in.
     *                     Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps   The horizontal resolution of the surface.
     * @param i_thetaSteps The vertical resolution of the surface.
     * @param i_colors     An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                     You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public TetrahedralEllipse(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors
    ) {
        super(
                i_g, i_phiSteps, i_thetaSteps,
                -1, 1, -PI, PI,
                null,
                i_colors
        );
    }

    public TetrahedralEllipse(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps
    ) {
        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                null
        );
    }

    protected void initValues() {
        identyThetaTable = new IdentityTable(thetaSteps, minTheta, maxTheta);
        cosPhiTable = new CosinusTable(phiSteps, minPhi, maxPhi);
        sinPhiTable = new SinusTable(phiSteps, minPhi, maxPhi);
    }

    protected float calculateX(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return (1 - identyThetaTable.getValue(i_thetaStep)) * cosPhiTable.getValue(i_phiStep);
    }

    protected float calculateY(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return (1 + identyThetaTable.getValue(i_thetaStep)) * sinPhiTable.getValue(i_phiStep);
    }

    protected float calculateZ(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return identyThetaTable.getValue(i_thetaStep);
    }

}
