package surface;

import processing.core.PGraphics;

public class SuperShape extends SuperDuperShape {

    public SuperShape(PGraphics i_g, int i_phiSteps, int i_thetaSteps) {
        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,
                null
        );
    }

    public SuperShape(PGraphics i_g, int i_phiSteps, int i_thetaSteps, int[][] i_colors) {
        this(
                i_g,
                i_phiSteps,
                i_thetaSteps,

                0.5f,
                1f,
                2.5f,
                5.7f,

                3f,
                0.2f,
                1f,
                10,
                i_colors
        );
    }


    public SuperShape(PGraphics i_g, int i_phiSteps, int i_thetaSteps,
                      float n1_1, float n1_2, float n1_3, float m1_1,
                      float n2_1, float n2_2, float n2_3, float m2_1) {
        this(
                i_g, i_phiSteps, i_thetaSteps,
                n1_1, n1_2, n1_3, m1_1,
                n2_1, n2_2, n2_3, m2_1,
                null
        );
    }

    public SuperShape(PGraphics i_g, int i_phiSteps, int i_thetaSteps,
                      SuperShapePreset r1,
                      SuperShapePreset r2) {
        this(
                i_g, i_phiSteps, i_thetaSteps,
                r1,
                r2,
                null
        );
    }

    public SuperShape(PGraphics i_g, int i_phiSteps, int i_thetaSteps,
                      SuperShapePreset r1,
                      SuperShapePreset r2,
                      int[][] i_colors) {
        this(
                i_g, i_phiSteps, i_thetaSteps,
                r1.n1, r1.n2, r1.n3, r1.m,
                r2.n1, r2.n2, r2.n3, r2.m,
                i_colors
        );
    }

    public SuperShape(PGraphics i_g, int i_phiSteps, int i_thetaSteps,
                      float n1_1, float n1_2, float n1_3, float m1_1,
                      float n2_1, float n2_2, float n2_3, float m2_1, int[][] i_colors) {
        super(
                i_g, i_phiSteps, i_thetaSteps,
                n1_1, n1_2, n1_3, m1_1,
                n2_1, n2_2, n2_3, m2_1,
                1, 1, 0,
                0, 0,
                0, 0,
                i_colors
        );
    }

}
