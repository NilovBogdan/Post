class NotFoundException(message: String) : RuntimeException(message)
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
    val canClose: Boolean,
    val data: Int,
    val text: String
)


data class Post(
    var id: Int,
    val date: Int,
    val text: String?,
    val comments: Comments?,
    val likes: Likes?,
    val isPinned: Int,
    val markedAsAds: Boolean,
    val geo: Geo,
)

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comments>()
    private var idCount: Int = 0
    fun clear() {
        comments = emptyArray()
        posts = emptyArray()
        idCount = 0
    }

    fun add(post: Post): Post {
        idCount += 1
        post.id = idCount
        posts += post
        return posts.last()
    }

    fun createComment(postId: Int, comment: Comments): Comments {
        for (post in posts) {
            if (post.id == postId) {
                comments += comment
                return comment
            }
        }
        return throw NotFoundException("Пост не найден")
    }

    fun update(post: Post): Boolean {
        for ((index, postId) in posts.withIndex()) {
            if (postId.id == post.id) {
                posts[index] = post.copy(
                    date = 7, text = "Drugoi text",
                    comments = Comments(2, true, false, false, 12, ""),
                    likes = Likes(2, false), isPinned = 2, markedAsAds = false
                )
                return true
            }
        }
        return false
    }
}

data class Notes(
    var noteId: Int,
    val date: Int,
    var comments: Int,
    var title: String,
    var text: String,
    var privacy: Int,
    var commentPrivacy: Int,
    val viewUrl: String,
    var noteComments: MutableList<NotesComments> = mutableListOf<NotesComments>()
)

data class NotesComments(
    var commentsId: Int,
    var message: String
)

object NoteService {
    private var notes = mutableListOf<Notes>()
    private var deletedNotes = mutableListOf<Notes>()
    private var deletedComments = mutableListOf<NotesComments>()
    private var noteIdCount = 0
    private var commentIdCount = 0
    fun clear() {
        noteIdCount = 0
        notes = mutableListOf()
        deletedNotes = mutableListOf()
        deletedComments = mutableListOf()
        commentIdCount = 0
    }

    fun add(note: Notes): Int {
        noteIdCount += 1
        note.comments = note.noteComments.size
        note.noteId = noteIdCount
        notes += note
        return note.noteId
    }

    fun createComment(noteId: Int, noteComments: NotesComments): Int {
        for (note in notes) {
            if (note.noteId == noteId) {
                commentIdCount += 1
                note.comments += 1
                note.noteComments
                noteComments.commentsId += commentIdCount
                note.noteComments += noteComments
                return noteComments.commentsId
            }

        }
        return throw NotFoundException("Заметка не найдена")
    }

    fun delete(noteId: Int): Int {
        for ((index, note) in notes.withIndex()) {
            if (noteId == note.noteId) {
                deletedNotes += note.copy()
                notes.removeAt(index)
                return 1
            }
        }
        return throw NotFoundException("Заметка не найдена")
    }

    fun deleteComment(noteId: Int, commentId: Int): Int {
        for (note in notes) {
            if (noteId == note.noteId) {
                for ((ind, noteComment) in note.noteComments.withIndex()) {
                    if (commentId == noteComment.commentsId) {
                        deletedComments += noteComment.copy()
                        note.noteComments.removeAt(ind)
                        note.comments -= 1
                        return 1
                    }
                }
            }

        }
        return throw NotFoundException("Комментарий не найден")
    }

    fun edit(noteId: Int, noteEdit: Notes): Int {
        for (note in notes) {
            if (noteId == note.noteId) {
                if (note.privacy == 0 || note.privacy == 1 || note.privacy == 2) {
                    note.title = noteEdit.title
                    note.text = noteEdit.text
                    return 1
                }
                return throw NotFoundException("Редактирование невозможно")

            }
        }
        return throw NotFoundException("Заметка не найдена")
    }

    fun editComment(noteId: Int, commentsId: Int, message: String): Int {
        for (note in notes) {
            if (noteId == note.noteId) {
                for ((ind, noteComment) in note.noteComments.withIndex()) {
                    if (commentsId == noteComment.commentsId) {
                        note.noteComments[ind].message = message
                        return 1
                    }
                }
                return throw NotFoundException("Комментарий не найден")
            }
        }
        return throw NotFoundException("Заметка не найдена")
    }

    fun get(): MutableList<Notes> {
        return notes
    }

    fun getById(noteId: Int): Notes {
        for (note in notes) {
            if (noteId == note.noteId) {
                return note
            }
        }
        return throw NotFoundException("Заметка не найдена")
    }

    fun getComments(noteId: Int): MutableList<NotesComments> {
        for (note in notes) {
            if (noteId == note.noteId) {
                return note.noteComments
            }
        }
        return throw NotFoundException("Заметка не найдена")
    }

    fun restoreComment(noteId: Int, commentId: Int): Int {
        for (note in notes) {
            if (noteId == note.noteId) {
                for ((index, delete) in deletedComments.withIndex()) {
                    if (commentId == deletedComments[index].commentsId) {
                        note.noteComments += deletedComments[index]
                        note.comments += 1
                        deletedComments.removeAt(index)
                        return 1
                    }
                }
            }
        }
        return throw NotFoundException("Комментарий не найден")
    }
}

fun main() {
}

