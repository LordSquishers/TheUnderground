#version 410 core

in vec2 _texCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;
in float visibility;

out vec4 _color;

uniform sampler2D bgTex;
uniform sampler2D rTex;
uniform sampler2D gTex;
uniform sampler2D bTex;
uniform sampler2D blendMap;

uniform float ambienceLevel;
uniform vec3 lightColor;

uniform float shineDamper;
uniform float reflectivity;
uniform vec3 skyColor;

void main() {

    vec4 blendMapColor = texture(blendMap, _texCoords);
    float backTextureAmount = 1 - (blendMapColor.r + blendMap.g + blendMapColor.b);
    vec2 tiledCoords = _texCoords * 40.0;

    vec4 bgTexColor = texture(bgTex, tiledCoords) * backTextureAmount;
    vec4 rTexColor = texture(rTex, tiledCoords) * blendMapColor.r;
    vec4 gTexColor = texture(gTex, tiledCoords) * blendMapColor.g;
    vec4 bTexColor = texture(bTex, tiledCoords) * blendMapColor.b;

    vec4 totalColor = bgTexColor + rTexColor + gTexColor + bTexColor;

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

    _color = vec4(diffuse, 1.0) * totalColor + vec4(finalSpecular, 1.0);
    _color = mix(vec4(skyColor, 1.0), _color, visibility);

}
