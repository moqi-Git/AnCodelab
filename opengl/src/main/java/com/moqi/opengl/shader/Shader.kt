package com.moqi.opengl.shader

import android.opengl.GLES30

class Shader {

    companion object {
        @JvmField
        val DEFAULT_VERTEX_SHADER =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}"

        @JvmField
        val DEFAULT_FRAGMENT_SHADER =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}"

        @JvmStatic
        fun loadShader(type: Int, shaderCode: String): Int {
            // create a vertex shader type (GLES30.GL_VERTEX_SHADER)
            // or a fragment shader type (GLES30.GL_FRAGMENT_SHADER)
            return GLES30.glCreateShader(type).also { shader ->
                // add the source code to the shader and compile it
                GLES30.glShaderSource(shader, shaderCode)
                GLES30.glCompileShader(shader)
            }
        }
    }
}