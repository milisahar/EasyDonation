package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.CheckPoint;
import com.example.easydonatemaster.entites.Progress;

import java.util.List;

public interface ICheckPointService {
    CheckPoint addCheckPoint (CheckPoint checkPoint , int id);
    CheckPoint updateCheckPoint (CheckPoint checkPoint);
    List<CheckPoint> getCheckPoints ();
    List<CheckPoint> getCheckpointsbyTaskId (int id);
    List<CheckPoint> getCheckpointsbyTaskProgress(Progress progress);
    CheckPoint getChecPointCheckPoint (int id);
    void deleteCheckPoint(int id);
}

