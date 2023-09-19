package com.gsiv.springboot.domin.req;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.domin.req
 * @className: NoteRequest
 * @author: 65151
 * @description: 新建/重命名笔记本 requestBody
 * @date: 2023-09-03 17:56
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
public class NoteRequest {

    private Integer delNoteId;

    private String noteTitle;

    private String notebookName;

    private String content;

    private String lastRef;

    private String versionRef;


    private String srcNotebook;

    private String srcTitle;

    private Boolean move;

    private boolean tmpSave = false;


}
