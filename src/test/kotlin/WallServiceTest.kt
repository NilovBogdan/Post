import org.junit.Assert
import org.junit.Test


class WallServiceTest {

    @Test
    fun add() {
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
        val result = WallService.add(post)
        Assert.assertTrue(result.id != 0)
    }

    @Test
    fun updateFalse() {
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
        val post1 = Post(
            10,
            12,
            "Text",
            Comments(1, true, true, true),
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
            10,
            12,
            "Text",
            Comments(1, true, true, true),
            Likes(1, true),
            1,
            true,
            Geo("Type", "coordinates")
        )
        val post1 = Post(
            11,
            12,
            "Text",
            Comments(1, true, true, true),
            Likes(1, true),
            1,
            true,
            Geo("Type", "coordinates")
        )
        WallService.add(post)
        val result = WallService.update(post1)
        Assert.assertEquals(result, true)
    }
}