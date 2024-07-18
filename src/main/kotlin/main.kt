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
                posts[index] = post.copy(date = 7, text = "Drugoi text",
                    comments = Comments(2, true, false, false),
                    likes = Likes(2, false), isPinned = 2, markedAsAds = false)
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
