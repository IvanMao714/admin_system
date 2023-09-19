package com.gsiv.springboot.service;

import com.gsiv.springboot.service.note.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.service
 * @className: NoteServiceImpl
 * @author: 65151
 * @description: TODO
 * @date: 2023-09-01 13:36
 * @version: 1.0
 */
@SpringBootTest
public class NoteServiceTest {
    @Autowired
    private NoteService noteService;
    @Test
    public void listNotebooksTest(){

        System.out.println(noteService.listNotebooks().toString());
    }


}
