<template>
  <div >
    <el-container class="home-layout">
      <el-header class="header-container"><Head/></el-header>
      <el-container>
        <el-aside class="aside-container" width="200px"><Menu/></el-aside>
        <el-container>
          <el-main><Tabs/></el-main>
          <el-footer><Footer/></el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import Menu from "../components/home/menu.vue";
import Head from "../components/home/head.vue";
import Tabs from "../components/home/tabs.vue";
import Footer from "../components/home/footer.vue";
export default {
  name: "Home.vue",
  components: {Footer, Tabs, Head, Menu}
}
</script>

<style scoped>
@import "../assets/css/home.css";
</style>