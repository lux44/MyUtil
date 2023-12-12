package com.example.myutil.utils

import com.arthenica.ffmpegkit.FFmpegKit
import com.arthenica.ffmpegkit.ReturnCode
import com.example.myutil.utils.ConstVariables.MinuteConst.MIX_CANCEL
import com.example.myutil.utils.ConstVariables.MinuteConst.MIX_FAIL
import com.example.myutil.utils.ConstVariables.MinuteConst.MIX_SUCCESS
import timber.log.Timber

class VideoMuxer {


    fun muxVideo(
        videoFilePath: String,
        audioUrl: String,
        cutTime: String,
        audioLatency: Long,
        outPutFilePath: String,
        mixResultListener : MixResultListener
    ): Boolean {
        Timber.d("MixData ${audioLatency} | ${cutTime} | ")
        val latencyOption = "[1:a]adelay=${audioLatency}|${audioLatency}[a1]"
        val audioMixOption = "[a1]amix=inputs=1" // 딜레이를 준 audio 파일만 넣기
//        val audioAndVideoMixOption = "[0:a][a1]amix=inputs=2" // 영상 소리 음원 둘다 믹싱

        val executeStr = "-i $videoFilePath " +
                " -i $audioUrl -ss 00:00:00.000 -t $cutTime " +         // 영상 길이만큼 자르기 [만약 오디오가 길 경우 대비]
                "-filter_complex '$latencyOption;$audioMixOption' " +   // audioLatency 만큼 딜레이주고 오디오 믹싱
                "-c:v copy ${outPutFilePath}"                           // outputFilePath 로 저장
        //test Url https://audio.fantoo.co.kr/testmusic.mp3
        FFmpegKit.executeAsync(
            executeStr
        ) { session ->
            if (ReturnCode.isSuccess(session.returnCode)) {
                Timber.tag("Muxing").d("success")
                mixResultListener.onResult(MIX_SUCCESS, null)
            } else if (ReturnCode.isCancel(session.returnCode)) {
                Timber.tag("Muxing").d("cancel")
                mixResultListener.onResult(MIX_CANCEL, null)
            } else {
                Timber.tag("Muxing").d("fail $session.returnCode messege : $session.failStackTrace")
                mixResultListener.onResult(MIX_FAIL, session.failStackTrace)
            }
        }
        return false
    }

    interface MixResultListener{
        fun onResult(code:String,message:String?)
    }
}