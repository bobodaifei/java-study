# toRef

* 作用：创建一个 ref 对象，其value值指向另一个对象中的某个属性。
* 语法：`const name = toRef(person,'name')`
* 应用: 要将响应式对象中的某个属性单独提供给外部使用时。
* 扩展：`toRefs`与 `toRef`功能一致，但可以批量创建多个 ref 对象，语法：`toRefs(person)`

```js
setup(){
    let person = reactive({
        a:"a",
        b:"b",
        c:"c",
        name:"王二",
    })
  
    return {
        // 改动后为引用数据类型
        a:toRef(person,'a'),
        b:toRef(person,'b'),
        // 同样为开辟新的 ref 原person未改变
        name:ref(name)
        // 此处为开拓新的内存赋值
        c:person.c,
  
  
        // toRefs 特别好用 简化语法
        // ES6 合并对象 toRefs返回值为对象 将每一个key都封装为 ref对象
        ...toRefs(person)
    }
}
```
