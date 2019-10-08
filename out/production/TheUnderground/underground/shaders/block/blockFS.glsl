#version 150

out vec4 _color;

in vec3 color;

void main(void){

    _color = vec4(color, 1.0);

}