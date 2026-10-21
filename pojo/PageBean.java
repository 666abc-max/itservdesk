package com.qingda.tliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
   //这两个数据和接口文档对应data里面的两个数据
   private long total;//总记录数
   private List rows;//当前数据列表

}
