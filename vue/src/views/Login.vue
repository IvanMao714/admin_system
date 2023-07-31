

<template>
  <div class="container" :class="{ 'sign-up-mode': signUpMode }">
<!--    登陆表单-->
    <div class="forms-container">
      <div class="signin-signup">
        <form action="#" class="sign-in-form" :model="loginData" >
          <h2 class="title">Sign in</h2>
          <div class="input-field">
            <i class="fas fa-user"></i>
            <input type="text" v-model="loginData.username" placeholder="Username" />
          </div>
          <div class="input-field">
            <i class="fas fa-lock"></i>
            <input type="password" v-model="loginData.password" placeholder="Password" />
          </div>
          <input type="submit" @click="handleLogin" value="Login" class="btn solid" />
          <p class="social-text">Or Sign in with social platforms</p>
          <div class="social-media">
            <a href="#" class="social-icon">
              <i class="fas fa-envelope"></i>
            </a>
            <a href="#" class="social-icon">
              <i class="fab fa-facebook-f"></i>
            </a>
            <a href="#" class="social-icon">
              <i class="fab fa-twitter"></i>
            </a>
            <a href="#" class="social-icon">
              <i class="fab fa-google"></i>
            </a>
            <a href="#" class="social-icon">
              <i class="fab fa-linkedin-in"></i>
            </a>
          </div>
        </form>

<!--        注册表单-->
        <form action="#" class="sign-up-form">
          <h2 class="title">Sign up</h2>
          <div class="input-field">
            <i class="fas fa-user"></i>
            <input type="text" placeholder="Username" />
          </div>
          <div class="input-field">
            <i class="fas fa-envelope"></i>
            <input type="email" placeholder="Email" />
          </div>
          <div class="input-field">
            <i class="fas fa-lock"></i>
            <input type="password" placeholder="Password" />
          </div>
          <input type="submit" class="btn" value="Sign up" />
          <p class="social-text">Or Sign up with social platforms</p>
          <div class="social-media">
            <a href="#" class="social-icon">
              <i class="fas fa-envelope"></i>
            </a>
            <a href="#" class="social-icon">
              <i class="fab fa-facebook-f"></i>
            </a>
            <a href="#" class="social-icon">
              <i class="fab fa-twitter"></i>
            </a>
            <a href="#" class="social-icon">
              <i class="fab fa-google"></i>
            </a>
            <a href="#" class="social-icon">
              <i class="fab fa-linkedin-in"></i>
            </a>
          </div>
        </form>
      </div>
    </div>

    <div class="panels-container">
      <div class="panel left-panel">
        <div class="content">
          <h3>New here ?</h3>
          <p>Enter your details and start your journey with us</p>
          <button class="btn transparent" id="sign-up-btn" @click="signUp">
            Sign up
          </button>
        </div>
<!--        <img src="../img/log.svg" class="image" alt="" />-->
      </div>
      <div class="panel right-panel">
        <div class="content">
          <h3>One of us ?</h3>
          <p>To keep connected with us please login with your personal info</p>
          <button class="btn transparent" id="sign-in-btn" @click="signIn">
            Sign in
          </button>
        </div>
<!--        <img src="../img/log.svg" class="image" alt="" />-->
      </div>
    </div>
  </div>
</template>

<script>

import {reactive} from "vue";
import axios from 'axios'
import global from '../global'
import router from "../router/index.js";


export default {
  data() {
    return {
      signUpMode: false,
      loginData: {
        username : null,
        password : null
      },
    };
  },
  methods: {
    signUp() {
      this.signUpMode = true;
    },
    signIn() {
      this.signUpMode = false;
    },

    handleLogin() {
      let user = {
        username: this.loginData.username,
        password: this.loginData.password
      }
      // console.log(user)
      let url = global.HOST_URL+"/user/login";
      axios.post(url,user).then(
          res => {
            res = res.data;
            if(res.code === 200){
                console.log(res.data)
                this.$notify({
                  type: 'success',
                  message: '登录成功',
                  duration: 1000
                });
                this.$store.commit('login', res.data.token);
                setTimeout(()=>{router.push('/');},700);
            }else{
              this.$notify({
                type: 'warning',
                message: '用户名或密码错误',
                duration: 2000
              });
            }
          }
      );
    }
  },

  handleRegister(){
    let user = {
      username: this.formData.username,
      password: this.formData.password
    };
    let url = global.HOST_URL+"/user/register";
    axios.post(url,user).then(
        res => {
          res = res.data;
          if(res.code === 0){
            this.$notify({
              type: 'success',
              message: '注册成功'
            });
          }else{
            this.$notify({
              type: 'warning',
              message: res.msg,
              duration: 700
            });
          }
        }
    );

  }
};
</script>

<style scoped>
@import "../assets/css/login.css";
</style>
