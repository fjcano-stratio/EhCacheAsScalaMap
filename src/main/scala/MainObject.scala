import net.sf.ehcache.CacheManager
import net.sf.ehcache.Element

/**
  * Created by fjcano on 3/16/16.
  */
object MainObject {
  def main(args: Array[String]): Unit = {
    // create CacheManager
    val cacheManager = if (args.length == 1) CacheManager.newInstance(args(0)) else CacheManager.getInstance()

    // get the cache region by the name "testCache"
    val testCacheRegion = cacheManager.getCache("viewer")


    // get the value from the cache region
    while (true) {
      // insert the value in cache
      testCacheRegion.put(new Element(s"${args(0).split('/').last}.${System.currentTimeMillis()}", "viewerValue"))
      val getValue = testCacheRegion.getKeys
      println(s"#######${getValue.size()}##########")
      getValue.toArray.foreach(println(_))
      println("#################")
      //println(" value from the cache :", getValue)
      Thread.sleep(5000)
    }

  }
}