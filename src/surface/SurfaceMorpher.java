package surface;

import processing.core.PApplet;

import java.util.Arrays;

/**
 * Use this class to morph between two different surfaces.
 * There are two different ways of morphing surfaces, you can
 * build a surface morpher from two surfaces and blend between both.
 * You also morph between more surfaces using the morphTo method.
 * Look at the documentation of the method to get more details.
 *
 * @author andreaskoeberle
 */
public class SurfaceMorpher extends Surface {

    private Surface surface1;
    private Surface surface2;

    /**
     * The constructor of the class takes two surfaces. The first one
     * is visible for the blend value 0. The second surface is visible
     * for the blend value 1.
     * A blend value between 0 and 1 morphs between the two surfaces.
     * Another way to use the surface morpher, is  to initialze it with one
     * surface and and use the morphTo method set the surface to blend to.
     *
     * @param i_surface1 Surface, surface for blend value 0
     * @param i_surface2 Surface, surface for blend value 1
     */
    public SurfaceMorpher(final Surface i_surface1,
                          final Surface i_surface2
    ) {
        super(i_surface1);
        surface1 = i_surface1;
        surface2 = i_surface2;
    }

    /**
     * @param i_surface Surface, surface
     */
    public SurfaceMorpher(
            final Surface i_surface
    ) {
        super(
                i_surface
        );
    }

    protected float calculateX(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return 0;
    }

    protected float calculateY(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return 0;
    }

    protected float calculateZ(
            final int i_phiStep,
            final int i_thetaStep
    ) {
        return 0;
    }

    /**
     * Returns the first surface of the surface morper.
     *
     * @return Surface, first Surface of the surface morpher.
     */
    public Surface surface1() {
        return surface1;
    }

    /**
     * Sets the second surface of the surface morpher.
     *
     * @param i_surface Surface, second surface of the surface morpher
     */
    public void setSurface2(final Surface i_surface) {
        surface1 = i_surface;
    }

    /**
     * Returns the second surface of the surface morper.
     *
     * @return Surface, second Surface of the surface morpher.
     */
    public Surface surface2() {
        return surface1;
    }

    /**
     * Sets the first surface of the surface morpher.
     *
     * @param i_surface Surface, first surface of the surface morpher
     */
    public void setSurface1(final Surface i_surface) {
        surface1 = i_surface;
    }

    /**
     * Sets the blend value to morph between the two base surfaces.
     *
     * @param i_blend, float value defining the influence of the two surfaces
     *                 on the resulting surface.
     */
    public void morph(final float i_blend) {
        if (surface2 == null) return;

        for (int phiStep = 0; phiStep < phiSteps; phiStep++) {
            for (int thetaStep = 0; thetaStep < thetaSteps; thetaStep++) {
                coords[phiStep][thetaStep][0] =
                        surface1.coords[phiStep][thetaStep][0] * (1 - i_blend) +
                                surface2.coords[phiStep][thetaStep][0] * (i_blend);

                coords[phiStep][thetaStep][1] =
                        surface1.coords[phiStep][thetaStep][1] * (1 - i_blend) +
                                surface2.coords[phiStep][thetaStep][1] * (i_blend);

                coords[phiStep][thetaStep][2] =
                        surface1.coords[phiStep][thetaStep][2] * (1 - i_blend) +
                                surface2.coords[phiStep][thetaStep][2] * (i_blend);
            }
        }
    }

    /**
     * Use this method to define the surface you want to morph to.
     * When you call this method the current state of the surface morpher
     * is kept as initial value for blend value 0. This way it is possible
     * to seamlessly morph between surfaces.
     *
     * @param i_surface Surface, the surface to morph to
     */
    public void morphTo(final Surface i_surface) {
        surface1 = new Surface(this);
        surface2 = i_surface;
    }
}
