package com.example.easydonatemaster.controller;


import com.example.easydonatemaster.entites.CheckPoint;
import com.example.easydonatemaster.entites.Progress;
import com.example.easydonatemaster.repositories.CheckPointRepositoy;
import com.example.easydonatemaster.services.ICheckPointService;
import com.example.easydonatemaster.services.ITaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "check_points")
@Slf4j
@CrossOrigin(origins = "*")
public class CheckPointController {

    ICheckPointService iCheckPointService;
    ITaskService taskService;
    CheckPointRepositoy checkPointRepositoy;

    @PostMapping("/add/{id}")
    CheckPoint addCheckPoint (@PathVariable("id") int id, @RequestBody CheckPoint checkPoint) {
        log.info("LEEEEEEEEEEEEEEEEEEEEEEEEENNNNNNNNNNNNNNNNNAAAAAAAAAAAAAAAAAAAAAA"+String.valueOf(id));
        return iCheckPointService.addCheckPoint(checkPoint, id);
    }

    @PutMapping("/update/{id}")
    CheckPoint updateCheckPoint (@PathVariable("id") int id ,@RequestBody CheckPoint checkPoint) {

        return iCheckPointService.updateCheckPoint(checkPoint);
    }

    @GetMapping("")
    List<CheckPoint> getCheckPoints(){
        return iCheckPointService.getCheckPoints();
    }
    @GetMapping("/task/{id}")
    List<CheckPoint> getCheckPointsByTaskId(@PathVariable int id){
        return iCheckPointService.getCheckpointsbyTaskId(id);
    }
    @GetMapping("/task/progress/{progress}")
    List<CheckPoint> getCheckPointsByTaskProgress(@PathVariable Progress progress){
        return iCheckPointService.getCheckpointsbyTaskProgress(progress);
    }
    @GetMapping("/{id}")
    CheckPoint getCheckPoint(@PathVariable int id){
        return iCheckPointService.getChecPointCheckPoint(id);
    }

    @GetMapping("/hedha")
    List<CheckPoint> hh(){
        return checkPointRepositoy.ff();
    }

    @DeleteMapping("/delete/{id}")
    void deleteCheckPoint(@PathVariable int id){
        iCheckPointService.deleteCheckPoint(id);
    }
}