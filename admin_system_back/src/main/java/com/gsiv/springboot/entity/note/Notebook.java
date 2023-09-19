package com.gsiv.springboot.entity.note;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.entity.note
 * @className: Notebook
 * @author: 65151
 * @description: TODO
 * @date: 2023-09-01 11:56
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
public class Notebook {

    private String notebookName;

    private List<Note> noteList;

}
