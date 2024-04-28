package net.modularmods.protoengine.engine.utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private final Map<String, Integer> uniformLocations;

    private final int programId;

    public Shader(String vertexFile, String fragmentFile) throws Exception {
        programId = GL20.glCreateProgram();
        if (programId == 0) {
            throw new Exception("Could not create Shader");
        }

        int vertexShaderId = createShader(vertexFile, GL20.GL_VERTEX_SHADER);
        int fragmentShaderId = createShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);

        GL20.glAttachShader(programId, vertexShaderId);
        GL20.glAttachShader(programId, fragmentShaderId);

        GL20.glLinkProgram(programId);
        if (GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking Shader code: " + GL20.glGetProgramInfoLog(programId, 1024));
        }

        GL20.glDetachShader(programId, vertexShaderId);
        GL20.glDetachShader(programId, fragmentShaderId);
        GL20.glDeleteShader(vertexShaderId);
        GL20.glDeleteShader(fragmentShaderId);

        this.uniformLocations = new HashMap<>();
    }

    private int createShader(String filename, int shaderType) throws Exception {
        int shaderId = GL20.glCreateShader(shaderType);
        if (shaderId == 0) {
            throw new Exception("Error creating shader of type " + shaderType);
        }

        GL20.glShaderSource(shaderId, readShaderFile(filename));
        GL20.glCompileShader(shaderId);

        if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling Shader code: " + GL20.glGetShaderInfoLog(shaderId, 1024));
        }

        return shaderId;
    }

    private String readShaderFile(String filename) throws Exception {
        InputStream in = getClass().getResourceAsStream(filename);
        if (in == null) {
            throw new Exception("Shader file not found: " + filename);
        }

        try (Scanner scanner = new Scanner(in, String.valueOf(StandardCharsets.UTF_8))) {
            return scanner.useDelimiter("\\A").next();
        }
    }

    public void bind() {
        GL20.glUseProgram(programId);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    private int getUniformLocation(String name) {
        if (uniformLocations.containsKey(name)) {
            return uniformLocations.get(name);
        }
        int location = glGetUniformLocation(programId, name);
        uniformLocations.put(name, location);
        return location;
    }

    public void setUniform(String name, int value) {
        int location = getUniformLocation(name);
        if (location != -1) {
            glUniform1i(location, value);
        }
    }

    public void setUniform(String name, float value) {
        int location = getUniformLocation(name);
        if (location != -1) {
            glUniform1f(location, value);
        }
    }

    public void setUniform(String name, Vector3f value) {
        int location = getUniformLocation(name);
        if (location != -1) {
            glUniform3f(location, value.x, value.y, value.z);
        }
    }

    public void setUniform(String name, Matrix4f value) {
        int location = getUniformLocation(name);
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);
        value.get(fb);
        if (location != -1) {
            glUniformMatrix4fv(location, false, fb);
        }
    }

    public void cleanup() {
        unbind();
        if (programId != 0) {
            GL20.glDeleteProgram(programId);
        }
    }
}
