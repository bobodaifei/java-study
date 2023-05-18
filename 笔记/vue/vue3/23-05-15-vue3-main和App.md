# 内容浅析

## main.js

```js
// 引入的不再是 Vue 构造函数了
// 引入的是名为 createApp 的工厂函数
import { createApp } from 'vue'
import App from './App.vue'

// 创建应用实例对象->app 类似Vue2中的 vm
// 但是 app 比 vm 更 “轻”
// app上挂载的方法 比 vm 少很多 
createApp(App).mount('#app')

// app上的方法
// mount 挂载 组件到页面中
// unmount 卸载 组件在页面中
```

Vue 2 对比

```js
import Vue from 'vue'
import App from './App.vue'
new Vue({
  render: h => h(App),
}).$mount('#app')
```

## App.vue

```html
<template>
<!-- Vue3 允许可以不包含根标签 -->
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Your Vue.js App"/>
</template>
```
