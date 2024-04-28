#version 330 core

out vec4 FragColor;
in vec2 TexCoords; // Received from vertex shader

uniform sampler2D texture1; // Texture sampler

void main() {
    FragColor = texture(texture1, TexCoords); // Sample the texture
}