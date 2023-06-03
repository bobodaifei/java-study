# 一、ES6是什么？

ECMAScript 6（简称ES6）是于2015年6月正式发布的JavaScript语言的标准，正式名为ECMAScript 2015（ES2015）。它的目标是使得 JavaScript语言可以用来编写复杂的大型应用程序，成为企业级开发语言。

# 二、语法规范

## 1.let关键字

特性：
let 关键字用来声明变量，使用 let 声明的变量有几个特点：

1. 不允许重复声明；
2. 块儿级作用域（局部变量）；
3. 不存在变量提升；
4. 不影响作用域链；

> (1)let创建变量代码示例：

```c
// （1）let关键字使用示例：
  let a; //单个声明 
  let b,c,d;//批量声明
  let e = 100;//单个声明并赋值 
  let f = 521, g = 'iloveyou', h = []; // 批量声明并赋值
```

(2).不允许重复声明：
代码实现：

```javascript
// （2）. 不允许重复声明；
 let dog = "狗"; 
 let dog = "狗";
 // 报错：Uncaught SyntaxError: Identifier 'dog' has already been declared
```

> (3).块儿级作用域（局部变量）：
> 代码实现：

```javascript
//(3).块儿级作用域（局部变量）：
 { 
     let cat = "猫";
     console.log(cat); 
  }
     console.log(cat); 
 // 报错：Uncaught ReferenceError: cat is not defined
```

> (4).不存在变量提升：
> 什么是变量提升：
> 就是在变量创建之前使用（比如输出：输出的是默认值），let不存在，var存在；
> 代码实现：

```javascript
// (4). 不存在变量提升； 
// 什么是变量提升：就是在变量创建之前使用（比如输出：输出的是默认值），let不存 在，var存在；
  console.log(people1); // 可输出默认值
  console.log(people2); // 报错：Uncaught ReferenceError: people2 is not defined
   var people1 = "大哥"; // 存在变量提升
   let people2 = "二哥"; // 不存在变量提升
```

> (5).不影响作用域链：

代码实现：

```javascript
// 5. 不影响作用域链； 
// 什么是作用域链：很简单，就是代码块内有代码块，跟常规编程语言一样，上级代码块中 的局部变量下级可用
{ 
   let p = "大哥"; 
   function fn(){
   console.log(p); // 这里是可以使用的 }
   fn(); 
 }
```

**应用场景：**
以后声明变量使用 let 就对了；

## 2.const 关键字

**特性：**
const 关键字用来声明常量，const 声明有以下特点：

1. 声明必须赋初始值；
2. 标识符一般为大写（习惯）；
3. 不允许重复声明；
4. 值不允许修改；
5. 块儿级作用域（局部变量）；

> const创建变量代码示例：

```javascript
<!DOCTYPE html> 
<html>
  <head>
  <meta charset="utf-8"> 
  <title>const</title> 
  </head> 
  <body>
     <script> 
     // const声明常量
      const DOG = "旺财"; 
      console.log(DOG); 
      </script> 
  </body> 
</html>
```

块儿级作用域（局部变量）：
代码实现：

```javascript
// 5. 块儿级作用域（局部变量）； 
{ 
   const CAT = "喵喵"; 
   console.log(CAT); 
}
  console.log(CAT);
```

## 3.解构赋值

什么是解构赋值：
ES6 允许按照一定模式，从数组和对象中提取值，对变量进行赋值，这被称为解构赋值；

代码演示及相关说明：

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <script>
        // ES6 允许按照一定模式，从数组和对象中提取值，对变量进行赋值，这被称为解构赋值；
        // 1、数组的解构赋值
        const F4=["大哥","二哥","三哥","四哥"];
        let [a,b,c,d]=F4;
        // 这就相当于我们声明4个变量a,b,c,d，其值分别对应"大哥","二哥","三哥","四哥"
        console.log(a + b + c + d); // 大哥二哥三哥四哥
        // 2、对象的解构赋值
        const F3 = {
             name : "大哥", 
             age : 22, sex : "男", 
             xiaopin : function(){
   
            console.log("我会演小品！"); 
        } 
    }
    let {name,age,sex,xiaopin} = F3; // 注意解构对象这里用的是{}
    console.log(name + age + sex + xiaopin); // 大哥22男
    xiaopin(); // 此方法可以正常调用
    </script>
  
</body>
</html>
```

**应用场景：**
频繁使用对象方法、数组元素，就可以使用解构赋值形式；

## 4.字符串

### 子串的识别

ES6 之前判断字符串是否包含子串，用 indexOf 方法，ES6 新增了子串的识别方法。

* **includes()** ：返回布尔值，判断是否找到参数字符串。
* **startsWith()** ：返回布尔值，判断参数字符串是否在原字符串的头部。
* **endsWith()** ：返回布尔值，判断参数字符串是否在原字符串的尾部。

```js
let string = "apple,banana,orange";
string.includes("banana");     // true
string.startsWith("apple");    // true
string.endsWith("apple");      // false
string.startsWith("banana",6)  // true
```

### 字符串重复

repeat()：返回新的字符串，表示将字符串重复指定次数返回。

```js
console.log("Hello,".repeat(2));// "Hello,Hello,"
```

如果参数是小数，向下取整

```js
console.log("Hello,".repeat(3.2));// "Hello,Hello,Hello,"
```

如果参数是 0 至 -1 之间的小数，会进行取整运算，0 至 -1 之间的小数取整得到 -0 ，等同于 repeat 零次

```js
console.log("Hello,".repeat(-0.5));// "" 
```

如果参数是 NaN，等同于 repeat 零次

```js
console.log("Hello,".repeat(NaN));// "" 
```

如果参数是负数或者 Infinity ，会报错:

```js
console.log("Hello,".repeat(-1));  
// RangeError: Invalid count value

console.log("Hello,".repeat(Infinity));  
// RangeError: Invalid count value
```

如果传入的参数是字符串，则会先将字符串转化为数字

```js
console.log("Hello,".repeat("hh"));// ""
console.log("Hello,".repeat("2"));// "Hello,Hello,"
```

### 字符串补全

* **padStart** ：返回新的字符串，表示用参数字符串从头部（左侧）补全原字符串。
* **padEnd** ：返回新的字符串，表示用参数字符串从尾部（右侧）补全原字符串。

以上两个方法接受两个参数，第一个参数是指定生成的字符串的最小长度，第二个参数是用来补全的字符串。如果没有指定第二个参数，默认用空格填充。

```js
console.log("h".padStart(5,"o"));// "ooooh"
console.log("h".padEnd(5,"o"));// "hoooo"
console.log("h".padStart(5));// "    h"
```

如果指定的长度小于或者等于原字符串的长度，则返回原字符串:

```js
console.log("hello".padStart(5,"A"));// "hello"
```

如果原字符串加上补全字符串长度大于指定长度，则截去超出位数的补全字符串:

```js
console.log("hello".padEnd(10,",world!"));// "hello,worl"
```

常用于补全位数：

```js
console.log("123".padStart(10,"0"));// "0000000123"
```

### 模板字符串

**概述：**

模板字符串（template string）是增强版的字符串，用反引号（`）标识，特点：

* 字符串中可以出现换行符；
* 可以使用 ${xxx} 形式引用变量；

> **代码演示及相关说明：**

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <script>
        // 声明字符串的方法：单引号（''）、双引号（""）、反引号（``） 
        // 声明
        let string = `我也是一个字符串奥！`;
        console.log(string);
        // 特性
        // 1、字符串中可以出现换行符
        let str =
            `<ul>
            <li>大哥</li> 
            <li>二哥</li> 
            <li>三哥</li> 
            <li>四哥</li>
         </ul>`;
        console.log(str);
        // 2、可以使用 ${xxx} 形式引用变量
        let s = "大哥";
        let out = `${s}是我最大的榜样！`;
        console.log(out);
    </script>
</body>

</html>
```

运行结果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/31324ad2a8224644ae7087f95565a6c4.png)
**应用场景：**
当遇到字符串与变量拼接的情况使用模板字符串；

## 5.简化对象和函数写法

**概述：**
ES6 允许在大括号里面，直接写入变量和函数，作为对象的属性和方法。这样的书写更加简洁

> 代码示例及相关说明：

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <script>
        // ES6允许在对象的大括号内直接写入变量和函数作为对象的属性和方法 
        // 变量和函数
        let name = "訾博";
        let change = function() {
            console.log("活着就是为了改变世界");
        }
        const school = {
            //完整写法
            // name:name,
            // change:change
            //简化写法
            name,
            change,
            //声明方法的简化
            say() {
                console.log("言行一致！");
            }
        }
        school.change();
        school.say();
    </script>
</body>

</html>
```

## 6.[箭头函数](https://so.csdn.net/so/search?q=%E7%AE%AD%E5%A4%B4%E5%87%BD%E6%95%B0&spm=1001.2101.3001.7020)

**概述：**
ES6允许使用箭头（=>）定义函数，箭头函数提供了一种更加简洁的函数书写方式，箭头函数多用于匿名函数的定义；
**箭头函数的注意点：**

* 如果形参只有一个，则小括号可以省略；
* 函数体如果只有一条语句，则花括号可以省略，函数的返回值为该条语 句的执行结果；
* 箭头函数this 指向声明时所在作用域下 this 的值；
* 箭头函数不能作为构造函数实例化；
* 不能使用 arguments；

**特性：**

- 箭头函数的this是静态的，始终指向函数声明时所在作用域下的this的值；
- 不能作为构造实例化对象；
- 不能使用 arguments 变量；

**代码演示及相关说明：**
注意：箭头函数不会更改 this 指向，用来指定回调函数会非常合适；

## 7.ES6中函数参数的默认值

**概述：**
ES6允许给函数的参数赋初始值；

**代码示例及相关说明：**

```javascript
    <script>
        //ES6允许给函数参数赋值初始值
        //1.形参构造值，具有默认值的参数，一般位置要靠后(潜规则)
        function add(a,b,c=10){
            return a+b+c;
        }
        let result=add(1,2);
        console.log(result);//13
        //2.与解构赋值结合
        //注意这里参数是一个对象
        function connect({
            host = "127.0.1",
            username,
            password,
            port
        }) {
            console.log(host)
            console.log(username)
            console.log(password);
            console.log(port)
        }
        connect({
            host: 'atguigu.com',
            username: 'root',
            password: 'root',
            port: 3306
        })
    </script>
```

## 8.rest参数

**概述：**

ES6 引入 rest 参数，用于获取函数的实参，用来代替 arguments；

```js
function data(){
         console.log(arguments); 
}
data("大哥","二哥","三哥","四哥");
// ES6的rest参数...args，rest参数必须放在最后面
function data(...args){
       console.log(args); // fliter some every map }
data("大哥","二哥","三哥","四哥")

```

**运行结果**

![在这里插入图片描述](https://img-blog.csdnimg.cn/85cb97006bf34f6089168a7e4fdbcc3b.png)

## 9.扩展运算符

**介绍：**
… 扩展运算符能将数组转换为逗号分隔的参数序列；
扩展运算符（spread）也是三个点（…）。它好比 rest 参数的逆运算，将一个数组转为用逗号分隔的参
数序列，对数组进行解包；
**基本使用**

```javascript
  // ... 扩展运算符能将数组转换为逗号分隔的参数序列  
  //声明一个数组 ... 
  const tfboys = ['易烊千玺', '王源', '王俊凯']; 
  // => '易烊千玺','王源','王俊凯' 
  // 声明一个函数
  function chunwan() {
  console.log(arguments);
  }
  chunwan(...tfboys); // chunwan('易烊千玺','王源','王俊凯')
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/f82d2c1575c64cc7bd24f837d841b0cb.png)
**应用**
1.数组的合并

```javascript
//1.数组的合并
const kuaizi=['王太利','肖扬'];
const fenghuang=['曾毅','玲花'];
// 传统的合并方式 
// const zuixuanxiaopingguo =kuaizi.concat(fenghuang); 
const zuixuanxiaopingguo = [...kuaizi, ...fenghuang]; 
console.log(zuixuanxiaopingguo);
```

2.数组的克隆

```javascript
//2. 数组的克隆 
const sanzhihua = ['E','G','M']; 
const sanyecao = [...sanzhihua];
// ['E','G','M'] 
console.log(sanyecao);
```

3.将伪数组转为真正的数组

```javascript
const divs = document.querySelectorAll('div'); const divArr = [...divs]; 
console.log(divArr); // arguments
```

## 10.Symbol

**Symbol 概述：**
ES6 引入了一种新的原始数据类型 Symbol，表示独一无二的值。它是JavaScript 语言的第七种数据类型，是一种类似于字符串的数据类型；
**Symbol 特点：**

1. Symbol 的值是唯一的，用来解决命名冲突的问题；
2. Symbol 值不能与其他数据进行运算；
3. Symbol 定义的对象属性不能使用for…in循环遍历 ，但是可以使用Reflect.ownKeys 来获取对象的
   所有键名；

**七种数据类型**
number,Boolean,String,null,undefined,Symbol,Object

由于每一个 Symbol 的值都是不相等的，所以 Symbol 作为对象的属性名，可以保证属性不重名。

```js
let sy = Symbol("key1");
 
// 写法1
let syObject = {};
syObject[sy] = "kk";
console.log(syObject);    // {Symbol(key1): "kk"}
 
// 写法2
let syObject = {
  [sy]: "kk"
};
console.log(syObject);    // {Symbol(key1): "kk"}
 
// 写法3
let syObject = {};
Object.defineProperty(syObject, sy, {value: "kk"});
console.log(syObject);   // {Symbol(key1): "kk"}
```

Symbol.for() 类似单例模式，首先会在全局搜索被登记的 Symbol 中是否有该字符串参数作为名称的 Symbol 值，如果有即返回该 Symbol 值，若没有则新建并返回一个以该字符串参数为名称的 Symbol 值，并登记在全局环境中供搜索。

```js
let yellow = Symbol("Yellow");
let yellow1 = Symbol.for("Yellow");
yellow === yellow1;      // false
 
let yellow2 = Symbol.for("Yellow");
yellow1 === yellow2;     // true
```

## 11.迭代器

**概述：**
遍历器（Iterator）就是一种机制。它是一种接口，为各种不同的数据结构提供统一的访问机制。任何数据结构只要部署 Iterator 接口，就可以完成遍历操作；
**特性：**
ES6 创造了一种新的遍历命令 for…of 循环，Iterator 接口主要供 for…of 消费；
原生具备 iterator 接口的数据(可用 for of 遍历)：
Array；
Arguments；
Set；
Map；
String；
TypedArray；
NodeList；
**工作原理：**

1. 创建一个指针对象，指向当前数据结构的起始位置；
2. 第一次调用对象的 next 方法，指针自动指向数据结构的第一个成员；
3. 接下来不断调用 next 方法，指针一直往后移动，直到指向最后一个成员；
4. 每调用 next 方法返回一个包含 value 和 done 属性的对象；

**注：需要自定义遍历数据的时候，要想到迭代器；**
演示代码：

```javascript

// 声明一个数组 
const xiyou = ['唐僧', '孙悟空', '猪八戒', '沙僧']; 

// 使用 for...of 遍历数组 
for(let v of xiyou){ 
        console.log(v);
 }
 let iterator = xiyou[Symbol.iterator](); 
 
 // 调用对象的next方法
  console.log(iterator.next()); 
  console.log(iterator.next()); 
  console.log(iterator.next()); 
  console.log(iterator.next()); 
  console.log(iterator.next());

 // 重新初始化对象，指针也会重新回到最前面 
 let iterator1 = xiyou[Symbol.iterator](); 
 console.log(iterator1.next());
```

**运行结果**
![在这里插入图片描述](https://img-blog.csdnimg.cn/7d07401325e940e2be0e0e377863b214.png)

## 12.Set集合

**概述：**
ES6 提供了新的数据结构 Set（集合）。 **它类似于数组，但成员的值都是唯一的** ，集合实现了iterator接口，所以可以使用『扩展运算符』和『for…of…』进行遍历，集合的属性和方法：

1. size 返回集合的元素个数；
2. add 增加一个新元素，返回当前集合；
3. delete 删除元素，返回 boolean 值；
4. has 检测集合中是否包含某个元素，返回 boolean 值；
5. clear 清空集合，返回 undefined；

**基本使用**

```javascript
let mySet = new Set();
 
mySet.add(1); // Set(1) {1}
mySet.add(5); // Set(2) {1, 5}
mySet.add(5); // Set(2) {1, 5} 这里体现了值的唯一性
mySet.add("some text"); 
// Set(3) {1, 5, "some text"} 这里体现了类型的多样性
var o = {a: 1, b: 2}; 
mySet.add(o);
mySet.add({a: 1, b: 2}); 
// Set(5) {1, 5, "some text", {…}, {…}} 
// 这里体现了对象之间引用不同不恒等，即使值相同，Set 也能存储

// Array 转 Set
var mySet = new Set(["value1", "value2", "value3"]);
// 用...操作符，将 Set 转 Array
var myArray = [...mySet];
String
// String 转 Set
var mySet = new Set('hello');  // Set(4) {"h", "e", "l", "o"}
// 注：Set 中 toString 方法是不能将 Set 转换成 String
```

**Set集合实践**

```javascript
       // Set集合实践 
       let arr = [1,2,3,4,5,4,3,2,1]; 
       // 数组去重 
       let s1 = new Set(arr); 
       console.log(s1); 
       // 交集 
       let arr2 = [3,4,5,6,5,4,3]; 
       let result = [...new Set(arr)].filter(item=>new Set(arr2).has(item)); 
       console.log(result); 
       // 并集 
       // ... 为扩展运算符，将数组转化为逗号分隔的序列 
       let union = [...new Set([...arr,...arr2])]; 
       console.log(union); 
       // 差集：比如集合1和集合2求差集，就是1里面有的，2里面没的 
       let result1 = [...new Set(arr)].filter(item=>!(new Set(arr2).has(item)));
        console.log(result1);
```

## 13.Map集合

**概述：**
ES6 提供了 Map 数据结构。它类似于对象，也是键值对的集合。但是“键”的范围不限于字符串，各种类
型的值（包括对象）都可以当作键。Map 也实现了iterator 接口，所以可以使用『扩展运算符』和
『for…of…』进行遍历；

 **Map 的属性和方法** ：

1. size 返回 Map 的元素个数；
2. set 增加一个新元素，返回当前 Map； 3. get 返回键名对象的键值；
3. has 检测 Map 中是否包含某个元素，返回 boolean 值；
4. clear 清空集合，返回 undefined；

### Map 中的 key

```js
//key 是字符串
var myMap = new Map();
var keyString = "a string"; 
myMap.set(keyString, "和键'a string'关联的值");
myMap.get(keyString);    // "和键'a string'关联的值"
myMap.get("a string");   // "和键'a string'关联的值"
                         // 因为 keyString === 'a string'

//key 是对象
var myMap = new Map();
var keyObj = {}, 
myMap.set(keyObj, "和键 keyObj 关联的值");
myMap.get(keyObj); // "和键 keyObj 关联的值"
myMap.get({}); // undefined, 因为 keyObj !== {}

//key 是函数
var myMap = new Map();
var keyFunc = function () {}, // 函数
myMap.set(keyFunc, "和键 keyFunc 关联的值");
myMap.get(keyFunc); // "和键 keyFunc 关联的值"
myMap.get(function() {}) // undefined, 因为 keyFunc !== function () {}

//key 是 NaN
var myMap = new Map();
myMap.set(NaN, "not a number");
myMap.get(NaN); // "not a number"
var otherNaN = Number("foo");
myMap.get(otherNaN); // "not a number"
//虽然 NaN 和任何值甚至和自己都不相等(NaN !== NaN 返回true)，NaN作为Map的键来说是没有区别的。
```

### Map 的迭代

对 Map 进行遍历，以下两个最高级。

```js
var myMap = new Map();
myMap.set(0, "zero");
myMap.set(1, "one");
 
// 将会显示两个 log。 一个是 "0 = zero" 另一个是 "1 = one"
for (var [key, value] of myMap) {
  console.log(key + " = " + value);
}
for (var [key, value] of myMap.entries()) {
  console.log(key + " = " + value);
}
/* 这个 entries 方法返回一个新的 Iterator 对象，它按插入顺序包含了 Map 对象中每个元素的 [key, value] 数组。 */
 
// 将会显示两个log。 一个是 "0" 另一个是 "1"
for (var key of myMap.keys()) {
  console.log(key);
}
/* 这个 keys 方法返回一个新的 Iterator 对象， 它按插入顺序包含了 Map 对象中每个元素的键。 */
 
// 将会显示两个log。 一个是 "zero" 另一个是 "one"
for (var value of myMap.values()) {
  console.log(value);
}
/* 这个 values 方法返回一个新的 Iterator 对象，它按插入顺序包含了 Map 对象中每个元素的值。 */


var myMap = new Map();
myMap.set(0, "zero");
myMap.set(1, "one");
 
// 将会显示两个 logs。 一个是 "0 = zero" 另一个是 "1 = one"
myMap.forEach(function(value, key) {
  console.log(key + " = " + value);
}, myMap)

```

### Map 对象的操作

```js
//Map 对象的操作
var kvArray = [["key1", "value1"], ["key2", "value2"]];
 
// Map 构造函数可以将一个 二维 键值对数组转换成一个 Map 对象
var myMap = new Map(kvArray);
 
// 使用 Array.from 函数可以将一个 Map 对象转换成一个二维键值对数组
var outArray = Array.from(myMap);

//Map 的克隆
var myMap1 = new Map([["key1", "value1"], ["key2", "value2"]]);
var myMap2 = new Map(myMap1);
 
console.log(original === clone); 
// 打印 false。 Map 对象构造函数生成实例，迭代出新的对象。

//Map 的合并
var first = new Map([[1, 'one'], [2, 'two'], [3, 'three'],]);
var second = new Map([[1, 'uno'], [2, 'dos']]);
 
// 合并两个 Map 对象时，如果有重复的键值，则后面的会覆盖前面的，对应值即 uno，dos， three
var merged = new Map([...first, ...second]);
```

## 14.Reflect 与 Proxy

见vue3的响应式

## 15.模块

### 基本用法

模块导入导出各种类型的变量，如字符串，数值，函数，类。

* 导出的函数声明与类声明必须要有名称（export default 命令另外考虑）。
* 不仅能导出声明还能导出引用（例如函数）。
* export 命令可以出现在模块的任何位置，但必需处于模块顶层。
* import 命令会提升到整个模块的头部，首先执行。

```js
/*-----export [test.js]-----*/
let myName = "Tom";
let myAge = 20;
let myfn = function(){
    return "My name is" + myName + "! I'm '" + myAge + "years old."
}
let myClass =  class myClass {
    static a = "yeah!";
}
export { myName, myAge, myfn, myClass }
 
/*-----import [xxx.js]-----*/
import { myName, myAge, myfn, myClass } from "./test.js";
console.log(myfn());// My name is Tom! I'm 20 years old.
console.log(myAge);// 20
console.log(myName);// Tom
console.log(myClass.a );// yeah!
```

建议使用大括号指定所要输出的一组变量写在文档尾部，明确导出的接口。

函数与类都需要有对应的名称，导出文档尾部也避免了无对应名称。

### as 的用法

export 命令导出的接口名称，须和模块内部的变量有一一对应关系。

导入的变量名，须和导出的接口名称相同，即顺序可以不一致。

```js
/*-----export [test.js]-----*/
let myName = "Tom";
export { myName as exportName }
 
/*-----import [xxx.js]-----*/
import { exportName } from "./test.js";
console.log(exportName);// Tom
使用 as 重新定义导出的接口名称，隐藏模块内部的变量
/*-----export [test1.js]-----*/
let myName = "Tom";
export { myName }
/*-----export [test2.js]-----*/
let myName = "Jerry";
export { myName }
/*-----import [xxx.js]-----*/
import { myName as name1 } from "./test1.js";
import { myName as name2 } from "./test2.js";
console.log(name1);// Tom
console.log(name2);// Jerry
```

### import 命令的特点

 **只读属性** ：不允许在加载模块的脚本里面，改写接口的引用指向，即可以改写 import 变量类型为对象的属性值，不能改写 import 变量类型为基本类型的值。

```js
import {a} from "./xxx.js"
a = {}; // error
 
import {a} from "./xxx.js"
a.foo = "hello"; // a = { foo : 'hello' }
```

**单例模式** ：多次重复执行同一句 import 语句，那么只会执行一次，而不会执行多次。import 同一模块，声明不同接口引用，会声明对应变量，但只执行一次 import 。

```js
import { a } "./xxx.js";
import { a } "./xxx.js";
// 相当于 import { a } "./xxx.js";
 
import { a } from "./xxx.js";
import { b } from "./xxx.js";
// 相当于 import { a, b } from "./xxx.js";
```

静态执行特性：import 是静态执行，所以不能使用表达式和变量。

```js
import { "f" + "oo" } from "methods";
// error
let module = "methods";
import { foo } from module;
// error
if (true) {
  import { foo } from "method1";
} else {
  import { foo } from "method2";
}
// error
```

### export default 命令

* 在一个文件或模块中，export、import 可以有多个，export default 仅有一个。
* export default 中的 default 是对应的导出接口变量。
* 通过 export 方式导出，在导入时要加{ }，export default 则不需要。
* export default 向外暴露的成员，可以使用任意变量来接收。

```js
var a = "My name is Tom!";
export default a; // 仅有一个
export default var c = "error"; 
// error，default 已经是对应的导出变量，不能跟着变量声明语句
 
import b from "./xxx.js"; // 不需要加{}， 使用任意变量接收
```

### 复合使用

> **注** ：import() 是提案，这边暂时不延伸讲解。

export 与 import 可以在同一模块使用，使用特点：

* 可以将导出接口改名，包括 default。
* 复合使用 export 与 import ，也可以导出全部，当前模块导出的接口会覆盖继承导出的。

**export**{**foo**, **bar**}**from**"**methods**"**;

```js
export { foo, bar } from "methods";
 
// 约等于下面两段语句，不过上面导入导出方式该模块没有导入 foo 与 bar
import { foo, bar } from "methods";
export { foo, bar };
 
/* ------- 特点 1 --------*/
// 普通改名
export { foo as bar } from "methods";
// 将 foo 转导成 default
export { foo as default } from "methods";
// 将 default 转导成 foo
export { default as foo } from "methods";
 
/* ------- 特点 2 --------*/
export * from "methods";
```

## 16.Promise 对象

### 概述

是异步编程的一种解决方案。

从语法上说，Promise 是一个对象，从它可以获取异步操作的消息。

### Promise 状态

##### 状态的特点

Promise 异步操作有三种状态：pending（进行中）、fulfilled（已成功）和 rejected（已失败）。除了异步操作的结果，任何其他操作都无法改变这个状态。

Promise 对象只有：从 pending 变为 fulfilled 和从 pending 变为 rejected 的状态改变。只要处于 fulfilled 和 rejected ，状态就不会再变了即 resolved（已定型）。

```js
const p1 = new Promise(function(resolve,reject){
    resolve('success1');
    resolve('success2');
}); 
const p2 = new Promise(function(resolve,reject){  
    resolve('success3'); 
    reject('reject');
});
p1.then(function(value){  
    console.log(value); // success1
});
p2.then(function(value){ 
    console.log(value); // success3
});
```

#### 状态的缺点

无法取消 Promise ，一旦新建它就会立即执行，无法中途取消。

如果不设置回调函数，Promise 内部抛出的错误，不会反应到外部。

当处于 pending 状态时，无法得知目前进展到哪一个阶段（刚刚开始还是即将完成）。

### then 方法

then 方法接收两个函数作为参数，第一个参数是 Promise 执行成功时的回调，第二个参数是 Promise 执行失败时的回调，两个函数只会有一个被调用。

#### then 方法的特点

在 JavaScript 事件队列的当前运行完成之前，回调函数永远不会被调用。

```js
const p = new Promise(function(resolve,reject){
  resolve('success');
});
 
p.then(function(value){
  console.log(value);
});
 
console.log('first');
// first
// success
```

通过 **.then** 形式添加的回调函数，不论什么时候，都会被调用。

通过多次调用.then，可以添加多个回调函数，它们会按照插入顺序并且独立运行。

```js
const p = new Promise(function(resolve,reject){
  resolve(1);
}).then(function(value){ // 第一个then // 1
  console.log(value);
  return value * 2;
}).then(function(value){ // 第二个then // 2
  console.log(value);
}).then(function(value){ // 第三个then // undefined
  console.log(value);
  return Promise.resolve('resolve'); 
}).then(function(value){ // 第四个then // resolve
  console.log(value);
  return Promise.reject('reject'); 
}).then(function(value){ // 第五个then //reject:reject
  console.log('resolve:' + value);
}, function(err) {
  console.log('reject:' + err);
});
```

then 方法将返回一个 resolved 或 rejected 状态的 Promise 对象用于链式调用，且 Promise 对象的值就是这个返回值。

#### then 方法注意点

简便的 Promise 链式编程最好保持扁平化，不要嵌套 Promise。

注意总是返回或终止 Promise 链。

```js
const p1 = new Promise(function(resolve,reject){
  resolve(1);
}).then(function(result) {
  p2(result).then(newResult => p3(newResult));
}).then(() => p4());
```

创建新 Promise 但忘记返回它时，对应链条被打破，导致 p4 会与 p2 和 p3 同时进行。

大多数浏览器中不能终止的 Promise 链里的 rejection，建议后面都跟上 **.catch(error => console.log(error));**

## 17.Generator

**概述：**
生成器函数是 ES6 提供的一种异步编程解决方案，语法行为与传统函数完全不同；
**基本使用：**

```javascript
function* gen() {
    console.log(111); 
    yield '一只没有耳朵'; 
    console.log(222); 
    yield '一只没有尾部'; 
    console.log(333); 
    yield '真奇怪';
    console.log(444); 
}
  let iterator = gen();
  console.log(iterator.next()); 
  console.log(iterator.next()); 
  console.log(iterator.next()); 
  console.log(iterator.next()); 
  console.log("遍历："); 
  //遍历 
  for(let v of gen()){ 
  console.log(v);
   }
```

**运行结果：**
![在这里插入图片描述](https://img-blog.csdnimg.cn/7e1a624938314c27ad74588ed5b070c2.png)

## 18.async 函数

### async

async 是 ES7 才有的与异步操作有关的关键字，和 Promise ， Generator 有很大关联的。

语法

```
async function name([param[, param[,... param]]]){ statements }
```

* name: 函数名称。
* param: 要传递给函数的参数的名称。
* statements: 函数体语句。

返回值

async 函数返回一个 Promise 对象，可以使用 then 方法添加回调函数。

```js
async function helloAsync(){
    return "helloAsync";
  }
  
console.log(helloAsync())  // Promise {<resolved>: "helloAsync"}
 
helloAsync().then(v=>{
   console.log(v);         // helloAsync
})
```

async 函数中可能会有 await 表达式，async 函数执行时，如果遇到 await 就会先暂停执行 ，等到触发的异步操作完成后，恢复 async 函数的执行并返回解析值。

await 关键字仅在 async function 中有效。如果在 async function 函数体外使用 await ，你只会得到一个语法错误。

```js
function testAwait(){
   return new Promise((resolve) => {
       setTimeout(function(){
          console.log("testAwait");
          resolve();
       }, 1000);
   });
}
 
async function helloAsync(){
   await testAwait();
   console.log("helloAsync");
 }
helloAsync();
// testAwait
// helloAsync
```

### await

await 操作符用于等待一个 Promise 对象, 它只能在异步函数 async function 内部使用。

语法 

```js
[return_value]= await expression;
```

* expression: 一个 Promise 对象或者任何要等待的值。

返回值

返回 Promise 对象的处理结果。如果等待的不是 Promise 对象，则返回该值本身。

如果一个 Promise 被传递给一个 await 操作符，await 将等待 Promise 正常处理完成并返回其处理结果。

```js
function testAwait (x) {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(x);
    }, 2000);
  });
}
 
async function helloAsync() {
  var x = await testAwait ("hello world");
  console.log(x); 
}
helloAsync ();
// hello world
```

正常情况下，await 命令后面是一个 Promise 对象，它也可以跟其他值，如字符串，布尔值，数值以及普通函数。

```js
function testAwait(){
   console.log("testAwait");
}
async function helloAsync(){
   await testAwait();
   console.log("helloAsync");
}
helloAsync();
// testAwait
// helloAsync
```

await针对所跟不同表达式的处理方式：

* Promise 对象：await 会暂停执行，等待 Promise 对象 resolve，然后恢复 async 函数的执行并返回解析值。
* 非 Promise 对象：直接返回对应的值。
