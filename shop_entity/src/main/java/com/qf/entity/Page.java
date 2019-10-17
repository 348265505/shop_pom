package com.qf.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data

@NoArgsConstructor
@Accessors(chain = true)
public class Page implements Serializable {

    //需要的数据

    private int currentPage=1;//当前页
    private int pageSize=12;//每页显示的数量
    private int totalPage;//总页数
    private  int offset; //偏移量
    private int counts;//总条数
    private List<?> list;//不同实体类展示的数据存储在这个集合


    //指定参数构造

    /**
     *
     * @param currentPage 当前页
     * @param pageSize 每页显示数量
     * @param counts    总条数
     */
    public Page(int currentPage,int pageSize,int counts){
        if (pageSize>1){
            this.pageSize=pageSize;
        }else {
            this.pageSize=12;
        }

        if (currentPage>1){
            this.currentPage=currentPage;
        }else {
            this.currentPage=1;
        }

        //得到总页数
        this.counts=counts%pageSize==0?counts/pageSize:counts/pageSize+1;
        this.counts=counts;//总记录数量
        if (currentPage>totalPage && this.currentPage>0){
            this.currentPage=currentPage;
        }


        //偏移量
        this.offset=(this.currentPage-1)*this.currentPage;


    }




}
