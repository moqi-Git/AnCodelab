package com.moqi.bitmaplab

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.moqi.bitmaplab.databinding.ActivityPictureBinding
import com.moqi.bitmaplab.utils.loadBitmapFromAssets
import com.moqi.bitmaplab.utils.loadBitmapFromDrawable
import com.moqi.bitmaplab.utils.logInfo

class PictureActivity : AppCompatActivity() {

    private lateinit var vb: ActivityPictureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        vb = ActivityPictureBinding.inflate(layoutInflater)
        setContentView(vb.root)

        val ivSize = intent.getStringExtra("ivSize")?:"0x0"
        val ivScaleType = intent.getIntExtra("ivScaleType", ImageView.ScaleType.FIT_CENTER.ordinal)
        setupImageView(ivSize, ivScaleType)

        val type = intent.getIntExtra("type", -1)
        val assetName = intent.getStringExtra("name")?:""
        val drawableId = intent.getIntExtra("resId", 0)
        val filePath = intent.getStringExtra("file")?:""
        val url = intent.getStringExtra("url")?:""

        var bitmap: Bitmap? = null
        when(type){
            1 -> {
                bitmap = loadBitmapFromAssets(this, assetName)
            }
            2 -> {
//                bitmap = loadBitmapFromDrawable(this, drawableId)
                vb.pictureIvBackground.setBackgroundResource(drawableId)
                val d = vb.pictureIvBackground.background
                if (d is BitmapDrawable){
                    bitmap = d.bitmap
                }
            }
            3 -> {
                Glide.with(this)
                        .load("file:///android_asset/$assetName")
                        .addListener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                return true
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                if (resource is BitmapDrawable){
                                    Log.e("asdfg", "onResourceReady bitmap")
                                    updateInfo(resource.bitmap)
                                }
                                return true
                            }
                        })
                        .into(vb.pictureIvBackground)
            }
            4 -> {
                Glide.with(this)
                        .load(drawableId)
                        .addListener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                return true
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                if (resource is BitmapDrawable){
                                    Log.e("asdfg", "onResourceReady bitmap")
                                    updateInfo(resource.bitmap)
                                }
                                return true
                            }
                        })
                        .into(vb.pictureIvBackground)
            }
        }

        if (bitmap != null){
            updateInfo(bitmap)
        }

        vb.pictureIvBackground.setOnClickListener {
            if(vb.pictureLayoutInfo.visibility != View.VISIBLE){
                vb.pictureLayoutInfo.visibility = View.VISIBLE
            } else {
                vb.pictureLayoutInfo.visibility = View.GONE
            }
        }
    }

    private fun updateInfo(bitmap: Bitmap){
        vb.pictureIvBackground.setImageBitmap(bitmap)
        vb.pictureIvBitmapInfo.text = bitmap.logInfo()
        vb.pictureIvBackground.post {
            vb.pictureIvInfo.text = vb.pictureIvBackground.logInfo()
        }
    }

    private fun setupImageView(ivSize: String, ivScaleType: Int){

        val size = ivSize.split('x')
        var w = 0
        var h = 0
        if (size.size == 2){
            w = size[0].toIntOrNull()?:0
            h = size[1].toIntOrNull()?:0
        }

        val sct = when(ivScaleType){
            ImageView.ScaleType.FIT_CENTER.ordinal -> {
                ImageView.ScaleType.FIT_CENTER
            }
            ImageView.ScaleType.FIT_END.ordinal -> {
                ImageView.ScaleType.FIT_END
            }
            ImageView.ScaleType.FIT_START.ordinal -> {
                ImageView.ScaleType.FIT_START
            }
            ImageView.ScaleType.FIT_XY.ordinal -> {
                ImageView.ScaleType.FIT_XY
            }
            ImageView.ScaleType.CENTER.ordinal -> {
                ImageView.ScaleType.CENTER
            }
            ImageView.ScaleType.CENTER_CROP.ordinal -> {
                ImageView.ScaleType.CENTER_CROP
            }
            ImageView.ScaleType.CENTER_INSIDE.ordinal -> {
                ImageView.ScaleType.CENTER_INSIDE
            }
            else -> {
                ImageView.ScaleType.FIT_CENTER
            }
        }

        if (w != 0 && h != 0){
            val params = vb.pictureIvBackground.layoutParams?:ViewGroup.LayoutParams(w, h)
            params.width = w
            params.height = h
            vb.pictureIvBackground.layoutParams = params
        }
        vb.pictureIvBackground.scaleType = sct
    }

    private fun ImageView.logInfo(): String{
        return "size=($width,$height);\nscaleType=${this.scaleType.name};"
    }
}