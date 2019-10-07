#version 150

layout (points) in;
layout (triangle_strip, max_vertices = 24) out;

in vec3 baseColor[]; //TODO- use textures

out vec3 color;

uniform mat4 transformMat;
uniform mat4 projectionMat;
uniform mat4 viewMat;
uniform float sides[6];

const float size = 0.5;
const vec3 lightDirection = normalize(vec3(0.75, -0.5, -0.85)); //TODO- calculate based off light and camera
const float ambientLighting = 0.3; //TODO- use Ref

void createVertex(vec3 offset, vec3 colour){
    vec4 actualOffset = vec4(offset * size, 0.0);
    vec4 worldPosition = (gl_in[0].gl_Position + actualOffset);
    gl_Position = projectionMat * viewMat * transformMat * worldPosition;
    color = colour;
    EmitVertex();
}

vec3 calculateLighting(vec3 faceNormal){
    float brightness = max(dot(-lightDirection, faceNormal), ambientLighting);
    return baseColor[0] * brightness;
}

void main(void) {

    // front
    vec3 faceNormal;
    vec3 colour;

    faceNormal = vec3(0.0, 0.0, 1.0);
    colour = calculateLighting(faceNormal);
    createVertex(vec3(-1.0, 1.0, 1.0), colour);
    createVertex(vec3(-1.0, -1.0, 1.0), colour);
    createVertex(vec3(1.0, 1.0, 1.0), colour);
    createVertex(vec3(1.0, -1.0, 1.0), colour);

    EndPrimitive();

    // right
    faceNormal = vec3(1.0, 0.0, 0.0);
    colour = calculateLighting(faceNormal);
    createVertex(vec3(1.0, 1.0, 1.0), colour);
    createVertex(vec3(1.0, -1.0, 1.0), colour);
    createVertex(vec3(1.0, 1.0, -1.0), colour);
    createVertex(vec3(1.0, -1.0, -1.0), colour);

    EndPrimitive();

    // back
    faceNormal = vec3(0.0, 0.0, -1.0);
    colour = calculateLighting(faceNormal);
    createVertex(vec3(1.0, 1.0, -1.0), colour);
    createVertex(vec3(1.0, -1.0, -1.0), colour);
    createVertex(vec3(-1.0, 1.0, -1.0), colour);
    createVertex(vec3(-1.0, -1.0, -1.0), colour);

    EndPrimitive();

    // left
    faceNormal = vec3(-1.0, 0.0, 0.0);
    colour = calculateLighting(faceNormal);
    createVertex(vec3(-1.0, 1.0, -1.0), colour);
    createVertex(vec3(-1.0, -1.0, -1.0), colour);
    createVertex(vec3(-1.0, 1.0, 1.0), colour);
    createVertex(vec3(-1.0, -1.0, 1.0), colour);

    EndPrimitive();

    // top
    faceNormal = vec3(0.0, 1.0, 0.0);
    colour = calculateLighting(faceNormal);
    createVertex(vec3(1.0, 1.0, 1.0), colour);
    createVertex(vec3(1.0, 1.0, -1.0), colour);
    createVertex(vec3(-1.0, 1.0, 1.0), colour);
    createVertex(vec3(-1.0, 1.0, -1.0), colour);

    EndPrimitive();

    // bottom
    faceNormal = vec3(0.0, -1.0, 0.0);
    colour = calculateLighting(faceNormal);
    createVertex(vec3(-1.0, -1.0, 1.0), colour);
    createVertex(vec3(-1.0, -1.0, -1.0), colour);
    createVertex(vec3(1.0, -1.0, 1.0), colour);
    createVertex(vec3(1.0, -1.0, -1.0), colour);

    EndPrimitive();

}