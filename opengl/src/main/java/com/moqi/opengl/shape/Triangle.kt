package com.moqi.opengl.shape

import android.graphics.PointF
import android.opengl.GLES30
import com.moqi.opengl.shader.Shader
import java.nio.ByteBuffer
import java.nio.ByteOrder

class Triangle(
    private val vertexA: PointF,
    private val vertexB: PointF,
    private val vertexC: PointF,
    var color: FloatArray = floatArrayOf(0f, 1f, 1f, 1f)
): Shape {

    private var mProgram: Int
    private var positionHandle: Int = 0
    private var mColorHandle: Int = 0

    private val COORDS_PER_VERTEX = 3
    private val triangleCoords = floatArrayOf(
        vertexA.x, vertexA.y, 0f,
        vertexB.x, vertexB.y, 0f,
        vertexC.x, vertexC.y, 0f,
    )
    private val vertexCount: Int = triangleCoords.size / COORDS_PER_VERTEX // triangle
    private val vertexStride: Int = vertexCount * 4
    private val vertexBuffer = ByteBuffer.allocateDirect(triangleCoords.size * 4).run {
        order(ByteOrder.nativeOrder())
        asFloatBuffer().apply {
            put(triangleCoords)
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
            // Prepare the triangle coordinate data
            GLES30.glVertexAttribPointer(
                it,
                COORDS_PER_VERTEX,
                GLES30.GL_FLOAT,
                false,
                vertexStride,
                vertexBuffer
            )

            // get handle to fragment shader's vColor member
            mColorHandle = GLES30.glGetUniformLocation(mProgram, "vColor").also { colorHandle ->
                // Set color for drawing the triangle
                GLES30.glUniform4fv(colorHandle, 1, color, 0)
            }
            // Draw the triangle
            GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vertexCount)
            // Disable vertex array
            GLES30.glDisableVertexAttribArray(it)
        }
    }
}