### ECMAScript 介绍

在2015 年 6 月,发布 ECMAScript 6（简称 ES6），即 ECMAScript 2015,因此，在此之前发布的统称es5，而es6及之后发布的统称es6

### ES6 的变量声明

`var、let、const`都是js声明变量的方法，`var`是es5语法，存在变量提升，`let`和`const`是es6语法新提出的块级作用域概念，存在暂时性死区，没有变量提升机制。

#### 1、var：定义全局变量

```javascript
  console.log(a); //undefind
    var a = 1；
    console.log(a); //1
```

等价于

```javascript
  var a;
    console.log(a); //undefined
    a = 1; 
    console.log(a); //1
```

**总结：**

`var`在全局作用域声明的变量会挂载在`window`对象上，它会创建一个新的全局变量作为全局对象的属性，这种行为说不定会覆盖到`window`对象上的某个属性

### 2、let：定义局部变量

`let、const`声明和`var`声明用法一样，不同点在于`let、var`声明的是变量，`const`声明的是常量。`var`存在变量提升，`let、const`不存在变量提升。`var`在全局作用域声明的变量会挂载在`window`对象上，它会创建一个新的全局变量作为全局对象的属性，这种行为说不定会覆盖到`window`对象上的某个属性，而`let const`声明的变量则不会有这一行为

```js
  console.log(a);//暂时性死区，报错 Unexpected identifier 'Uncaught'
  let a = 1;
  console.log(a)；
```

```javascript
    {
        console.log(a); //暂时性死区，报错 Unexpected identifier 'Uncaught'
        let a = 1;
        console.log(a);
    }
```

```
    {
        let a = 1;
        console.log(a); //1;
    }
    console.log(a); 访问不了内部变量，报错 Unexpected identifier 'Uncaught'
```

```
    let a = 1;
    console.log(window.a); //undefined
```

上述代码说明：`let`声明的变量没有变量提升，存在暂时性死区。如果在同一个作用域中某个变量已经存在，再次使用let关键字声明的话会报错。`let`声明的变量不会挂载到`window`对象上

#### 3、const：定义常量

在程序开发中，有些变量是希望声明后，在业务层就不再发生变化，此时可以用 const 来定义**常量**。常量就是值（内存地址）不能变化的量。

举例：

```javascript
const name = 'smyhvae'; //定义常量
```

用 const 声明的常量，只在局部（块级作用域内）起作用；而且，用 const 声明常量时，必须赋值，否则报错。

### let 和 const 的特点【重要】

-   不存在变量提升

-   禁止重复声明

-   支持块级作用域

-   暂时性死区


相反， 用`var`声明的变量：存在变量提升、可以重复声明、**没有块级作用域**。


### var/let/const 的共同点

-   全局作用域中定义的变量，可以在函数中使用。

-   函数中声明的变量，只能在函数及其子函数中使用，外部无法使用。

#### 暂时性死区 DTC

ES6 规定：使用 let/const 声明的变量，会使区块形成封闭的作用域。若在声明之前使用变量，就会报错。

也就是说，在使用 let/const 声明变量时，**变量需要先声明，再使用**（声明语句必须放在使用之前）。这在语法上，称为 “暂时性死区”（ temporal dead zone，简称 TDZ）。

DTC 其实是一种保护机制，可以让我们养成良好的编程习惯。

### 箭头函数

#### 定义箭头函数的语法

ES6 允许使用“箭头”（`=>`）定义函数。

```
		function fun(a){ return a; }
//  去掉 function 在 ( ) 和 { } 之间添加 => 
		var fun = (a) => { return a; }  
//  如果只有一个形参可省略 ( ) 如果一个形参都没有，必须加( )         
		var fun = a => { return a; }  
//  如果函数体只有一句话，可省略{ }，如果仅有的这句话还是 return， 则必须省略 return         
		var fun = a => a         
```

解释：

-   如果有且仅有 1 个形参，则`()`可以省略
-   如果函数体内有且仅有 1 条语句，则`{}`可以省略，但前提是，这条语句必须是 return 语句。

注意：

1. 箭头函数会改变函数内 `this` 的指向与上级作用域中的this指向保持一致。
2. 不可用当做构造函数，也就是说不能用`new`调用。
3. 不能在里边使用 `arguments` 对象，该对象在箭头函数中不存在，但还是可用使用剩余参数代替。

#### 箭头函数的 this 的指向

ES6 之前的普通函数中：this 指向的是函数被调用的对象（也就是说，谁调用了函数，this 就指向谁）。

而 ES6 的箭头函数中：**箭头函数本身不绑定 this**，this 指向的是**箭头函数定义位置的 this**（也就是说，箭头函数在哪个位置定义的，this 就跟这个位置的 this 指向相同）。

代码举例：

```js
var student = {
    sname : "LiMing",
    sage : 18,
    friends:["Jenny","Danny"],
    speak:function(){
        console.log(`${ this.sname} is ${this.sage} years old !`);
        this.friends.forEach(function(elem){
                            console.log(`${this.sname} and ${elem} are good friends ！`)
                            })
    }
}
student.speak();  
// LiMing is 18 years old !
// undefined and Jenny are good friends ！
// undefined and Danny are good friends ！
```

代码解释：（一定要好好理解下面这句话）

可以看到 speak 方法中 this 的指向没有问题，而 forEach 函数的回调函数中的 this 指向却不是我们所希望的，其实这个 this 指向的是全局对象 window 。因为回调函数是主函数在自己需要的时候自己调用的，调用时前边没有加任何 对象.前缀 所以 this指向的是 window （js中this的指向总结）， window 中并没有 sname 属性所以是 undefined。

如果想要让回调函数中 `this` 指向正确，es6之前的传统方法是使用 `bind()` 解决（[js中call，apply和bind方法的区别和使用场景]

```
var student = {
    sname : "LiMing",
    sage : 18,
    friends:["Jenny","Danny"],
    speak:function(){
        console.log(`${ this.sname} is ${this.sage} years old !`);
        this.friends.forEach(function(elem){
                            console.log(`${this.sname} and ${elem} are good friends ！`)
                            }.bind(this))
    }
}
student.speak();  
// LiMing is 18 years old !
// LiMing and Jenny are good friends ！
// LiMing and Danny are good friends ！

```

es6中因为箭头函数内外 this 指向相同，所以用箭头函数解决。

```
var student = {
    sname : "LiMing",
    sage : 18,
    friends:["Jenny","Danny"],
    speak:function(){
        console.log(`${ this.sname} is ${this.sage} years old !`);
        this.friends.forEach((elem) => {
                            console.log(`${this.sname} and ${elem} are good friends ！`)
                            })
    }
}
student.speak();  
// LiMing is 18 years old !
// LiMing and Jenny are good friends ！
// LiMing and Danny are good friends ！
```

其实以上对象中的方法 `speak` 理论也可以用箭头函数简化，但是我们并不希望这里的 `this` 于外边的相同，这里如果进行了简化请看以下代码

```
var student = {
    sname : "LiMing",
    sage : 18,
    friends:["Jenny","Danny"],
    speak:()=>{
        console.log(`${ this.sname} is ${this.sage} years old !`);
    }
}
student.speak();  
// undefined is undefined years old !

```

简化后 `speak` 方法中的 `this` 会指向全局 `window` ，而我们希望的是它指向调用时 `.` 前的对象，所以这里不能简化。