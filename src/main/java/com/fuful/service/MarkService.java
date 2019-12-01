package com.fuful.service;

import com.fuful.dao.MarkDao;
import com.fuful.domain.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MarkService {

    @Autowired
    MarkDao markDao;
    public boolean saveMark(Mark mark){
        int res=markDao.addNewMark(mark);
        if(res>0)
            return true;
        else
            return false;
    }
}
