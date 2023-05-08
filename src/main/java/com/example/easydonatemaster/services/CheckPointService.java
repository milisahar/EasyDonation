package com.example.easydonatemaster.services;


import com.example.easydonatemaster.entites.CheckPoint;
import com.example.easydonatemaster.entites.Progress;
import com.example.easydonatemaster.entites.Task;
import com.example.easydonatemaster.repositories.CheckPointRepositoy;
import com.example.easydonatemaster.repositories.TaskRepositoy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class CheckPointService implements ICheckPointService{
    CheckPointRepositoy checkPointRepositoy;
    TaskRepositoy taskRepositoy;
    ITaskService taskService;

    @Override
    public CheckPoint addCheckPoint(CheckPoint checkPoint , int id) {

        Task task  = taskService.getTaskById(id);
        CheckPoint checkPointSaved = checkPoint;

        checkPointSaved.setTask(task);
        task.getCheckPoints().add(checkPointSaved);
        task.setProgress(Progress.INPROGRESS);
        taskService.updateTask(task);
        checkPointRepositoy.save(checkPointSaved);
        return checkPointSaved;


    }

    @Override
    public CheckPoint updateCheckPoint(CheckPoint checkPoint) {

        return checkPointRepositoy.save(checkPoint);
    }

    @Override
    public List<CheckPoint> getCheckPoints() {
        return checkPointRepositoy.findAll();
    }

    @Override
    public List<CheckPoint> getCheckpointsbyTaskId(int id) {
        return checkPointRepositoy.findByTaskId(id);
    }

    @Override
    public List<CheckPoint> getCheckpointsbyTaskProgress(Progress progress) {
        return checkPointRepositoy.findByTaskProgress(progress);
    }

    @Override
    public CheckPoint getChecPointCheckPoint(int id) {
        return checkPointRepositoy.findById(id).orElse(null);
    }

    @Override
    public void deleteCheckPoint(int id) {
        checkPointRepositoy.deleteById(id);
    }
   /* public Map<String,Integer> getit(){
        for (CheckPoint c: checkPointRepositoy.findAll()
             ) {
            if (Calendar.getInstance().setTime(c.getDateDeCreation()).get(Calendar.DAY_OF_WEEK))
        }
        return  null;
    }


    /*public List<Object[]> checkPointsByDatesStats() {
        return checkPointRepositoy.countTasksByDayOfWeek();
    }
    @Override
    public List<Map<String, Object>> countchByDayOfWeek() {
        List<Object[]> results = checkPointRepositoy.countTasksByDayOfWeek();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("dayOfWeek", result[0]);
            map.put("count", result[1]);
            response.add(map);
        }
        return response;
    }*/


}
