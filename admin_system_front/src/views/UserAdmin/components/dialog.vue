<template>
  <el-dialog
    model-value="dialogVisible"
    :title="dialogTitle"
    width="30%"
  @close="handleClose"
  >

    <el-form
        ref="this.formRef"
        :model="this.form"
        :rules="this.rules"
        label-width="100px"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="this.form.username" :disabled="this.form.id==-1?false:'disabled'"  />
        <el-alert
            v-if="this.form.id==-1"
            title="默认初始密码：123456"
            :closable="false"
            style="line-height: 10px;"
            type="success" >
        </el-alert>
      </el-form-item>

      <el-form-item label="手机号" prop="phonenumber">
        <el-input v-model="this.form.phonenumber" />
      </el-form-item>

      <el-form-item label="邮箱" prop="email">
        <el-input v-model="this.form.email" />
      </el-form-item>

      <el-form-item label="状态" prop="status">
        <el-radio-group v-model="this.form.status">
          <el-radio :label="'0'">正常</el-radio>
          <el-radio :label="'1'">禁用</el-radio>
        </el-radio-group>
      </el-form-item>


      <el-form-item label="备注" prop="remark">
        <el-input v-model="this.form.remark" type="textarea" :rows="4"/>
      </el-form-item>


    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="handleConfirm">确认</el-button>
        <el-button  @click="handleClose">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script >
import {defineEmits, defineProps, ref, watch} from "vue";
import { ElMessage } from 'element-plus'
import global from "../../../global.js";
import axios from "axios";

export default {
  data() {
    return {
      tableData:[],
      form:{
            id:-1,
            username:"",
            password:"123456",
            status:"0",
            phonenumber:"",
            email:"",
            remark:""
          },
      formRef:ref(null),
      rules:ref({
              username:[
                { required: true, message: '请输入用户名'},
                { required: true, validator: this.checkUsername, trigger: "blur" }
              ],
              email: [{ required: true, message: "邮箱地址不能为空", trigger: "blur" }, { type: "email", message: "请输入正确的邮箱地址", trigger: ["blur", "change"] }],
              phonenumber: [{ required: true, message: "手机号码不能为空", trigger: "blur" }, { pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur" }],
            })
    }
  },
  mounted() {
    console.log(this.from)
  },
  methods:{
    async checkUsername(rule, value, callback) {
      if(this.form.id==-1){
        let token = sessionStorage.getItem("token");
        const res = await axios.post(global.HOST_URL+"/user/checkUserName" ,{userName:this.form.username},{headers: {'token':token }})
        if (res.data.code==500) {
          callback(new Error("用户名已存在！"));
        }else{
           callback();
        }

      }else{
        callback();
      }
    },
    async initFormData() {
      console.log("id:"+this.user_id)
      if(this.user_id!=-1) {
        let token = sessionStorage.getItem("token");
        const res = await axios.get(global.HOST_URL+"/user/" + this.user_id,{headers: {'token':token }})
        const user = res.data.data.User
        this.form = {
          id:user.id,
          username:user.userName,
          password:user.password,
          status:user.status,
          phonenumber:user.phonenumber,
          email:user.email,
          remark:user.remark
        };
      }else{
        this.form ={
          id:-1,
          username:"",
          password:"123456",
          status:"0",
          phonenumber:"",
          email:"",
          remark:""
        }
      }
    },
    handleClose(){
      this.$emit('update:modelValue',false);
    },
    async handleConfirm() {
      let token = sessionStorage.getItem("token");
      let result = await axios.post(global.HOST_URL + "/user/save", this.form,{headers: {'token':token }});
      console.log(result)
      let data = result.data;
      if (data.code == 200) {
        ElMessage.success("执行成功！")
        this.$emit("initUserList")
        this.handleClose();
      } else {
        ElMessage.error(data.msg);
      }
    }
  },
  props:{
    dialogTitle:{
          type:String,
          default:'',
          required:true
        },
        dialogVisible:{
          type:Boolean,
          default:false,
          required:true
        },
        user_id:{
          type:Number,
          default:-1,
          required:true
        },
  },
  watch:{
    dialogVisible(){
      // console.log(newValue)
      //   let id=newValue;
          this.initFormData()
    },
    user_id(newValue){
      this.initFormData(newValue)
    }

  },
  emits: ['update:modelValue','initUserList']

}

</script>

<style scoped>

</style>
