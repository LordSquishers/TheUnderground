#version 150

in vec2 _texCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;
in float visibility;

out vec4 _color;

uniform sampler2D texSampler;

uniform float ambienceLevel;
uniform float shineDamper;
uniform float reflectivity;
uniform vec3 lightColor;
uniform vec3 skyColor;

void main(void) {

  vec3 uNormal = normalize(surfaceNormal);
  vec3 uLightVector = normalize(toLightVector);
  vec3 uCameraVector = normalize(toCameraVector);

  float nDot1 = dot(uNormal, uLightVector);
  float brightness = max(nDot1, ambienceLevel);
  vec3 diffuse = brightness * lightColor;

  vec3 lightDirection = -uLightVector;
  vec3 reflectedLightDir = reflect(lightDirection, uNormal);
  float specular = max(dot(reflectedLightDir, uCameraVector), 0.0);
  float damp = pow(specular, shineDamper);
  vec3 finalSpecular = damp * reflectivity * lightColor;

  vec4 textureColor = texture(texSampler, _texCoords);
  if(textureColor.a < 0.5) {
      discard;
  }

  _color = vec4(diffuse, 1.0) * textureColor + vec4(finalSpecular, 1.0);
  _color = mix(vec4(skyColor, 1.0), _color, visibility);

}
