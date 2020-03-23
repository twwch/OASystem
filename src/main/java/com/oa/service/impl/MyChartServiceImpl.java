package com.oa.service.impl;

import com.oa.bean.Attendance;
import com.oa.service.MyChartService;
import com.oa.utils.MyChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/23 14:37
 */
@Service
public class MyChartServiceImpl implements MyChartService {

    @Autowired
    private AttendanceServiceImpl attendanceService;

    @Override
    public List<MyChartData> data() {
        List<MyChartData> datas = new ArrayList<MyChartData>();
        //获取当前月打卡成功的人数
        List<Attendance> suDAKAMonth = attendanceService.getSuDAKAMonth();
        List<Attendance> suDAKAMonth2 = attendanceService.getCIDAODAKAMonth();
        List<Attendance> suDAKAMonth3 = attendanceService.getZAODAKAMonth();
        MyChartData myChartData1 = new MyChartData();
        MyChartData myChartData2 = new MyChartData();
        MyChartData myChartData3 = new MyChartData();
        myChartData1.setName("打卡成功");
        myChartData1.setValue(suDAKAMonth.size());
        myChartData2.setName("迟到");
        myChartData2.setValue(suDAKAMonth2.size());
        myChartData3.setName("早退");
        myChartData3.setValue(suDAKAMonth3.size());
        datas.add(myChartData1);
        datas.add(myChartData2);
        datas.add(myChartData3);
        return datas;
    }
}
