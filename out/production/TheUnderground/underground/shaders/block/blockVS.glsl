#version 150

in vec3 position;
in vec3 color;

out vec3 baseColor;

void main(void) {
    gl_Position = vec4(position, 1.0);
    baseColor = color;
}