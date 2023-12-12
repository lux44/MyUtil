package com.example.myutil.utils

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.util.Date

class VoiceRecorder(
    private val voiceRecorderListener: VoiceRecorderListener
) {

    private var mediaRecorder: MediaRecorder? = null
    private var currentState = RECORD_STATE_NOT_PREPARE
    private var outFilePath: String? = null
    fun initVoiceRecorder(context: Context) {
        mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }
        val dir = context.filesDir.absolutePath
        val filePath = "${dir}/Fantoo"
        val fileDir = File(filePath)
        if (!fileDir.exists()) {
            fileDir.mkdirs()
        }
        outFilePath = "${filePath}/fantooRecording_${Date().time}.m4a"
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        mediaRecorder?.setOutputFile(outFilePath)
        currentState = RECORD_STATE_PREPARE
    }

    fun startRecording() {
        Timber.d("VoiceRecord startRecording : ${currentState}")
        try {
            if (currentState == RECORD_STATE_PREPARE) {
                mediaRecorder?.prepare()
                mediaRecorder?.start()
                currentState = RECORD_STATE_RECORDING
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            voiceRecorderListener.onError(e.message ?: "")
        } catch (e: IOException) {
            e.printStackTrace()
            voiceRecorderListener.onError(e.message ?: "")
        }
    }

    fun stopRecording() {
        Timber.d("VoiceRecord[${outFilePath}] stopRecording : ${currentState}")
        if (currentState == RECORD_STATE_RECORDING || currentState == RECORD_STATE_PAUSE) {
            mediaRecorder?.stop()
            mediaRecorder?.release()
            mediaRecorder=null
            currentState = RECORD_STATE_STOP
            if (outFilePath != null) {
                voiceRecorderListener.onResult(outFilePath!!)
            }
        }
    }

    fun pauseRecording() {
        Timber.d("VoiceRecord pauseRecording : ${currentState}")
        if (currentState == RECORD_STATE_RECORDING) {
            mediaRecorder?.pause()
            currentState = RECORD_STATE_PAUSE
        }
    }

    fun resumeRecording() {
        Timber.d("VoiceRecord resumeRecording : ${currentState}")
        if (currentState == RECORD_STATE_PAUSE) {
            mediaRecorder?.resume()
            currentState = RECORD_STATE_RECORDING
        }
    }

    fun getRecorderState()=currentState

    interface VoiceRecorderListener {
        fun onResult(outputPath: String)
        fun onError(code: String)
    }

    companion object {
        const val RECORD_STATE_NOT_PREPARE = 0
        const val RECORD_STATE_PREPARE = 1
        const val RECORD_STATE_RECORDING = 2
        const val RECORD_STATE_STOP = 3
        const val RECORD_STATE_PAUSE = 4
    }
}