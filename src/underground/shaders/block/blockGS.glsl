#version 150

layout (points) in;
layout (triangle_strip, max_vertices = 24) out;

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

    if(sides[3]) {
        // right
        faceNormal = vec3(1.0, 0.0, 0.0);
        colour = calculateLighting(faceNormal);
        createVertex(vec3(1.0, 1.0, 1.0), colour);
        createVertex(vec3(1.0, -1.0, 1.0), colour);
        createVertex(vec3(1.0, 1.0, -1.0), colour);
        createVertex(vec3(1.0, -1.0, -1.0), colour);

        EndPrimitive();
    }

    if(sides[5]) {
        // back
        faceNormal = vec3(0.0, 0.0, -1.0);
        colour = calculateLighting(faceNormal);
        createVertex(vec3(1.0, 1.0, -1.0), colour);
        createVertex(vec3(1.0, -1.0, -1.0), colour);
        createVertex(vec3(-1.0, 1.0, -1.0), colour);
        createVertex(vec3(-1.0, -1.0, -1.0), colour);

        EndPrimitive();
    }

    if(sides[2]) {
        // left
        faceNormal = vec3(-1.0, 0.0, 0.0);
        colour = calculateLighting(faceNormal);
        createVertex(vec3(-1.0, 1.0, -1.0), colour);
        createVertex(vec3(-1.0, -1.0, -1.0), colour);
        createVertex(vec3(-1.0, 1.0, 1.0), colour);
        createVertex(vec3(-1.0, -1.0, 1.0), colour);

        EndPrimitive();
    }

    if(sides[0]) {
        // top
        faceNormal = vec3(0.0, 1.0, 0.0);
        colour = calculateLighting(faceNormal);
        createVertex(vec3(1.0, 1.0, 1.0), colour);
        createVertex(vec3(1.0, 1.0, -1.0), colour);
        createVertex(vec3(-1.0, 1.0, 1.0), colour);
        createVertex(vec3(-1.0, 1.0, -1.0), colour);

        EndPrimitive();
    }

    if(sides[1]) {
        // bottom
        faceNormal = vec3(0.0, -1.0, 0.0);
        colour = calculateLighting(faceNormal);
        createVertex(vec3(-1.0, -1.0, 1.0), colour);
        createVertex(vec3(-1.0, -1.0, -1.0), colour);
        createVertex(vec3(1.0, -1.0, 1.0), colour);
        createVertex(vec3(1.0, -1.0, -1.0), colour);

        EndPrimitive();
    }

}
>>>>>>> parent of 214a626... fixed rendering
