<template>
  <el-container>
    <!-- 笔记本列表  -->
    <el-aside width="200px" class="notebooklist noselect" v-show="showAside ">
<!--      <div style="margin:5px">-->
<!--        <el-input clearable v-model="keyWord" @blur="keyWord = null" @keyup.enter.native="searchNotes" placeholder="搜索笔记"></el-input>-->
<!--      </div>-->
<!--      <div @contextmenu.prevent class="notebook" style="  padding-bottom: 10px;color:grey">-->
<!--        <span  style="font-size: 15px">-->
<!--          <strong>笔记本</strong>-->
<!--        </span>-->
<!--        <el-popover v-model="newNoteBookVisible" placement="bottom">-->
<!--          <div>-->
<!--            <el-input v-model="newNotebookName" @keyup.enter.native="handleCreateNotebook" placeholder="新笔记本名" />-->
<!--          </div>-->
<!--          <i title="新建笔记本" slot="reference" style="color: grey;  margin-top: 3px;font-size: 15px;" class="fa fa-plus-square-o pull-right "/>-->
<!--        </el-popover>-->
<!--      </div>-->
      <div v-for="item of notebookList"
           v-bind:class="{chosenNotebook:curNotebook.notebookName === item.notebookName}"
           v-contextmenu:notebookRightMenu
           :key="item.notebookName"
      >
<!--      <div v-for="item of notebookList"-->
<!--      >-->
        <div v-if="toRenameNotebookName && toRenameNotebookName.length > 0 && toRenameNotebookName === item.notebookName"  class="notebook"  >
          <el-input @blur="toRenameNotebookName = null"
                    v-model="destNotebookName" @keyup.enter.native="handleRenameNotebook" placeholder="新笔记本名" />
        </div>
        <div class="notebook" @click="selectNoteList(item.notebookName)" v-else>{{item.notebookName}}</div>
<!--        <div class="notebook" >{{item.notebookName}}</div>-->
      </div>
<!--      <el-collapse @contextmenu.prevent.native accordion style="background-color: rgb(248, 248, 248);">-->
<!--        <el-collapse-item style="background-color: rgb(248, 248, 248);">-->
<!--            <template  slot="title">-->
<!--              <div v-contextmenu:delNotesRightMenu  class="notebook" style=" font-size: 15px; padding-bottom: 10px;color:grey;border-bottom:0px; background-color: rgb(248, 248, 248);">垃圾桶</div>-->
<!--            </template>-->
<!--          <div v-contextmenu:delNoteRightMenu class="delnote" v-for="item of delNoteList" :key="item.id">-->
<!--            {{item.notebook}}/{{item.title}}-->
<!--          </div>-->
<!--        </el-collapse-item>-->
<!--      </el-collapse>-->
    </el-aside>
    <!-- 笔记列表  -->
    <el-aside @contextmenu.prevent.native class="noselect noteList" width="300px" v-show="showAside && showNotes">
      <div  class="notebookInfo">
        <div>
           <span class="noselect">📙 </span>{{curNotebook.notebookName}}
          <el-popover
              v-model="newNoteVisible"
              v-if="curNotebook.notebookName !== null"
              placement="bottom"
              trigger="click">
            <div>
              <el-input v-model="newNoteTitle" @keyup.enter.native="handleCreateNote" placeholder="新笔记名" />
            </div>
            <i title="新建笔记" slot="reference" style="color: grey;  margin-top: 8px;font-size: 15px;" class="fa fa-plus-square-o pull-right"></i>
          </el-popover>
        </div>
      </div>
      <div class="note"
           v-bind:class="{chosenNote:curNote.noteTitle === item.title}"
           v-contextmenu:noteRightMenu

           v-for="item of curNotebook.noteList"
           @click="selectNote(item.title, curNotebook.notebookName)"
           :key="item.title">
        <div class="notetitle">
          <span class="noselect">📝 </span>{{item.title}}
          <span class="pull-right">
            <span class="vditor-tooltipped vditor-tooltipped__nw " aria-label="修改尚未保存">
              <i v-if = "item.status === 2" aria-label="修改尚未保存" style = "color:lightgrey" class="fa fa-warning  "></i>
            </span>
            <span class="vditor-tooltipped vditor-tooltipped__nw" aria-label="公开笔记">
              <i v-if = "item.articleId" style = "color:lightgrey;padding-left:5px" class=" fa fa-eye "></i>
            </span>
          </span>
        </div>
        <div class="notePreview">{{item.previewContent}}</div>
      </div>
    </el-aside>
  </el-container>

</template>

<script>
import { ArrowDown } from '@element-plus/icons-vue'
import axios from "axios";
import global from '../../global.js';
import Editor from '../../components/note/Editor.vue'
export default {
  name: "NoteAdmin",
  data() {
    return {
      //request head数据
      config : {
        headers: {
          token : sessionStorage.getItem('token')
        }
      },
      // 显示侧栏
      showAside: true,
      // 搜索关键词
      keyWord:null,
      // 新建笔记本弹窗
      newNoteBookVisible:false,
      // 新建笔记按钮弹窗
      newNoteVisible:false,
      // 新笔记名
      newNotebookName: "",
      //当前的笔记
      curNotebook: {
        notebookName: null,
        noteList:[]
      },
      curNote: {
        notebookName:null,
        noteTitle: null,
        content: ""
      },
      // 是否展示笔记列表
      showNotes: true,
      // 用于存放右键菜单选中的笔记信息
      noteRightMenuValues:{},
      notebookList:null,
      noteList: [],
      // 待重命名笔记本
      toRenameNotebookName: null,
      // 重命名笔记本目标名
      destNotebookName: null,
      //是否展示历史预览
      showHistoryPreview: false,
    }
  } ,
  methods: {
    //加载后执行
    doInit(){
      this.refreshNotebookList();
      this.timerId = setInterval(() => {
        this.autoSaveNote();
      }, 10 * 1000);
      // this.setIsMobile();
      // window.onresize = this.setIsMobile;
    },

    //更新笔记列表
    refreshNotebookList(notebookName){
      // this.clearRenameInfo();
      axios.get(global.HOST_URL+"/note", this.config).then(res => {
        res = res.data;
        console.log(res)
        if(res.code === 200){
          this.notebookList = res.data;
          // if(notebookName){
          //   this.doSwitchNotebook(notebookName);
          //   return ;
          //
          // }
          // this.updateCurNotebookInfo()
        }
      })
      // axios.get(global.HOST_URL+"/delnote", this.config).then( res => {
      //   res = res.data;
      //   if(res.code === 0){
      //     this.delNoteList = res.data;
      //   }
      // } )
    },

    // // 切换笔记本
    // doSwitchNotebook(notebookName){
    //   for (const notebook of this.notebookList) {
    //     if(notebook.notebookName === notebookName){
    //       this.curNotebook = notebook;
    //       if(notebook.noteList && notebook.noteList.length > 0){
    //         this.doSwitchNote(notebook.noteList[0].title, notebookName, true);
    //       }else{
    //         this.clearCurNoteInfo();
    //       }
    //       return ;
    //     }
    //   }
    // },
    selectNoteList(notebookName){
      if(this.showSearch) {
        this.showSearch = false;
        this.showNotes = true;
      }
      // 判断是否是同一个笔记本
      if(this.curNotebook.notebookName === notebookName){
        return ;
      }
      // 判断是否有未保存的内容
      if(this.isModifUnsaved()){
        this.$confirm('修改尚未保存', 'Confirm', {
          distinguishCancelAndClose: true,
          confirmButtonText: '保存',
          cancelButtonText: '丢弃'
        }).then(() => {

          this.handleSaveContentAndSwitchNotebook(this.$refs.editor.getContent(), notebookName);
        }).catch(
            action => {
              this.$notify({
                type: action === 'cancel' ? 'warning' : 'info',
                message: action === 'cancel'
                    ? '丢弃修改'
                    : '停留在当前页',
                duration: 1500
              });
              if( action === 'cancel'){
                this.handleDelTmpNote();
                this.doSwitchNotebook(notebookName);
              }
            }
        )
      }else{
        this.doSwitchNotebook(notebookName);
      }
    },
    //搜索笔记
    searchNotes(){
      if(this.isModifUnsaved()) {
        this.$notify({
          type:"warning",
          message:"笔记尚未保存"
        })
        return ;
      }
    },
    handleSaveContentAndSwitchNotebook(content, notebookName){
      let request = {
        content: content
      }
      let url = global.HOST_URL + "/note/" + this.curNote.notebookName + "/"+ this.curNote.noteTitle;
      axios.post(url, request, this.config).then(res => {
        res = res.data;
        if(res.code === 0){
          this.curNote.content = content;
          this.refreshNotebookList(notebookName);
        }


      })
    },
    //重命名笔记
    handleRenameNotebook() {
      let url = global.HOST_URL+"/note/"+this.destNotebookName;
      let request = {
        move : true,
        srcNotebook : this.toRenameNotebookName
      }
      this.clearRenameInfo();
      this.showHistory = false;
      axios.put(url, request, this.config).then(res => {
        res = res.data;
        if(res.code === 0) {
          res = res.data;
          if(request.srcNotebook === this.curNote.notebookName) {
            this.$refs.editor.setNotebookName(this.destNotebookName);
          }
          this.notebookList.forEach(notebook => {

            if(notebook.notebookName === request.srcNotebook) {
              notebook.notebookName = this.destNotebookName;
              notebook.noteList = res;
            }
          });
        } else {
          this.$notify({
            type: 'error',
            message: res.msg,
            duration: 1000
          });
        }
      })

    },
    //清除需要重命名的笔记
    clearRenameInfo(){
      this.toRenameNotebookName = null;
    },

    //修改文件是否保存
    isModifUnsaved(raw){
      if(this.$refs.editor.getContent(raw).charCodeAt() === 10 && this.curNote.content === ""){
        return false;
      }
      return this.curNote.content !== this.$refs.editor.getContent(raw);
    },
  },

  mounted() {
    this.refreshNotebookList()
    console.log("list"+ this.notebookList)
  }

};
</script>

<style lang="scss" scoped>
.notebook{
  /* margin: 15px; */
  color: rgb(41, 38, 38);
  font-size: 18px;
  margin-top: 1px;
  margin-left: 10px;
  margin-right: 10px;
  padding: 10px;
  padding-top: 15px;
  padding-bottom: 15px;
  /* background-color: rgb(247, 244, 242); */

  /* border-bottom:1px solid rgb(240, 237, 237); */
}
.createNotebook{
  text-align: center;
  border:1px solid rgb(240, 237, 237);
  border-radius:7px;
  margin-top: 5px;
  padding-top: 10px;
  padding-bottom: 10px;
  background-color: rgb(247, 245, 240);


}
.createNote{
  text-align: left;
  padding: 5px;

  font-weight: lighter;
  font-size: 16px;
  background-color: rgb(247, 245, 240);
  border:1px solid rgb(240, 237, 237);


}

.notebookInfo{
  margin-top: 5px;
  /* background-color: rgb(255, 253, 246); */
  padding:10px;
  font-size: 26px;
  /* font-weight: bold; */
}

.notebooklist{
  /* height:200px; */
  background-color: rgb(248, 248, 248);

  /* border: 1px solid rgb(240, 237, 237); */

}
.chosenNotebook{
  background-color: rgb(252, 251, 251);
  border-left: 3px solid rgb(199, 199, 199);
}
.note{
  padding: 10px;

}

.notetitle{
  /* font-weight: bold; */
  margin-bottom: 5px;
}

.header{
  /* margin-top: 10px; */
  /* padding-top: 10px; */
  color: rgb(24, 21, 17);
  /* line-height: 52px; */
  font-size: 25px;
  display:table-cell;
  vertical-align:bottom;
  /* border-top-left-radius:5px;
    border-top-right-radius:5px; */
  /* border: 1px solid rgb(240, 237, 237); */


  /* background-color: rgb(252, 250, 250); */

}
.editor{
  /* border: 1px solid rgb(240, 237, 237); */

}
.noteList{
  /* border: 1px solid rgb(240, 237, 237); */
  background-color: rgb(251, 250, 250);

}
.rightMenu{
  min-width: 100px;
}
.delnote{
  /* margin: 5px 15px 5px 15px; */
  padding: 6px 16px 6px 20px;
  color: gray;
  font-size: 15px;
  background-color: rgb(251, 250, 250);

  /* border-bottom:1px solid rgb(240, 237, 237); */
  /* border-top:1px solid rgb(240, 237, 237); */

}
.lightGreyBackground{
  background-color: lightgray;
}
.markidea-dropdown-item{
  min-width: 45px;
  text-align: center;
}
.chosenNote{
  background-color: white;
  /* border-left: 3px solid rgb(199, 199, 199); */
}

.notePreview{
  font-size: 14px;
  color: rgb(87, 87, 87);
}
</style>
