#version 330 core

layout (location = 0) in vec3 aPos; // Vertex position
layout (location = 1) in vec2 aTex; // Texture coordinates

out vec2 TexCoords; // Pass texture coordinates to fragment shader

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

void main() {
    TexCoords = aTex;
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(aPos, 1.0);
}
