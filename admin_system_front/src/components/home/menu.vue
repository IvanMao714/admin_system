<template >
  <el-menu
      active-text-color="#ffd04b"
      background-color="#2d3a4b"
      class="el-menu-vertical-demo"
      text-color="#fff"
      router
      :default-active="'/'"
  >
    <el-menu-item index="/">
      <el-icon><home-filled /></el-icon>
      <span>首页</span>
    </el-menu-item>

    <template v-for="menu in menuList">
      <el-menu-item :index="menu.routePath" v-if="menu.menuType==='C'">
        <el-icon><svg-icon :class="'iconfont '+menu.icon"/></el-icon>
        <span>{{menu.menuName}}</span>
      </el-menu-item>

      <el-sub-menu :index="menu.routePath" v-if="menu.menuType==='M'">
        <template #title >
          <el-icon><svg-icon :class="'iconfont '+menu.icon"/></el-icon>
          <span>{{menu.menuName}}</span>
        </template>

        <el-menu-item :index="item.routePath" v-for="item in menu.children">
          <el-icon><svg-icon :class="'iconfont '+item.icon"/></el-icon>
          <span>{{item.menuName}}</span>
        </el-menu-item>
      </el-sub-menu>

    </template>



  </el-menu>
</template>

<script>
  import {HomeFilled,Menu,User,Tickets,Goods,DocumentAdd,Management,Setting,Edit,SwitchButton,
    Promotion} from '@element-plus/icons'
  import {ref} from 'vue'
  import store from '../../store'
  import axios from "axios";
  import router from "../../router/index.js";
  import global from "../../global.js";
  import initMenu from "../../store/modules/menu";

export default {
  name: "menu",
  components: {HomeFilled,Menu},
  data() {
    return {
      menuList: [],
      user:null
    }
  },

  mounted() {
    this.init()
  },

  methods: {
    async init() { //获取菜单数据
      let token = sessionStorage.getItem('token');
      let menuList = null;
      await axios.get(global.HOST_URL + "/menu/list", {headers: {'token':token }}).then(
          res => {
            res = res.data;
            menuList = res.data;
          }
      );
      this.menuList = menuList;
    }

  }
}
</script>

<style lang="scss" scoped>
</style>

