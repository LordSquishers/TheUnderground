#version 150

in vec3 position;
<<<<<<< HEAD
in vec2 texCoords;

out vec2 _texCoords;
out vec3 toLightVector;
out vec3 toCameraVector;
out float visibility;

uniform vec3 lightPos;

uniform mat4 transformMat;
uniform mat4 projectionMat;
uniform mat4 viewMat;

const float density = 0.0035;
const float gradient = 5.0;

void main(void) {

  vec4 worldPos = transformMat * vec4(position, 1.0);
  vec4 positionRelativeToCam = viewMat * worldPos;
  gl_Position = projectionMat * positionRelativeToCam;

  _texCoords = texCoords;

  toLightVector = lightPos - worldPos.xyz;
  toCameraVector = (inverse(viewMat) * vec4(0.0, 0.0, 0.0, 1.0)).xyz - worldPos.xyz;

  float distance = length(positionRelativeToCam.xyz);
  visibility = exp(-pow((distance * density), gradient));
  visibility = clamp(visibility, 0.0, 1.0);

}
=======
in vec3 color;

out vec3 baseColor;

void main(void) {
    gl_Position = vec4(position, 1.0);
    baseColor = color;
}
>>>>>>> parent of 0e07589... working on rendering
