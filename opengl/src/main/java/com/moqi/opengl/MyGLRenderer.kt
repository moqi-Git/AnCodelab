package com.moqi.opengl

import android.graphics.PointF
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import com.moqi.opengl.shape.Shape
import com.moqi.opengl.shape.Square
import com.moqi.opengl.shape.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MyGLRenderer: GLSurfaceView.Renderer{

    private lateinit var shape: Shape

    override fun onSurfaceCreated(unused: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(1f,0f,0f,1f) // red
//        shape = Triangle(
//            PointF(0f, 0.6f),
//            PointF(-0.5f, -0.3f),
//            PointF(0.5f, -0.3f),
//        )
        shape = Square(
            PointF(-0.5f, 0.5f),
            PointF(-0.5f, -0.5f),
            PointF(0.5f, 0.5f),
            PointF(0.5f, -0.5f),
        )
    }

    override fun onSurfaceChanged(unused: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0,0,width,height)
    }

    override fun onDrawFrame(unused: GL10?) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)
        shape.draw()
    }

}