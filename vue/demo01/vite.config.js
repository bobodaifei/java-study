import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    port: 9876,//启动端口
    open: true,
    proxy: {
      '/proxy': {   // 路径中有 /proxy 的请求都会走这个代理 
        target: 'http://localhost:8080',    // 目标代理接口地址,实际跨域要访问的接口，记得是填你后端springboot的端口，
        changeOrigin: true,  // 开启代理，在本地创建一个虚拟服务端
        rewrite: (path) => path.replace(/^\/proxy/, '')
        // pathRewrite: {   // 去掉 路径中的  /api  的这一截
        //   '/proxy': ''
        // }
      },
    },
    cors: true
  }
})
