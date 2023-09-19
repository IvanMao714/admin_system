package com.gsiv.springboot.service.note;

import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.entity.note.Note;

import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.service.note
 * @InterfaceName: NoteService
 * @author: Ivan Mao
 * @description: TODO
 * @date: 2023-09-01 12:05
 * @version: 1.0
 */
public interface NoteService {

    public List<String> listNotebooks();

    public List<Note> listNotes(String notebookName);

    public int createNotebook(String notebookName);

    public void renameNotebook(String srcNotebookName, String targetNotebookName);

//    public String getNote(String notebookName, String noteTitle);
//    public String getNote(String notebookName, String noteTitle, String username);
}
