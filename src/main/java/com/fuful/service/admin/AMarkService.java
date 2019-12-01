package com.fuful.service.admin;

import com.fuful.dao.MarkDao;
import com.fuful.dao.admin.AMarkDao;
import com.fuful.domain.Book;
import com.fuful.domain.Mark;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AMarkService {
    @Autowired
    AMarkDao aMarkDao;




    public PageInfo<Mark> getMarkList(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Mark> markList = aMarkDao.findAllMark();
        PageInfo<Mark> data = new PageInfo<>(markList);
        return data;
    }

    public boolean deletemark(String mid) {
        boolean res=aMarkDao.DeleteMark(mid);
        return res;
    }
}
