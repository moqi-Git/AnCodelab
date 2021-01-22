package com.moqi.opengl.shape

import android.graphics.PointF
import android.opengl.GLES30
import com.moqi.opengl.shader.Shader
import java.nio.ByteBuffer
import java.nio.ByteOrder

class Square(
    private val vertexA: PointF,
    private val vertexB: PointF,
    private val vertexC: PointF,
    private val vertexD: PointF,
    var color: FloatArray = floatArrayOf(0f, 1f, 1f, 1f)
): Shape {

    private var mProgram: Int
    private var positionHandle: Int = 0
    private var mColorHandle: Int = 0

    private val COORDS_PER_VERTEX = 3
    private val squareCoords = floatArrayOf(
        vertexA.x, vertexA.y, 0f,
        vertexB.x, vertexB.y, 0f,
        vertexC.x, vertexC.y, 0f,
        vertexD.x, vertexD.y, 0f,
    )
    private val vertexCount: Int = squareCoords.size / COORDS_PER_VERTEX // square
    private val vertexStride: Int = COORDS_PER_VERTEX * 4
    private val vertexBuffer = ByteBuffer.allocateDirect(squareCoords.size * 4).run {
        order(ByteOrder.nativeOrder())
        asFloatBuffer().apply {
            put(squareCoords)
            position(0)
        }
    }

    private val drawOrder = shortArrayOf(0, 1, 2, 0, 2, 3)
    private val drawBuffer = ByteBuffer.allocateDirect(drawOrder.size * 2).run {
        order(ByteOrder.nativeOrder())
        asShortBuffer().apply {
            put(drawOrder)
            position(0)
        }
    }

    init {
        val vertexShader: Int = Shader.loadShader(GLES30.GL_VERTEX_SHADER, Shader.DEFAULT_VERTEX_SHADER)
        val fragmentShader: Int = Shader.loadShader(GLES30.GL_FRAGMENT_SHADER, Shader.DEFAULT_FRAGMENT_SHADER)

        mProgram = GLES30.glCreateProgram().also {
            GLES30.glAttachShader(it, vertexShader)
            GLES30.glAttachShader(it, fragmentShader)
            GLES30.glLinkProgram(it)
        }
    }

    override fun draw() {
        // Add program to OpenGL ES environment
        GLES30.glUseProgram(mProgram)
        // get handle to vertex shader's vPosition member
        positionHandle = GLES30.glGetAttribLocation(mProgram, "vPosition").also {
            // Enable a handle to the triangle vertices
            GLES30.glEnableVertexAttribArray(it)
            GLES30.glVertexAttribPointer(
                it,
                COORDS_PER_VERTEX,
                GLES30.GL_FLOAT,
                false,
                vertexStride,
                vertexBuffer
            )

            mColorHandle = GLES30.glGetUniformLocation(mProgram, "vColor").also { colorHandle ->
                GLES30.glUniform4fv(colorHandle, 1, color, 0)
            }

//            GLES30.glDrawElements(GLES30.GL_TRIANGLES, drawOrder.size, GLES30.GL_UNSIGNED_SHORT, drawBuffer)
            GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, vertexCount)
            // Disable vertex array
            GLES30.glDisableVertexAttribArray(it)
        }
    }
}