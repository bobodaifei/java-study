以下是Vue 3中选项式和组合式的对比代码，包括了生命周期、父子组件传参和组件化示例：

选项式：

```
<template>
  <div>
    <h1>{{ title }}</h1>
    <ChildComponent :message="message" />
  </div>
</template>

<script>
export default {
  name: 'ParentComponent',
  data() {
    return {
      title: 'Parent Component',
      message: 'Hello from parent component',
    };
  },
  components: {
    ChildComponent
  },
  created() {
    console.log('Parent Component created');
  },
  mounted() {
    console.log('Parent Component mounted');
  },
  updated() {
    console.log('Parent Component updated');
  },
  destroyed() {
    console.log('Parent Component destroyed');
  },
}
</script>
```

组合式：

```
<template>
  <div>
    <h1>{{ title }}</h1>
    <ChildComponent :message="message" />
  </div>
</template>

<script>
  import { defineComponent, ref, onMounted, onUpdated, onBeforeUnmount } from 'vue';
  import ChildComponent from './ChildComponent.vue';

  export default defineComponent({
    name: 'ParentComponent',
    components: {
      ChildComponent
    },
    setup() {
      const title = ref('Parent Component');
      const message = ref('Hello from parent component');

      onMounted(() => {
        console.log('Parent Component mounted');
      });

      onUpdated(() => {
        console.log('Parent Component updated');
      });

      onBeforeUnmount(() => {
        console.log('Parent Component destroyed');
      });

      return {
        title,
        message
      };
    }
  });
</script>
```

在选项式代码中，通过定义了一个Vue组件，同时提供了`data`选项来定义状态，
使用`components`选项来定义子组件以便在该组件中使用。
同时使用了Vue的生命周期钩子函数来实现生命周期功能。

在组合式代码中，使用`defineComponent`函数来定义了一个Vue组件。
在`setup`函数中定义了`title`和`message`的状态，并通过Vue提供的`onMounted`、`onUpdated`和`onBeforeUnmount`
来实现了组件的生命周期钩子函数，同时`ChildComponent`使用了ES6模块来引入。

总结来说，组合式API在写法上更加的简洁，便于理解和维护，代码更加清晰。
相比选项式，组合式在性能和代码的维护上都得到了优化。此外，在大型应用中，组合式API提供了更好的复用性和可测试性，可以方便地提取逻辑以及注入全局属性。