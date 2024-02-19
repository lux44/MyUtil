package com.example.myutil.data.remote.model.post

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myutil.data.remote.model.OpenGraph
import com.example.myutil.data.remote.model.community.BoardPostDetailHashTag
import com.example.myutil.data.remote.model.community.DetailAttachList
import com.google.gson.annotations.SerializedName

data class BoardPostDataObj(
    @SerializedName("post")val post : List<BoardPostData>,
    @SerializedName("listSize")val listSize : Int,
    @SerializedName("nextId") val nextId :String
)

@Entity(tableName = "post_data_tbl")
data class BoardPostData(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("dbID")
    val pkID :Long=0,
    @SerializedName("postId") val postId :Int,
    @SerializedName("code") val code :String,
    @SerializedName("subCode") var subCode :String?,
    @SerializedName("title") var title :String?,
    @ColumnInfo(name = "integUid")
    @SerializedName("integUid") val integUid :String,
    @SerializedName("langCode") val langCode :String,
    @SerializedName("activeStatus") var activeStatus :Int,
    @SerializedName("content") var content : String?,
    @SerializedName("likeCnt") var likeCnt :Int?,
    @SerializedName("dislikeCnt") var dislikeCnt :Int?,
    @SerializedName("honorCnt") var honorCnt :Int?,
    @SerializedName("replyCnt") var replyCnt :Int?,
    @SerializedName("attachYn") var attachYn :Boolean?,
    @SerializedName("anonymYn") var anonymYn :Boolean?,
    @SerializedName("likeYn") var likeYn :Boolean?,
    @SerializedName("dislikeYn") var dislikeYn :Boolean?,
    @SerializedName("userNick") val userNick :String?,
    @SerializedName("userPhoto") val userPhoto :String?,
    @SerializedName("createDate") val createDate :String?,
    @SerializedName("attachList") var attachList : List<DetailAttachList>?,
    @SerializedName("hashtagList") var hashtagList : List<BoardPostDetailHashTag>?,
    @SerializedName("openGraphList") var openGraphList : List<OpenGraph>?,
    @SerializedName("userBlockYn") var userBlockYn :Boolean?,
    @SerializedName("pieceBlockYn") var pieceBlockYn :Boolean?,

    @SerializedName("categoryImage") val categoryImage :String?,
    @SerializedName("clubName") val clubName : String?,
    @SerializedName("boardName") val boardName : String?,
    @SerializedName("honorYn") var honorYn : Boolean?,
    @SerializedName("userStatus") var userStatus : Int?,
//    translate
    var translateState :Int?,
    var translateSubject : String?,
    var translateContent : String?,
    ) : PostListData()
