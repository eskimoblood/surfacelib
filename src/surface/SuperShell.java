package surface;

import processing.core.PGraphics;

public class SuperShell extends SuperDuperShape {

    public SuperShell(PGraphics i_g, int i_phiSteps, int i_thetaSteps) {
        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                null
        );
    }

    public SuperShell(PGraphics i_g, int i_phiSteps, int i_thetaSteps, int[][] i_colors) {
        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,

                10,
                0,
                0,
                0,

                10,
                0,
                0,
                0,

                5,
                2,
                1,
                1,

                i_colors
        );
    }


    public SuperShell(PGraphics i_g, int i_phiSteps, int i_thetaSteps,
                      float n1_1, float n1_2, float n1_3, float m1_1,
                      float n2_1, float n2_2, float n2_3, float m2_1,
                      float c1, float t2, float d1, float d2) {
        this(
                i_g, i_phiSteps, i_thetaSteps,
                n1_1, n1_2, n1_3, m1_1,
                n2_1, n2_2, n2_3, m2_1,
                c1, t2, d1, d2,
                null
        );
    }

    public SuperShell(PGraphics i_g, int i_phiSteps, int i_thetaSteps,
                      SuperShapePreset r1,
                      SuperShapePreset r2,
                      float c1, float t2, float d1, float d2) {
        this(
                i_g, i_phiSteps, i_thetaSteps,
                r1,
                r2,
                c1, t2, d1, d2,
                null
        );
    }

    public SuperShell(PGraphics i_g, int i_phiSteps, int i_thetaSteps,
                      SuperShapePreset r1,
                      SuperShapePreset r2,
                      float c1, float t2, float d1, float d2,
                      int[][] i_colors) {
        this(
                i_g, i_phiSteps, i_thetaSteps,
                r1.n1, r1.n2, r1.n3, r1.m,
                r2.n1, r2.n2, r2.n3, r2.m,
                c1, t2, d1, d2,
                i_colors
        );
    }

    public SuperShell(PGraphics i_g, int i_phiSteps, int i_thetaSteps,
                      float n1_1, float n1_2, float n1_3, float m1_1,
                      float n2_1, float n2_2, float n2_3, float m2_1,
                      float c1, float t2, float d1, float d2,
                      int[][] i_colors
    ) {
        super(
                i_g, i_phiSteps, i_thetaSteps,
                n1_1, n1_2, n1_3, m1_1,
                n2_1, n2_2, n2_3, m2_1,
                c1, 1, 0,
                0, t2,
                d1, d2,
                i_colors
        );
    }

}
