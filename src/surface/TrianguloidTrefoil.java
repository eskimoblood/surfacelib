package surface;

import processing.core.PApplet;
import processing.core.PGraphics;
import surface.calculation.LookUpTable;
import surface.calculation.CosinusTable;
import surface.calculation.CosinusMultiplyTable;
import surface.calculation.CosinusAddTable;
import surface.calculation.SinusTable;
import surface.calculation.SinusMultiplyTable;

/**
 * @author andreas
 */
public class TrianguloidTrefoil extends Surface {

    private LookUpTable cosuTable;
    private LookUpTable cosvTable;
    private LookUpTable cos2uTable;
    private LookUpTable cosv2pi3Table;
    private LookUpTable sinuTable;
    private LookUpTable sin2uTable;
    private LookUpTable sin3uTable;

    /**
     * @param i_g          A PGraphics object where the surface should be drawn in.
     *                     Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps   The horizontal resolution of the surface.
     * @param i_thetaSteps The vertical resolution of the surface.
     * @param i_colors     An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                     You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public TrianguloidTrefoil(
            final PApplet i_g,
            final int i_phiSteps,
            final int i_thetaSteps,
            final int[][] i_colors) {

        super(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                -PI, PI, -PI, PI,
                null,
                i_colors);
    }

    public TrianguloidTrefoil(
            final PApplet i_g,
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
        cosuTable = new CosinusTable(thetaSteps, minTheta, maxTheta);
        cosvTable = new CosinusTable(phiSteps, minPhi, maxPhi);
        cos2uTable = new CosinusMultiplyTable(thetaSteps, minTheta, maxTheta, 2f);
        cosv2pi3Table = new CosinusAddTable(phiSteps, minPhi, maxPhi, TWO_PI / 3f);
        sinuTable = new SinusTable(thetaSteps, minTheta, maxTheta);
        sin2uTable = new SinusMultiplyTable(thetaSteps, minTheta, maxTheta, 2f);
        sin3uTable = new SinusMultiplyTable(thetaSteps, minTheta, maxTheta, 3f);
    }

    protected float calculateX(final int i_phiStep, final int i_thetaStep) {
        return 2f * sin3uTable.getValue(i_thetaStep) / (2f + cosvTable.getValue(i_phiStep));
    }

    protected float calculateY(final int i_phiStep, final int i_thetaStep) {
        return 2f * (sinuTable.getValue(i_thetaStep) + 2f * sin2uTable.getValue(i_thetaStep)) /
                (2f + cosv2pi3Table.getValue(i_phiStep));
    }

    protected float calculateZ(final int i_phiStep, final int i_thetaStep) {
        return (cosuTable.getValue(i_thetaStep) - 2f * cos2uTable.getValue(i_thetaStep)) *
                (2f + cosvTable.getValue(i_phiStep)) * (2f + cosv2pi3Table.getValue(i_phiStep)) / 4f;
    }

}
