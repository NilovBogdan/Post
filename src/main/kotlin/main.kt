interface Attachment {
    val type: String
}

data class AudioAttachment(val audio: Audio) : Attachment {
    override val type: String = "Audio"
}

data class Audio(
    val id: Int = 0,
    val artist: String = "",
    val title: String = "",
    val duration: Int = 0,
    val url: String = "",
    val date: Int = 0
)

data class VideoAttachment(val video: Video) : Attachment {
    override val type: String = "Video"
}

data class Video(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val description: String = "",
    val duration: Int = 0,
    val image: String = "",
    val date: Int = 0,
    val views: Int = 0,
    val comments: Int = 0,
    val likes: Int = 0,
    val repost: Int = 0
)

data class PhotoAttachment(val photo: Photo) : Attachment {
    override val type: String = "Photo"
}

data class Photo(
    val id: Int = 0,
    val albumId: Int = 0,
    val userId: Int = 0,
    val text: String = "",
    val date: Int = 0,
    val width: Int = 0,
    val height: Int = 0
)

data class FileAttachment(val file: File) : Attachment {
    override val type: String = "File"
}

data class File(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val size: Int = 0,
    val url: String = "",
    val date: Int = 0
)


data class HistoryAttachment(val history: History) : Attachment {
    override val type: String = "History"
}

data class History(
    val id: Int = 0,
    val ownerId: Int = 0,
    val date: Int = 0,
    val expiresAt: Int = 0,
    val countSee: Int = 0
)

data class AttachedLinkAttachment(val attachedLink: AttachedLink) : Attachment {
    override val type: String = "Attached link"
}

data class AttachedLink(
    val url: String = "",
    val title: String = "",
    val caption: String = "",
    val previewUrl: String = ""
)

data class Likes(
    val count: Int,
    val userLikes: Boolean
)

data class Geo(
    val type: String,
    val coordinates: String
)

data class Comments(
    val count: Int,
    val canPost: Boolean,
    val groupsCanPost: Boolean,
    val canClose: Boolean
)

data class Post(
    var id: Int,
    val date: Int,
    val text: String?,
    val comments: Comments?,
    val likes: Likes?,
    val isPinned: Int,
    val markedAsAds: Boolean,
    val geo: Geo
) {

}

object WallService {
    var posts = emptyArray<Post>()
    var idCount: Int = 0
    fun clear() {
        posts = emptyArray()
        idCount = 0
    }

    fun add(post: Post): Post {
        idCount += 1
        post.id = idCount
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postId) in posts.withIndex()) {
            if (postId.id == post.id) {
                posts[index] = post.copy(
                    date = 7, text = "Drugoi text",
                    comments = Comments(2, true, false, false),
                    likes = Likes(2, false), isPinned = 2, markedAsAds = false
                )
                return true
            }
        }
        return false
    }
}

fun main() {
    val post = Post(
        0,
        12,
        "Text",
        Comments(1, true, true, true),
        Likes(1, true),
        1,
        true,
        Geo("Type", "coordinates")
    )
}