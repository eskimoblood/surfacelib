package surface;

import processing.core.PVector;

public class SurfaceMesh {

    public final class Face {

        public final Vertex a, b, c;
        public final PVector normal;

        Face(Vertex a, Vertex b, Vertex c) {
            this.a = a;
            this.b = b;
            this.c = c;
            normal = PVector.sub(a, c).cross(PVector.sub(a, b));
            normal.normalize();
            a.addFaceNormal(normal);
            b.addFaceNormal(normal);
            c.addFaceNormal(normal);
        }

        public final Vertex[] getVertices(Vertex[] verts) {
            if (verts != null) {
                verts[0] = a;
                verts[1] = b;
                verts[2] = c;
            } else {
                verts = new Vertex[]{a, b, c};
            }
            return verts;
        }

        public String toString() {
            return "TriangleMesh.Face: " + a + ", " + b + ", " + c;
        }
    }

    public final class Vertex extends PVector {

        public final PVector normal = new PVector();
        private int valence = 0;

        Vertex(PVector v) {
            super(v.x, v.y, v.z);
        }

        final void addFaceNormal(PVector n) {
            normal.add(n);
            valence++;
        }

        final PVector computeNormal() {
            normal.mult(1f / valence);
            normal.normalize();
            return normal;
        }

        public String toString() {
            return ": p: " + super.toString() + " n:" + normal.toString();
        }
    }

    private Surface surface;
    private Face[][][] faces;
    protected PVector normals[][];
    private Vertex[][] vertices;

    public SurfaceMesh(Surface surface) {
        this.surface = surface;
        updateFaces();
    }

    protected void updateFaces() {
        vertices = new Vertex[surface.phiSteps][surface.thetaSteps];
        normals = new PVector[surface.phiSteps][surface.thetaSteps];
        faces = new Face[surface.phiSteps][surface.thetaSteps][2];
        PVector scale = new PVector(surface.xScale, surface.yScale, surface.zScale);

        float[][][] coords = surface.coords;
        for (int i = 0; i < coords.length; i++) {
            for (int j = 0; j < coords[i].length; j++) {
                vertices[i][j] = new Vertex(new PVector(coords[i][j][0] * scale.x, coords[i][j][1] * scale.y, coords[i][j][2]* scale.z));
            }
        }

        for (int i = 0; i < coords.length - 1; i++) {
            for (int j = 0; j < coords[i].length - 1; j++) {
                Vertex p0 = vertices[i][j];
                Vertex p1 = vertices[i][j + 1];
                Vertex p2 = vertices[(i + 1)][j];
                Vertex p3 = vertices[(i + 1)][j + 1];
                faces[i][j][0] = new Face(p0, p2, p1);
                faces[i][j][1] = new Face(p1, p2, p3);
            }
        }
        for (int i = 0; i < coords.length; i++) {
            for (int j = 0; j < coords[i].length; j++) {
                normals[i][j] = vertices[i][j].computeNormal();
            }
        }
    }

}
