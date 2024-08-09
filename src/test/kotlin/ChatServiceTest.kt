import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ChatServiceTest {
    @Before
    fun clear() {
        ChatService.clear()
    }

    @Test
    fun addNew() {
        val result = ChatService.add(1, "Hello World")
        Assert.assertEquals(result, 2)
    }

    @Test
    fun add() {
        ChatService.add(1, "message")
        val result = ChatService.add(1, "new message")
        Assert.assertEquals(result, 1)
    }

    @Test
    fun getChats() {
        val mes = mutableListOf("Message")
        val chats: MutableList<Chats> = mutableListOf(Chats(1, 1, mes))
        ChatService.add(1, "Message")
        val result = ChatService.getChats()
        Assert.assertEquals(result, chats)
    }

    @Test
    fun lastMessages() {
        val lastMessage = listOf("Message")
        ChatService.add(1, "Message")
        val result = ChatService.lastMessages()
        Assert.assertEquals(result, lastMessage)
    }

    @Test
    fun getById() {
        val mes = listOf("Message")
        ChatService.add(1, "Message")
        val result = ChatService.getById(1)
        Assert.assertEquals(result, mes)
    }

    @Test
    fun getByIdThrow() {
        ChatService.add(1, "Message")
        val result = Assert.assertThrows(NotFoundException::class.java) {
            ChatService.getById(2)
        }
        Assert.assertEquals("Чат не найден", result.message)
    }

    @Test
    fun getByQuantityOfMessage() {
        val mes = mutableListOf("Message")
        ChatService.add(1, "Message")
        val result = ChatService.getByQuantityOfMessage(1)
        Assert.assertEquals(result, mes)
    }

    @Test
    fun getByQuantityOfMessageThrow() {
        ChatService.add(1, "Message")
        val result = Assert.assertThrows(NotFoundException::class.java) {
            ChatService.getByQuantityOfMessage(2)
        }
        Assert.assertEquals("Чат не найден", result.message)
    }

    @Test
    fun deleteMessage() {
        ChatService.add(1, "Message")
        val result = ChatService.deleteMessage(1, "Message")
        Assert.assertEquals(result, 1)
    }

    @Test
    fun deleteMessageThrow() {
        ChatService.add(1, "Message")
        val result = Assert.assertThrows(NotFoundException::class.java) {
            ChatService.deleteMessage(2, "Message")
        }
        Assert.assertEquals("Чат не найден", result.message)
    }

    @Test
    fun deleteChat() {
        ChatService.add(1, "Message")
        val result = ChatService.deleteChat(1)
        Assert.assertEquals(result, 1)
    }

    @Test
    fun deleteChatThrow() {
        ChatService.add(1, "Message")
        val result = Assert.assertThrows(NotFoundException::class.java) {
            ChatService.deleteChat(2)
        }
        Assert.assertEquals("Чат не найден", result.message)
    }

    @Test
    fun getUnreadChat() {
        ChatService.add(1, "Message")
        val result = ChatService.getUnreadChat()
        Assert.assertEquals(result, 1)
    }
}
