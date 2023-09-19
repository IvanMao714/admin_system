package com.gsiv.springboot.entity.note;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.entity.note
 * @className: Article
 * @author: 65151
 * @description: TODO
 * @date: 2023-09-01 11:26
 * @version: 1.0
 */
@Data
//减少模板化代码
@AllArgsConstructor
@NoArgsConstructor
@TableName("T_ARTICLE")
public class Article {
    private static final long serialVersionUID = -40356785423868312L;
    @TableId
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    private String notebookName;


    private String articleTitle;


    private String noteTitle;


    private String username;


}
