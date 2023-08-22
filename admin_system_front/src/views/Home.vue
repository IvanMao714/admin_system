<template >
  <div class="app-wrapper">
    <el-container class="home-layout">
      <el-aside width="200px" class="sidebar-container"><Menu/></el-aside>
      <el-container>
        <el-header><Head/></el-header>
        <el-main><router-view/></el-main>
        <el-footer><Footer/></el-footer>
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