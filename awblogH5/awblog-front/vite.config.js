import { fileURLToPath, URL } from 'node:url'

import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'

/** css框架 **/
import WindiCSS from 'vite-plugin-windicss'

export default defineConfig({
  plugins: [
    vue(),
    WindiCSS(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('src', import.meta.url))
    }
  },
  server:{
    port:8080,
    hmr:true,
    proxy:{
      "/api":{
        target:"http:localhost:9090",
        changeOrigin:"true",
        pathRewrite:{
          "^api":"api"
        }
      }
    }
  },
  build:{


  },
  preview:{

  }
})
