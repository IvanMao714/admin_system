package com.gsiv.springboot.entity.note;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.entity.note
 * @className: UserNoteKey
 * @author: 65151
 * @description: TODO
 * @date: 2023-09-04 15:30
 * @version: 1.0
 */

@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class UserNoteKey {

    private String username;

    private String notebookName;

    private String noteTitle;

}
