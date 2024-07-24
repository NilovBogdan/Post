import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class WallServiceTest {
    @Before
    fun clear() {
        WallService.clear()
    }


    @Test
    fun add() {
        val post = Post(
            10,
            12,
            "Text",
            Comments(1, true, true, true, 12, ""),
            Likes(1, true),
            1,
            true,
            Geo("Type", "coordinates")
        )
        val result = WallService.add(post)
        Assert.assertTrue(result.id != 0)
    }

    @Test
    fun updateFalse() {
        val post = Post(
            0,
            12,
            "Text",
            Comments(1, true, true, true, 12, ""),
            Likes(1, true),
            1,
            true,
            Geo("Type", "coordinates")
        )
        val post1 = Post(
            0,
            12,
            "Text",
            Comments(1, true, true, true, 12, ""),
            Likes(1, true),
            1,
            true,
            Geo("Type", "coordinates")
        )
        WallService.add(post)
        val result = WallService.update(post1)
        Assert.assertEquals(result, false)
    }

    @Test
    fun updateTrue() {
        val post = Post(
            0,
            12,
            "Text",
            Comments(1, true, true, true, 12, ""),
            Likes(1, true),
            1,
            true,
            Geo("Type", "coordinates")
        )
        val post1 = Post(
            1,
            12,
            "Text",
            Comments(1, true, true, true, 12, ""),
            Likes(1, true),
            1,
            true,
            Geo("Type", "coordinates")
        )
        WallService.add(post)
        val result = WallService.update(post1)
        Assert.assertEquals(result, true)
    }

    @Test
    fun createComment() {
        val comments = Comments(
            1,
            false,
            false,
            false,
            0,
            ""
        )
        val post = Post(
            1,
            12,
            "Text",
            Comments(1, true, true, true, 12, ""),
            Likes(1, true),
            1,
            true,
            Geo("Type", "coordinates")
        )
        WallService.add(post)
        val result = WallService.createComment(1, comments)
        assertEquals(result, comments)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentThrow() {
        val comments = Comments(
            1,
            false,
            false,
            false,
            0,
            ""
        )
        val post = Post(
            1,
            12,
            "Text",
            Comments(1, true, true, true, 12, ""),
            Likes(1, true),
            1,
            true,
            Geo("Type", "coordinates")
        )
        WallService.add(post)
        val result = WallService.createComment(5, comments)
        assertEquals(result, comments)
    }
}