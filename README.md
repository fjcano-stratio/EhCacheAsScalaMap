# EhCache as Scala Map
Exposing relevant EhCache features as a [scala mutable Map](http://docs.scala-lang.org/overviews/collections/maps.html) provides interesting possibilities for use & integration.
*Note* by merely switching the ehcache implementation with [BigMemory](http://terracotta.org/documentation/4.0/bigmemorymax), we can exploit the same set of
features with local offheap in-memory store as well as clustered caches.

Apart from the obvious type-safety with a Map, other obvious features include:-

### Add
```java
val m = new CacheAsMap[Int, String]
//add key+value pair using the following syntax
m(1) = "a"
//or
m += (2 -> "b")
//or since its mutable
m+(3->"c")
//or to add 2 cache maps together
val n = new CacheAsMap[Int,String]()
n(4)="d"
n++=m
```

### Remove
```java
m-=2
//or, remove more than one key at a time
n -=(1,2)
```

### Get
```java
println(m(1))
```

### Filter
```java
val m = new CacheAsMap[String, String]()
//add elements
m("apple") = "fruit"
m("potato") = "vegetable"
m("grapes") = "fruit"
//now filter

//For a key value pair, filter results on a value, for example, find all fruits
val filteredResults=m.filter { case(key,value) => value.equals("fruit") }
```


### Transform the values using a closure
```java
 val m = new CacheAsMap[Int,Int]()
    m(1)=1
    m(2)=2
    m(3)=3
    // increment all the values by 1
    m transform ( (k,v) => v+1 )
```



## Building & running samples
Use [SBT](http://www.scala-sbt.org) and run the unit tests

USE sbt assembly => generate an uber jar => java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005 -jar target/scala-2.10/my-project-assembly-1.0.jar src/main/resources/ehcache1.xml


## TODO
* Figure how to have a *BigMemory* profile within SBT along with the opensource ehcache to test and try BigMemory features
