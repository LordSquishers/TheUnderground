#version 410 core

in vec3 position;
in vec2 texCoords;
in vec3 normal;

out vec2 _texCoords;
out vec3 surfaceNormal;
out vec3 toLightVector;
out vec3 toCameraVector;
out float visibility;

uniform mat4 transformMat;
uniform mat4 projectionMat;
uniform mat4 viewMat;

uniform vec3 lightPos;

const float density = 0.0035;
const float gradient = 5.0;

void main() {

    vec4 worldPos = transformMat * vec4(position, 1.0);
    vec4 positionRelativeToCam = viewMat * worldPos;
    gl_Position = projectionMat * positionRelativeToCam;
    _texCoords = texCoords * 40.0;

    surfaceNormal = (transformMat * vec4(normal, 0.0)).xyz;
    toLightVector = lightPos - worldPos.xyz;
    toCameraVector = (inverse(viewMat) * vec4(0.0, 0.0, 0.0, 1.0)).xyz - worldPos.xyz;

    float distance = length(positionRelativeToCam.xyz);
    visibility = exp(-pow((distance * density), gradient));
    visibility = clamp(visibility, 0.0, 1.0);

}
