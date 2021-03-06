package com.moqi.opengl.texture

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLES30
import android.opengl.GLUtils
import com.moqi.opengl.App
import java.nio.IntBuffer

class PictureTexture private constructor(private val bitmap: Bitmap) {

    private fun activateTexture(textureHandle: Int) {
        //激活指定纹理单元
        val mTextureBuffer = IntBuffer.allocate(1)
        GLES30.glGenBuffers(1, mTextureBuffer)
        val mTextureId = mTextureBuffer.get()

        GLES30.glActiveTexture(GLES30.GL_TEXTURE0)
        //绑定纹理ID到纹理单元
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, mTextureId)
        //将激活的纹理单元传递到着色器里面
        GLES30.glUniform1i(textureHandle, 0)

        //配置边缘过渡参数
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR.toFloat())
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR.toFloat())
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_S, GLES30.GL_CLAMP_TO_EDGE)
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_T, GLES30.GL_CLAMP_TO_EDGE)

        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, bitmap, 0)
    }

    companion object{
        @JvmStatic
        fun createFormAsset(assetName: String): PictureTexture{
            val inputStream = App.requireContext().assets.open(assetName)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            return PictureTexture(bitmap)
        }
    }
}