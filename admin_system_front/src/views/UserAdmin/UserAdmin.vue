<template>
  <div class="app-container">
    <el-row :gutter="20" class="header">
      <el-col :span="7">
        <el-input placeholder="请输入用户名..." v-model="queryForm.query" clearable ></el-input>
      </el-col>
      <el-button type="primary" :icon="Search" @click="initUserList">搜索</el-button>
      <el-button type="success" :icon="DocumentAdd" @click="handleDialogValue()" >新增</el-button>
      <el-popconfirm title="您确定批量删除这些记录吗？" @confirm="handleDelete(null)">
        <template #reference>
          <el-button type="danger" :disabled="delBtnStatus" :icon="Delete" >批量删除</el-button>
        </template>
      </el-popconfirm>
    </el-row>

    <el-table :data="tableData" stripe style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="userName" label="用户名" width="180" />
      <el-table-column prop="email" label="Email" />
      <el-table-column prop="phonenumber" label="电话" />
<!--      <el-table-column prop="status" label="状态" width="200" align="center" >-->
<!--        <template v-slot="{row}" >-->
<!--          <el-switch  v-model="row.status" @change="statusChangeHandle(row)" active-text="正常"-->
<!--                      inactive-text="禁用" active-value="0" inactive-value="1"></el-switch>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="拥有角色" width="200" align="center">
        <template v-slot="scope">
          <!--          {{scope}}-->
          <el-tag size="small" type="warning"  v-for="item in scope.row.roleList">   {{item.name}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" />

    </el-table>
    <el-pagination
        v-model:currentPage="this.queryForm.pageNum"
        v-model:page-size="this.queryForm.pageSize"
        :page-sizes="[10, 20, 30, 40,50]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
    <Dialog v-model="dialogVisible" :user_id="user_id" :dialogVisible="dialogVisible" :dialogTitle="dialogTitle" @initUserList="initUserList"></Dialog>
  </div>

</template>

<script >

import axios from "axios";
import global from '../../global.js';
import Dialog from './components/dialog.vue';
import {Search, DocumentAdd, Delete} from '@element-plus/icons';
import {ElMessage} from "element-plus";
import {ref} from "vue";


export default {
  computed: {
    Delete() {
      return Delete
    },
    DocumentAdd() {
      return DocumentAdd
    },
    Search() {
      return Search
    }
  },
  components: {Dialog},
  data() {
    return {
      queryForm: {
                    query:'',
                    pageNum:1,
                    pageSize:10
                  },
      tableData: [],
      total:0,
      user_id:-1,
      dialogTitle:'',
      dialogVisible:false,
      delBtnStatus: true,
      multipleSelection:[],
    }
  },
  mounted() {
    this.initUserList()
    console.log(this.id)
  },
  methods:{
    async initUserList() {
      let url = global.HOST_URL + "/user/list";
      let token = sessionStorage.getItem('token');
      const res = await axios.post(url, this.queryForm,{headers: {'token':token }})
      this.tableData = res.data.data.userList;
      // console.log(this.tableData)
      this.total = res.data.data.total;
    },
    handleSizeChange(pageSize){
      this.queryForm.pageNum=1;
      this.queryForm.pageSize=pageSize;
      this.initUserList();
    },
    handleCurrentChange(pageNum){
      this.queryForm.pageNum=pageNum;
      this.initUserList();
    },
    handleDialogValue(){
      if(this.multipleSelection[0]){
        console.log(this.multipleSelection[0].id.toString())
        this.user_id=this.multipleSelection[0].id.toString();
        this.dialogTitle="用户修改"
      }else{
        this.user_id=-1;
        this.dialogTitle="用户添加"
      }
      this.dialogVisible=true
    },
    handleDelete(id){
      const ids = [];
      if(id){
            ids.push(id)
          }else{
            this.multipleSelection.forEach(row=>{
              ids.push(row.id)
            })
          }
          // const res=await requestUtil.post("sys/user/delete",ids)
          // if(res.data.code==200){
          //   ElMessage({
          //     type: 'success',
          //     message: '执行成功!'
          //   })
          //   initUserList();
          // }else{
          //   ElMessage({
          //     type: 'error',
          //     message: res.data.msg,
          //   })
          // }
    },
    handleSelectionChange(selection){
      this.multipleSelection=selection;
      console.log(this.multipleSelection)
      this.delBtnStatus=selection.length==0;
    }
  },

}


</script>

<style lang="scss" scoped>

.header{
  padding-bottom: 16px;
  box-sizing: border-box;
}

.el-pagination{
  float: right;
  padding: 20px;
  box-sizing: border-box;
}

::v-deep th.el-table__cell{
  word-break: break-word;
  background-color: #f8f8f9 !important;
  color: #515a6e;
  height: 40px;
  font-size: 13px;

}

.el-tag--small {
  margin-left: 5px;
}

</style>
