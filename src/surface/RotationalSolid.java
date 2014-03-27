package surface;

import processing.core.PApplet;
import processing.core.PGraphics;
import surface.calculation.CosinusTable;
import surface.calculation.LookUpTable;
import surface.calculation.SinusTable;

/**
 * @author andreaskoeberle
 */
public class RotationalSolid extends Surface {

    private LookUpTable cosPhiTable;

    private LookUpTable sinPhiTable;

    private float points[][];

    /**
     * This constructer needs a point array of the type [[x1,y1],[x2,y2] ...].
     * The surface is created by rotating the points upon it's X axis.The
     * vertical resolution is given by the length of the point array.
     *
     * @param i_g        A PGraphics object where the surface should be drawn in.
     *                   Mostly this is g, the current PGraphics object of your sketch.
     * @param i_phiSteps The horizontal resolution of the surface.
     * @param i_Points   A point array of the type [x1,y1,x2,y2 ... ]. This points
     *                   are used to generate the surface shape.
     * @param i_colors   An array which holds the colors for the vertical and horizontal gradient ([[[verticalColor1],[verticalColor2],...],[[horizontalColor1],[horizontalColor2]]]).
     *                   You can also use [[[verticalColor1],[verticalColor2],...],null]) to get only an vertical gradient. Note that the the color stuff only work in the OPENGL mode.
     */
    public RotationalSolid(
            final PApplet i_g,
            final int i_phiSteps,
            final float[] i_Points,
            final int[][] i_colors) {

        super(
                i_g,
                i_phiSteps,
                i_Points.length / 2,
                0, i_Points.length / 2, 0, TWO_PI,
                i_Points,
                i_colors
        );

    }

    public RotationalSolid(
            final PApplet i_g,
            final int i_phiSteps,
            final float[] i_Points) {

        this(
                i_g,
                i_phiSteps,
                i_Points,
                null
        );

    }

    protected void initValues() {
        cosPhiTable = new CosinusTable(phiSteps, minPhi, maxPhi);
        sinPhiTable = new SinusTable(phiSteps, minPhi, maxPhi);
        points = new float[parameter.length / 2][2];
        for (int i = 0; i < parameter.length / 2; i++) {
            points[i][0] = parameter[i * 2];
            points[i][1] = parameter[i * 2 + 1];
        }
    }

    protected float calculateX(final int i_phiStep, final int i_thetaStep) {
        if (points != null) {
            return points[i_thetaStep][0];
        } else
            return 0;
    }

    protected float calculateY(final int i_phiStep, final int i_thetaStep) {
        if (points != null) {
            return points[i_thetaStep][1] * sinPhiTable.getValue(i_phiStep);
        } else
            return 0;
    }

    protected float calculateZ(final int i_phiStep, final int i_thetaStep) {
        if (points != null) {
            return points[i_thetaStep][1] * cosPhiTable.getValue(i_phiStep);
        } else
            return 0;
    }
}
