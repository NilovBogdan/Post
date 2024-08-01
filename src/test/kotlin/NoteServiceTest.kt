import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import kotlin.jvm.Throws

class NoteServiceTest {

    @Before
    fun clear() {
        NoteService.clear()
    }

    @Test
    fun add() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val result = NoteService.add(note)
        Assert.assertEquals(result, 1)
    }

    @Test
    fun createComment() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val newNoteComments = NotesComments(0, "Новый комментарий")
        NoteService.add(note)
        val result = NoteService.createComment(1, newNoteComments)
        Assert.assertEquals(result, 1)
    }

    @Test
    fun createCommentThrow() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val newNoteComments = NotesComments(0, "Новый комментарий")
        NoteService.add(note)
        val result = Assert.assertThrows(NotFoundException::class.java) {
            NoteService.createComment(2, newNoteComments)
        }

        Assert.assertEquals("Заметка не найдена", result.message)
    }

    @Test
    fun delete() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        NoteService.add(note)
        val result = NoteService.delete(1)
        Assert.assertEquals(result, 1)
    }

    @Test
    fun deleteThrow() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        NoteService.add(note)
        val result = Assert.assertThrows(NotFoundException::class.java) {
            NoteService.delete(2)
        }
        Assert.assertEquals("Заметка не найдена", result.message)
    }


    @Test
    fun deleteComment() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val newNoteComments = NotesComments(0, "Новый комментарий")
        NoteService.add(note)
        NoteService.createComment(1, newNoteComments)
        val result = NoteService.deleteComment(1, 1)
        Assert.assertEquals(result, 1)
    }

    @Test
    fun deleteCommentThrow() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val newNoteComments = NotesComments(0, "Новый комментарий")
        NoteService.add(note)
        NoteService.createComment(1, newNoteComments)
        val result = Assert.assertThrows(NotFoundException::class.java) {
            NoteService.deleteComment(1, 2)
        }
        Assert.assertEquals("Комментарий не найден", result.message)
    }

    @Test
    fun edit() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val note2 = Notes(
            1,
            10,
            0,
            "ffgfg",
            "reetert",
            0,
            0,
            ""
        )
        NoteService.add(note)
        val result = NoteService.edit(1, note2)
        Assert.assertEquals(result, 1)
    }

    @Test
    fun editThrowPrivacy() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            3,
            0,
            ""
        )
        val note2 = Notes(
            1,
            10,
            0,
            "ffgfg",
            "reetert",
            3,
            0,
            ""
        )
        NoteService.add(note)
        val result = Assert.assertThrows(NotFoundException::class.java) {
            NoteService.edit(1, note2)
        }
        Assert.assertEquals("Редактирование невозможно", result.message)
    }

    @Test
    fun editThrow() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val note2 = Notes(
            1,
            10,
            0,
            "ffgfg",
            "reetert",
            0,
            0,
            ""
        )
        NoteService.add(note)
        val result = Assert.assertThrows(NotFoundException::class.java) {
            NoteService.edit(2, note2)
        }
        Assert.assertEquals("Заметка не найдена", result.message)
    }

    @Test
    fun editComment() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val newNoteComments = NotesComments(0, "Новый комментарий")
        NoteService.add(note)
        NoteService.createComment(1, newNoteComments)
        val result = NoteService.editComment(1, 1, "New text")
        Assert.assertEquals(result, 1)
    }

    @Test
    fun editCommentThrowComment() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val newNoteComments = NotesComments(0, "Новый комментарий")
        NoteService.add(note)
        NoteService.createComment(1, newNoteComments)
        val result = Assert.assertThrows(NotFoundException::class.java) {
            NoteService.editComment(1, 2, "New text")
        }
        Assert.assertEquals("Комментарий не найден", result.message)
    }

    @Test
    fun editCommentThrow() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val newNoteComments = NotesComments(0, "Новый комментарий")
        NoteService.add(note)
        NoteService.createComment(1, newNoteComments)
        val result = Assert.assertThrows(NotFoundException::class.java) {
            NoteService.editComment(2, 1, "New text")
        }
        Assert.assertEquals("Заметка не найдена", result.message)
    }

    @Test
    fun get() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        NoteService.add(note)
        val result = NoteService.get()
        val list = mutableListOf<Notes>() + note
        Assert.assertEquals(result, list)
    }

    @Test
    fun getById() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        NoteService.add(note)
        val result = NoteService.getById(1)
        Assert.assertEquals(result, note)
    }

    @Test
    fun getComments() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        NoteService.add(note)
        val newNoteComments = NotesComments(0, "Новый комментарий")
        NoteService.createComment(1, newNoteComments)
        val result = NoteService.getComments(1)
        val list = mutableListOf<NotesComments>() + newNoteComments
        Assert.assertEquals(result, list)
    }

    @Test
    fun restoreComment() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val newNoteComments = NotesComments(0, "Новый комментарий")
        NoteService.add(note)
        NoteService.createComment(1, newNoteComments)
        NoteService.deleteComment(1, 1)
        val result = NoteService.restoreComment(1, 1)
        Assert.assertEquals(result, 1)
    }

    @Test
    fun restoreCommentThrow() {
        val note = Notes(
            1,
            12,
            0,
            "",
            "",
            0,
            0,
            ""
        )
        val newNoteComments = NotesComments(0, "Новый комментарий")
        NoteService.add(note)
        NoteService.createComment(1, newNoteComments)
        NoteService.deleteComment(1, 1)
        val result = Assert.assertThrows(NotFoundException::class.java) {
            NoteService.restoreComment(1, 2)
        }
        Assert.assertEquals("Комментарий не найден", result.message)
    }
}