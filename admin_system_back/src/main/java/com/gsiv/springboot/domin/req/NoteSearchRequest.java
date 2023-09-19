package com.gsiv.springboot.domin.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.domin.req
 * @className: NoteSearchRequest
 * @author: 65151
 * @description: TODO
 * @date: 2023-09-03 17:16
 * @version: 1.0
 */
@Data
public class NoteSearchRequest {

    String keyWord;
    List<String> searchNotebookList;
}
