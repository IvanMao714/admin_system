package com.gsiv.springboot.entity.note;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.entity.note
 * @className: Note
 * @author: 65151
 * @description: TODO
 * @date: 2023-09-01 11:57
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
public class Note {

    public static final int STATUS_PRIVATE = 0;

    public static final int STATUS_PUBLIC = 1;

    public static final int STATUS_TMP_SAVED = 2;

    private String title;

    private int status =  STATUS_PRIVATE;

    private String content;

    private String previewContent;

    private Date lastModifiedTime;

    private String notebookName;

    private int searchCount;

    private Integer articleId;

}
