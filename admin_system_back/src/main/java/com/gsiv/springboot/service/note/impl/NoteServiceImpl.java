package com.gsiv.springboot.service.note.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.domin.exception.PromptException;
import com.gsiv.springboot.entity.note.Note;
import com.gsiv.springboot.entity.note.Notebook;
import com.gsiv.springboot.entity.note.UserNoteKey;
import com.gsiv.springboot.service.note.FileService;
import com.gsiv.springboot.service.note.NoteService;
import com.gsiv.springboot.utils.FileUtil;
import com.gsiv.springboot.utils.GitUtil;
import com.gsiv.springboot.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.service.note.impl
 * @className: NoteServiceImpl
 * @author: 65151
 * @description: TODO
 * @date: 2023-09-01 12:07
 * @version: 1.0
 */
@Service("noteService")
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Value("${notesDir}")
    private String notesDir;

    @Autowired
    @Qualifier("userNoteCache")
    LoadingCache<UserNoteKey, String> userNoteCache;

    @Autowired
    private FileService fileService;

    private String getUserid(){
        return ThreadLocalUtil.getUserid();
    }
    private static final String NOTEBOOK_FLAG_FILE = ".notebook";



    @Override
    public List<Note> listNotes(String notebookName){
        return listNotesByNotebookName(notebookName);
    }

    @Override
    public List<String> listNotebooks(){

        File dir = getOrCreateUserNotebookDir();
        File[] childFiles = dir.listFiles();
        if (childFiles == null || childFiles.length == 0){
            return Collections.emptyList();
        }
        List<String> notebookNameList = new ArrayList<>();
        for (File file : childFiles){
            if (file.isDirectory()){
                String fileName = file.getName();
                if (fileName.startsWith(".")){
                    continue;
                }
                notebookNameList.add(file.getName());
            }
        }
        return notebookNameList;
    }

    @Override
    public int createNotebook(String notebookName){
        File notebookDir = new File(getOrCreateUserNotebookDir(), notebookName);
        if (notebookDir.exists()) {
            throw new PromptException("笔记本已存在");
        }
        if (!notebookDir.mkdir()) {
            throw new RuntimeException("Create notebook failed");
        }

        File notebookFlagFile = new File(notebookDir, NOTEBOOK_FLAG_FILE);
        try {
            if (!notebookFlagFile.createNewFile()) {
                throw new RuntimeException("Create notebook failed");
            }
        } catch (IOException e) {
            throw new RuntimeException("Create notebook failed");
        }
        String relativeName = notebookName + "/" + NOTEBOOK_FLAG_FILE;
        GitUtil.addAndCommit(getOrCreateUserGit(), relativeName);
        log.info("create notebook: {}", notebookName);
        return 200;
    }

    @Override
    public void renameNotebook(String srcNotebookName, String targetNotebookName) {
        File srcNotebookDir = getNotebookDir(srcNotebookName);
        if (!srcNotebookDir.exists() || srcNotebookDir.isFile()) {
            throw new PromptException("笔记本不存在");
        }

        File targetNotebookDir = getNotebookDir(targetNotebookName);
        if (targetNotebookDir.exists()) {
            throw new PromptException("目标笔记本已存在");
        }
//        if (!targetNotebookDir.mkdir()) {
//            throw new PromptException("目标笔记本无法创建");
//        }
        List<Note> noteVoList = listNotes(srcNotebookName);
        if (!FileUtil.renameFileOrDir(srcNotebookDir, targetNotebookDir)) {
            throw new PromptException("重命名笔记本失败");
        }
        GitUtil.rmAndCommit(getOrCreateUserGit(), srcNotebookName);
        GitUtil.addAndCommit(getOrCreateUserGit(), targetNotebookName);
//        noteVoList.forEach(noteVo -> invalidateCache(buildUserNoteKey(srcNotebookName, noteVo.getTitle())));
//        articleService.updateArticlesNotebookName(srcNotebookName, targetNotebookName);
    }

//    @Override
//    public int deleteNotebook(String notebookName){
//        File notebookDir = new File(getOrCreateUserNotebookDir(), notebookName);
//        listNotes(notebookName).forEach(noteVo -> deleteNote(notebookName, noteVo.getTitle()));
//        fileService.deleteFile(notebookDir);
//        GitUtil.rmAndCommit(getOrCreateUserGit(),notebookName + "/" + NOTEBOOK_FLAG_FILE);
//        return 200;
//    }

//    @Override
//    public List<Note> search(String keyWord, List<String> searchNotebooks) {
//        List<String> notebookNameList ;
//        if (!CollectionUtils.isEmpty(searchNotebooks)){
//            notebookNameList = searchNotebooks;
//        } else {
//            notebookNameList =  listNotebooks();
//        }
//        if (CollectionUtils.isEmpty(notebookNameList)){
//            return null;
//        }
//        List<Note> res = new ArrayList<>();
//        notebookNameList.forEach(notebookName ->
//                listNotesByNotebookName(notebookName).stream()
//                        .map(noteVo -> noteVo.setContent(userNoteCache.get(buildUserNoteKey(notebookName, noteVo.getTitle()))))
//                        .filter(noteVo -> StringUtils.isNotBlank(noteVo.getContent()) && (noteVo.getContent().contains(keyWord)
//                                || noteVo.getTitle().contains(keyWord)))
//                        .map(noteVo -> noteVo.setSearchCount(subStrCount(noteVo.getContent(), keyWord) + subStrCount(noteVo.getTitle(), keyWord)))
//                        .forEach(res::add));
//        res.sort((o1, o2) -> o2.getSearchCount() - o1.getSearchCount());
//        return res;
//
//    }
    @Override
    public String getNote(String notebookName, String noteTitle){
        return getNote(notebookName, noteTitle, getUserid());
//        return getNote(notebookName, noteTitle);
    }

    @Override
    public String getNote(String notebookName, String noteTitle, String userid) {
        String content = userNoteCache.get(buildUserNoteKey(notebookName, noteTitle, userid));
        if (content == null){
            return "读取笔记失败";
        }
        return content;
    }








    /**
      *@Description: Utils Functions
      *@Author: Ivan Mao
      *@Date:  2023.09.02
     **/

    private List<Note> listNotesByNotebookName(String notebookName) {
        File notebookDir = new File(getOrCreateUserNotebookDir(), notebookName);
        if (!notebookDir.exists() || notebookDir.isFile()){
            throw new RuntimeException("No such notebook");
        }
        File[] childFiles = notebookDir.listFiles();
        if (childFiles == null || childFiles.length == 0 ){
            return Collections.emptyList();
        }

        Set<String> modifiedSet = GitUtil.getModifiedButUnCommitted(getOrCreateUserGit(), notebookName);
        // sort by lastModifiedTime and convert
        Arrays.sort(childFiles, (f1, f2) -> (int) (
                f2.lastModified() - f1.lastModified()));
        return Arrays.stream(childFiles).
                filter(file -> !file.isDirectory())
//                .filter(file -> checkExtension(file.getName()))
                .map(file -> {
                    String title = file.getName().substring(0,file.getName().lastIndexOf("."));
                    Date lastModifiedDate = new Date(file.lastModified());
                    String previewContent = null;
                    Integer articleId = null;
//                    NotePreviewInfo previewInfo = userNotePreviewCache.get(buildUserNoteKey(notebookName, title));
//                    if (previewInfo != null) {
//                        previewContent = previewInfo.getPreviewContent();
//                        articleId = previewInfo.getArticleId();
//                    }
                    int noteStatus = modifiedSet.contains(getRelativeFileName(notebookName, title)) ? Note.STATUS_TMP_SAVED:Note.STATUS_PRIVATE;
                    return new Note().setNotebookName(notebookName)
                            .setTitle(title)
                            .setStatus(noteStatus)
                            .setArticleId(articleId)
                            .setLastModifiedTime(lastModifiedDate)
                            .setPreviewContent(previewContent);
                })
                .collect(Collectors.toList());
    }

    private File getOrCreateUserNotebookDir(){
//        String name = getUserid();
        File dir = new File(notesDir, getUserid());
//        File dir = new File(notesDir);
        if (dir.exists()){
            return dir;
        }
        dir.mkdir();
        return dir;
    }

    public Git getOrCreateUserGit(){
        return GitUtil.getOrInitGit(getOrCreateUserNotebookDir());
    }

    private String getRelativeFileName(String notebookName, String noteTitle) {
        return notebookName + "/" + noteTitle+".md";
    }

    File getNotebookDir(String notebookName) {
        return new File(getOrCreateUserNotebookDir(), notebookName);
    }

    private UserNoteKey buildUserNoteKey(String notebookName, String noteTitle, String username){
        return new UserNoteKey().setNotebookName(notebookName).setNoteTitle(noteTitle).setUsername(username);
    }

}
