#define PROCESSING_LIGHT_SHADER

uniform mat4 modelview;
uniform mat4 transform;
uniform mat3 normalMatrix;

uniform vec4 lightPosition;
uniform vec3 lightNormal;
uniform float smX;
uniform float smY;

attribute vec4 vertex;
attribute vec4 color;
attribute vec3 normal;
varying vec4 vertColor;
varying vec3 ecNormal;
varying vec3 lightDir;

void main() {
  gl_Position = transform * vertex;
  vec3 ecVertex = vec3(modelview * vertex);

  ecNormal = normalize(normalMatrix * normal);
  lightDir = normalize(lightPosition.xyz - ecVertex);

  float x = fract(vertex.x / smX);
  float y = fract(vertex.y / smY);
  float f = smoothstep(.2, .4, x) - smoothstep(.7,.9, y);

  vec4 color1 = vec4(0.0, 0.0, 1.0, 1.0);
  vec4 color2 = vec4(1.0, 1.0, 0.5, 1.0);
  vertColor = mix(color, color1, f);
}
