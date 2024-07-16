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
    val text: String,
    val comments: Comments,
    val likes: Likes,
    val isPinned: Int,
    val markedAsAds: Boolean,
    val geo: Geo
) {

}

object WallService {
    var posts = emptyArray<Post>()
    fun add(post: Post): Post {
        post.id += 1
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postId) in posts.withIndex()) {
            if (postId.id == post.id) {
                posts[index] = post
                return true
            }
        }
        return false
    }
}

fun main() {
    val post = Post(
        10,
        12,
        "Text",
        Comments(1, true, true, true),
        Likes(1, true),
        1,
        true,
        Geo("Type", "coordinates")
    )
}