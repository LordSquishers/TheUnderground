#version 150

layout (points) in;
layout (triangle_strip, max_vertices = 24) out;

<<<<<<< HEAD
in vec2 _texCoords[];
in vec3 toLightVector[];
in vec3 toCameraVector[];
in float visibility[];

out vec2 texCoords;
out vec3 surfaceNormal;

out vec3 _toLightVector;
out vec3 _toCameraVector;
out float _visibility;

uniform bool sides[6];

void createVertex(vec3 offset, vec3 normal){
    vec4 actualOffset = vec4(offset * 0.5, 0.0);
    vec4 worldPosition = (gl_in[0].gl_Position + actualOffset);
    gl_Position = worldPosition;

    texCoords = _texCoords[0];
    _toLightVector = toLightVector[0];
    _toCameraVector = toCameraVector[0];
    _visibility = visibility[0];
    surfaceNormal = normal;

    EmitVertex();
}

void main(void) {

<<<<<<< HEAD
  // front
  vec3 faceNormal;

  if(sides[4]) {
      faceNormal = vec3(0.0, 0.0, 1.0);
      createVertex(vec3(-1.0, 1.0, 1.0), faceNormal);
      createVertex(vec3(-1.0, -1.0, 1.0), faceNormal);
      createVertex(vec3(1.0, 1.0, 1.0), faceNormal);
      createVertex(vec3(1.0, -1.0, 1.0), faceNormal);

      EndPrimitive();
  }

  // right
  if(sides[3]) {
      faceNormal = vec3(1.0, 0.0, 0.0);
      createVertex(vec3(1.0, 1.0, 1.0), faceNormal);
      createVertex(vec3(1.0, -1.0, 1.0), faceNormal);
      createVertex(vec3(1.0, 1.0, -1.0), faceNormal);
      createVertex(vec3(1.0, -1.0, -1.0), faceNormal);

      EndPrimitive();
  }

  // back
  if(sides[5]) {
      faceNormal = vec3(0.0, 0.0, -1.0);
      createVertex(vec3(1.0, 1.0, -1.0), faceNormal);
      createVertex(vec3(1.0, -1.0, -1.0), faceNormal);
      createVertex(vec3(-1.0, 1.0, -1.0), faceNormal);
      createVertex(vec3(-1.0, -1.0, -1.0), faceNormal);

      EndPrimitive();
  }

  // left
  if(sides[2]) {
      faceNormal = vec3(-1.0, 0.0, 0.0);
      createVertex(vec3(-1.0, 1.0, -1.0), faceNormal);
      createVertex(vec3(-1.0, -1.0, -1.0), faceNormal);
      createVertex(vec3(-1.0, 1.0, 1.0), faceNormal);
      createVertex(vec3(-1.0, -1.0, 1.0), faceNormal);

      EndPrimitive();
  }

  // top
  if(sides[0]) {
      faceNormal = vec3(0.0, 1.0, 0.0);
      createVertex(vec3(1.0, 1.0, 1.0), faceNormal);
      createVertex(vec3(1.0, 1.0, -1.0), faceNormal);
      createVertex(vec3(-1.0, 1.0, 1.0), faceNormal);
      createVertex(vec3(-1.0, 1.0, -1.0), faceNormal);

      EndPrimitive();
  }

  // bottom
  if(sides[1]) {
      faceNormal = vec3(0.0, -1.0, 0.0);
      createVertex(vec3(-1.0, -1.0, 1.0), faceNormal);
      createVertex(vec3(-1.0, -1.0, -1.0), faceNormal);
      createVertex(vec3(1.0, -1.0, 1.0), faceNormal);
      createVertex(vec3(1.0, -1.0, -1.0), faceNormal);

      EndPrimitive();
  }
}
=======
=======
in vec3 baseColor[]; //TODO- use textures

out vec3 color;

uniform mat4 transformMat;
uniform mat4 projectionMat;
uniform mat4 viewMat;
uniform bool sides[6];

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

>>>>>>> parent of 0e07589... working on rendering
    // front
    vec3 faceNormal;
    vec3 colour;

    if(sides[4]) {
        faceNormal = vec3(0.0, 0.0, 1.0);
        colour = calculateLighting(faceNormal);
        createVertex(vec3(-1.0, 1.0, 1.0), colour);
        createVertex(vec3(-1.0, -1.0, 1.0), colour);
        createVertex(vec3(1.0, 1.0, 1.0), colour);
        createVertex(vec3(1.0, -1.0, 1.0), colour);

        EndPrimitive();
    }

<<<<<<< HEAD
    if(sides[3]) {
        // right
=======
    // right
    if(sides[3]) {
>>>>>>> parent of 0e07589... working on rendering
        faceNormal = vec3(1.0, 0.0, 0.0);
        colour = calculateLighting(faceNormal);
        createVertex(vec3(1.0, 1.0, 1.0), colour);
        createVertex(vec3(1.0, -1.0, 1.0), colour);
        createVertex(vec3(1.0, 1.0, -1.0), colour);
        createVertex(vec3(1.0, -1.0, -1.0), colour);

        EndPrimitive();
    }

<<<<<<< HEAD
    if(sides[5]) {
        // back
=======
    // back
    if(sides[5]) {
>>>>>>> parent of 0e07589... working on rendering
        faceNormal = vec3(0.0, 0.0, -1.0);
        colour = calculateLighting(faceNormal);
        createVertex(vec3(1.0, 1.0, -1.0), colour);
        createVertex(vec3(1.0, -1.0, -1.0), colour);
        createVertex(vec3(-1.0, 1.0, -1.0), colour);
        createVertex(vec3(-1.0, -1.0, -1.0), colour);

        EndPrimitive();
    }

<<<<<<< HEAD
    if(sides[2]) {
        // left
=======
    // left
    if(sides[2]) {
>>>>>>> parent of 0e07589... working on rendering
        faceNormal = vec3(-1.0, 0.0, 0.0);
        colour = calculateLighting(faceNormal);
        createVertex(vec3(-1.0, 1.0, -1.0), colour);
        createVertex(vec3(-1.0, -1.0, -1.0), colour);
        createVertex(vec3(-1.0, 1.0, 1.0), colour);
        createVertex(vec3(-1.0, -1.0, 1.0), colour);

        EndPrimitive();
    }

<<<<<<< HEAD
    if(sides[0]) {
        // top
=======
    // top
    if(sides[0]) {
>>>>>>> parent of 0e07589... working on rendering
        faceNormal = vec3(0.0, 1.0, 0.0);
        colour = calculateLighting(faceNormal);
        createVertex(vec3(1.0, 1.0, 1.0), colour);
        createVertex(vec3(1.0, 1.0, -1.0), colour);
        createVertex(vec3(-1.0, 1.0, 1.0), colour);
        createVertex(vec3(-1.0, 1.0, -1.0), colour);

        EndPrimitive();
    }

<<<<<<< HEAD
    if(sides[1]) {
        // bottom
=======
    // bottom
    if(sides[1]) {
>>>>>>> parent of 0e07589... working on rendering
        faceNormal = vec3(0.0, -1.0, 0.0);
        colour = calculateLighting(faceNormal);
        createVertex(vec3(-1.0, -1.0, 1.0), colour);
        createVertex(vec3(-1.0, -1.0, -1.0), colour);
        createVertex(vec3(1.0, -1.0, 1.0), colour);
        createVertex(vec3(1.0, -1.0, -1.0), colour);

        EndPrimitive();
    }

<<<<<<< HEAD
}
>>>>>>> parent of 214a626... fixed rendering
=======
}
>>>>>>> parent of 0e07589... working on rendering
