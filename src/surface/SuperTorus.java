package surface;

import processing.core.PApplet;
import processing.core.PGraphics;

public class SuperTorus extends SuperDuperShape {

    public SuperTorus(PApplet i_g, int i_phiSteps, int i_thetaSteps) {
        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                null
        );
    }

    public SuperTorus(PApplet i_g, int i_phiSteps, int i_thetaSteps, int[][] i_colors) {
        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,

                11.3f,
                9,
                10.1f,
                0,

                5,
                12.1f,
                9.5f,
                5,

                .4f,
                3.6f,

                i_colors
        );
    }


    public SuperTorus(PApplet i_g, int i_phiSteps, int i_thetaSteps,
                      float n1_1, float n1_2, float n1_3, float m1_1,
                      float n2_1, float n2_2, float n2_3, float m2_1,
                      float c3, float t1) {
        this(
                i_g, i_phiSteps, i_thetaSteps,
                n1_1, n1_2, n1_3, m1_1,
                n2_1, n2_2, n2_3, m2_1,
                c3, t1,
                null
        );
    }

    public SuperTorus(PApplet i_g, int i_phiSteps, int i_thetaSteps,
                      SuperShapePreset r1,
                      SuperShapePreset r2,
                      float c3, float t1) {
        this(
                i_g, i_phiSteps, i_thetaSteps,
                r1,
                r2,
                c3, t1,
                null
        );
    }

    public SuperTorus(PApplet i_g, int i_phiSteps, int i_thetaSteps,
                      SuperShapePreset r1,
                      SuperShapePreset r2,
                      float c3, float t1,
                      int[][] i_colors) {
        this(
                i_g, i_phiSteps, i_thetaSteps,
                r1.n1, r1.n2, r1.n3, r1.m,
                r2.n1, r2.n2, r2.n3, r2.m,
                c3, t1,
                i_colors
        );
    }

    public SuperTorus(PApplet i_g, int i_phiSteps, int i_thetaSteps,
                      float n1_1, float n1_2, float n1_3, float m1_1,
                      float n2_1, float n2_2, float n2_3, float m2_1, float c3, float t1, int[][] i_colors) {
        super(
                i_g, i_phiSteps, i_thetaSteps,
                n1_1, n1_2, n1_3, m1_1,
                n2_1, n2_2, n2_3, m2_1,
                1, 2, c3,
                t1, 0,
                0, 0,
                i_colors
        );
    }

}
