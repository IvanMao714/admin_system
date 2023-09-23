package com.gsiv.springboot.controller.note;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.domin.req.NoteRequest;
import com.gsiv.springboot.domin.req.NoteSearchRequest;
import com.gsiv.springboot.entity.note.Notebook;
import com.gsiv.springboot.service.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.controller.note
 * @className: NoteController
 * @author: 65151
 * @description: TODO
 * @date: 2023-09-01 11:52
 * @version: 1.0
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;
    /**
      *@Description: 列举所用的笔记列表
      *@Param:
      *@return: ResponseResult
      *@Author: Ivan Mao
      *@Date:  2023.09.03
     **/
    @GetMapping("")
    public ResponseResult getNotebooks(){
        List<String>  notebookNames = noteService.listNotebooks();
        List<Notebook> notebookList = new ArrayList<>(notebookNames.size());
        for (String notebookName : notebookNames){
            Notebook notebook = new Notebook().setNotebookName(notebookName).setNoteList(new ArrayList<>());
            notebook.setNoteList(noteService.listNotes(notebookName));
            notebookList.add(notebook);
        }
        return new ResponseResult(200,notebookList);
    }
    /**
      *@Description: 搜索笔记
      *@Param:
      *@return:
      *@Author: Ivan Mao
      *@Date:  2023.09.03
     **/
//    @PostMapping("/search")
//    public ResponseResult searchNote(@RequestBody NoteSearchRequest request){
//        if (StringUtils.isBlank(request.getKeyWord())){
//            throw new IllegalArgumentException();
//        }
//        return noteService.search(request.getKeyWord(), request.getSearchNotebookList());
//
//    }

    /**
      *@Description: 新建/重命名笔记本
      *@Param:
      *@return:
      *@Author: Ivan Mao
      *@Date:  2023.09.03
     **/
    @PutMapping("/{notebookName}")
    public ResponseResult createNotebook(@PathVariable String notebookName, @RequestBody NoteRequest request){
        if (Boolean.TRUE.equals(request.getMove())) {
            noteService.renameNotebook(request.getSrcNotebook(), notebookName);
            return new ResponseResult(200, noteService.listNotes(notebookName));
        }
        return new ResponseResult(noteService.createNotebook(notebookName), "创建成功");
    }
    /**
      *@Description: 获取特定笔记内容
      *@Param:
      *@return:
      *@Author: Ivan Mao
      *@Date:  2023.09.04
     **/
    @GetMapping("/{notebookName}/{noteTitle}")
    public ResponseResult getNote(@PathVariable String notebookName, @PathVariable String noteTitle){
        return new ResponseResult(200, noteService.getNote(notebookName, noteTitle));
    }
}