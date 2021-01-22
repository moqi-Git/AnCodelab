package com.moqi.opengl

import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val glSurfaceView = findViewById<GLSurfaceView>(R.id.gl_surface_view)
        glSurfaceView.setEGLContextClientVersion(2)
        glSurfaceView.setRenderer(MyGLRenderer())
        glSurfaceView.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }
}