# Android-KotlinTest

## Kotlin 用途

- 用于服务器端开发
- 用于 Android 开发
- 用于 Javascript 开发
- 用于原生开发
- 用于数据科学

## Kotlin 特点

- 性能：由于非常相似的字节码结构，Kotlin 应用程序的运行速度与 Java 类似。随着 Kotlin 对内联函数的支持，使用 lambda 表达式的代码通常比用 Java 写的代码运行得更快。
- 互操作性：Kotlin 可与 Java 进行 100％ 的互操作


## 单例模式

- 饿汉模式

```kotlin
object Singleton {}
```

- 懒汉模式

```kotlin
class Singleton private constructor() {
    companion object {
        private var instance: Singleton? = null
            get() {
                if (field == null) {
                    field = Singleton()
                }
                return field
            }

        fun get(): Singleton {
            return instance!!
        }
    }
}
```

- 线程安全懒汉模式

```kotlin
class Singleton private constructor() {
    companion object {
        private var instance: Singleton? = null
            get() {
                if (field == null) {
                    field = Singleton()
                }
                return field
            }

        @Synchronized
        fun get(): Singleton {
            return instance!!
        }
    }
}
```

- 双重校验锁模式

```kotlin
class Singleton private constructor() {
    companion object {
        val instance: Singleton by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Singleton()
        }
    }
}
```

- 静态内部类模式

```kotlin
class Singleton private constructor() {
    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = Singleton()
    }
}
```

- 枚举模式

```kotlin
enum class Singleton {
    INSTANCE
}
```
## 对象（object）

Kotlin 中的对象指的是使用 object 关键字定义的 类型声明，一般用作单例模式和伴生对象。

```kotlin
object Test {
    val name = "Haha"
    
    fun say() {
        println("Hello")
    }
}
```

这样，我们就能得到一个单例类。

## 伴生对象（companion object）

Kotlin 没有静态成员。静态成员在 Java 中有很大的作用，因为 Java 没有全局变量，也不存在包级函数，一切属性和方法都是在类里面，所以在写一些工具函数和全局变量时，都需要用到 static 关键字修饰的静态成员。

Kotlin 之所以能抛弃静态成员，主要原因在于它允许包级属性和函数的存在，而且 Kotlin 为了维持与 Java 完全的兼容性，为静态成员提供了多种替代方案：

- 使用包级属性和包级函数：主要用于 全局常量 和 工具函数 
- 使用伴生对象：主要用于与类有紧密联系的变量和函数
- 使用 @JvmStatic 注解：与伴生对象搭配使用，将变量和函数声明为真正的 JVM 静态成员


```kotlin
class Test{
    companion object { // 伴生对象
        const val STATIC = "常量"
    }
}
```

Kotlin 允许在类中使用 companion object 创建伴生对象，用伴生对象的成员来代替静态成员。使用伴生对象实际上是在这个类内部创建了一个名为 Companion 的静态单例内部类。伴生对象中定义的属性会直接编译为外部类的静态字段，而函数会被编译为伴生对象的方法。

### @JvmStatic 注解

@JvmStatic 注解只能用在伴生对象里，修饰伴生对象内的属性和函数，用来告诉编译器将属性和函数编译为真正的 JVM 静态成员。需要注意到，如果在伴生对象声明里使用 @JvmStatic 注解，那么没有加该注解的属性和函数将不会被编译为静态成员：

```kotlin
class Person(val name: String) {
  companion object {
    @JvmStatic 
    val anonymous = Person("Anonymous")
    fun say() = println("Hello")
  }
}
```

## lateinit var 和 by lazy

Kotlin 中有两种延迟初始化的方式。一种是 lateinit var，一种是 by lazy。

### lateinit var

```kotlin
lateinit var name: String
```

lateinit var 只能用来修饰类属性，不能用来修饰局部变量，并且只能用来修饰对象，不能用来修饰基本类型（因为基本类型的属性在类加载后的准备阶段都会被初始化为默认值）。

lateinit var 的作用也比较简单，就是让编译期在检查时不要因为属性变量未被初始化而报错。后续在哪里以及何时初始化还需要开发者自己决定。

### by lazy

by lazy 本身是一种属性委托。属性委托的关键字是by。

```kotlin
val name: Int by lazy { 1 }

public fun test(){
    val bar by lazy { "hello" }
    println(bar)
}
```

以 name 属性为代表来讲解 by lazy 的原理，局部变量的初始化也是一样的原理。

by lazy 要求属性声明为 val，即不可变变量，在 Java 中相当于被 final 修饰。

这意味着该变量一旦初始化后就不允许再被修改值了（基本类型是值不能被修改，对象类型是引用不能被修改）。{}内的操作就是返回唯一一次初始化的结果。

by lazy 可以使用于类属性或者局部变量。

## 可见性修饰符

- private 意味着只在这个类内部（包含其所有成员）可见；
- protected—— 和 private一样 + 在子类中可见。
- internal —— 能见到类声明的 本模块内 的任何客户端都可见其 internal 成员；
- public —— 能见到类声明的任何客户端都可见其 public 成员。

## 扩展

扩展并不是真正的修改了被扩展类。而只是在 Kotlin 中的调用像是修改了被扩展类。

```kotlin
// 为 String 扩展一个 size() 函数
fun String.size(): Int {
    return length
}
```

## 数据类（data）

```kotlin
data class User(val name: String, val age: Int)
```

当我们声明一个数据类时，编辑器自动为这个类做了一些事情。

- 生成 equals() 函数与 hasCode() 函数
- 生成 toString() 函数，由类名（参数1 = 值1，参数2 = 值2，....）构成
- 由所定义的属性自动生成 component1()、component2()、...、componentN() 函数，其对应于属性的声明顺序。
- copy() 函数

## 密封类（sealed）

密封类用来表示受限的类继承结构：当一个值为有限几种的类型、而不能有任何其他类型时。在某种意义上，他们是枚举类的扩展：枚举类型的值集合也是受限的，但每个枚举常量只存在一个实例，而密封类的一个子类可以有可包含状态的多个实例。

一个密封类是自身抽象的，它不能直接实例化并可以有抽象（abstract）成员。

密封类不允许有非-private 构造函数（其构造函数默认为 private）。

```kotlin
sealed class Test()
```

密封类是不能被实例化的。

```kotlin
sealed class Test {
    data class Person(val name: String): Test()
    object a: Test() // 单例模式
}
```

我们知道普通的继承类使用 open 关键字定义，在项目中的类都可继承至该类。

而密封类的子类必须是在密封类的内部或必须存在于密封类的同一文件。这一点就可以有效的代码保护。

```kotlin
// 使用
val test = Test.Person("haha")
```

## 类型别名（typealias）

类型别名为现有类型提供替代名称，类型别名不会引入新类型 它们等效于相应的底层类型。

```kotlin
typealias ArrayList<E> = java.util.ArrayList<E>
```

## 内联函数

在程序编译时能将程序中内联函数的调用表达式直接替换成内联函数的函数体。

内联函数可以消除函数调用的开销。

```kotlin
inline fun test(a: Int, b: Int): Int {
    println("a: $a, b: $b")
    return a + b
}
```

## 内联类（inline class）

内联类可以消除创建对象的开销。

```kotlin
inline class Test(val value:String) {

}
```

内联类必须有一个主构造函数，并且在主构造函数里必须有且只有一个 val 属性，除此之外，不能再拥有其他的字段。

## 委托（by）

在委托模式中，当有两个对象参与处理同一个请求是，接受请求的对象将请求委托给另一个对象来处理。

委托模式已证明是实现继承的一个很好的替代方式。

Kotlin中委托分为类委托和委托属性，Kotlin官方库也封装了一些常用的委托。

### 类委托

类 Derived 可以继承一个接口 Base，并将其所有共有的方法委托给一个指定的对象，也就是说把类 Derived 因继承而需要实现的方法委托给一个对象，从而不需要在该类内显式的实现：

```kotlin
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base) : Base by b

fun main() {
    val b = BaseImpl(10)
    Derived(b).print()
}
```

如果 Derived 类没有写 by b，那么就必须重写 print 方法，否则编译器会报错。

### 属性委托

属性的委托指的是一个类中的某个属性的值不是在类中直接进行定义，而是由某个类的方法来进行 setter 和 getter。默认属性委托都是线程安全的。属性委托适合那些属性的需要复杂的计算但是计算过程可以被重用的场合。

> 语法：val/var <属性名>: <类型> by <表达式>

- by lazy

```kotlin
val name: Int by lazy { 1 }
```

- by Delegates.observable()

```kotlin
var a: Int by Delegates.observable(1) { 
    property, oldValue, newValue ->

}
```

- by Delegates.nonNull()

```kotlin
var a: Int by Delegates.notNull()
```

## 操作符重载（operator）

将一个函数标记为重载一个操作符，也就是操作符重载。

```kotlin
class Data(var value: String) {

    operator fun plus(other: Data): Data {
        this.value = this.value + "_**_" + other.value
        return this
    }
}

fun main() {
    val str = Data("haha") + Data("ooo")
    println(str.value) // haha_**_ooo
}
```

## lambda 表达式

### Lambda 表达式的特点

- Lambda 表达式总是被大括号括着
- 参数（如果存在）在 -> 之前声明（参数类型可以省略）
- 函数体（如果存在）在 -> 后面

```kotlin
// 无参数
var test = { }

// 有参数 in -> out
var test2 = { name: String ->
    println(name)
    it + "aaa" // 不能直接使用 return，最后一个表达式为返回值
}

// 作为参数
fun test3(a: Int, b: (num: Int) -> Int): Int {
    return a + b.invoke(2)
}
```

## 闭包

所谓闭包，即是函数中包含函数，这里的函数我们可以包含（Lambda表达式，匿名函数，局部函数，对象表达式）。

看一段 Java 代码：

```java
public class TestJava{

    private void test() {
        private void test() { // 错误，因为Java中不支持函数包含函数
        }
    }

    private void test1(){} // 正确，Java中的函数只能包含在对象中+
}
```

看一段 Kotlin 代码：

```kotlin
fun test1(){
    fun test2() { // 正确，因为 Kotlin 中可以函数嵌套函数
    }
}
```

- 携带状态

让函数返回一个函数，并携带状态值。

```kotlin
fun test(b: Int): () -> Int {
    var a = 3
    return fun(): Int {
        a++
        return a + b
    }
}
```

- 引用外部变量，并改变外部变量的值

```kotlin
var sum : Int = 0
val arr = arrayOf(1,3,5,7,9)
arr.filter { it < 7  }.forEach { sum += it }
```

广义上来说，在 Kotlin 语言之中，函数、条件语句、控制流语句、花括号逻辑块、Lambda 表达式都可以称之为闭包，但通常情况下，我们所指的闭包都是在说 Lambda 表达式。


## 解构声明

Kotlin 可以将一个对象解构为多个变量：

```kotlin
val (name, age) = person
```

这种语法被称为解构声明。解构声明一次创建多个变量。比如声明 name 和 age 两个新的变量，可以单独使用。

解构声明最终编译为下面的代码：

```kotlin
val name = person.component1()
val age = person.component2()
```

component1() 和 component2() 函数是 Kotlin 中广泛使用的惯例原则的例子（如+、* 操作符，for 循环等）。解构声明右侧能放任意对象，只要可以调用所需的组件函数，如组件 component3()，component4() 等等。

解构声明同样可以在 for 循环中使用：

```kotlin
for ((a, b) in collection) {
 //...
}
```

变量 a 和 b 为集合中元素 component1() 和 component2() 的值。

### 一个函数返回两个值

如果需要一个函数返回两个值，如：一个返回对象和一些排序状态，Kotlin 可以通过声明一个 data 类并返回它的实例方式。

```kotlin
data class Result(val result: Int , val state: Status)

fun function(): Result{
    // 计算
    return Result(result ,status)
}

// 现在可以使用这个函数了
val (result , status) = function()
```

### 解构声明和映射

下面示例可能是迭代 Map 的好的方式：

```kotlin
for((key , value ) in map){
    // ...
}
```

要让这正常工作，我们得

- 通过提供一个 iterator() 函数让映射表现得像一个队列值，
- 通过提供函数 component1() and component2() 让每组元素表现得像一对。

而且事实上，标准库提供了这些扩展：

```kotlin
operator fun <K, V> Map<K, V>.iterator(): Iterator<Map.Entry<K, V>> = entrySet().iterator()
operator fun <K, V> Map.Entry<K, V>.component1() = getKey()
operator fun <K, V> Map.Entry<K, V>.component2() = getValue()
```

所以可以在有映射 for 循环（与 data 类集合一样）中自由地使用解构声明。

## 作用域函数

Kotlin标准库包含几个函数，它们的唯一目的是在对象的上下文中执行代码块。当您对提供lambda表达式的对象调用这样一个函数时，它将形成一个临时作用域。在这个范围内，您可以访问没有名称的对象。这些函数称为作用域函数。

```kotlin
// 创建画笔，并设置一些基础属性
val paint = Paint()
paint.isAntiAlias = true
paint.color = Color.parseColor("#999999")
paint.textSize = sp(8f).toFloat()

// apply 作用域函数来优化一下
val mRangTextPaint = Paint().apply {
  isAntiAlias = true
  color = Color.parseColor("#999999")
  textSize = sp(8f).toFloat()
}
```

作用域函数猛地一看很相似，但是他们有以下两个主要区别：

| 作用域函数 | Object reference | Return value  |
| :--------: | :--------------: | :-----------: |
|    run     |       this       | Lambda result |
|    with    |       this       | Lambda result |
|   apply    |       this       |  上下文对象   |
|    let     |        it        | Lambda result |
|    also    |        it        |  上下文对象   |

- let

let 经常用于仅使用非空值执行代码块。如需对非空对象执行操作，可对其使用安全调用操作符 ?. 并调用 let 在 lambda 表达式中执行操作。

```kotlin
val str: String? = "Hello" 
//processNonNullString(str)       // 编译错误：str 可能为空
val length = str?.let { 
    println("let() called on $it")
    processNonNullString(it)      // 编译通过：'it' 在 '?.let { }' 中必不为空
    it.length
}
```

- with

在代码中，with 可以理解为对于这个对象，执行以下操作。

```kotlin
val numbers = mutableListOf("one", "two", "three")
with(numbers) {
    println("'with' is called with argument $this")
    println("It contains $size elements")
}
```

- run

run 和 with 做同样的事情，但是调用方式和 let 一样——作为上下文对象的扩展函数。

- apply

apply 的常见情况是对象配置。这样的调用可以理解为将以下赋值操作应用于对象。

```kotlin
val adam = Person("Adam").apply {
    age = 32
    city = "London"        
}
```

- also

also 对于执行一些将上下文对象作为参数的操作很有用。可以将其理解为并且执行以下操作。

```kotlin
val numbers = mutableListOf("one", "two", "three")
numbers
    .also { println("The list elements before adding new one: $it") }
    .add("four")
```

### 使用场景

很多人接触到作用域函数的时候，最头疼的问题不是不会用，而是不知道如何选择合适的作用域函数。之所以出现这种情况，是因为其实作用域函数在多数情况下是可以互换的，因此官方文档也给我们推荐了各个函数常见的使用场景。

![](https://user-gold-cdn.xitu.io/2019/9/6/16d049bbdb6f658d?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

## 协程

https://www.bennyhuo.com/2019/04/01/basic-coroutines/

https://kaixue.io/tag/kotlin-xie-cheng/

「协程 Coroutines」源自 Simula 和 Modula-2 语言，这个术语早在 1958 年就被 Melvin Edward Conway 发明并用于构建汇编程序，说明协程是一种编程思想，并不局限于特定的语言。

Go 语言也有协程，叫 Goroutines，从英文拼写就知道它和 Coroutines 还是有些差别的（设计思想上是有关系的），否则 Kotlin 的协程完全可以叫 Koroutines 了。

协程是一种非抢占式或者说协作式的计算机程序并发调度的实现，程序可以主动挂起或者恢复执行。

我们在 Java 虚拟机上所认识到的线程大多数的实现是映射到内核的线程的，也就是说线程当中的代码逻辑在线程抢到 CPU 的时间片的时候才可以执行，否则就得歇着，当然这对于我们开发者来说是透明的；而经常听到所谓的协程更轻量的意思是，协程并不会映射成内核线程或者其他这么重的资源，它的调度在用户态就可以搞定，任务之间的调度并非抢占式，而是协作式的。

```kotlin
public fun CoroutineScope.launch(
    // 上下文
    context: CoroutineContext = EmptyCoroutineContext,
    // 启动模式
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val newContext = newCoroutineContext(context)
    val coroutine = if (start.isLazy)
        LazyStandaloneCoroutine(newContext, block) else
        StandaloneCoroutine(newContext, active = true)
    coroutine.start(start, coroutine, block)
    return coroutine
}
```

### 基本使用

```kotlin
// 方法一，使用 runBlocking 顶层函数
runBlocking {
    getImage(imageId)
}

// 方法二，使用 GlobalScope 单例对象
// 可以直接调用 launch 开启协程
GlobalScope.launch {
    getImage(imageId)
}

// 方法三，自行通过 CoroutineContext 创建一个 CoroutineScope 对象
// 需要一个类型为 CoroutineContext 的参数
val coroutineScope = CoroutineScope(context)
coroutineScope.launch {
    getImage(imageId)
}
```

- 方法一通常适用于单元测试的场景，而业务开发中不会用到这种方法，因为它是线程阻塞的。
- 方法二和使用 runBlocking 的区别在于不会阻塞线程。但在 Android 开发中同样不推荐这种用法，因为它的生命周期会和 app 一致，且不能取消。
- 方法三是比较推荐的使用方法，我们可以通过 context 参数去管理和控制协程的生命周期（这里的 context 和 Android 里的不是一个东西，是一个更通用的概念，会有一个 Android 平台的封装来配合使用）。

协程最常用的功能是并发，而并发的典型场景就是多线程。可以使用 Dispatchers.IO 参数把任务切到 IO 线程执行：

```kotlin
coroutineScope.launch(Dispatchers.IO) {
   // ...
}
```

也可以使用 Dispatchers.Main 参数切换到主线程：

```kotlin
coroutineScope.launch(Dispatchers.Main) {
   // ...
}
```

### 启动模式

- DEFAULT：饿汉式
- LAZY：懒汉式
- ATOMIC
- UNDISPATCHED

### 上下文

### 拦截器

### 调度器

### 异常处理

## suspend

## async

## 通道

## 管道

## Mutex

## Actors




