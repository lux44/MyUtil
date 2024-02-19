package com.example.myutil.data.remote.model.post

import com.example.myutil.data.remote.model.ClubReplyData
import com.example.myutil.data.remote.model.CommunityReplyData
import com.example.myutil.data.remote.model.OpenGraph
import com.example.myutil.data.remote.model.community.BoardPostDetailData
import com.example.myutil.data.remote.model.community.BoardPostDetailHashTag
import com.example.myutil.data.remote.model.community.ClubPostDetailData
import com.example.myutil.data.remote.model.community.CommunityNoticeData
import com.example.myutil.data.remote.model.community.DetailAttachList

sealed class DetailPostItem {

    data class CommunityPostDetail(
        val type : String,
        val item : BoardPostDetailData,
        val attachItems : List<DetailAttachList>,
        val hashTagItems : List<BoardPostDetailHashTag>,
        val openGraphItems : List<OpenGraph>?
    ) : DetailPostItem()

    data class ClubPostDetail(
        val type : String,
        var item : ClubPostDetailData,
    ) : DetailPostItem()

    data class NoticePostDetail(
        val type : String,
        val item : CommunityNoticeData,
        val attachItems : List<DetailAttachList>?,
        val openGraphItems : List<OpenGraph>?
    ) : DetailPostItem()

    data class CommunityPostComment(
        val type : String,
        val item : CommunityReplyData
    ) : DetailPostItem()

    data class ClubPostComment(
        val type : String,
        val item : ClubReplyData
    ) : DetailPostItem()

    data class PostDetailAD(
        val type : String,
    ) : DetailPostItem()

    data class PostTnkAd(
        val type : String,
    ) : DetailPostItem()

    data class PostTool(
        val type : String,
    ): DetailPostItem()
}