package com.moqi.opengl.shape

import android.opengl.GLES30
import com.moqi.opengl.shader.Shader

class Picture : Shape {

    private val picVertexShader =
        "attribute vec4 aPosition;" +
                "attribute vec2 aCoordinate;" +
                "varying vec2 vCoordinate;" +
                "void main() {" +
                "  gl_Position = aPosition;" +
                "  vCoordinate = aCoordinate;" +
                "}"
    private val picFragmentShader =
        "precision mediump float;" +
                "uniform sampler2D uTexture;" +
                "varying vec2 vCoordinate;" +
                "void main() {" +
                "  vec4 color = texture2D(uTexture, vCoordinate);" +
                "  gl_FragColor = color;" +
                "}"

    private var mProgram: Int
    private var mPositionHandle: Int = 0
    private var mTexturePosHandle: Int = 0
    private var mTextureHandle = 0

    init {
        val vertexShader = Shader.loadShader(GLES30.GL_VERTEX_SHADER, picVertexShader)
        val fragmentShader = Shader.loadShader(GLES30.GL_FRAGMENT_SHADER, picFragmentShader)

        mProgram = GLES30.glCreateProgram().also {
            GLES30.glAttachShader(it, vertexShader)
            GLES30.glAttachShader(it, fragmentShader)
            GLES30.glLinkProgram(it)

            mPositionHandle = GLES30.glGetAttribLocation(it, "aPosition")
            mTexturePosHandle = GLES30.glGetAttribLocation(it, "aCoordinate")
            mTextureHandle = GLES30.glGetUniformLocation(it, "uTexture")
        }
        GLES30.glUseProgram(mProgram)
    }


    override fun draw() {



        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 4)
    }
}